package com.example.builderslog.model

data class LogElement (
    val date: String?= null,
    val hours: String?= null,
    val minutes: String?= null,
    val calories: String?= null,
    val muscleGroupsList: ArrayList<String>?= null,
    val weight: String?= null,
    val timeInMilli: String?= null
)