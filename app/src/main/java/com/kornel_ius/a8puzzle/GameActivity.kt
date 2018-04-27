package com.kornel_ius.a8puzzle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game.*
import android.R.attr.y
import android.R.attr.x
import android.graphics.Point
import android.os.Handler
import android.view.Display
import android.view.View


class GameActivity : AppCompatActivity() {

    private val array = ArrayList<ImageView>()
    private var tileSize: Int = 0
    private var time:Long = 0
    private lateinit var handler: Handler
    private lateinit var runnable:Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar!!.hide()
        Log.e("id",        intent.extras.getInt("id").toString())
        calcTileSize()
        init()
        startTimer()
    }

    private fun init() {
        array.add(tile_0)
        array.add(tile_1)
        array.add(tile_2)
        array.add(tile_3)
        array.add(tile_4)
        array.add(tile_5)
        array.add(tile_6)
        array.add(tile_7)
        array.add(tile_8)

        tile_0.layoutParams.width = tileSize
        tile_0.layoutParams.height = tileSize
        tile_1.layoutParams.width = tileSize
        tile_1.layoutParams.height = tileSize
        tile_2.layoutParams.width = tileSize
        tile_2.layoutParams.height = tileSize
        tile_3.layoutParams.width = tileSize
        tile_3.layoutParams.height = tileSize
        tile_4.layoutParams.width = tileSize
        tile_4.layoutParams.height = tileSize
        tile_5.layoutParams.width = tileSize
        tile_5.layoutParams.height = tileSize
        tile_6.layoutParams.width = tileSize
        tile_6.layoutParams.height = tileSize
        tile_7.layoutParams.width = tileSize
        tile_7.layoutParams.height = tileSize
        tile_8.layoutParams.width = tileSize
        tile_8.layoutParams.height = tileSize


        tile_0.background = resources.getDrawable(R.drawable.sunset)
        tile_1.background = resources.getDrawable(R.drawable.images)
        tile_3.background = resources.getDrawable(R.drawable.panoramic)
        tile_2.background = resources.getDrawable(R.drawable.fantasy)
        tile_4.background = resources.getDrawable(R.drawable.flowe)

        background_image_view.background = resources.getDrawable(R.drawable.sunset)

        tile_0.setOnClickListener { stopTimer() }

        runnable = Runnable {
            time += 1
            handler.postDelayed(runnable,100)
            timer_text_view.text = time.toString()
        }
    }

    private fun startTimer() {
        handler = Handler()
        handler.postDelayed(runnable,100)
    }

    private fun stopTimer() {
        handler.removeCallbacks(runnable)
    }


    private fun calcTileSize() {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        tileSize = (size.x - 100) / 3
    }



}
