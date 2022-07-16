package com.cyborg.feature_dynamic_list.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
sealed class OptionDto {
  @Json(name = "type")
  abstract val type: TypeDto

  @Json(name = "id")
  abstract val id: String

  @Json(name = "title")
  abstract val title: String
}

@JsonClass(generateAdapter = true)
data class PhotoDto(
  override val type: TypeDto = TypeDto.PHOTO,
  override val id: String,
  override val title: String,
) : OptionDto()

@JsonClass(generateAdapter = true)
data class SingleChoiceDto(
  override val type: TypeDto = TypeDto.SINGLE_CHOICE,
  override val id: String,
  override val title: String,

  @Json(name = "dataMap")
  val dataMapDto: DataMapDto,
) : OptionDto()

@JsonClass(generateAdapter = true)
data class CommentDto(
  override val type: TypeDto = TypeDto.COMMENT,
  override val id: String,
  override val title: String,
) : OptionDto()

data class DataMapDto(
  @field:Json(name = "options")
  val options: List<String>,
)

enum class TypeDto {
  PHOTO,
  SINGLE_CHOICE,
  COMMENT
}
