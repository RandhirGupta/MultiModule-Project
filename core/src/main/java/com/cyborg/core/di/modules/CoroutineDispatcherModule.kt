package com.cyborg.core.di.modules

import com.cyborg.core.dispatcher.CoroutineDispatcherProvider
import com.cyborg.core.dispatcher.DispatcherProvider
import dagger.Binds
import dagger.Module

@Module
interface CoroutineDispatcherModule {
  @Binds
  fun bindDispatcher(dispatcherProvider: CoroutineDispatcherProvider): DispatcherProvider
}
