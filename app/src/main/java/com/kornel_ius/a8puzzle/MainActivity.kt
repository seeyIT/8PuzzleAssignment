package com.kornel_ius.a8puzzle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

    }

    fun play(view: View) {
        startActivity(Intent(this,PreGameActivity::class.java))
    }

    fun showRanking(view: View) {
        startActivity( Intent(this,RankingActivity::class.java))
    }
}
