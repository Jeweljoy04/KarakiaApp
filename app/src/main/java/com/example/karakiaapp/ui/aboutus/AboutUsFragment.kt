package com.example.karakiaapp.ui.aboutus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.karakiaapp.databinding.FragmentAboutUsBinding

class AboutUsFragment: Fragment() {

    private var _binding: FragmentAboutUsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val aboutUsViewModel =
            ViewModelProvider(this).get(AboutUsViewModel::class.java)

        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val textView: RelativeLayout = binding.textAboutus
        aboutUsViewModel.text.observe(viewLifecycleOwner) {
            //textView.requestLayout() = it
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}