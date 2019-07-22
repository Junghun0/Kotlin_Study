package com.example.kotlin_study.sample06


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AlertFragment(private val onClickListener: () -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("권한체크!")
        builder.setMessage("외부 저장소 권한이 필수입니다.")
        builder.setPositiveButton("수락") { _, _ ->
            onClickListener.invoke()
        }
        builder.setNegativeButton("거부",null)
        return builder.create()
    }
}
