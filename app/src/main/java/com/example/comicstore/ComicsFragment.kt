package com.example.comicstore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_comics.*


/**
 * A simple [Fragment] subclass.
 * Use the [ComicsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComicsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comics_recycler.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = ComicsAdapter(createData())
        }
    }

    fun createData(): ArrayList<Comic>{
        val comics = ArrayList<Comic>()
        comics.add(Comic(R.drawable.superman,"Find X", "Oppo","2018"))
        comics.add(Comic(R.drawable.superman,"Iphone 12", "Apple","2020"))
        comics.add(Comic(R.drawable.superman,"V60 ThinQ 5G", "LG","2020"))
        comics.add(Comic(R.drawable.superman,"Mi 10", "Xiaomi","2020"))
        comics.add(Comic(R.drawable.superman,"Mi 11", "Xiaomi","2020"))
        comics.add(Comic(R.drawable.superman,"Note 20 Ultra", "Samsung","2020"))
        comics.add(Comic(R.drawable.superman,"7 Pro", "Oneplus","2019"))
        comics.add(Comic(R.drawable.superman,"8 Pro", "Oneplus","2020"))
        comics.add(Comic(R.drawable.superman,"Nord", "Oneplus","2020"))
        comics.add(Comic(R.drawable.superman,"Pixel 4a", "Google","2020"))
        comics.add(Comic(R.drawable.superman,"Pixel 5", "Google","2020"))
        comics.add(Comic(R.drawable.superman,"Note 10 Pro", "Redmi","2021"))
        comics.add(Comic(R.drawable.superman,"7 Pro", "Realme","2020"))
        comics.add(Comic(R.drawable.superman,"S21", "Samsung","2020"))
        comics.add(Comic(R.drawable.superman,"S20 Ultra", "Samsung","2020"))


        return comics
    }
}