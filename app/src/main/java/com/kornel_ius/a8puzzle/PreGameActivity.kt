package com.kornel_ius.a8puzzle

import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.activity_pre_game.*
import kotlinx.android.synthetic.main.fragment_pre_game.view.*

class PreGameActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_game)

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun startGame(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("imageName",getImageNameFromIndex(container.currentItem))
        startActivity(intent)
        finish()
    }

    private fun getImageNameFromIndex(index: Int): String {
        when (index) {
            0 -> return "los"
            1 -> return "tree"
            2 -> return "flowe"
            3 -> return "flowe"
            4 -> return "countryside"
            5 -> return "countryside"
            6 -> return "countryside"
            7 -> return "countryside"
        }
        return ""
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position )
        }

        override fun getCount(): Int {
            return 8
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_pre_game, container, false)
            val id = arguments.getInt(ARG_SECTION_NUMBER)

            when (id) {
                0 -> rootView.section_image.setImageDrawable(resources.getDrawable(R.drawable.los))
                1 -> rootView.section_image.setImageDrawable(resources.getDrawable(R.drawable.tree))
                2 -> rootView.section_image.setImageDrawable(resources.getDrawable(R.drawable.flowe))
                3 -> rootView.section_image.background = resources.getDrawable(R.drawable.gleise)
                4 -> rootView.section_image.background = resources.getDrawable(R.drawable.images)
                5 -> rootView.section_image.background = resources.getDrawable(R.drawable.panoramic)
                6 -> rootView.section_image.background = resources.getDrawable(R.drawable.pobrane)
                7 -> rootView.section_image.background = resources.getDrawable(R.drawable.sunset)
            }
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
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
