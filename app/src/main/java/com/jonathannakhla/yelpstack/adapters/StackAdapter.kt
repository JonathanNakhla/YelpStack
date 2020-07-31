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

class StackAdapter(private val restaurants: List<Restaurant>): RecyclerView.Adapter<StackAdapter.StackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_card_view, parent, false)
        return StackViewHolder(itemView)
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: StackViewHolder, position: Int) {
        holder.bind(restaurants[position])
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
}