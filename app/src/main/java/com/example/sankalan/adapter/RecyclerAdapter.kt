package com.example.sankalan.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sankalan.R
import com.example.sankalan.model.Event

class RecyclerAdapter(private val context : Context,
                               private val dataset: List<Event>):
    RecyclerView.Adapter<RecyclerAdapter.EventViewHolder>() {

    class EventViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.event_title_card)
        val poster: ImageView=view.findViewById(R.id.event_poster_card)
        val type: TextView=view.findViewById(R.id.event_type_card)
        val nos: TextView=view.findViewById(R.id.event_nos_card)
        val venue: TextView=view.findViewById(R.id.event_venue_card)
        val timing: TextView=view.findViewById(R.id.event_timing_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.event_card_layout,parent,false)
        return EventViewHolder(v)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = dataset[position]
        holder.title.text = context.resources.getString(item.eventTitleId)
        holder.poster.setImageResource(item.eventPosterId)
        holder.type.text = context.resources.getString(item.eventTypeId)
        holder.nos.text = context.resources.getString(item.eventNosId)
        holder.timing.text = context.resources.getString(item.eventTimingId)
        holder.venue.text = context.resources.getString(item.eventVenueId)
    }
    override fun getItemCount() = dataset.size
}