package com.example.kycapp.ui.agentDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.kycapp.R
import com.example.kycapp.ui.dashboard.DashboardViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.agent_details_fragment.*
import kotlinx.android.synthetic.main.step2_fragment.*

class AgentDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = AgentDetailsFragment()
    }

    val model: DashboardViewModel by activityViewModels<DashboardViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.agent_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // implémentation bouton retour
        back.setOnClickListener {
            findNavController().navigateUp()
        }
        observeViewModel()

    }

    fun observeViewModel(){
        model.selectedAgent.observe(viewLifecycleOwner, Observer { agent->
            agent?.let {
                Picasso.get().load(it.photoAgent).error(R.drawable.ic_baseline_broken_image_24).into(cardImages)
                Picasso.get().load(it.imageRecto).error(R.drawable.ic_baseline_broken_image_24).into(cni_recto)
                Picasso.get().load(it.imageVerso).error(R.drawable.ic_baseline_broken_image_24).into(cni_verso)
                Picasso.get().load(it.photoAgent).error(R.drawable.ic_baseline_broken_image_24).into(photoAgent)
                Picasso.get().load(it.photoPointVente).error(R.drawable.ic_baseline_broken_image_24).into(photopos)
                cardTitle.text=it.agentName +" "+ it.prenomAgent ?:"N/a"
                birht_day_value.text=it.dateNaissance.toString()?:"N/a"
                birth_place_value.text=it.lieuNaissance?:"N/a"
                phone_number_value.text=it.phoneNumber?:"N/a"
                living_place_value.text=it.residenceAgent?:"N/a"
                cc_value.text=it.entrepriseAgent?:"N/a"
                id_card_value.text=it.numCni?:"N/a"
                agent_preffession_value.text=it.professionAgent?:"N/a"
                activity_value.text=it.activitéAgent?:"N/a"
                initial_deposit_value.text=it.capitalInitialAgent.toString()?:"N/a"
                finance_source.text=it.sourceFinancièreAgent?:"N/a"
                cc_company_value.text=it.nomEntrepriseCollecteur?:"N/a"
                smobilpay_user_id.text=it.smobilpayIdUtilisateur?:"N/a"



            }
        })
    }

}