package com.jonathannakhla.yelpstack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jonathannakhla.yelpstack.R
import com.jonathannakhla.yelpstack.data.Restaurant

class StackAdapter(private val restaurants: List<Restaurant>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val RESTAURANT_VIEW_TYPE = 0
        private const val PROGRESS_BAR_VIEW_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == RESTAURANT_VIEW_TYPE) {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.restaurant_card_view, parent, false)
            return StackViewHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.progress_bar_viewholder, parent, false)
            return ProgressBarViewHolder(itemView)
        }
    }

    override fun getItemCount() = restaurants.size + if (restaurants.isNotEmpty()) 1 else 0

    override fun getItemViewType(position: Int): Int {
        return if (position >= restaurants.size) {
            PROGRESS_BAR_VIEW_TYPE
        } else {
            RESTAURANT_VIEW_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == RESTAURANT_VIEW_TYPE) {
            (holder as StackViewHolder).bind(restaurants[position])
        }
    }

    class StackViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val restaurantNameTextView: TextView = itemView.findViewById(R.id.restaurant_name_textview)
        private val restaurantRatingTextView: TextView = itemView.findViewById(R.id.restaurant_rating_textview)
        private val restaurantImage: ImageView = itemView.findViewById(R.id.restaurant_image)

        fun bind(restaurant: Restaurant) {
            restaurantNameTextView.text = restaurant.name
            restaurantRatingTextView.text = restaurant.rating.toString()

            Glide.with(restaurantImage)
                .load(restaurant.imageUrl)
                .into(restaurantImage)
        }
    }

    class ProgressBarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}