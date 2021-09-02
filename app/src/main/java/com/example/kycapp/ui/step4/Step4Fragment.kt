package com.example.kycapp.ui.step4

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.kycapp.R

class Step4Fragment : Fragment() {

    private lateinit var photoAgent: ImageView
    private lateinit var photoVente: ImageView
    val REQUEST_IMAGE_CAPTURE = 1

    private var isRecto = true

    companion object {
        fun newInstance() = Step4Fragment()
    }

    private lateinit var viewModel: Step4ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.step4_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Step4ViewModel::class.java)
        // TODO: Use the ViewModel

        photoAgent = requireView().findViewById(R.id.photoAgent)
        photoVente = requireView().findViewById(R.id.photoVente)
        photoAgent.setOnClickListener {
            isRecto =true
            callCamera()
        }
        photoVente.setOnClickListener {
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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            if (isRecto){
                photoAgent.setImageBitmap(imageBitmap)
            }else{
                photoVente.setImageBitmap(imageBitmap)
            }
        }
    }


    }

