package com.cyborg.feature_dynamic_list.domain.usecase

import com.cyborg.feature_dynamic_list.data.Result
import com.cyborg.feature_dynamic_list.domain.entities.Option
import com.cyborg.feature_dynamic_list.domain.repository.DynamicListRepository
import javax.inject.Inject

class GetDynamicListUseCase
@Inject constructor(
  private val dynamicListRepository: DynamicListRepository,
) {

  suspend operator fun invoke(fileName: String): Result<List<Option>> = dynamicListRepository.getDynamicList(fileName)
}
