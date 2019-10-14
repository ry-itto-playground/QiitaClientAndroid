package com.example.ry_itto.qiitaclient.domain.model

data class Tag(
    val name: String,
    val iconUrl: String?,
    val followersCount: Int,
    val itemsCount: Int
)