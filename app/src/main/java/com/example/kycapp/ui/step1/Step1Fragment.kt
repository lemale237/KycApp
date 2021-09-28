package com.example.kycapp.ui.step1

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kycapp.MainActivity
import com.example.kycapp.R
import com.example.kycapp.databinding.FragmentHomeBinding
import com.example.kycapp.ui.HomeFragmentViewModel
import com.example.kycapp.ui.adapter.ViewPagerAdapter
import com.example.kycapp.ui.home.HomeFragment
import com.example.kycapp.ui.home.HomeFragment.Companion.generalPosition
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.step1_fragment.*
import kotlin.math.log

class Step1Fragment : Fragment() {


    private lateinit var recto: ImageView
    private lateinit var verso: ImageView

    var imageFront=""
    var imageBack=""
    val REQUEST_IMAGE_CAPTURE = 1

    private var isRecto = true

    companion object {
        fun newInstance() = Step1Fragment()
    }

    val model: HomeFragmentViewModel by activityViewModels<HomeFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.step1_fragment, container, false)


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onClickButton()
        recto = requireView().findViewById(R.id.PhotoRecto)
        verso = requireView().findViewById(R.id.PhotoVerso)

        recto.setOnClickListener {
            isRecto =true
            callCamera()
        }
        verso.setOnClickListener {
            isRecto =false
            callCamera()
        }

    }

    private fun callCamera() {

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            val uri = Uri.parse("file://somewhere_that_you_choose")
            val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            var uri= data?.extras?.get("uri")
            val imageBitmap = data?.extras?.get("data") as Bitmap

            if (isRecto){
                recto.setImageBitmap(imageBitmap)
                imageFront=uri.toString()
                Log.e("imqge front",data.data.toString())
            }else{
                verso.setImageBitmap(imageBitmap)
                imageBack=uri.toString()
                Log.e("imqge front",data.data.toString())
            }
        }
    }





    // code clique du bouton terminé à succregistration
    private fun onClickButton() {
        next.setOnClickListener {
            if(imageBack.isNotEmpty() and imageFront.isNotEmpty()){
                model.userToCreate.value!!.imageRecto=imageFront
                model.userToCreate.value!!.imageVerso=imageBack
                Log.e("images front",imageFront)
                Log.e("images front",imageBack)
                generalPosition.value?.let {
                    generalPosition.value = 1
                }
            }else{
                Toast.makeText(requireContext(),"you should provide both images",Toast.LENGTH_LONG).show()
            }


        }

    }




}