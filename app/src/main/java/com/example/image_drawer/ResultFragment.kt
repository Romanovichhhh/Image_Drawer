package com.example.image_drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
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

        viewModel.title.observe(viewLifecycleOwner) {
            binding.resultTitleTv.text = it
        }
        viewModel.seekBarMax.observe(viewLifecycleOwner) {
            binding.seekBar.max = it
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                viewPager.currentItem = progress
            }
        })

        val seekBar = binding.seekBar
        binding.imagesOriginalVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                seekBar.progress = position
            }
        })

        return binding.root
    }

}