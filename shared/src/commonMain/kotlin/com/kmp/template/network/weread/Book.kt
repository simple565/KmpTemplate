package com.kmp.template.network.weread

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val bookInfo: BookInfo,
    val hints: String,
    val reading: Int = 0,
    val readingCount: Int,
    val reason: String,
    val scope: Int = 0,
    val scopeCount: Int = 0,
    val searchIdx: Int = 0,
    val searchReport: String,
    val seq: Int = 0,
    val subscribeCount: Int = 0,
    val type: Int
)