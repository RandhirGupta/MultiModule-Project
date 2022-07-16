package com.cyborg.feature_dynamic_list.domain.entities

sealed class Option {
  abstract val type: Type
  abstract val id: String
  abstract val title: String
  abstract var ans: String
}

enum class Type {
  Photo,
  SingleChoice,
  Comment
}

data class Photo(
  override val type: Type = Type.Photo,
  override val id: String,
  override val title: String,
  override var ans: String,
) : Option()

data class SingleChoice(
  override val type: Type = Type.SingleChoice,
  override val id: String,
  override val title: String,
  override var ans: String,

  val dataMap: DataMap,
) : Option()

data class Comment(
  override val type: Type = Type.Comment,
  override val id: String,
  override val title: String,
  override var ans: String,

  ) : Option()

data class DataMap(
  val options: List<String>,
)
