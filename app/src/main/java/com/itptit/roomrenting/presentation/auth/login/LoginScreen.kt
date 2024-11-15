package com.itptit.roomrenting.presentation.auth.login

import FullScreenLoadingModal
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.itptit.roomrenting.R
import com.itptit.roomrenting.presentation.navgraph.Route

@Composable
fun LoginScreen(
    navController: NavController,
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLoading by viewModel.isLoading.collectAsState()
    val loginResult by viewModel.loginResult.collectAsState()
    val focusManager = LocalFocusManager.current
    val isButtonEnabled = username.isNotEmpty() && password.isNotEmpty()

    FullScreenLoadingModal(isVisible = isLoading)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    focusManager.clearFocus()
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email field
            Column(modifier = Modifier.fillMaxWidth()) {
                TextFieldWithLabel(
                    label = "Tên đăng nhập",
                    placeholder = "Nhập vào tên đăng nhập",
                    keyboardType = KeyboardType.Text,
                    text = username,
                    onTextChange = {
                        username = it
                    },
                    labelFontSize = 16f
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Password field
            TextFieldWithLabel(
                label = "Mật khẩu",
                placeholder = "Nhập mật khẩu",
                keyboardType = KeyboardType.Password,
                text = password,
                onTextChange = { password = it },
                labelFontSize = 16f,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (loginResult.isNotEmpty()) {
                Text(
                    text = loginResult,
                    color = if (loginResult.startsWith("Login successful")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )

                if (loginResult.startsWith("Login successful")) {
                    onLoginSuccess()
                }
            }

            // Register account text (clickable)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Đăng ký tài khoản",
                    color = Color(0xFF0B9E43), // Màu xanh lá cây
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        navController.navigate(route = Route.RegisterScreen.route)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Login Button
            Button(
                onClick = {
                    if (isButtonEnabled) {
                        viewModel.login(username, password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) Color(0xFF0B9E43) else Color(0xFFA5D6A7), // Màu xanh lá cây nhạt khi chưa nhập đủ
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(6.dp),
            ) {
                Text(
                    text = "Đăng nhập",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Footer
            Text(
                text = "© 2024 DS",
                fontSize = 14.sp,
                color = Color(0xFF333333),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_flag),
                    contentDescription = "Flag",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Tiếng Việt", fontSize = 14.sp, color = Color(0xFF333333))
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color(0xFF333333)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Version 1.0.0 - 15112024",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Composable
fun TextFieldWithLabel(
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    text: String,
    onTextChange: (String) -> Unit,
    labelFontSize: Float,
    isPassword: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF1F5F9), shape = MaterialTheme.shapes.medium)
            .padding(8.dp)
    ) {
        Text(
            text = label,
            fontSize = labelFontSize.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
    }
}
