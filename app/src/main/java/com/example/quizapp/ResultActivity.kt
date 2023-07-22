package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
        val tv_Name=findViewById<TextView>(R.id.tv_name)
        val username=intent.getStringExtra(Constants.USER_NAME)
        tv_Name.text=username
        val totalQuestions=intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswer=intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        val tv_Score=findViewById<TextView>(R.id.tv_score)
        tv_Score.text="Your Score is $correctAnswer out of $totalQuestions"

        val btn_Finish=findViewById<Button>(R.id.btn_finish)
        btn_Finish.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
}