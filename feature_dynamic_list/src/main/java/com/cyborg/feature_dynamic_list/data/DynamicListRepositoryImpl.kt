package com.cyborg.feature_dynamic_list.data

import android.content.Context
import com.cyborg.core.dispatcher.DispatcherProvider
import com.cyborg.feature_dynamic_list.data.Result.Error
import com.cyborg.feature_dynamic_list.data.Result.Success
import com.cyborg.feature_dynamic_list.data.mapper.DynamicListMapper
import com.cyborg.feature_dynamic_list.data.response.OptionDto
import com.cyborg.feature_dynamic_list.domain.entities.Option
import com.cyborg.feature_dynamic_list.domain.repository.DynamicListRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DynamicListRepositoryImpl
@Inject constructor(
  private val context: Context,
  private val dispatcher: DispatcherProvider,
  private val mapper: DynamicListMapper,
) : DynamicListRepository {

  private val moshi: Moshi = DynamicListMoshiFactory.getInstance()

  override suspend fun getDynamicList(fileName: String): Result<List<Option>> =
    withContext(dispatcher.io) {
      try {
        val inputSteam = context.assets.open(fileName)
        val dynamicListJson = inputSteam.bufferedReader().use { it.readText() }
        val listType = Types.newParameterizedType(List::class.java, OptionDto::class.java)
        val adapter: JsonAdapter<List<OptionDto>> = moshi.adapter(listType)
        val dynamicList = adapter.fromJson(dynamicListJson)
        if (dynamicList != null) Success(mapper.map(dynamicList)) else Error(Exception("Exception while reading asset"))
      } catch (e: Exception) {
        Error(e)
      }
    }
}
