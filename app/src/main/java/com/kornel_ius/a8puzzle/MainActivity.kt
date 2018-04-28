package com.kornel_ius.a8puzzle

import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val backgroundImages: ArrayList<Drawable> = ArrayList()
    private val random: Random = Random()
    private var currentDrawableIndex: Int = 0
    lateinit var runnable: Runnable
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        start_game_layout.setOnClickListener { startGame() }
        show_ranking_layout.setOnClickListener { showRanking() }

        backgroundImages.add(ResourcesCompat.getDrawable(resources, R.drawable.test, null)!!)
        backgroundImages.add(ResourcesCompat.getDrawable(resources, R.drawable.tree, null)!!)
        backgroundImages.add(ResourcesCompat.getDrawable(resources, R.drawable.flowe, null)!!)
        backgroundImages.add(ResourcesCompat.getDrawable(resources, R.drawable.fantasy, null)!!)
        backgroundImages.add(ResourcesCompat.getDrawable(resources, R.drawable.sunset, null)!!)

        setFirstBackground()
        infinityBackgroundChanger()
    }

    private fun setFirstBackground() {
        currentDrawableIndex = random.nextInt(backgroundImages.size)
        main_activity_background_image.setImageDrawable(backgroundImages[currentDrawableIndex])
    }

    private fun infinityBackgroundChanger() {
        runnable = Runnable {
            var newDrawableIndex = currentDrawableIndex + 1
            if (newDrawableIndex == backgroundImages.size) {
                newDrawableIndex = 0
            }

            val images1 = arrayOf(backgroundImages[currentDrawableIndex], backgroundImages[newDrawableIndex])
            val trans1 = TransitionDrawable(images1)
            main_activity_background_image.setImageDrawable(trans1)

            trans1.startTransition(1000)
            currentDrawableIndex = newDrawableIndex
            handler.postDelayed(runnable, 5000)

        }
        handler.postDelayed(runnable, 5000)

    }


    private fun startGame() {
        startActivity(Intent(this, PreGameActivity::class.java))
    }

    private fun showRanking() {
        startActivity(Intent(this, RankingActivity::class.java))
    }
}
