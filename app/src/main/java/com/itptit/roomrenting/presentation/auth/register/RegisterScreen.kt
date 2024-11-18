package com.itptit.roomrenting.presentation.auth.register

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
import kotlinx.coroutines.delay

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun RegisterScreen(
    navController: NavController,
    onRegisterSuccess: () -> Unit,
    viewModel: RegisterViewModel
) {
    var username by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordTouched by remember { mutableStateOf(false) }
    var confirmPasswordTouched by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val isLoading by viewModel.isLoading.collectAsState()
    val registerResult by viewModel.registerResult.collectAsState()
    val emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$".toRegex(RegexOption.IGNORE_CASE)
    val phoneNumberRegex = "^(\\+84|0084|0)[235789][0-9]{8}\$".toRegex(RegexOption.IGNORE_CASE)
    val isButtonEnabled =
        username.isNotEmpty() && fullName.isNotEmpty() && phoneNumber.isNotEmpty() &&
                email.isNotEmpty() && emailRegex.matches(email) && phoneNumberRegex.matches(
            phoneNumber
        ) && password.length >= 6 && confirmPassword.length >= 6 && password == confirmPassword
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState() // Thêm state cho scroll

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // Thêm tính năng cuộn
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with Back Image and Title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 48.dp,
                        bottom = 16.dp
                    ), // Tăng `top` padding để nút Back hạ xuống thấp hơn
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack() // Quay lại màn hình trước đó
                    },
                    modifier = Modifier
                        .padding(start = 0.dp) // Đảm bảo nút gần sát bên trái
                        .size(56.dp) // Tăng kích thước nút cho dễ nhấn hơn
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back_regis_icon_v2),
                        contentDescription = "Back",
                        modifier = Modifier.size(32.dp) // Tăng kích thước icon để nhìn rõ hơn
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

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
                    label = "Tên người dùng",
                    placeholder = "Nhập vào tên người dùng",
                    keyboardType = KeyboardType.Text,
                    text = username,
                    onTextChange = { username = it },
                    labelFontSize = 16f
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Full Name field
            Column(modifier = Modifier.fillMaxWidth()) {
                TextFieldWithLabel(
                    label = "Họ và tên",
                    placeholder = "Nhập vào họ và tên",
                    keyboardType = KeyboardType.Text,
                    text = fullName,
                    onTextChange = { fullName = it },
                    labelFontSize = 16f
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Phone Number field
            Column(modifier = Modifier.fillMaxWidth()) {
                TextFieldWithLabel(
                    label = "Số điện thoại",
                    placeholder = "Nhập vào số điện thoại",
                    keyboardType = KeyboardType.Phone,
                    text = phoneNumber,
                    onTextChange = { phoneNumber = it },
                    labelFontSize = 16f
                )
                if (phoneNumber.isNotEmpty() && !phoneNumberRegex.matches(phoneNumber)) {
                    Text(
                        text = "Số điện thoại không hợp lệ",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Email field
            Column(modifier = Modifier.fillMaxWidth()) {
                TextFieldWithLabel(
                    label = "Email",
                    placeholder = "Nhập vào email",
                    keyboardType = KeyboardType.Email,
                    text = email,
                    onTextChange = { email = it },
                    labelFontSize = 16f
                )
                if (email.isNotEmpty() && !emailRegex.matches(email)) {
                    Text(
                        text = "Email không hợp lệ",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }
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
                    },
                    labelFontSize = 16f,
                    isPassword = true,
                    isPasswordVisible = passwordVisible,
                    onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
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
                    },
                    labelFontSize = 16f,
                    isPassword = true,
                    isPasswordVisible = confirmPasswordVisible,
                    onPasswordVisibilityChange = {
                        confirmPasswordVisible = !confirmPasswordVisible
                    }
                )
                if (confirmPasswordTouched && confirmPassword != password) {
                    Text(
                        text = "Mật khẩu không khớp",
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
                    if (isButtonEnabled) {
                        viewModel.register(username, password, fullName, email, phoneNumber)
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

            if (registerResult.isNotEmpty()) {
                Text(
                    text = registerResult,
                    color = if (registerResult.startsWith("Đăng ký thành công")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )

                if (registerResult.startsWith("Đăng ký thành công")) {
                    LaunchedEffect(Unit) {
                        delay(2000)
                        onRegisterSuccess()
                    }
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

@Composable
fun TextFieldWithLabel(
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    text: String,
    onTextChange: (String) -> Unit,
    labelFontSize: Float,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onPasswordVisibilityChange: () -> Unit = {}
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
            visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if (isPassword) {
                    Box(
                        modifier = Modifier
                            .clickable { onPasswordVisibilityChange() }
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (isPasswordVisible) "Ẩn" else "Hiện",
                            color = Color(0xFF3B78AD),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
    }
}
