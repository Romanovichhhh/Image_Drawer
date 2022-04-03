package com.example.image_drawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.image_drawer.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var viewModel: ResultFragmentVM
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args by navArgs<ResultFragmentArgs>()

        viewModel = ResultFragmentVM(args.id)

        binding = FragmentResultBinding.inflate(inflater, container, false)
        binding.vm = viewModel

        val viewPager: ViewPager2 = binding.imagesOriginalVp
        viewPager.adapter = viewModel.adapter
        viewModel.loadLesson()

        return binding.root
    }

}