package com.molyavin.expensetracker.presentation.screen.setting

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.ButtonBack
import com.molyavin.expensetracker.design_system.ButtonTransparent
import com.molyavin.expensetracker.design_system.CurrencyText
import com.molyavin.expensetracker.design_system.DefaultButton
import com.molyavin.expensetracker.design_system.Divider
import com.molyavin.expensetracker.design_system.IconSize
import com.molyavin.expensetracker.design_system.Scaffold
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.BaseSettingsScreen
import com.molyavin.expensetracker.presentation.ObserveLifecycleEvents
import com.molyavin.expensetracker.presentation.bottom_sheet_dialog.currency.CurrencyBottomSheetDialog

private val viewModel: SettingViewModel = Injector.INSTANCE.provideSettingViewModel()

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingScreen(navController: NavController) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)
    val isLoading by viewModel.isLoading.collectAsState()
    var showSheet by remember { mutableStateOf(false) }

    val currencyDollar by viewModel.currencyDollar.collectAsState()
    val currencyEuro by viewModel.currencyEuro.collectAsState()

    BaseSettingsScreen(
        isLoading = isLoading,
        navController = navController,
        showBottomNavBar = false
    ) {
        Scaffold(
            topBar = {
                ButtonBack(
                    modifier = Modifier.padding(Spacing.M),
                    onClick = { navController.popBackStack() }
                )
            },
            bottomBar = {
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.M),
                    text = stringResource(id = R.string.exit_from_account),
                    onClick = { viewModel.logOut(navController = navController) }
                )
            }
        ) {
            LazyColumn(modifier = Modifier.padding(Spacing.M)) {

                item {
                    if (currencyDollar.isNotEmpty() || currencyEuro.isNotEmpty()) {
                        CurrencyText(
                            modifier = Modifier.animateItemPlacement(),
                            dollar = currencyDollar,
                            euro = currencyEuro
                        )
                        Spacer(modifier = Modifier.height(Spacing.XL))
                    }

                    ButtonTransparent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(),
                        text = "Change currency",
                        trailingIcon = {
                            Icon(
                                modifier = Modifier.size(IconSize.XS),
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                            )
                        },
                        onClick = { showSheet = true }
                    )
                }
            }

            if (showSheet) {
                CurrencyBottomSheetDialog(
                    onClickSave = { showSheet = false },
                    onDismiss = { showSheet = false },
                )
            }
        }
    }
}

