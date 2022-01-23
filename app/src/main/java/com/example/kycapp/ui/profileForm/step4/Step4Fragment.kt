package com.example.kycapp.ui.profileForm.step4

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.kycapp.R
import com.example.kycapp.api.AgentApi

import com.example.kycapp.ui.profileForm.HomeFragmentViewModel
import com.example.kycapp.ui.profileForm.HomeFragment.Companion.generalPosition
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.step4_fragment.*
import kotlinx.android.synthetic.main.step4_fragment.next
import kotlinx.android.synthetic.main.step4_fragment.progress_Bar_upload

class Step4Fragment : Fragment() {

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
        ImagePicker.with(this)
            .crop()	    			//Crop image(Optional), Check Customization for more option
            .compress(1024)			//Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
            .start()

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            val api = AgentApi()
            if (isRecto){
                photoAgent.setImageURI(uri)
                progress_Bar_upload.isVisible=true

                api.uploadFile(uri,{
                    imageFront=it
                    progress_Bar_upload.isVisible=false
                    model.userToCreate.value!!.photoAgent=it
                    Toast.makeText(requireContext(),"upload succeeded",Toast.LENGTH_LONG).show()
                    Log.e("imqge agent",it)
                },{
                    progress_Bar_upload.isVisible=false
                    Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
                })

            }else{
                photoVente.setImageURI(uri)
                progress_Bar_upload.isVisible=true
                api.uploadFile(uri,{
                    imageBack=uri.toString()
                    progress_Bar_upload.isVisible=false
                    Toast.makeText(requireContext(),"upload succeeded",Toast.LENGTH_LONG).show()
                    Log.e("imqge point de vente",it)
                    model.userToCreate.value!!.photoPointVente=it
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickButton()

    }


    // code cique du bouton terminé à succregistration
    private fun onClickButton() {
        next.setOnClickListener {
            if(validateFields()){
                progress_Bar_submission.isVisible=true
                model.createAgent({
                    progress_Bar_submission.isVisible=false
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

