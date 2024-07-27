package com.molyavin.expensetracker.presentation.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.AuthFooter
import com.molyavin.expensetracker.design_system.DefaultButton
import com.molyavin.expensetracker.design_system.DefaultEmailField
import com.molyavin.expensetracker.design_system.DefaultImageLogo
import com.molyavin.expensetracker.design_system.DefaultPasswordField
import com.molyavin.expensetracker.design_system.DefaultSocialAuthButton
import com.molyavin.expensetracker.design_system.DefaultText
import com.molyavin.expensetracker.design_system.DividerOr
import com.molyavin.expensetracker.design_system.IconSize
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.BaseScreen
import com.molyavin.expensetracker.presentation.BaseSettingsScreen
import com.molyavin.expensetracker.presentation.ObserveLifecycleEvents

private val viewModel: RegistrationViewModel = Injector.INSTANCE.provideRegistrationViewModel()

@Composable
fun RegistrationScreen(navController: NavController) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordConfirm by viewModel.passwordConfirm.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    BaseSettingsScreen(isLoading = isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            DefaultImageLogo(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(Spacing.L)
                    .size(IconSize.XXL),
                idImage = R.drawable.app_icon
            )
            DefaultText(text = stringResource(id = R.string.text_registration))


            DefaultEmailField(
                modifier = Modifier
                    .padding(Spacing.XS)
                    .weight(1f),
                email = email,
                onValueChange = { viewModel.setPhone(it) },
                label = stringResource(id = R.string.label_email),
                hint = stringResource(id = R.string.email_field_hint),
            )


            DefaultPasswordField(
                modifier = Modifier
                    .padding(Spacing.XS)
                    .weight(1f),
                password = password,
                onValueChange = { viewModel.setPasswordOne(it) },
                label = stringResource(id = R.string.label_password),
                hint = stringResource(id = R.string.password_field_hint),
                focusColor = AppTheme.colors.onBackground.grey,
                unFocusColor = AppTheme.colors.onBackground.lightGrey,
            )

            DefaultPasswordField(
                modifier = Modifier
                    .padding(Spacing.XS)
                    .weight(1f),
                password = passwordConfirm,
                onValueChange = { viewModel.setPasswordTwo(it) },
                label = stringResource(id = R.string.label_password),
                hint = stringResource(id = R.string.password_confirm_field_hint),
            )

            DividerOr()

            Row {
                DefaultSocialAuthButton(imageId = R.drawable.google_icone)
                DefaultSocialAuthButton(imageId = R.drawable.facebook_icon)
            }

            DefaultButton(
                modifier = Modifier
                    .padding(start = Spacing.M, end = Spacing.M, bottom = Spacing.S, top = 80.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.text_btn_create_account),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = Spacing.S)
                            .size(IconSize.XS),
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null,
                        tint = Color.White,
                    )
                },
                onClick = { viewModel.registration(navController) },
            )

            AuthFooter(
                modifier = Modifier.padding(bottom = Spacing.S),
                text = stringResource(id = R.string.text_reg_footer_log_in_account),
                textButton = stringResource(id = R.string.text_reg_footer_btn),
                onClick = { viewModel.startAuthScreen(navController) },
            )
        }
    }
}