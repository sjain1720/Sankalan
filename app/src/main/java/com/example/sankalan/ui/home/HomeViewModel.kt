package com.example.sankalan.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sankalan.data.Events
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val eventList: MutableLiveData<ArrayList<Events>> by lazy {
        MutableLiveData<ArrayList<Events>>().also {
            loadEvent()
        }
    }


    fun getEvent(): MutableLiveData<ArrayList<Events>> {
        return eventList
    }

    private fun loadEvent() {
        GlobalScope.launch {

            val database = FirebaseDatabase.getInstance().getReference("Events")
            database.get().addOnSuccessListener {
                if (it.exists()) {
                    val list = arrayListOf<Events>()
                    for (childEventname in it.children) {
                        val obj = Events(childEventname.key.toString())
                        for (child in childEventname.children) {
                            when (child.key.toString()) {
                                "Description" -> obj.eventDescription = child.value.toString()
                                "Image" ->  obj.eventPoster = child.value.toString()
                                "Type"->    obj.eventType = child.value.toString()
                                "Team" ->   obj.team = child.value as Boolean
                                "Venue" -> obj.eventVenue = child.value.toString()
                                "Time" -> obj.eventTiming = child.value.toString()
                                "Coordinator" -> obj.eventCoordinator = child.value.toString()
                                else -> Log.e(
                                    "Error in RECIEVING",
                                    "${child} :  DONT KNOW THE ERROR!!"
                                )
                            }
                        }

                        list.add(obj)

                    }

                    eventList.value = list
                } else {
                    Log.w("Error", "NOT found data.")
                }
            }
                .addOnFailureListener {
                    Log.w("Error", "DATABSE NOT FOUND.")
                }


        }
    }

}