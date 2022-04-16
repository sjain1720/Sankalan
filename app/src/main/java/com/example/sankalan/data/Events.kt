package com.example.sankalan.data


data class Events (
    val eventName: String
){

    var eventDescription:String = "NONE"
    var eventPoster: String = "NONE" //url
    var eventType: String = "NONE" // Technical nontechnical
    var team: Boolean = false
    var eventVenue: String = "NONE"
    var eventTiming: String = "00:00"
    var eventCoordinator:String = "XXXX"
}