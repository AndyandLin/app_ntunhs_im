package com.example.hw_mora

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //延遲以下元件的初始化(在 onCreate 方法中初始化 UI 元件)並且僅在類內部可見
    private lateinit var txtCom: TextView
    private lateinit var txtResult: TextView
    private lateinit var imageView: ImageView
    private lateinit var imabtn_scissor: ImageButton
    private lateinit var imabtn_rock: ImageButton
    private lateinit var imabtn_paper: ImageButton

    // enum(列舉)類型：剪刀、石頭、布(從0開始)
    enum class Choice{
        SCISSORS,ROCK,PAPER
    }

    // 遊戲運行的函數，根據玩家的選擇進行遊戲
    fun playGame(playerChoice: Choice){
        val choices = Choice.values()  // val 不可變量

        // 電腦的選擇用Random
        var computerChoice = choices[Random.nextInt(choices.size)]  //var 可變量

        // 根據電腦的選擇給予相應的圖片
        when (computerChoice) {
            Choice.SCISSORS -> imageView.setImageResource(R.drawable.scissor)
            Choice.ROCK -> imageView.setImageResource(R.drawable.rock)
            Choice.PAPER -> imageView.setImageResource(R.drawable.paper)
        }

        // 判斷遊戲結果並顯示在畫面上
        when{
            playerChoice == computerChoice ->{
                txtCom.setText(getChoiceString(computerChoice))
                txtResult.setText(R.string.draw)                    // 平局
            }
            (playerChoice == Choice.SCISSORS && computerChoice == Choice.PAPER) ||
                    (playerChoice == Choice.ROCK && computerChoice == Choice.SCISSORS) ||
                    (playerChoice == Choice.PAPER && computerChoice == Choice.ROCK) -> {
                        txtCom.setText(getChoiceString(computerChoice))
                        txtResult.setText(R.string.win)         // 玩家勝利
                    }
            else -> {
                txtCom.setText(getChoiceString(computerChoice))
                txtResult.setText(R.string.lose)                // 電腦勝利
            }
        }
    }

    // 根據choice 獲取對應的字串
    private fun getChoiceString(choice: Choice): Int {
        return when (choice){
            Choice.SCISSORS -> R.string.scissors
            Choice.ROCK -> R.string.rock
            Choice.PAPER -> R.string.paper
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化各個視圖元件
        txtCom = findViewById(R.id.txtCom)
        txtResult = findViewById(R.id.txtResult)
        imabtn_scissor = findViewById(R.id.imabtn_scissor)
        imabtn_rock = findViewById(R.id.imabtn_rock)
        imabtn_paper = findViewById(R.id.imabtn_paper)
        imageView = findViewById(R.id.imageView)

        // 點擊剪刀按鈕時進行剪刀選擇
        imabtn_scissor.setOnClickListener{
            playGame(Choice.SCISSORS)
        }
        // 點擊石頭按鈕時進行石頭選擇
        imabtn_rock.setOnClickListener{
            playGame(Choice.ROCK)
            }
        // 點擊布按鈕時進行布選擇
        imabtn_paper.setOnClickListener{
            playGame(Choice.PAPER)
        }
    }
}