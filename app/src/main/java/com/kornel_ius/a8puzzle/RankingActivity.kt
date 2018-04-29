package com.kornel_ius.a8puzzle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : AppCompatActivity() {

    private var games: ArrayList<Game> = ArrayList()
    private val database: MyDBHandler = MyDBHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = resources.getString(R.string.ranking)

        games = database.getGames()

        if(games.size == 0){
            no_records_text_view.visibility = View.VISIBLE
            ranking_recycler_view.visibility = View.GONE
        } else {
            no_records_text_view.visibility = View.GONE
            ranking_recycler_view.visibility = View.VISIBLE

            ranking_recycler_view.layoutManager = LinearLayoutManager(this)
            ranking_recycler_view.adapter = GamesAdapter(this, games)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
