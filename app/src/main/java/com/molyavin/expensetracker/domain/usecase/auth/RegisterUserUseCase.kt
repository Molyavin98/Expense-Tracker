package com.molyavin.expensetracker.domain.usecase.auth

import com.molyavin.expensetracker.data.local.dto.toDTO
import com.molyavin.expensetracker.data.repository.UserRepository
import com.molyavin.expensetracker.domain.model.NewUserVM
import com.molyavin.expensetracker.domain.usecase.base.IAsyncUseCase
import com.molyavin.expensetracker.utils.AppDispatchers
import com.molyavin.expensetracker.utils.IncorrectUserInfoException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume

class RegisterUserUseCase @Inject constructor(
    private val validateRegisterUserInfoUseCase: ValidateRegisterUserInfoUseCase,
    private val fireBaseRepository: UserRepository,
    private val dispatchers: AppDispatchers
) : IAsyncUseCase<NewUserVM, Boolean> {

    override suspend fun execute(income: NewUserVM): Boolean {
        return withContext(dispatchers.io) {
            suspendCancellableCoroutine {
                if (!validateRegisterUserInfoUseCase.execute(income)) {
                    it.cancel(IncorrectUserInfoException())
                    return@suspendCancellableCoroutine
                }
                fireBaseRepository.registerUser(income.toDTO())
                    .addOnCompleteListener { task ->
                        if (task.exception != null) {
                            it.cancel(task.exception)
                            return@addOnCompleteListener
                        }
                        it.resume(task.isSuccessful)
                    }
            }
        }
    }
}