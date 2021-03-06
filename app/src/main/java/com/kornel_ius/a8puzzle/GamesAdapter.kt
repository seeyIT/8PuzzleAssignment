package com.kornel_ius.a8puzzle

/**
 * Created by Kornel_ius.
 */
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ranking_record.view.*

class GamesAdapter(private val context: Context, private val items: ArrayList<Game>) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.ranking_record, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.playerNameTextView?.text = items[position].playerName
        holder?.timeTextView?.text = Utilities.getFormatedTime(items[position].time)
        holder?.placeNumberTextView?.text = (position + 1).toString()
        if (position % 2 == 0) {
            holder?.rowBoxLinear?.setBackgroundColor(Color.parseColor("#eeeeee"))
        } else {
            holder?.rowBoxLinear?.setBackgroundColor(Color.parseColor("#cccccc"))

        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rowBoxLinear = view.ranking_row_box!!
        val placeNumberTextView = view.ranking_place_number!!
        val playerNameTextView = view.ranking_player_name!!
        val timeTextView = view.ranking_time!!

    }
}

