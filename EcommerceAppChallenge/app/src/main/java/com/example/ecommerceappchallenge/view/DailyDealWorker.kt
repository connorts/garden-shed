package com.example.ecommerceappchallenge.view

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.ecommerceappchallenge.domain.usecase.offers.DeleteOffersUseCase
import com.example.ecommerceappchallenge.domain.usecase.offers.GetDailyDealUseCase
import com.example.ecommerceappchallenge.domain.usecase.offers.SaveDailyDealUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

@HiltWorker
class DailyDealWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val getDailyDealUseCase: GetDailyDealUseCase,
    private val saveDailyDealUseCase: SaveDailyDealUseCase,
    private val deleteOffersUseCase: DeleteOffersUseCase
): CoroutineWorker(context,params){

    override suspend fun doWork(): Result {
        return try { withContext(Dispatchers.IO) {
            val deal = getDailyDealUseCase.execute().data
            if (deal != null) {
                deleteOffersUseCase.execute()
                saveDailyDealUseCase.execute(deal)
            }
        }
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}