package com.kornel_ius.a8puzzle

import android.content.Intent
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.activity_pre_game.*
import kotlinx.android.synthetic.main.fragment_pre_game.view.*

class PreGameActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_game)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                swipe_right_layout.visibility = View.GONE

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun startGame(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("imageName", getImageNameFromIndex(container.currentItem))
        startActivity(intent)
        finish()
    }


    private fun getImageNameFromIndex(index: Int): String {
        when (index) {
            0 -> return "moose"
            1 -> return "tree"
            2 -> return "bridge"
            3 -> return "land"
            4 -> return "bay"
        }
        return ""
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return PlaceholderFragment.newInstance(position)
        }

        override fun getCount(): Int {
            return 5
        }
    }

    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_pre_game, container, false)
            val id = arguments.getInt(ARG_SECTION_NUMBER)
            when (id) {
                0 -> {
                    rootView.section_image.setImageDrawable(resources.getDrawable(R.drawable.moose))
                    rootView.section_background_image.setImageDrawable(resources.getDrawable(R.drawable.moose))
                }
                1 -> {
                    rootView.section_image.setImageDrawable(resources.getDrawable(R.drawable.tree))
                    rootView.section_background_image.setImageDrawable(resources.getDrawable(R.drawable.tree))
                }
                2 -> {
                    rootView.section_image.setImageDrawable(resources.getDrawable(R.drawable.bridge))
                    rootView.section_background_image.setImageDrawable(resources.getDrawable(R.drawable.bridge))
                }
                3 -> {
                    rootView.section_image.setImageDrawable(resources.getDrawable(R.drawable.land))
                    rootView.section_background_image.setImageDrawable(resources.getDrawable(R.drawable.land))
                }
                4 -> {
                    rootView.section_image.setImageDrawable(resources.getDrawable(R.drawable.bay))
                    rootView.section_background_image.setImageDrawable(resources.getDrawable(R.drawable.bay))
                }

            }
            return rootView
        }

        companion object {
            private val ARG_SECTION_NUMBER = "section_number"

            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
