package com.example.kotlin_study.sample03


import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.kotlin_study.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

class BmiResultFragment : Fragment() , AnkoLogger {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bmi_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val height = arguments?.getDouble("height")
        val weight = arguments?.getDouble("weight")

        debug { "${height},${weight}" }

        //sharedPreference
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())

        pref.edit{
            putBoolean("check",true)
        }

        pref.getBoolean("check", false)
    }

    //확장함수
    fun Fragment.toast(text: String){
        Toast.makeText(requireContext(),text,Toast.LENGTH_SHORT).show()
    }
}
