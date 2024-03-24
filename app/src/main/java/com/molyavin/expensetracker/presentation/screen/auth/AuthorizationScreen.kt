package com.molyavin.expensetracker.presentation.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.AuthFooter
import com.molyavin.expensetracker.design_system.DefaultButton
import com.molyavin.expensetracker.design_system.DefaultCheckBox
import com.molyavin.expensetracker.design_system.DefaultEmailField
import com.molyavin.expensetracker.design_system.DefaultImageLogo
import com.molyavin.expensetracker.design_system.DefaultPasswordField
import com.molyavin.expensetracker.design_system.DefaultSocialAuthButton
import com.molyavin.expensetracker.design_system.DefaultText
import com.molyavin.expensetracker.design_system.DividerOr
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.BaseScreen

class AuthorizationScreen : BaseScreen() {

    override val viewModel: AuthorizationViewModel by lazy {
        Injector.INSTANCE.provideAuthorizationViewModel()
    }

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            DefaultImageLogo(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(24.dp),
                idImage = R.drawable.app_icon
            )

            DefaultText(text = stringResource(id = R.string.welcome_app_text))

            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            val statusCheckBox by viewModel.statusCheckBox.collectAsState()

            DefaultEmailField(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(50f),
                email = email,
                onValueChange = { newPhone -> viewModel.setPhone(newPhone) },
                label = stringResource(id = R.string.label_email),
                hint = stringResource(id = R.string.email_field_hint),
            )

            DefaultPasswordField(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(50f),
                password = password,
                onValueChange = { newPassword -> viewModel.setPassword(password = newPassword) },
                label = stringResource(id = R.string.label_password),
                textForgotPassword = stringResource(id = R.string.text_btn_forgot_password),
                hint = stringResource(id = R.string.password_field_hint),
            )

            DefaultCheckBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                checkBoxState = statusCheckBox,
                onValueChange = { viewModel.setStatusCheckBox(status = it) },
                text = stringResource(id = R.string.checkbox_remember_me)
            )

            DividerOr()

            Row {
                DefaultSocialAuthButton(imageId = R.drawable.google_icone)
                DefaultSocialAuthButton(imageId = R.drawable.facebook_icon)
            }

            DefaultButton(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 80.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.text_btn_log_in),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(16.dp),
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null,
                        tint = Color.White,
                    )
                },
                onClick = {
                    viewModel.login()
                },
            )

            AuthFooter(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(id = R.string.text_auth_footer_reg_account),
                textButton = stringResource(id = R.string.text_auth_footer_btn),
                onClick = { viewModel.startRegistration() },
            )
        }
    }
}