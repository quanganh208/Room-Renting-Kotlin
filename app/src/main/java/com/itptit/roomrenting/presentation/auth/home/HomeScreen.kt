package com.itptit.roomrenting.presentation.auth.home

import com.itptit.roomrenting.presentation.auth.register.RegisterViewModel

import FullScreenLoadingModal
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun RegisterScreen(
    navController: NavController,
    onRegisterSuccess: () -> Unit,
    viewModel: RegisterViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordTouched by remember { mutableStateOf(false) }
    var confirmPasswordTouched by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    var isCountingDown by remember { mutableStateOf(false) }
    var countdownTime by remember { mutableStateOf(3) }

    val isLoading by viewModel.isLoading.collectAsState()
    val isButtonEnabled = username.isNotEmpty() && password.length >= 6 && confirmPassword.length >= 6
    val focusManager = LocalFocusManager.current

    FullScreenLoadingModal(isVisible = isLoading)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable(
                onClick = {
                    focusManager.clearFocus()
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Column {
            // Header with Back Image and Title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 48.dp, bottom = 16.dp), // Điều chỉnh padding để nút Back nằm thấp hơn
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_regis_icon), // Sử dụng hình ảnh back_regis_icon từ drawable
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(40.dp) // Kích thước icon to hơn
                        .padding(12.dp) // Thêm padding để dễ nhấn hơn
                        .clickable {
                            navController.popBackStack() // Quay lại màn hình trước đó
                        }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Nội dung màn hình
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Username field
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextFieldWithLabel(
                        label = "Tên đăng nhập",
                        placeholder = "Nhập vào tên đăng nhập",
                        keyboardType = KeyboardType.Text,
                        text = username,
                        onTextChange = { username = it },
                        labelFontSize = 16f
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Password field
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextFieldWithLabel(
                        label = "Mật khẩu",
                        placeholder = "Nhập mật khẩu",
                        keyboardType = KeyboardType.Password,
                        text = password,
                        onTextChange = {
                            password = it
                            passwordTouched = true
                            errorMessage = if (passwordTouched && it.length < 6) "Mật khẩu tối thiểu 6 ký tự" else ""
                        },
                        labelFontSize = 16f,
                        isPassword = true
                    )
                    if (passwordTouched && password.length < 6) {
                        Text(
                            text = "Mật khẩu tối thiểu 6 ký tự",
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Confirm Password field
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextFieldWithLabel(
                        label = "Nhập lại mật khẩu",
                        placeholder = "Nhập lại mật khẩu",
                        keyboardType = KeyboardType.Password,
                        text = confirmPassword,
                        onTextChange = {
                            confirmPassword = it
                            confirmPasswordTouched = true
                            errorMessage = if (confirmPasswordTouched && it.length < 6) "Mật khẩu tối thiểu 6 ký tự" else ""
                        },
                        labelFontSize = 16f,
                        isPassword = true
                    )
                    if (confirmPasswordTouched && confirmPassword.length < 6) {
                        Text(
                            text = "Mật khẩu tối thiểu 6 ký tự",
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Register Button
                Button(
                    onClick = {
                        if (password != confirmPassword) {
                            errorMessage = "Mật khẩu không khớp"
                            successMessage = ""
                        } else {
                            errorMessage = ""
                            successMessage = "Đăng ký thành công! Chuyển qua đăng nhập"
                            isCountingDown = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isButtonEnabled) Color(0xFF0B9E43) else Color(0xFFA5D6A7),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp),
                ) {
                    Text(
                        text = "Đăng ký",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Error or success message
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                } else if (successMessage.isNotEmpty()) {
                    Text(
                        text = successMessage,
                        color = Color.Green,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Countdown logic
                if (isCountingDown) {
                    LaunchedEffect(Unit) {
                        while (countdownTime > 0) {
                            kotlinx.coroutines.delay(1000L)
                            countdownTime--
                            successMessage = "Đăng ký thành công! Chuyển qua đăng nhập sau $countdownTime giây."
                        }
                        onRegisterSuccess()
                        navController.navigate(Route.LoginScreen.route)
                    }
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