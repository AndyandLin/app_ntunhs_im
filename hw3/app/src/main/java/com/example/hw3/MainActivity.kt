package com.example.hw3

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // 取得 EditText 輸入框中的 ID
    val ID = findViewById<EditText>(R.id.id_edit)

    // 取得 EditText 輸入框中的 PWD（密碼）
    val PWD = findViewById<EditText>(R.id.pwd_edit)
    PWD.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    // 設置為密碼輸入

    // 取得 EditText 輸入框中的 Name
    val Name = findViewById<EditText>(R.id.name_edit)
    Name.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
    // 設置為多行文本

    // 點擊 TextView 以彈出 DatePickerDialog 選擇 Birthday
    val applyDate = findViewById<TextView>(R.id.applydate)
    applyDate.setOnClickListener {
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, { _, year, month, day ->
            run {
                var format = "${setDateFormat(year, month, day)}"
                applyDate.setText(format)  // 更新 TextView 的文字內容為選擇的日期
            }
        }, year, month, day).show()
    }

    // 選擇 Gender 的 RadioButton（單選按鈕）的 RadioGroup
    val radGrp_Gender = findViewById<RadioGroup>(R.id.radGrpGender)
    var gender = "0"  // 默認值為 "性別"
        radGrp_Gender.setOnCheckedChangeListener { _, checkedId ->
            gender = radGrp_Gender.findViewById<RadioButton>(checkedId).text.toString()
            // 獲取選中的 RadioButton 的文字
        }

    // 複選框（CheckBox）選擇 Vencile，並彈出 Dialog 顯示選擇的內容
    val chkbox1 = findViewById<CheckBox>(R.id.ckb1)
    val chkbox2 = findViewById<CheckBox>(R.id.ckb2)
    val chkbox3 = findViewById<CheckBox>(R.id.ckb3)
    val btn_send = findViewById<Button>(R.id.btn_send)
        btn_send.setOnClickListener {
            val id = ID.text.toString()                 // 獲取 ID 輸入框中的內容
            val pwd = PWD.text.toString()               // 獲取 PWD 輸入框中的內容
            val name = Name.text.toString()             // 獲取 Name 輸入框中的內容
            val birthday = applyDate.text.toString()    // 獲取選擇的 Birthday
            val genderText = gender                     // 獲取選擇的 Gender
            val checkBoxes = mutableListOf<String>()    // 創建用於儲存選中複選框的列表
            if (chkbox1.isChecked) {
                checkBoxes.add(chkbox1.text.toString()) // 如果複選框 1 被選中，將其文字加入到列表中
            }
            if (chkbox2.isChecked) {
                checkBoxes.add(chkbox2.text.toString()) // 如果複選框 2 被選中，將其文字加入到列表中
            }
            if (chkbox3.isChecked) {
                checkBoxes.add(chkbox3.text.toString()) // 如果複選框 3 被選中，將其文字加入到列表中
            }

            // 構建顯示用戶選擇內容的訊息
            val message = buildString {
                append("ID: $id\n")
                append("PWD: $pwd\n")
                append("Name: $name\n")
                append("Birthday: $birthday\n")
                append("Gender: $genderText\n")
                if (checkBoxes.isNotEmpty()) {
                    append("Vencile: ")
                    append(checkBoxes.joinToString(separator = ", "))  // 以逗號分隔並連接複選框選中的文字
                }
            }

            // 顯示 AlertDialog 彈窗來確認變更資料
            AlertDialog.Builder(this)
                .setTitle("確認變更資料")
                .setMessage(message)
                .setPositiveButton("確定") { dialog, _ ->
                    dialog.dismiss()
                    // 在這裡可以加上確定按鈕的其他邏輯處理
                }
                .setNegativeButton("取消") { dialog, _ ->
                    dialog.dismiss()
                    // 在這裡可以加上取消按鈕的其他邏輯處理
                }
                .create()
                .show()
        }

    }

    // 將日期格式化為"YYYY-MM-DD"形式
    private  fun setDateFormat(year: Int,month: Int, day: Int): String {
        return "$year-${month   +1}-$day"
    }
}