package com.example.movcompnssd2

import android.R
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class AlbumRVAdapter(
    private val albumRVModalArrayList: ArrayList<AlbumRVModal>,
    context: Context
) : RecyclerView.Adapter<AlbumRVAdapter.ViewHolder>() {
    private val context: Context
    init {
        this.context = context
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        // setting data to text view and image view on below line.
        val albumRVModal = albumRVModalArrayList[position]
        Picasso.get().load(albumRVModal.imageUrl).into(holder.albumIV)
        holder.albumNameTV.text = albumRVModal.name
        holder.albumDetailTV.text = albumRVModal.artistName
        // adding click listener for album item on below line.
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // on below line opening a new album detail
                // activity for displaying songs within that album.
                val i = Intent(context, AlbumDetailActivity::class.java)
                // on below line passing album related data.
                i.putExtra("id", albumRVModal.id)
                i.putExtra("name", albumRVModal.name)
                i.putExtra("img", albumRVModal.imageUrl)
                i.putExtra("artist", albumRVModal.artistName)
                i.putExtra("albumUrl", albumRVModal.external_urls)
                context.startActivity(i)
            }
        })
    }

    // on below line returning the size of list
    override fun getItemCount(): Int {
        return albumRVModalArrayList.size()
    }

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line creating variables
        // for image view and text view.
        private val albumIV: ImageView
        private val albumNameTV: TextView
        private val albumDetailTV: TextView

        init {
            // on below line initializing variables.
            albumIV = itemView.findViewById(R.id.idIVAlbum)
            albumNameTV = itemView.findViewById(R.id.idTVAlbumName)
            albumDetailTV = itemView.findViewById(R.id.idTVALbumDetails)
        }
    }
}