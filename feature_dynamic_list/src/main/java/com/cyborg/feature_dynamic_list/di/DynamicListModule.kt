package com.cyborg.feature_dynamic_list.di

import com.cyborg.core.di.scope.FeatureScope
import com.cyborg.feature_dynamic_list.data.mapper.DynamicListMapper
import dagger.Module
import dagger.Provides

@Module
class DynamicListModule {

  @Provides
  @FeatureScope
  fun provideDynamicListMapper(): DynamicListMapper = DynamicListMapper()
}
