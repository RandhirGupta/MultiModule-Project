package com.cyborg.feature_dynamic_list.presentation.screen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyborg.core.DynamicListApplication
import com.cyborg.feature_dynamic_list.R
import com.cyborg.feature_dynamic_list.data.Result
import com.cyborg.feature_dynamic_list.data.Result.*
import com.cyborg.feature_dynamic_list.databinding.FragmentDynamicListBinding
import com.cyborg.feature_dynamic_list.di.DaggerDynamicListComponent
import com.cyborg.feature_dynamic_list.domain.entities.Option
import com.cyborg.feature_dynamic_list.presentation.adapter.DynamicListAdapter
import com.cyborg.feature_dynamic_list.presentation.viewmodel.DynamicListViewModel
import com.cyborg.presentation.BaseFragment

class DynamicListFragment : BaseFragment<FragmentDynamicListBinding, DynamicListViewModel>() {

  private val adapter by lazy { DynamicListAdapter(requireContext(), this) }
  private var dynamicList = listOf<Option>()

  override fun getLayoutResourceId(): Int = R.layout.fragment_dynamic_list

  override fun initDaggerComponent() {
    DaggerDynamicListComponent
      .builder()
      .coreComponent(DynamicListApplication.coreComponent(requireContext()))
      .build()
      .inject(this)
  }

  override fun getViewModelClass(): Class<DynamicListViewModel> = DynamicListViewModel::class.java

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    askForCameraPermission()
    initView()
    registerGetDynamicListObserver()
    vm.getDynamicList()
  }

  @SuppressLint("NotifyDataSetChanged")
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
      adapter.notifyDataSetChanged()
    }
  }

  private fun initView() {
    val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    binding.rvDynamicList.layoutManager = layoutManager
    binding.rvDynamicList.adapter = adapter

    binding.btnSubmit.setOnClickListener { showResult() }
  }

  private fun registerGetDynamicListObserver() {
    vm.dynamicList.observe(viewLifecycleOwner) { subscribeGetDynamicList(it) }
  }

  private fun subscribeGetDynamicList(result: Result<List<Option>>) {
    when (result) {
      is Loading -> handleDynamicListLoadingState()
      is Success -> handleDynamicListSuccessState(result.data)
      is Error -> handleDynamicListErrorState(result)
    }
  }

  private fun handleDynamicListLoadingState() {
    startRotatingImage()
    binding.loadingLayout.visibility = View.VISIBLE
    binding.errorLayout.visibility = View.GONE
    binding.contentLayout.visibility = View.GONE
  }

  private fun handleDynamicListSuccessState(dynamicList: List<Option>) {
    binding.loadingLayout.visibility = View.GONE
    binding.errorLayout.visibility = View.GONE
    binding.contentLayout.visibility = View.VISIBLE

    this.dynamicList = dynamicList
    adapter.setDynamicList(dynamicList)
  }

  private fun handleDynamicListErrorState(error: Error) {
    binding.loadingLayout.visibility = View.GONE
    binding.errorLayout.visibility = View.VISIBLE
    binding.contentLayout.visibility = View.GONE
  }

  private fun startRotatingImage() {
    val startRotateAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.linear_interpolator)
    binding.ivLoader.startAnimation(startRotateAnimation)
  }

  private fun askForCameraPermission() {
    if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
    }
  }

  private fun showResult() {
    val alertDialog = AlertDialog.Builder(requireContext()).create()
    alertDialog.setTitle("Input")

    var message = ""
    dynamicList.forEach {
      message += "${it.id} : ${it.ans} \n"
    }

    alertDialog.setMessage(message)

    alertDialog.setButton("OK") { _, _ -> alertDialog.dismiss() }

    alertDialog.show()
  }
}
