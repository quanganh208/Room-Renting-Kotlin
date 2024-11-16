import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.itptit.roomrenting.R
import com.itptit.roomrenting.presentation.auth.rentalhouse.LabelWithAsterisk
import com.itptit.roomrenting.presentation.navgraph.Route

@Composable
fun AddressLocationScreen(navController: NavController) {
    var province by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var ward by remember { mutableStateOf("") }
    var detailedAddress by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Địa chỉ",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { /* Placeholder to balance layout */ }) {
                Spacer(modifier = Modifier.size(24.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Warning Box
        WarningBox()

        Spacer(modifier = Modifier.height(16.dp))

        // Input fields
        LabelWithAsterisk("Tỉnh/Thành phố")
        OutlinedTextField(
            value = province,
            onValueChange = { province = it },
            placeholder = { Text("Nhập tỉnh/thành phố") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        LabelWithAsterisk("Quận/Huyện")
        OutlinedTextField(
            value = district,
            onValueChange = { district = it },
            placeholder = { Text("Nhập quận/huyện") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        LabelWithAsterisk("Phường/Xã")
        OutlinedTextField(
            value = ward,
            onValueChange = { ward = it },
            placeholder = { Text("Nhập phường/xã") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        LabelWithAsterisk("Địa chỉ chi tiết")
        OutlinedTextField(
            value = detailedAddress,
            onValueChange = { detailedAddress = it },
            placeholder = { Text("Nhập số nhà, hẻm, đường") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Instructions and Buttons
        Text(
            text = "Định vị vị trí nhà cho thuê trên bản đồ giúp cho người tìm trọ tìm thấy bạn. Tăng tỷ lệ tiếp cận khách thuê.",
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Nút "Lấy vị trí trên bản đồ"
        Button(
            onClick = { /* TODO: Handle Map Location */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)), // Màu xám nhẹ
            shape = RoundedCornerShape(6.dp) // Bo nhẹ góc giống nút "Đóng"
        ) {
            Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Lấy vị trí trên bản đồ",
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách nhỏ giữa các nút

        // Nút "Xác nhận địa chỉ & tiếp tục"
        Button(
            onClick = { /* TODO: Handle Confirm Address */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Màu xanh lá cây
            shape = RoundedCornerShape(6.dp) // Bo nhẹ góc giống nút "Tiếp theo"
        ) {
            Text(
                text = "Xác nhận địa chỉ & tiếp tục",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun WarningBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(width = 1.dp, color = Color(0xFFFFA000), shape = RoundedCornerShape(4.dp))
            .background(Color(0xFFFFF8E1))
            .padding(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "Warning",
            tint = Color(0xFFFFA000),
            modifier = Modifier.size(20.dp).align(Alignment.Top)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = "Thông tin:",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = "Nếu địa chỉ của bạn không có trong danh sách bên dưới. Vui lòng liên hệ với nhân viên để được hỗ trợ!",
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}