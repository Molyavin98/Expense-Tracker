package com.molyavin.expensetracker.domain.model

import com.molyavin.expensetracker.data.network.dto.NewsLinkDTO

data class NewsLink(
    val url: String
)

fun NewsLink.asDomain() = NewsLinkDTO(
    url = url
)
