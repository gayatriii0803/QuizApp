package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity()  ,OnClickListener{
    private var mCurrentPosition:Int=1
    private var mQuestionsList:ArrayList<Question>?=null
    private var mSelectedOptionPosition:Int=0
    private var mCorrectAnswer:Int=0
    private lateinit var option_One:TextView
    private lateinit var option_Two:TextView
    private lateinit var option_Three:TextView
    private lateinit var option_Four:TextView
    private lateinit var  btn_Submit:Button
    private var mUserName:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        mUserName=intent.getStringExtra(Constants.USER_NAME)
       mQuestionsList=Constants.getQuestions()
        setQuestion()
        option_One.setOnClickListener(this)
        option_Two.setOnClickListener(this)
        option_Three.setOnClickListener(this)
        option_Four.setOnClickListener(this)
        btn_Submit.setOnClickListener(this)


    }
    private fun setQuestion(){

        val question=mQuestionsList!![mCurrentPosition-1]
//        defaultOptionsView()


        val progressbar=findViewById<ProgressBar>(R.id.progressBar)
        progressbar.progress=mCurrentPosition
        val tv_Progress=findViewById<TextView>(R.id.tv_progress)
        tv_Progress.text="$mCurrentPosition"+"/"+progressbar.max
        val tv_Question=findViewById<TextView>(R.id.tv_question)
        tv_Question.text=question!!.question
        val iv_Image=findViewById<ImageView>(R.id.iv_image)
        iv_Image.setImageResource(question.image)
        option_One=findViewById<TextView>(R.id.tv_option_one)
        option_One.text=question.optionOne
         option_Two=findViewById<TextView>(R.id.tv_option_two)
        option_Two.text=question.optionTwo
        option_Three=findViewById<TextView>(R.id.tv_option_three)
        option_Three.text=question.optionThree
        option_Four=findViewById<TextView>(R.id.tv_option_four)
        option_Four.text=question.optionFour
        btn_Submit=findViewById<Button>(R.id.btn_submit)
        defaultOptionsView()
        if(mCurrentPosition==mQuestionsList!!.size){
            btn_Submit.text="FINISH"
        }
        else{
            btn_Submit.text="SUBMIT"
        }
    }
    private fun defaultOptionsView(){
        val options=ArrayList<TextView>()
        options.add(0,option_One)
        options.add(1,option_Two)
        options.add(2,option_Three)
        options.add(3,option_Four)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,
                R.drawable.aw

                )
        }
    }
    override fun onClick(v:View?){


        when(v?.id){
            R.id.tv_option_one->{
                selectedOptionView(option_One,1)
            }
            R.id.tv_option_two->{
                selectedOptionView(option_Two,2)
            }
            R.id.tv_option_three->{
                selectedOptionView(option_Three,3)
            }
            R.id.tv_option_four->{
                selectedOptionView(option_Four,4)
            }
            R.id.btn_submit-> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent= Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList!!.size)
                            startActivity(intent)

                        }
                    }


                }
                else{
                    val question=mQuestionsList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer!=mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong)
                    }
                    else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.correct)

                    if(mCurrentPosition==mQuestionsList!!.size){
                        btn_Submit.text="FINISH"
                    }
                    else{
                        btn_Submit.text="GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition=0

                }
            }

        }

    }
    private fun answerView(answer:Int,drawableView:Int){
        when(answer){
            1->{
                option_One.background=ContextCompat.getDrawable(this,drawableView)



            }
            2->{
                option_Two.background=ContextCompat.getDrawable(this,drawableView)



            }
            3->{
                option_Three.background=ContextCompat.getDrawable(this,drawableView)



            }
            4->{
                option_Four.background=ContextCompat.getDrawable(this,drawableView)



            }
        }

    }
    private fun selectedOptionView(tv:TextView,selectedOptionNum:Int){
        defaultOptionsView()
        mSelectedOptionPosition=selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
       tv.background=ContextCompat.getDrawable(this,
            R.drawable.selected)
    }

}