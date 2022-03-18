package com.example.builderslog.data

import com.example.builderslog.R
import com.example.builderslog.model.MuscleGroupElement

class DataSource {
    fun loadMuscleGroups(): List<MuscleGroupElement>{
        return listOf<MuscleGroupElement>(
            MuscleGroupElement("biceps", R.drawable.biceps_img),
            MuscleGroupElement("triceps", R.drawable.triceps_img),
            MuscleGroupElement("shoulders", R.drawable.shoulders_img),
            MuscleGroupElement("chest", R.drawable.chest_img),
            MuscleGroupElement("legs", R.drawable.legs_img),
            MuscleGroupElement("back", R.drawable.back_img),
            MuscleGroupElement("abs", R.drawable.abs_img),
        )
    }
}