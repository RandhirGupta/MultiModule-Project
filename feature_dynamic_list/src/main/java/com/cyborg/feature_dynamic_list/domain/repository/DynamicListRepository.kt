package com.cyborg.feature_dynamic_list.domain.repository

import com.cyborg.feature_dynamic_list.data.Result
import com.cyborg.feature_dynamic_list.domain.entities.Option

interface DynamicListRepository {
  suspend fun getDynamicList(fileName: String): Result<List<Option>>
}
