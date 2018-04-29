package com.kornel_ius.a8puzzle

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game.*
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Handler
import android.view.View
import android.widget.Toast
import java.util.*
import android.content.Intent
import android.view.inputmethod.InputMethodManager

class GameActivity : AppCompatActivity() {

    private val CLICK_DURATION: Int = 100
    private val ANIM_DURATION: Long = 300
    private val arrayOfImages = ArrayList<ImageView>()
    private val arrayOfInts = IntArray(9, { it })
    private var tileSize: Int = 0
    private var screenHeight: Int = 0
    private var time: Int = 0
    private var emptyField: Int = 8
    private var gameStarted: Boolean = true
    private var imageName: String = ""
    private lateinit var timerHandler: Handler
    private lateinit var timerRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar!!.hide()
        imageName = intent.extras.getString("imageName").toString()
        // back to main menu then there is no image
        if (imageName.isEmpty()) {
            finish()
        }
        calcTileSize()
        init()
        startTimer()
    }

    override fun onPause() {
        super.onPause()
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
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

        // set background same image but with alpha
        val id: Int = resources.getIdentifier(imageName, "drawable", packageName)
        background_image_view.background = resources.getDrawable(id)

        timerRunnable = Runnable {
            time += 1
            timerHandler.postDelayed(timerRunnable, 100)
            setTimeTextView()

        }
        addListeners()
        shuffle()
    }

    private fun addListeners() {
        tile_0.setOnClickListener { swap(0) }
        tile_1.setOnClickListener { swap(1) }
        tile_2.setOnClickListener { swap(2) }
        tile_3.setOnClickListener { swap(3) }
        tile_4.setOnClickListener { swap(4) }
        tile_5.setOnClickListener { swap(5) }
        tile_6.setOnClickListener { swap(6) }
        tile_7.setOnClickListener { swap(7) }
        tile_8.setOnClickListener { swap(8) }
    }

    private fun removeListeners() {
        tile_0.setOnClickListener { }
        tile_1.setOnClickListener { }
        tile_2.setOnClickListener { }
        tile_3.setOnClickListener { }
        tile_4.setOnClickListener { }
        tile_5.setOnClickListener { }
        tile_6.setOnClickListener { }
        tile_7.setOnClickListener { }
        tile_8.setOnClickListener { }
    }

    private fun swap(first: Int) {
        if (!canMove(first, emptyField)) {
            return
        }

        if (gameStarted) {
            val firstImage: Drawable = arrayOfImages[first].background
            val secondImage: Drawable = arrayOfImages[emptyField].background

            val images1 = arrayOf(firstImage, secondImage)
            val trans1 = TransitionDrawable(images1)
            arrayOfImages[first].background = trans1
            trans1.startTransition(CLICK_DURATION)

            val images2 = arrayOf(secondImage, firstImage)
            val trans2 = TransitionDrawable(images2)
            arrayOfImages[emptyField].background = trans2
            trans2.startTransition(CLICK_DURATION)
        }

        val tempInt: Int = arrayOfInts[first]
        arrayOfInts[first] = arrayOfInts[emptyField]
        arrayOfInts[emptyField] = tempInt
        emptyField = first

        if (gameStarted) {
            if (checkWin()) {
                gameFinish()
            }
        }
    }


    private fun correctImagesAfterShuffle() {
        var id: Int

        for (i in 0..8) {
            id = resources.getIdentifier(imageName + arrayOfInts[i].toString(), "drawable", packageName)
            arrayOfImages[i].background = resources.getDrawable(id)
        }
    }

    private fun shuffle() {
        gameStarted = false
        val random: Random = Random()
        for (i in 0..200) {
            arrayOfImages[random.nextInt(9)].performClick()
        }
        correctImagesAfterShuffle()
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

    private fun checkWin(): Boolean {
        val win: Boolean = (0..8).none { arrayOfInts[it] != it }
        return win
    }

    private fun gameFinish() {
        stopTimer()
        removeListeners()
        player_time_result_text_view.text = Utilities.getFormatedTime(time)
        showPopup()
        player_name_edit_text.requestFocus()
    }

    private fun showPopup() {
        finish_game_popup.animate().translationY(-(screenHeight / 2).toFloat()).setDuration(ANIM_DURATION).start()
        finish_game_popup.visibility = View.VISIBLE

        val handler: Handler = Handler()
        handler.postDelayed(Runnable {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }, ANIM_DURATION)
    }

    private fun hidePopup() {
        finish_game_popup.animate().translationY((screenHeight / 2).toFloat()).setDuration(ANIM_DURATION).start()
        val handler: Handler = Handler()
        handler.postDelayed(Runnable {
            finish_game_popup.visibility = View.GONE
        }, ANIM_DURATION)
    }


    private fun setTimeTextView() {
        timer_text_view.text = Utilities.getFormatedTime(time)
    }

    private fun startTimer() {
        timerHandler = Handler()
        timerHandler.postDelayed(timerRunnable, 300)
    }

    private fun stopTimer() {
        timerHandler.removeCallbacks(timerRunnable)
    }

    private fun calcTileSize() {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        tileSize = (size.x - 100) / 3
        screenHeight = size.y
    }

    fun finishGame(view: View) {
        if (player_name_edit_text.text.isEmpty()) {
            Toast.makeText(this, "Name is to short", Toast.LENGTH_SHORT).show()
            return
        }

        // add record to database
        val database: MyDBHandler = MyDBHandler(this)
        database.addGame(Game(player_name_edit_text.text.toString(), time))

        // hide keyboard
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        // finish activity
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
