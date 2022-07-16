package com.cyborg.feature_dynamic_list.di

import com.cyborg.core.di.CoreComponent
import com.cyborg.core.di.scope.FeatureScope
import com.cyborg.feature_dynamic_list.presentation.screen.DynamicListFragment
import dagger.Component

@FeatureScope
@Component(
  dependencies = [CoreComponent::class],
  modules = [
    RepositoryModule::class,
    DynamicListModule::class,
    ViewModelModule::class
  ]
)
interface DynamicListComponent {
  fun inject(dynamicListFragment: DynamicListFragment)
}
