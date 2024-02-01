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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.molyavin.expensetracker.design_system.DefaultText
import com.molyavin.expensetracker.design_system.DividerOr
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.AuthFooter
import com.molyavin.expensetracker.design_system.DefaultButton
import com.molyavin.expensetracker.design_system.DefaultEmailField
import com.molyavin.expensetracker.design_system.DefaultImageLogo
import com.molyavin.expensetracker.design_system.DefaultPasswordField
import com.molyavin.expensetracker.design_system.DefaultSocialAuthButton
import com.molyavin.expensetracker.design_system.RememberMeCheckBox
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.screen.BaseActivity
import com.molyavin.expensetracker.presentation.viewmodel.auth.AuthorizationViewModel

class AuthorizationScreen : BaseActivity() {

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

            DefaultText(text = "Welcome in Expense Tracker")

            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            val statusCheckBox by viewModel.statusCheckBox.collectAsState()

            DefaultEmailField(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(50f),
                email = email,
                onValueChange = { newPhone -> viewModel.setPhone(newPhone) },
                label = "Email",
                hint = "Enter your email",
            )

            DefaultPasswordField(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(50f),
                password = password,
                onValueChange = { newPassword -> viewModel.setPassword(password = newPassword) },
                label = "Password",
                textForgotPassword = "Forgot password?",
                hint = "Enter your password",
            )

            RememberMeCheckBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                checkBoxState = statusCheckBox,
                onValueChange = { viewModel.setStatusCheckBox(status = it) },
                text = "Remember me"
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
                text = "Log in",
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
                text = "Don`t have an account?",
                textButton = "Sing up now.",
                onClick = { viewModel.startRegistration() },
            )
        }
    }
}