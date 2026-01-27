package com.kmp.template.network.weread

import kotlinx.serialization.Serializable

@Serializable
data class WeReadResponse(
    val books: List<Book>,
    val correction: String,
    val hasMore: Int,
    val parts: List<String>,
    val queryUid: String,
    val sid: String,
    val totalCount: Int
)