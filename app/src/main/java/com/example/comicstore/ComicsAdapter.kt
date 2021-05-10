package com.example.comicstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ComicsAdapter(private val data: List<Comic>?) : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(property: Comic) {
            lateinit var database: FirebaseDatabase
            var reference: DatabaseReference
            val title = view.findViewById<TextView>(R.id.titulo)
            val number = view.findViewById<TextView>(R.id.issue_num)
            val pageCount = view.findViewById<TextView>(R.id.page_count)
            val price = view.findViewById<TextView>(R.id.price)
            val imageView = view.findViewById<ImageView>(R.id.photo)
            val add_btn = view.findViewById<Button>(R.id.add_Cart)

            val usuario = Firebase.auth.currentUser

            title.text = property.title
            number.text = ("Número " + property.issueNumber.toString())
            pageCount.text = ("Número de páginas " + property.pageCount.toString())
            price.text = ("$" + property.prices.get(0).price.toString())
            Glide.with(view.context)
                    .load(property.thumbnail.path + "." + property.thumbnail.extension)
                    .fitCenter()
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView)

            database = FirebaseDatabase.getInstance()
            //reference=database.getReference("Comics")
            add_btn.setOnClickListener {
                reference = database.getReference("Comics/${usuario.uid}")
                val id = reference.push().key
                val comic = ComicCarrito(id.toString(),property.title,property.issueNumber.toString(),property.pageCount.toString(),property.prices.get(0).price.toString(),property.thumbnail.path + "." + property.thumbnail.extension)
                reference.child(id!!).setValue(comic)
                Toast.makeText(view.context, "Comic agregado", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.comics_renglon,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data!![position])
        //val comic :Comic = data!![position]
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}