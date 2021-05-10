package com.example.comicstore

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_carrito.*

abstract class SwipeToDelete(context: Context, direccion: Int, direccionArrastre: Int):
        ItemTouchHelper.SimpleCallback(direccion, direccionArrastre){
    override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }
}

class CarritoFragment: Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        manager = LinearLayoutManager(context)

        getAllData()
    }

    fun getAllData() {

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val usuario = Firebase.auth.currentUser
        reference = database.getReference("Comics/${usuario.uid}")

        recyclerView = carrito_recycler.apply {
            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var listaDatos = ArrayList<ComicCarrito>()
                    for (comic in snapshot.children) {
                        var objeto = comic.getValue(ComicCarrito::class.java)
                        listaDatos.add(objeto as ComicCarrito)
                    }

                    if (listaDatos.isEmpty()) {
                        Toast.makeText(view?.context, "No has agregado ningun comic", Toast.LENGTH_LONG).show()
                    } else {

                        layoutManager = manager
                        myAdapter = CarritoAdapter(listaDatos)
                        adapter = myAdapter

                        val item = object : SwipeToDelete(context, ItemTouchHelper.UP, ItemTouchHelper.LEFT) {

                            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                                super.onSwiped(viewHolder, direction)
                                val comic = listaDatos[viewHolder.adapterPosition]
                                borrarComics(comic)
                            }
                        }

                        val itemTouchHelper = ItemTouchHelper(item)
                        itemTouchHelper.attachToRecyclerView(carrito_recycler)
                    }
                    Log.i("datos", listaDatos.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    fun borrarComics(comic: ComicCarrito) {
        val usuario = Firebase.auth.currentUser
        val referencia = FirebaseDatabase.getInstance().getReference("Comics/${usuario.uid}/${comic.id}")
        referencia.removeValue()
    }
}