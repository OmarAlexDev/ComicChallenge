package com.example.comicstore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.*

import kotlinx.android.synthetic.main.fragment_comic.*
import kotlinx.android.synthetic.main.fragment_store.*

/**
 * A simple [Fragment] subclass.
 * Use the [ComicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComicFragment : Fragment() {

    private val args by navArgs<ComicFragmentArgs>()
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database=FirebaseDatabase.getInstance()
        reference=database.getReference("Comics")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        titulo.text = args.comic.title
        issue_num.text = ("Número " + args.comic.issueNumber.toString())
        pageCount.text = ("Número de páginas " + args.comic.pageCount.toString())
        descrip.text = ("hola")
        view?.let {
            Glide.with(it)
                .load(args.comic.thumbnail.path +"." + args.comic.thumbnail.extension)
                .fitCenter()
                .error(R.drawable.ic_launcher_background)
                .into(photo)
        }
        add_button.setOnClickListener {
            /*val comic = Comic(args.comic.data.results.get(0).title,args.comic.data.results.get(0).issueNumber,args.comic.data.results.get(0).pageCount.toString(),args.comic.data.results.get(0).description,)
            val id = reference.push().key
            reference.child(id!!).setValue(comic)*/
        }
    }
}