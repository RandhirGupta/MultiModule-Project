package com.cyborg.feature_dynamic_list.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyborg.feature_dynamic_list.databinding.ItemSingleChoiceOptionsBinding

class SingleChoiceAdapter(
  private val options: List<String>,
  private val listener: SingleChoiceSelection,
) : RecyclerView.Adapter<SingleChoiceAdapter.OptionViewHolder>() {

  var selectedItem = -1

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    return OptionViewHolder(ItemSingleChoiceOptionsBinding.inflate(layoutInflater, parent, false))
  }

  override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
    val option = options[position]
    holder.bind(option, position)
  }

  override fun getItemCount(): Int = options.size

  inner class OptionViewHolder(private val binding: ItemSingleChoiceOptionsBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("NotifyDataSetChanged")
    fun bind(option: String, position: Int) {
      binding.tvOption.text = option
      binding.rbOption.isChecked = position == selectedItem

      val clickListener: View.OnClickListener = View.OnClickListener {
        selectedItem = adapterPosition
        listener.onSingleChoiceSelected(selectedItem)
        notifyDataSetChanged()
      }

      binding.rbOption.setOnClickListener(clickListener)
      binding.tvOption.setOnClickListener(clickListener)
    }
  }
}

interface SingleChoiceSelection {
  fun onSingleChoiceSelected(position: Int)
}
