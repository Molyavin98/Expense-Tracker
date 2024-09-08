package com.molyavin.expensetracker.data.repository

import com.google.firebase.database.DatabaseReference
import com.molyavin.expensetracker.data.network.dto.NewsLinkDTO
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NewsLinkRepositoryImpl @Inject constructor(
    private val database: DatabaseReference,
) : NewsLinkRepository {

    override suspend fun getNewsLinks(): NewsLinkDTO {
        val snapshot = database.child(LINK).get().await()
        val url = snapshot.getValue(String::class.java) ?: ""
        return NewsLinkDTO(url)
    }

    companion object {
        private const val LINK = "url"
    }

}