package com.example.glowroadtest

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.glowroadtest.data.Photo

class ImageItemAdapter :
    RecyclerView.Adapter<ImageItemAdapter.ItemVH>() {

    private val urls = ArrayList<Photo>()

    fun updateList(list: List<Photo>) {
        val temp = urls.size
        urls.addAll(list)
        notifyItemRangeChanged(temp, list.size)
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bindItems(urls[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_data_item, parent, false)
        return ItemVH(v)
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    class ItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var text: TextView = itemView.findViewById(R.id.grid_item_tv)
        private var image: ImageView = itemView.findViewById(R.id.grid_item_image)

        fun bindItems(item: Photo) {
            text.text = item.title

            Glide.with(itemView)
                .load(item.getUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_background)
                .into(image)
        }
    }


}