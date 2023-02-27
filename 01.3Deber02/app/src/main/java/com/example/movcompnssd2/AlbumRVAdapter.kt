package com.example.movcompnssd2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


abstract class AlbumRVAdapter(
    private val albumRVModalArrayList: ArrayList<AlbumRVModal>,
    context: Context
) : RecyclerView.Adapter<AlbumRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_rv_item,parent, false)
        return ViewHolder(view, parent.context)
    }

    fun onBindViewHolder(holder: ViewHolder, position: Int, context: Context) {
        val albumRVModal = albumRVModalArrayList[position]
        Picasso.get().load(albumRVModal.imageUrl).into(holder.albumIV)
        holder.albumNameTV.text = albumRVModal.name
        holder.albumDetailTV.text = albumRVModal.artistName
        holder.itemView.setOnClickListener {
            val i = Intent(context, AlbumDetailActivity::class.java)
            i.putExtra("id", albumRVModal.id)
            i.putExtra("name", albumRVModal.name)
            i.putExtra("img", albumRVModal.imageUrl)
            i.putExtra("artist", albumRVModal.artistName)
            i.putExtra("albumUrl", albumRVModal.external_urls)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return albumRVModalArrayList.size
    }


    class ViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        val albumIV: ImageView
        val albumNameTV: TextView
        val albumDetailTV: TextView
        private val context: Context

        init {
            albumIV = itemView.findViewById(R.id.idIVAlbum)
            albumNameTV = itemView.findViewById(R.id.idTVAlbumName)
            albumDetailTV = itemView.findViewById(R.id.idTVALbumDetails)
            this.context = context
        }
    }
}