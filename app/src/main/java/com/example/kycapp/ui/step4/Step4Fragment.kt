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
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kycapp.R
import com.example.kycapp.databinding.FragmentHomeBinding
import com.example.kycapp.ui.HomeFragmentViewModel
import com.example.kycapp.ui.adapter.ViewPagerAdapter
import com.example.kycapp.ui.home.HomeFragment
import com.example.kycapp.ui.home.HomeFragment.Companion.generalPosition
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.step4_fragment.*

class Step4Fragment : Fragment() {

    private lateinit var photoAgent: ImageView
    private lateinit var photoVente: ImageView
    var imageFront=""
    var imageBack=""
    val REQUEST_IMAGE_CAPTURE = 1

    private var isRecto = true

    companion object {
        fun newInstance() = Step4Fragment()
    }

    val model: HomeFragmentViewModel by activityViewModels<HomeFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.step4_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
                imageFront=data.data.toString()
                model.userToCreate.value!!.photoAgent=data.data.toString()
            }else{
                photoVente.setImageBitmap(imageBitmap)
                imageBack=data.data.toString()
                model.userToCreate.value!!.photoPointVente=data.data.toString()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickButton()

    }


    // code cique du bouton terminé à succregistration
    private fun onClickButton() {
        next.setOnClickListener {
            if(validateFields()){
                model.createAgent({
                    generalPosition.value?.let {
                        findNavController().navigate(R.id.action_navigation_home_to_sucessRegistrationFragment)
                    }
                },{
                    Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
                })
            }


        }
        previews.setOnClickListener {
            generalPosition.value?.let {
                    generalPosition.value = 2
            }

        }
    }
     fun validateFields():Boolean{
         var result= true
          if (imageFront.isEmpty() or imageBack.isEmpty()){
              return false
              Toast.makeText(requireContext(),"my guy both images",Toast.LENGTH_LONG).show()
          }
         if (entreprise_agent.text.toString().isEmpty() or entreprise_agent.text.toString().isBlank()){
             entreprise_agent.error="required"
             return false
         }else{
             model.userToCreate.value!!.entrepriseAgent=entreprise_agent.text.toString()
         }
         if (smobilpay_id_utilisateur.text.toString().isEmpty() or smobilpay_id_utilisateur.text.toString().isBlank()){
             entreprise_agent.error="required"
             return false
         }else{
             model.userToCreate.value!!.smobilpayIdUtilisateur=smobilpay_id_utilisateur.text.toString()
         }
         return result
      }




}

