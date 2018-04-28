package com.kornel_ius.a8puzzle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game.*
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Handler
import android.widget.Toast
import java.util.*


class GameActivity : AppCompatActivity() {

    private val DURATION: Int = 500
    private val arrayOfImages = ArrayList<ImageView>()
    private val arrayOfInts = IntArray(9, {it})
    private var tileSize: Int = 0
    private var time: Int = 0
    private var emptyField: Int = 8
    private var gameStarted: Boolean = true
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar!!.hide()
        Log.e("id", intent.extras.getInt("id").toString())
        calcTileSize()
        init()
        startTimer()
    }

    private fun init() {
        arrayOfImages.add(tile_0)
        arrayOfImages.add(tile_1)
        arrayOfImages.add(tile_2)
        arrayOfImages.add(tile_3)
        arrayOfImages.add(tile_4)
        arrayOfImages.add(tile_5)
        arrayOfImages.add(tile_6)
        arrayOfImages.add(tile_7)
        arrayOfImages.add(tile_8)
        checkWin()
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


        tile_0.background = resources.getDrawable(R.drawable.land1)
        tile_1.background = resources.getDrawable(R.drawable.land2)
        tile_2.background = resources.getDrawable(R.drawable.land3)
        tile_3.background = resources.getDrawable(R.drawable.land4)
        tile_4.background = resources.getDrawable(R.drawable.land5)
        tile_5.background = resources.getDrawable(R.drawable.land6)
        tile_6.background = resources.getDrawable(R.drawable.land7)
        tile_7.background = resources.getDrawable(R.drawable.land8)
        tile_8.background = resources.getDrawable(R.drawable.land9)

        background_image_view.background = resources.getDrawable(R.drawable.sunset)

        tile_0.setOnClickListener { stopTimer() }

        runnable = Runnable {
            time += 1
            handler.postDelayed(runnable, 100)
            setTime()

        }

        tile_0.setOnClickListener { swap(0) }
        tile_1.setOnClickListener { swap(1) }
        tile_2.setOnClickListener { swap(2) }
        tile_3.setOnClickListener { swap(3) }
        tile_4.setOnClickListener { swap(4) }
        tile_5.setOnClickListener { swap(5) }
        tile_6.setOnClickListener { swap(6) }
        tile_7.setOnClickListener { swap(7) }
        tile_8.setOnClickListener { swap(8) }
//        shuffle()
    }

    private fun swap(first: Int) {
        if(!canMove(first, emptyField)) {
            return
        }

        val firstImage: Drawable = arrayOfImages[first].background
        val secondImage: Drawable = arrayOfImages[emptyField].background

        val images1 = arrayOf( firstImage, secondImage)
        val trans1 = TransitionDrawable(images1)
        arrayOfImages[first].background = trans1
        trans1.startTransition(DURATION)

        val images2 = arrayOf( secondImage,  firstImage)
        val trans2 = TransitionDrawable(images2)
        arrayOfImages[emptyField].background  = trans2
        trans2.startTransition(DURATION)

        val tempInt: Int = arrayOfInts[first]
        arrayOfInts[first] = arrayOfInts[emptyField]
        arrayOfInts[emptyField] = tempInt
        emptyField = first
        if(gameStarted){
            checkWin()
        }

    }

    private fun shuffle() {
        gameStarted = false
        val random: Random = Random()
        for( i in 0..20) {
            arrayOfImages[ random.nextInt(9)].performClick()
        }
        gameStarted = true
    }

    private fun canMove(first: Int, second: Int): Boolean {
        if (Math.abs(first - second) == 3) {
            return true
        }
        if ((first == 2 || first == 5) && (second == 3 || second == 6)) {
            return false
        }
        if ((first == 6 || first == 3) && (second == 2 || second == 5)) {
            return false
        }
        if (Math.abs(first - second) == 1) {
            return true
        }
        return false
    }

    private fun checkWin() {
        val win: Boolean = (0..8).none { arrayOfInts[it] != it }

        Log.e("win",win.toString())
    }

    private fun setTime() {
        timer_text_view.text = Utilities.getFormatedTime(time)

    }

    private fun startTimer() {
        handler = Handler()
        handler.postDelayed(runnable, 300)
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
