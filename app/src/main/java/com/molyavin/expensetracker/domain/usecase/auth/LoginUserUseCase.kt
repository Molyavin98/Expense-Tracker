package com.molyavin.expensetracker.domain.usecase.auth

import com.molyavin.expensetracker.data.network.dto.toDTO
import com.molyavin.expensetracker.data.repository.UserRepository
import com.molyavin.expensetracker.domain.model.NewUser
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import com.molyavin.expensetracker.utils.AppDispatchers
import com.molyavin.expensetracker.utils.IncorrectUserInfoException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume

class LoginUserUseCase @Inject constructor(
    private val validateLoginUserInfoUseCase: ValidateLoginUserInfoUseCase,
    private val fireBaseRepository: UserRepository,
    private val dispatcher: AppDispatchers,
) : IAsyncUseCase<NewUser, Boolean> {

    override suspend fun execute(income: NewUser): Boolean {
        return withContext(dispatcher.io) {
            val task = suspendCancellableCoroutine {
                if (!validateLoginUserInfoUseCase.execute(income)) {
                    it.cancel(IncorrectUserInfoException())
                    return@suspendCancellableCoroutine
                }
                fireBaseRepository.loginUser(income.toDTO())
                    .addOnCompleteListener { task ->
                        if (task.exception != null) {
                            it.cancel(task.exception)
                            return@addOnCompleteListener
                        }
                        it.resume(task)
                    }
            }
            return@withContext task.isSuccessful
        }
    }
}