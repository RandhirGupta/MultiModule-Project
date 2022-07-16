package com.cyborg.feature_dynamic_list.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyborg.feature_dynamic_list.data.Result
import com.cyborg.feature_dynamic_list.data.Result.Loading
import com.cyborg.feature_dynamic_list.domain.entities.Option
import com.cyborg.feature_dynamic_list.domain.usecase.GetDynamicListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DynamicListViewModel
@Inject constructor(
  private val dynamicListUseCase: GetDynamicListUseCase,
) : ViewModel() {

  var dynamicList = MutableLiveData<Result<List<Option>>>()
    private set

  fun getDynamicList() {
    dynamicList.value = Loading
    viewModelScope.launch { dynamicList.value = dynamicListUseCase.invoke("dynamic_list.json") }
  }
}
