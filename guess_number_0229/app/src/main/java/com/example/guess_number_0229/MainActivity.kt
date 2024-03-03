package com.example.guess_number_0229

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val result_textView = findViewById<TextView>(R.id.result_textview)
        val guess_button = findViewById<Button>(R.id.guess_button)
        val reset_button = findViewById<Button>(R.id.reset_button)
        val editText = findViewById<EditText>(R.id.editText)

        var minRange : Int = 1              // 先設定 最小值 與 最大值
        var maxRange : Int = 100            // 之後會將 最小值 最大值 當成 範圍標準
        var secret : Int = Random().nextInt(100) + 1  // 系統會隨機給出 1~100 之間的正確數字

        guess_button.setOnClickListener{ // 當按下 guess 按鈕後 會執行以下內容
            val guess: Int = editText.text.toString().toInt() // 將用戶所猜數轉成整數值
            var ans_str : String                              // 此值為 最後結果 (範圍值 or 是否猜對)

            if (guess > secret) {                        //若guess > secret 最大值即變成guess
                maxRange = guess
                ans_str = "介於 $minRange ~ $guess "     // $minRange $guess 是Kotlin中的字串插值語法
            }else if(guess < secret) {                  //若guess < secret 最小值即變成guess
                minRange = guess
                ans_str="介於 $guess ~ $maxRange"
            }
            else{
                ans_str="你猜對了喔!"
            }
            textView.text = ans_str
        }

        reset_button.setOnClickListener {   // 須重設 最大最小值 與 終極密碼
            minRange = 1
            maxRange = 100
            secret = Random().nextInt(100) + 1
            textView.text = "我們再猜一次"
        }
    }
}
