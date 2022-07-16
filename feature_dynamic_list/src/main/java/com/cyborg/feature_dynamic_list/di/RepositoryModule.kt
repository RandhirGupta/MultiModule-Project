package com.cyborg.feature_dynamic_list.di

import com.cyborg.feature_dynamic_list.data.DynamicListRepositoryImpl
import com.cyborg.feature_dynamic_list.domain.repository.DynamicListRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

  @Binds
  fun bindDynamicListRepository(repository: DynamicListRepositoryImpl): DynamicListRepository
}
