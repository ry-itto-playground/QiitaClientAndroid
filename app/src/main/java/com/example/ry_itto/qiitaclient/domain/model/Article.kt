package com.example.ry_itto.qiitaclient.domain.model

import java.util.Date

data class Article(
    val id: String,
    val title: String,
    val url: String,
    val body: String,
    val renderedBody: String,
    val user: User,
    val tags: List<Tag>,
    val group: Group?,
    val commentsCount: Int,
    val likesCount: Int,
    val viewCount: Int,
    val reactionsCount: Int,
    val isPrivate: Boolean,
    val coediting: Boolean,
    val createdAt: Date,
    val updatedAt: Date
)