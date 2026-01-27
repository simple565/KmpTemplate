package com.kmp.template.network.weread

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookInfo(
    val author: String,
    val autoBookRequest: Int = 0,
    val bookId: String,
    val bookStatus: Int,
    val cover: String,
    val cpid: Int,
    val finished: Int,
    val format: String,
    val intro: String,
    val isAutoDownload: Int,
    @SerialName("ispub")
    val isPub: Int,
    val newRating: Int = 0,
    val newRatingCount: Int = 0,
    val newRatingDetail: NewRatingDetail? = null,
    val paperBook: PaperBook,
    val payType: Int,
    val price: Double,
    val publisher: String,
    val showPaidInSearch: Int,
    @SerialName("soldout")
    val soldOut: Int,
    val title: String,
    val translator: String = "",
    val type: Int
)