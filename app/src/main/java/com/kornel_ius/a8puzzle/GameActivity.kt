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
import android.graphics.drawable.TransitionDrawable
import android.graphics.drawable.ColorDrawable




class GameActivity : AppCompatActivity() {

    private val array = ArrayList<ImageView>()
    private var tileSize: Int = 0
    private var time: Int = 0
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
            setTime()
//            val color = arrayOf(resources.getDrawable(R.drawable.sunset), resources.getDrawable(R.drawable.images))
//            val trans = TransitionDrawable(color)
//            tile_0.background = trans
//            trans.startTransition(3000)
//
//            val color2 = arrayOf(resources.getDrawable(R.drawable.images), resources.getDrawable(R.drawable.sunset))
//            val trans2 = TransitionDrawable(color2)
//            tile_1.background = trans2
//            trans2.startTransition(3000)
        }
    }

    private fun getFormatedTime(): String {
        var timeCopy = time

        var minutesString:String
        var secondsString:String

        val minutes:Int = timeCopy / 600
        minutesString = if(minutes < 10){
            "0" + minutes.toString()
        } else {
            minutes.toString()

        }
        timeCopy %= 600

        val seconds = timeCopy / 10
        secondsString = if(seconds < 10){
            "0" + seconds.toString()
        } else {
            seconds.toString()

        }
        timeCopy %= 10

        val finalTime:String = "$minutesString:$secondsString.$timeCopy"

        return finalTime
    }

    private fun setTime() {
        timer_text_view.text = getFormatedTime()

    }

    private fun startTimer() {
        handler = Handler()
        handler.postDelayed(runnable,300)
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
