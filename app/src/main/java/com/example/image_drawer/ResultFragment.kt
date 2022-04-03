package com.example.image_drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.image_drawer.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var viewModel: ResultFragmentVM
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val args by navArgs<ResultFragmentArgs>()

        binding = FragmentResultBinding.inflate(inflater, container, false)
        viewModel = ResultFragmentVM(args.id)
        binding.vm = viewModel

        viewModel = ResultFragmentVM(args.id)

        val viewPager = binding.imagesOriginalVp
        viewPager.adapter = viewModel.adapter
        viewModel.loadLesson()

        val seekBar = binding.seekBar
        seekBar.max = 10
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                viewPager.currentItem = progress
            }
        })

        return binding.root
    }

}