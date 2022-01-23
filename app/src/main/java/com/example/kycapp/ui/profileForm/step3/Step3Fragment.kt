package com.example.kycapp.ui.profileForm.step3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.kycapp.R
import com.example.kycapp.ui.profileForm.HomeFragmentViewModel
import com.example.kycapp.ui.profileForm.HomeFragment.Companion.generalPosition
import kotlinx.android.synthetic.main.step2_fragment.*
import kotlinx.android.synthetic.main.step3_fragment.*
import kotlinx.android.synthetic.main.step3_fragment.next
import kotlinx.android.synthetic.main.step3_fragment.previews

class Step3Fragment : Fragment() {




    companion object {
        fun newInstance() = Step3Fragment()
    }

    val model: HomeFragmentViewModel by activityViewModels<HomeFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.step3_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickButton()
    }


    private fun onClickButton() {
        next.setOnClickListener {
            if(validatFields()){
                generalPosition.value?.let {
                    generalPosition.value = 3
                }
            }
        }
        previews.setOnClickListener {
            generalPosition.value = 1
        }
    }

    fun validatFields():Boolean{
        var result = true
        if(nom_entreprise_collecteur.text.toString().isEmpty() or nom_entreprise_collecteur.text.toString().isBlank()){
            nom_entreprise_collecteur.error="required"
            return  false
        }else{
            model.userToCreate.value!!.nomEntrepriseCollecteur=nom_entreprise_collecteur.text.toString()
        }
        if(numero_cni.text.isEmpty() or numero_cni.text.isBlank()){
            numero_cni.error="required"
            return  false
        }else{
            model.userToCreate.value!!.numCni=numero_cni.text.toString()
        }
        if(profession_agent.text.isEmpty() or profession_agent.text.isBlank()){
            numero_cni.error="required"
            return  false
        }else{
            model.userToCreate.value!!.professionAgent=profession_agent.text.toString()
        }
        if(activite.text.isEmpty() or activite.text.isBlank()){
            activite.error="required"
            return  false
        }else{
            model.userToCreate.value!!.activitéAgent=activite.text.toString()
        }
        if(capital_initial_agent.text.isEmpty() or capital_initial_agent.text.isBlank()){
            residence.error="required"
            return  false
        }else{
            try {
                model.userToCreate.value!!.capitalInitialAgent=capital_initial_agent.text.toString().toInt()
            }catch (e:Exception){

            }

        }
        if(source_financiere_agent.text.isEmpty() or source_financiere_agent.text.isBlank()){
            activite.error="required"
            return  false
        }else{
            model.userToCreate.value!!.sourceFinancièreAgent=source_financiere_agent.text.toString()
        }

        return result
    }




}