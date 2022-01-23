package com.example.kycapp.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kycapp.ui.profileForm.step1.Step1Fragment
import com.example.kycapp.ui.profileForm.step2.Step2Fragment
import com.example.kycapp.ui.profileForm.step3.Step3Fragment
import com.example.kycapp.ui.profileForm.step4.Step4Fragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4
    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = Step1Fragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            val ARG_OBJECT="agument"
            putInt(ARG_OBJECT, position + 1)
        }
        return when(position){
            1-> Step2Fragment()
            2-> Step3Fragment()
            3-> Step4Fragment()
            0-> Step1Fragment()
            else -> Step1Fragment()
        }

    }
}