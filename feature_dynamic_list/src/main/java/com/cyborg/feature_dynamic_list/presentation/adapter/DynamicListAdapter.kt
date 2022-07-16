package com.cyborg.feature_dynamic_list.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cyborg.feature_dynamic_list.databinding.ItemCommentBinding
import com.cyborg.feature_dynamic_list.databinding.ItemPhotoBinding
import com.cyborg.feature_dynamic_list.databinding.ItemSingleChoiceBinding
import com.cyborg.feature_dynamic_list.domain.entities.*
import com.cyborg.feature_dynamic_list.utils.createImageFile
import java.io.File

class DynamicListAdapter(private val context: Context, private val fragment: Fragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private val dynamicList = mutableListOf<Option>()

  @SuppressLint("NotifyDataSetChanged")
  fun setDynamicList(dynamicList: List<Option>) {
    if (!dynamicList.isNullOrEmpty()) {
      this.dynamicList.clear()
      this.dynamicList.addAll(dynamicList)
      notifyDataSetChanged()
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)

    return when (viewType) {
      Type.Photo.ordinal -> PhotoViewHolder(ItemPhotoBinding.inflate(layoutInflater, parent, false))
      Type.SingleChoice.ordinal -> SingleChoiceViewHolder(ItemSingleChoiceBinding.inflate(layoutInflater, parent, false))
      else -> CommentViewHolder(ItemCommentBinding.inflate(layoutInflater, parent, false))
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    when (val option = dynamicList[position]) {
      is Photo -> (holder as PhotoViewHolder).bind(option, position)
      is SingleChoice -> (holder as SingleChoiceViewHolder).bind(option, position)
      is Comment -> (holder as CommentViewHolder).bind(option, position)
    }
  }

  override fun getItemCount(): Int = dynamicList.size

  override fun getItemViewType(position: Int): Int = dynamicList[position].type.ordinal

  inner class PhotoViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("QueryPermissionsNeeded")
    fun bind(option: Photo, position: Int) {
      var photoFile: File?

      binding.tvTitle.text = option.title

      binding.ivClear.setOnClickListener {
        binding.tvPhotoPreview.setImageBitmap(null)
        dynamicList[position].ans = ""
      }

      if (option.ans.isNotEmpty()) {
        val bitmap = BitmapFactory.decodeFile(option.ans)
        binding.tvPhotoPreview.setImageBitmap(bitmap)
      } else {
        binding.tvPhotoPreview.setImageBitmap(null)
      }

      binding.tvPhotoPreview.setOnClickListener {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra("position", position)

        if (takePictureIntent.resolveActivity(context.packageManager) != null) {
          try {
            photoFile = createImageFile(context)

            if (photoFile != null) {
              dynamicList[position].ans = photoFile!!.absolutePath
              val photoURI = FileProvider.getUriForFile(context, "com.cyborg.dynamiclists.fileprovider", photoFile!!)
              takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
              fragment.startActivityForResult(takePictureIntent, 1)
            }

          } catch (e: Exception) {
          }
        }
      }
    }
  }

  inner class SingleChoiceViewHolder(private val binding: ItemSingleChoiceBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(option: SingleChoice, adapterPosition: Int) {
      val adapter = SingleChoiceAdapter(option.dataMap.options, object : SingleChoiceSelection {

        override fun onSingleChoiceSelected(position: Int) {
          dynamicList[adapterPosition].ans = option.dataMap.options[position]
        }
      })

      adapter.selectedItem = option.dataMap.options.indexOfFirst { it == option.ans }

      val layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)

      binding.tvTitle.text = option.title
      binding.rvSingleChoice.layoutManager = layoutManager
      binding.rvSingleChoice.adapter = adapter
    }
  }

  inner class CommentViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(option: Comment, position: Int) {
      binding.tvTitle.text = option.title
      binding.etComment.setText(option.ans)

      binding.switchComment.setOnCheckedChangeListener { _, b ->
        if (b) binding.etComment.visibility = View.VISIBLE
        else binding.etComment.visibility = View.GONE
      }

      binding.etComment.addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
          dynamicList[position].ans = s.toString()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
      })
    }
  }
}
