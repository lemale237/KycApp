package com.example.kycapp.ui.profileForm.step2

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.kycapp.R
import com.example.kycapp.ui.profileForm.HomeFragmentViewModel
import com.example.kycapp.ui.profileForm.HomeFragment.Companion.generalPosition
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.step2_fragment.*
import java.util.*

class Step2Fragment : Fragment(), CountryCodePicker.OnCountryChangeListener {

    private var ccp: CountryCodePicker? = null
    private var countryCode: String? = null
    private var countryName: String? = null


    private lateinit var birthDay : Date


    companion object {
        fun newInstance() = Step2Fragment()
    }

    val model: HomeFragmentViewModel by activityViewModels<HomeFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.step2_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        date_naissance.setOnClickListener {

            displayCallendar()

        }
        onClickButton()

        ccp = country_code_picker
        ccp!!.setOnCountryChangeListener(this)
//to set default country code as Japan

        ccp!!.setDefaultCountryUsingNameCode("CM")
        ccp?.registerCarrierNumberEditText(phoneNumber)


    }


    // code cique du bouton terminé à succregistration
    private fun onClickButton() {
        next.setOnClickListener {
            if(validatFields()){
                generalPosition.value?.let {
                    generalPosition.value = 2
                }
            }


        }
        previews.setOnClickListener {
            generalPosition.value = 0
        }
    }

    fun validatFields():Boolean{
        var result = true
        if(nom.text.toString().isEmpty() or nom.text.toString().isBlank()){
            nom.error="required"
            return  false
        }else{
            model.userToCreate.value!!.agentName=nom.text.toString()
        }
        if(prenom.text.isEmpty() or prenom.text.isBlank()){
            prenom.error="required"
            return  false
        }else{
            model.userToCreate.value!!.prenomAgent=prenom.text.toString()
        }
        if(!(this::birthDay.isInitialized)){
            date_naissance.error= " choose a birthday"
        }else{
            model.userToCreate.value!!.dateNaissance=birthDay
        }
        if(lieu_naissance.text.isEmpty() or lieu_naissance.text.isBlank()){
            lieu_naissance.error="required"
            return  false
        }else{
            model.userToCreate.value!!.lieuNaissance=lieu_naissance.text.toString()
        }
        if(!(ccp!!.isValidFullNumber)){
            phoneNumber.error="required"
            return  false
        }else{
            model.userToCreate.value!!.phoneNumber=ccp!!.fullNumberWithPlus
        }
        if(residence.text.isEmpty() or residence.text.isBlank()){
            residence.error="required"
            return  false
        }else{
            model.userToCreate.value!!.residenceAgent=residence.text.toString()
        }

        return result
    }

    override fun onCountrySelected() {
        countryCode = ccp!!.selectedCountryCode
        countryName = ccp!!.selectedCountryName
        ccp?.formattedFullNumber

    }


    private fun displayCallendar() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                date_naissance.setText("" + dayOfMonth + "/" + monthOfYear + "/" + year)
                birthDay = GregorianCalendar(year, month - 1, day).time

            },
            year,
            month,
            day
        )

        dpd.show()
    }


}