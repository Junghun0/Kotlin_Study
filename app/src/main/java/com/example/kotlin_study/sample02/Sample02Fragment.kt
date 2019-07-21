package com.example.kotlin_study.sample02

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_study.R
import com.example.kotlin_study.databinding.Sample02RecyclerItemLayoutBinding
import kotlinx.android.synthetic.main.sample02fragment.*
import java.io.Serializable


class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.sample02fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sample02_recyclerView.adapter = SampleAdapter{ sample ->
            val intent = Intent(requireActivity(), Sample02Activity::class.java)
            startActivity(intent)
        }
    }

    // 팩토리 패턴
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}

data class MySample(val name: String , val state: String) : Serializable

class SampleAdapter(private val clickListener: (sample: MySample) -> Unit) : RecyclerView.Adapter<SampleAdapter.SampleViewHolder>(){
    var items = arrayListOf<MySample>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sample02_recycler_item_layout, parent , false)

        return SampleViewHolder(Sample02RecyclerItemLayoutBinding.bind(view))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.binding.samples = items[holder.adapterPosition]
    }

    class SampleViewHolder(val binding : Sample02RecyclerItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}
