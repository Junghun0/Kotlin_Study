package com.example.kotlin_study.sample03


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.kotlin_study.R
import kotlinx.android.synthetic.main.fragment_bmi_main.*

class BmiMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_bmi_main,container,false);


        result_button.setOnClickListener{
            //자바 형식 bundle 사용
            /*
            val bundle = Bundle()
            bundle.putDouble("height",height_edit.text.toString().toDouble())
            bundle.putDouble("weight",weight_edit.text.toString().toDouble())
            */

            //kotlin
            val bundle = bundleOf(
                "height" to height_edit.text.toString().toDouble(),
                "weight" to weight_edit.text.toString().toDouble()
            )

            it.findNavController()
                .navigate(R.id.action_bmiMainFragment_to_bmiResultFragment, bundle)
        }

        return view
    }


}
