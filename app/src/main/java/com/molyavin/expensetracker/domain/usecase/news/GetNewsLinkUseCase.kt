package com.molyavin.expensetracker.domain.usecase.news

import com.molyavin.expensetracker.data.network.dto.asPresentation
import com.molyavin.expensetracker.data.repository.NewsLinkRepository
import com.molyavin.expensetracker.domain.model.NewsLink
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class GetNewsLinkUseCase @Inject constructor(
    private val newsLinkRepository: NewsLinkRepository
) : IAsyncUseCase<Unit, NewsLink> {

    override suspend fun execute(income: Unit): NewsLink {
        return newsLinkRepository.getNewsLinks().asPresentation()
    }
}