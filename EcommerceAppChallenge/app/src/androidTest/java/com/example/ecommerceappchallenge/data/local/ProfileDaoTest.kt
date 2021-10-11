package com.example.ecommerceappchallenge.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ecommerceappchallenge.data.model.Profile
import com.example.ecommerceappchallenge.getOrAwaitValue
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: ProfileDAO
    private lateinit var database: GardenShedDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GardenShedDatabase::class.java
        ).build()
        dao = database.profileDAO
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertProfileTest() {
        runBlocking {
            val profile = Profile(1,"fname1","lname1","email1","phone1","address1","pw1")
            dao.insertProfile(profile)

            val allProfiles = dao.getProfile().getOrAwaitValue()
            Truth.assertThat(allProfiles).isEqualTo(profile)
        }
    }

    @Test
    fun deleteProfileTest() {
        runBlocking {
            val profile = Profile(1,"fname1","lname1","email1","phone1","address1","pw1")
            dao.insertProfile(profile)
            dao.deleteProfile(profile)

            val allProfiles = dao.getProfile().getOrAwaitValue()
            Truth.assertThat(allProfiles).isNull()
        }
    }

    @Test
    fun deleteAllTest() {
        runBlocking {
            val profile1 = Profile(1,"fname1","lname1","email1","phone1","address1","pw1")
            val profile2 = Profile(2,"fname2","lname2","email2","phone2","address2","pw2")
            dao.insertProfile(profile1)
            dao.insertProfile(profile2)
            dao.deleteAll()

            val allProfiles = dao.getProfile().getOrAwaitValue()
            Truth.assertThat(allProfiles).isNull()
        }
    }

    @Test
    fun updateEmailTest() {
        runBlocking {
            val profile = Profile(1,"fname1","lname1","email1","phone1","address1","pw1")
            dao.insertProfile(profile)
            val newEmail = "new email"
            dao.updateEmail(newEmail)

            val newProfile = dao.getProfile().getOrAwaitValue()
            Truth.assertThat(newProfile.email).isEqualTo(newEmail)
        }
    }

    @Test
    fun updatePhoneTest() {
        runBlocking {
            val profile = Profile(1,"fname1","lname1","email1","phone1","address1","pw1")
            dao.insertProfile(profile)
            val newPhone = "new phone"
            dao.updatePhone(newPhone)

            val newProfile = dao.getProfile().getOrAwaitValue()
            Truth.assertThat(newProfile.phone).isEqualTo(newPhone)
        }
    }

    @Test
    fun updateAddressTest() {
        runBlocking {
            val profile = Profile(1,"fname1","lname1","email1","phone1","address1","pw1")
            dao.insertProfile(profile)
            val newAddress = "new address"
            dao.updateAddress(newAddress)

            val newProfile = dao.getProfile().getOrAwaitValue()
            Truth.assertThat(newProfile.address).isEqualTo(newAddress)
        }
    }
}