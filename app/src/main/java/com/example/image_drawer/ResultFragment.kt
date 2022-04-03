package com.example.image_drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.image_drawer.databinding.FragmentResultBinding
import com.example.image_drawer.utils.convertMediaUrl

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


        binding.imagesOriginalVp.adapter = viewModel.adapter
        viewModel.loadLesson()
        viewModel.loadGif()

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
                binding.imagesOriginalVp.currentItem = progress
            }
        })

        binding.imagesOriginalVp.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.seekBar.progress = position
            }
        })

//        setGifVisibility(false)
        viewModel.gitUrl.observe(viewLifecycleOwner) { str ->
//            if (!str.isNullOrEmpty()) setGifVisibility(true)
            context?.let {
                Glide.with(it)
                    .load(convertMediaUrl(str))
                    .into(binding.gifImage)
            }
        }

        return binding.root
    }

//    private fun setGifVisibility(isVisible: Boolean) {
//        binding.gifImage.isVisible = isVisible
//        binding.gifProgressBar.isVisible = !isVisible
//    }

}