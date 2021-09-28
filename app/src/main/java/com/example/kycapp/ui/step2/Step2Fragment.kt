package com.example.kycapp.ui.step2

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kycapp.R
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.step2_fragment.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class Step2Fragment : Fragment() ,CountryCodePicker.OnCountryChangeListener{


    private var ccp: CountryCodePicker?=null
    private var countryCode:String?=null
    private var countryName:String?=null

    companion object {
        fun newInstance() = Step2Fragment()
    }

    private lateinit var viewModel: Step2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.step2_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Step2ViewModel::class.java)
        // TODO: Use the ViewModel


        date_naissance.setOnClickListener {

            displayCallendar()

        }

        ccp = country_code_picker
        ccp!!.setOnCountryChangeListener(this)
//to set default country code as Japan

        ccp!!.setDefaultCountryUsingNameCode("CM")
ccp?.registerCarrierNumberEditText(phoneNumber)



    }



    override fun onCountrySelected() {
        countryCode=ccp!!.selectedCountryCode
        countryName=ccp!!.selectedCountryName
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

            },
            year,
            month,
            day
        )

        dpd.show()
    }


}