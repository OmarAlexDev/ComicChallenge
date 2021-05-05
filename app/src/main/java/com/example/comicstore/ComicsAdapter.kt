package com.example.comicstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ComicsAdapter (private val comics: List <Comic>?): RecyclerView.Adapter<ComicsAdapter.ComicViewHolder>()  {
    inner class ComicViewHolder(renglon: View): RecyclerView.ViewHolder(renglon){
        var titulo = renglon.findViewById<TextView>(R.id.titulo)
        var issue_num = renglon.findViewById<TextView>(R.id.issue_num)
        var foto = renglon.findViewById<ImageView>(R.id.photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val renglon_vista = inflater.inflate(R.layout.comics_renglon,parent, false)
        return ComicViewHolder(renglon_vista)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic= comics!![position]
        holder.foto.setImageResource(comic.picture)
        holder.titulo.text = comic.title
        holder.issue_num.text = comic.issueNumber
        holder.itemView.setOnClickListener {
            val action = ComicsFragmentDirections.actionComicsFragmentToComicFragment(comic)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return comics!!.size
    }
}