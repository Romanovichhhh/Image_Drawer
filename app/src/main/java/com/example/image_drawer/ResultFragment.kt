package com.example.image_drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2


class ResultFragment : Fragment() {

    private lateinit var viewModel: ResultFragmentVM
    lateinit var viewPager: ViewPager2
    lateinit var seekBar : SeekBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args by navArgs<ResultFragmentArgs>()

        viewModel = ResultFragmentVM(args.id)

        val view = inflater.inflate(R.layout.fragment_result, container, false)

        viewPager = view.findViewById(R.id.imagesOriginal)
        seekBar = view.findViewById(R.id.seek_bar)
        viewPager.adapter = viewModel.adapter

        seekBar.max = 10

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                viewPager.currentItem = progress
            }
        })



        viewModel.loadLesson()

        return view
    }

}