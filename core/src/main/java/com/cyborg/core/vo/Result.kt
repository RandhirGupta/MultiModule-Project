package com.cyborg.core.vo

import com.cyborg.core.network.HttpResult

sealed class Result<out T> {
  object Loading : Result<Nothing>()
  data class Success<out T>(val data: T) : Result<T>()
  data class Error(val cause: HttpResult, val code: Int? = null, val errorMessage: String? = null) : Result<Nothing>()
}
