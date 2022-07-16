package com.cyborg.feature_dynamic_list.data

import com.cyborg.feature_dynamic_list.data.response.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object DynamicListMoshiFactory {

  private val moshi: Moshi = Moshi.Builder()
    .add(PolymorphicJsonAdapterFactory.of(OptionDto::class.java, "type")
      .withSubtype(PhotoDto::class.java, TypeDto.PHOTO.name)
      .withSubtype(SingleChoiceDto::class.java, TypeDto.SINGLE_CHOICE.name)
      .withSubtype(CommentDto::class.java, TypeDto.COMMENT.name))
    .add(KotlinJsonAdapterFactory())
    .build()

  fun getInstance() = moshi
}
