package com.kornel_ius.a8puzzle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : AppCompatActivity() {

    private var games: ArrayList<Game> = ArrayList()
    private val database: MyDBHandler = MyDBHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        games = database.getGames()
        ranking_recycler_view.layoutManager = LinearLayoutManager(this)

        ranking_recycler_view.adapter = GamesAdapter(this, games)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
