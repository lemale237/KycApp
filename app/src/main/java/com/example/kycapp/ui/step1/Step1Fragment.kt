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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.example.kycapp.MainActivity
import com.example.kycapp.R

class Step1Fragment : Fragment() {
    private lateinit var recto: ImageView
    private lateinit var verso: ImageView
    val REQUEST_IMAGE_CAPTURE = 1

    private var isRecto = true

    companion object {
        fun newInstance() = Step1Fragment()
    }

    private lateinit var viewModel: Step1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.step1_fragment, container, false)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Step1ViewModel::class.java)

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
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            if (isRecto){
                recto.setImageBitmap(imageBitmap)
            }else{
                verso.setImageBitmap(imageBitmap)
            }
        }
    }


}