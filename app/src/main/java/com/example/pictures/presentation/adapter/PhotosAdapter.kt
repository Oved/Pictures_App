package com.example.pictures.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pictures.R
import com.example.pictures.data.models.PhotoModel

class PhotosAdapter(
    private val context: Context,
    private var photos: List<PhotoModel>,
    private val itemClick: (PhotoModel) -> Unit
) :
    RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.card)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val albumId: TextView = itemView.findViewById(R.id.tv_albumdId)
        val imageView: ImageView = itemView.findViewById(R.id.profile)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return PhotoViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]

        holder.card.setOnClickListener {
            itemClick(photo)
        }
        holder.title.text = photo.title
        holder.albumId.text = "Id: ${photo.id}"

        Glide.with(context)
            .load(photo.thumbnailUrl)
            .error(Glide.with(context).load(R.drawable.ic_launcher_foreground))
            .centerCrop()
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    fun deleteItem(id : Int){
        val item = photos.find { it.id == id }
        if (item != null) {
            val pos = photos.indexOf(item)
            photos = photos.filterNot { it.id == id }
            notifyItemRemoved(pos)
        }
    }
}