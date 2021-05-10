package com.example.comicstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CarritoAdapter(private val data: List<ComicCarrito>?) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(property: ComicCarrito) {
            val title = view.findViewById<TextView>(R.id.titulo)
            val number = view.findViewById<TextView>(R.id.issue_num)
            val pageCount = view.findViewById<TextView>(R.id.page_count)
            val price = view.findViewById<TextView>(R.id.price)
            val imageView = view.findViewById<ImageView>(R.id.photo)

            title.text = property.title
            number.text = ("Número " + property.issueNumber)
            pageCount.text = ("Número de páginas " + property.pageCount)
            price.text = ("$" + property.prices)
            Glide.with(view.context)
                    .load(property.thumbnail)
                    .fitCenter()
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.carrito_renglon,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data!![position])
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}