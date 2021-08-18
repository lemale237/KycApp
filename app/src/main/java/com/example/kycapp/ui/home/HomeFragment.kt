package com.example.kycapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kycapp.R
import com.example.kycapp.databinding.FragmentHomeBinding
import com.example.kycapp.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.absoluteValue

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    lateinit var viewPager: ViewPager2
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var tabLayout: TabLayout
    lateinit var next: Button
    lateinit var previews: Button
    var generalPosition = MutableLiveData<Int>().apply {
        value = 0
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewPager = root.findViewById(R.id.pager)
        tabLayout = root.findViewById(R.id.tabLayout)
        next = root.findViewById(R.id.next)
        previews = root.findViewById(R.id.previews)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
        observeLiveData()
        onClickButton()
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Step ${(position + 1)}"
        }.attach()
//voici le sharabiya de Aubin.
    }

    private fun observeLiveData() {
        generalPosition.observe(viewLifecycleOwner, {
            viewPager.setCurrentItem(it)
            if (it==3)next.text =  "Terminer"
        })
    }
// code cique du bouton terminé à succregistration
    private fun onClickButton() {
        next.setOnClickListener {
            generalPosition.value?.let {
                if (it < 3) {
                    generalPosition.value = generalPosition.value?.plus(1)
                }else{

                    findNavController().navigate(R.id.action_navigation_home_to_sucessRegistrationFragment)
                }
            }

        }
        previews.setOnClickListener {
            generalPosition.value?.let {
                if (it > 0) {
                    generalPosition.value = generalPosition.value?.minus(1)
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}