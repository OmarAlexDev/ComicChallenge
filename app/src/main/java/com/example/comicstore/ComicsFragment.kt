package com.example.comicstore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.comics_renglon.*
import kotlinx.android.synthetic.main.fragment_comics.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass.
 * Use the [ComicsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComicsFragment : Fragment() {

    private val BASE_URL = "http://gateway.marvel.com/v1/public/"
    lateinit var results: Results
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        manager = LinearLayoutManager(context)
        getAllData()

        cart.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_comicsFragment_to_carritoFragment)
        }

        logoutbtn.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(context, Login::class.java))
            activity?.finish()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getAllData() {


        val callToService = getRetrofit().create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val responseFromService = callToService.getComics()
            activity?.runOnUiThread{
                results = responseFromService.body() as Results


                if (responseFromService.isSuccessful) {
                    Log.i("Comics", results.data?.results.toString())
                    //Toast.makeText(applicationContext, "Exit!", Toast.LENGTH_LONG).show()


                    recyclerView = comics_recycler.apply {

                        layoutManager = manager
                        myAdapter = ComicsAdapter(results.data?.results)
                        adapter = myAdapter

                    }


                }  else {
                    Log.i("Comics", "No jaló")
                    Toast.makeText(view?.context, "Error!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}