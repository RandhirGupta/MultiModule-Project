package com.cyborg.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseFragment<out B : ViewDataBinding, V : ViewModel> : Fragment() {

  val binding: B get() = viewDataBinding

  lateinit var vm: V

  @Inject
  lateinit var factory: ViewModelProvider.Factory

  private lateinit var viewDataBinding: B

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    initDaggerComponent()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)

    vm = ViewModelProvider(this, factory)[getViewModelClass()]

    viewDataBinding.lifecycleOwner = this
    viewDataBinding.executePendingBindings()

    return viewDataBinding.root
  }

  @LayoutRes
  abstract fun getLayoutResourceId(): Int
  abstract fun initDaggerComponent()
  abstract fun getViewModelClass(): Class<V>
}
