package com.example.ecommerceappchallenge.view.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceappchallenge.databinding.FragmentProfileBinding
import com.example.ecommerceappchallenge.viewmodel.profile.ProfileViewModel
import com.example.ecommerceappchallenge.viewmodel.profile.ProfileViewModelFactory
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    @Inject lateinit var profileFactory: ProfileViewModelFactory

    private val galleryPhotoGetter = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
        uri?.let { it->
            Picasso.get().load(it).into(binding.circleImageViewProfilePicture)
            profileViewModel.updatePhoto(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel = ViewModelProvider(this, profileFactory).get(ProfileViewModel::class.java)
        binding.profileViewModel = profileViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setUpEditProfileObserver()
        setUpUpdateProfileObserver()
        setUpChooseProfilePhotoObserver()
    }

    private fun setUpEditProfileObserver() {
        profileViewModel.navigateToEditProfile.observe(viewLifecycleOwner, Observer {
            when (it) {
                1 -> startActivity(Intent(context, EditProfileElementActivity::class.java)
                    .putExtra("Element Name","Edit email"))
                2 -> startActivity(Intent(context, EditProfileElementActivity::class.java)
                    .putExtra("Element Name","Edit phone number"))
                3 -> startActivity(Intent(context, EditProfileElementActivity::class.java)
                    .putExtra("Element Name","Edit address"))
            }
        })
    }

    private fun setUpUpdateProfileObserver() {
        profileViewModel.navigateToUpdateProfile.observe(viewLifecycleOwner, Observer {
            val intent = Intent(context, ProfileUpdateActivity::class.java)
            startActivity(intent)
        })
    }

    private fun setUpChooseProfilePhotoObserver() {
        profileViewModel.chooseProfilePhoto.observe(viewLifecycleOwner, Observer {
            galleryPhotoGetter.launch(arrayOf("image/*"))
        })
    }
}