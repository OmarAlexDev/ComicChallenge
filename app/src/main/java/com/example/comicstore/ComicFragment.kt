package com.example.comicstore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
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

        photo.setImageResource(args.comic.picture)
        titulo.text = args.comic.title
        issue_num.text = args.comic.issueNumber
        descrip.text = args.comic.description
        pageCount.text = args.comic.pageCount



        add_button.setOnClickListener {
            val comic = Comic(args.comic.picture,args.comic.title,args.comic.issueNumber,args.comic.description,args.comic.pageCount)
            val id = reference.push().key
            reference.child(id!!).setValue(comic)
        }
    }


}