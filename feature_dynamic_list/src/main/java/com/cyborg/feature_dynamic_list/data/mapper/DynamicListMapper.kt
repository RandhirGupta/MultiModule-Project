package com.cyborg.feature_dynamic_list.data.mapper

import com.cyborg.feature_dynamic_list.data.response.*
import com.cyborg.feature_dynamic_list.domain.entities.DataMap
import com.cyborg.feature_dynamic_list.domain.entities.Option
import com.cyborg.feature_dynamic_list.domain.entities.Type
import com.cyborg.feature_dynamic_list.domain.entities.Type.*
import com.cyborg.feature_dynamic_list.domain.entities.Comment as CommentDM
import com.cyborg.feature_dynamic_list.domain.entities.Photo as PhotoDM
import com.cyborg.feature_dynamic_list.domain.entities.SingleChoice as SingleChoiceDM

class DynamicListMapper {

  fun map(dynamicList: List<OptionDto>): List<Option> =
    dynamicList.map { optionDto ->
      when (optionDto) {
        is PhotoDto -> optionDto.toPhoto()
        is SingleChoiceDto -> optionDto.toSingleChoice()
        is CommentDto -> optionDto.toComment()
      }
    }

  private fun OptionDto.toComment(): CommentDM = CommentDM(
    type = type.toType(),
    id = id,
    title = title,
    ans = ""
  )

  private fun OptionDto.toPhoto(): PhotoDM = PhotoDM(
    type = type.toType(),
    id = id,
    title = title,
    ans = ""
  )

  private fun SingleChoiceDto.toSingleChoice(): SingleChoiceDM = SingleChoiceDM(
    type = type.toType(),
    id = id,
    title = title,
    ans = "",
    dataMap = dataMapDto.toDataMap()
  )

  private fun TypeDto.toType(): Type = when (this) {
    TypeDto.PHOTO -> Photo
    TypeDto.SINGLE_CHOICE -> SingleChoice
    TypeDto.COMMENT -> Comment
  }

  private fun DataMapDto.toDataMap(): DataMap = DataMap(
    options = options
  )
}
