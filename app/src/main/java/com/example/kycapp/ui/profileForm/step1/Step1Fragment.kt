package com.example.kycapp.ui.profileForm.step1

import android.app.Activity
import android.content.Intent
import android.net.Uri

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView

import android.widget.Toast
import androidx.core.view.isVisible

import androidx.fragment.app.activityViewModels

import com.example.kycapp.R
import com.example.kycapp.api.AgentApi

import com.example.kycapp.ui.profileForm.HomeFragmentViewModel
import com.example.kycapp.ui.profileForm.HomeFragment.Companion.generalPosition
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.step1_fragment.next
import kotlinx.android.synthetic.main.step1_fragment.progress_Bar_upload


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
        ImagePicker.with(this)
            .crop()	    			//Crop image(Optional), Check Customization for more option
            .compress(1024)			//Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            val api = AgentApi()
            if (isRecto){
                recto.setImageURI(uri)
              progress_Bar_upload.isVisible=true
                api.uploadFile(uri,{
                    imageFront=it
                    progress_Bar_upload.isVisible=false
                    model.userToCreate.value!!.imageRecto=it
                    Toast.makeText(requireContext(),"upload succeeded",Toast.LENGTH_LONG).show()
                    Log.e("imqge recto",it)
                },{
                    progress_Bar_upload.isVisible=false
                    Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
                })

            }else{
                verso.setImageURI(uri)
                progress_Bar_upload.isVisible=true
                api.uploadFile(uri,{
                    imageBack=uri.toString()
                    progress_Bar_upload.isVisible=false
                    model.userToCreate.value!!.imageVerso=it
                    Toast.makeText(requireContext(),"upload succeeded",Toast.LENGTH_LONG).show()
                    Log.e("imqge verso",it)
                },{
                    progress_Bar_upload.isVisible=false
                    Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
                })
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
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