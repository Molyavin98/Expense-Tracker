package com.molyavin.expensetracker.data.network.dto

import com.molyavin.expensetracker.domain.model.NewsLink

data class NewsLinkDTO(
    val url: String? = null,
)

fun NewsLinkDTO.asPresentation() = NewsLink(
    url = url ?: ""
)
