package com.cme.projectcme.signup.ui.verification

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.cme.cmekotlin.components.CustomInputField
import com.cme.cmekotlin.ui.theme.softGreen
import java.util.*

@Composable
fun VerificationView(
    onVerify: () -> Unit,
    onSkip: () -> Unit,
    viewModel: VerificationViewModel = hiltViewModel()
) {
    val firstName = viewModel.firstName
    val lastName = viewModel.lastName
    val dob = viewModel.dob
    val address = viewModel.address
    val stateVal = viewModel.stateVal
    val zipCode = viewModel.zipCode
    val agreed = viewModel.agreed
    val states = viewModel.states

    var showDatePicker by remember { mutableStateOf(false) }
    var showStateDialog by remember { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }

    if (showDatePicker) {
        val ctx = LocalContext.current
        val c = Calendar.getInstance()
        DatePickerDialog(
            ctx,
            { _, y, m, d ->
                viewModel.dob = "%02d/%02d/%04d".format(m + 1, d, y)
                showDatePicker = false
            },
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (showStateDialog) {
        Dialog(onDismissRequest = { showStateDialog = false }) {
            Surface(
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                color = Color(0xFF1E1E1E),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp)
            ) {
                Column(
                    Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    states.forEach { option ->
                        Text(
                            text = option.stateCode,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.onStateSelected(option.stateCode)
                                    showStateDialog = false
                                }
                                .padding(16.dp)
                        )
                        Divider(color = Color.Gray, thickness = 0.5.dp)
                    }
                }
            }
        }
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            title = { Text("Verification Submitted") },
            text = {
                Text(
                    "Thank you! Your information has been submitted. " +
                            "We will verify your account within 1–3 business days."
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    showAlert = false
                    onVerify()
                }) {
                    Text("OK")
                }
            }
        )
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Box(Modifier.fillMaxWidth()) {
            Row(
                Modifier.align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    Modifier
                        .width(40.dp)
                        .height(4.dp)
                        .background(softGreen)
                )
                Box(
                    Modifier
                        .width(40.dp)
                        .height(4.dp)
                        .background(Color.Gray)
                )
            }
            Text(
                "Skip",
                color = softGreen,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { onSkip() }
            )
        }

        Spacer(Modifier.height(24.dp))

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Icon(Icons.Filled.Verified, null, tint = softGreen, modifier = Modifier.size(48.dp))
        }

        Spacer(Modifier.height(16.dp))

        Text(
            "Verify Your Account",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(24.dp))

        Text("Personal Info (Must Match Gov’t Issued I.D)", color = Color.LightGray, fontSize = 12.sp)
        Spacer(Modifier.height(8.dp))

        CustomInputField(firstName, { viewModel.firstName = it }, "First Name")
        Spacer(Modifier.height(12.dp))
        CustomInputField(lastName, { viewModel.lastName = it }, "Last Name")
        Spacer(Modifier.height(16.dp))

        Text("Date of Birth", color = Color.LightGray, fontSize = 12.sp)
        Spacer(Modifier.height(8.dp))
        CustomInputField(
            dob, {},
            "Date of Birth",
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Filled.CalendarToday, null, tint = softGreen)
                }
            }
        )
        Spacer(Modifier.height(16.dp))

        Text("Address", color = Color.LightGray, fontSize = 12.sp)
        Spacer(Modifier.height(8.dp))
        CustomInputField(address, { viewModel.address = it }, "Address")
        Spacer(Modifier.height(16.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(Modifier.weight(1f)) {
                CustomInputField(
                    stateVal, {},
                    "State",
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showStateDialog = true }) {
                            Icon(Icons.Filled.ArrowDropDown, null, tint = Color.Gray)
                        }
                    }
                )
            }
            Box(Modifier.weight(1f)) {
                CustomInputField(zipCode, { viewModel.zipCode = it }, "Zip Code", keyboardType = KeyboardType.Number)
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = agreed,
                onCheckedChange = { viewModel.onAgreeChange(it) },
                colors = CheckboxDefaults.colors(checkedColor = softGreen)
            )
            Text(
                "I confirm that my personal information is accurate, providing false information will result in a failed verification and restrict participation in contests.",
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.submitVerification()
                showAlert = true
            },
            enabled = agreed,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (agreed) softGreen else Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            Text("Verify Account")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VerificationViewPreview() {
    VerificationView(onVerify = {}, onSkip = {})
}
