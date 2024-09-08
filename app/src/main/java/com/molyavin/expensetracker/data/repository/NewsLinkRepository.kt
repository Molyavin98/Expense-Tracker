package com.molyavin.expensetracker.data.repository

import com.molyavin.expensetracker.data.network.dto.NewsLinkDTO

interface NewsLinkRepository {
    suspend fun getNewsLinks(): NewsLinkDTO
}