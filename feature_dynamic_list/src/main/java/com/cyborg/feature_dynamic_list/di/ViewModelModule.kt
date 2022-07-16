package com.cyborg.feature_dynamic_list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cyborg.core.di.ViewModelFactory
import com.cyborg.core.di.ViewModelKey
import com.cyborg.feature_dynamic_list.presentation.viewmodel.DynamicListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(DynamicListViewModel::class)
  abstract fun providesDynamicListViewModel(viewModel: DynamicListViewModel): ViewModel
}
