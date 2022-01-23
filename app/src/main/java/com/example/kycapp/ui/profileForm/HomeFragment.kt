package com.example.kycapp.ui.profileForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.example.kycapp.R
import com.example.kycapp.databinding.FragmentHomeBinding
import com.example.kycapp.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    val model: HomeFragmentViewModel by activityViewModels<HomeFragmentViewModel>()
    private var _binding: FragmentHomeBinding? = null
    lateinit var viewPager: ViewPager2
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var tabLayout: TabLayout
    lateinit var next: Button
    lateinit var previews: Button

    companion object{
        var generalPosition = MutableLiveData<Int>().apply {
            value = 0
        }
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewPager = root.findViewById(R.id.pager)
        tabLayout = root.findViewById(R.id.tabLayout)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
        observeLiveData()

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Step ${(position + 1)}"
        }.attach()
        viewPager.setCurrentItem(generalPosition.value!!)


    }

    private fun observeLiveData() {
        generalPosition.observe(viewLifecycleOwner, {
            viewPager.setCurrentItem(it)
        })
    }
// code clique du bouton terminé à succregistration


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}