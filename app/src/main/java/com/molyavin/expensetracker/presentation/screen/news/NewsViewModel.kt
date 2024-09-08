package com.molyavin.expensetracker.presentation.screen.news

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.molyavin.expensetracker.domain.usecase.news.GetNewsLinkUseCase
import com.molyavin.expensetracker.presentation.BaseViewModel
import com.molyavin.expensetracker.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getNewsLinkUseCase: GetNewsLinkUseCase,
    toaster: Toaster
) : BaseViewModel(toaster) {

    private val _link: MutableStateFlow<String> = MutableStateFlow(String())
    val link: StateFlow<String> = _link

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModelScope.launch {
            val link = getNewsLinkUseCase.execute(Unit)
            _link.value = link.url
        }
    }


}