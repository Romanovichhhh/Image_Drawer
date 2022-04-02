package com.example.image_drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2



class ResultFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lateinit var viewPager: ViewPager2
        val args by navArgs<ResultFragmentArgs>()

        val vm = ResultFragmentVM(args.id)

        val view =  inflater.inflate(R.layout.fragment_result, container, false)

        viewPager = view.findViewById(R.id.imagesOriginal)

        viewPager.adapter = vm.adapter

        vm.loadLesson()

        return view
    }

}