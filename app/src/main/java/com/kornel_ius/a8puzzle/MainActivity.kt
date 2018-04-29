package com.kornel_ius.a8puzzle

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        // hide keyboard
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        start_game_layout.setOnClickListener { startGame() }
        show_ranking_layout.setOnClickListener { showRanking() }

    }

    private fun startGame() {
        startActivity(Intent(this, PreGameActivity::class.java))
    }

    private fun showRanking() {
        startActivity(Intent(this, RankingActivity::class.java))
    }
}
