package com.example.sankalan.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sankalan.R
import com.example.sankalan.adapter.RecyclerAdapter
import com.example.sankalan.data.DataSource

class HomeFragment : Fragment() {



   // private val binding get() = _binding!!
    //private lateinit var recyclerView: RecyclerView
   // private var layoutManager: RecyclerView.LayoutManager?=null
    //private var adapter: RecyclerView.Adapter<RecyclerAdapter.EventViewHolder>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_home,container,false)
        val recyclerView : RecyclerView=view.findViewById(R.id.recycler_view_home)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val dataset = DataSource().loadEvents()
        recyclerView.adapter = RecyclerAdapter(requireContext(),dataset)
        return view
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}


