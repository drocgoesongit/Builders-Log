package com.example.builderslog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.builderslog.data.DataSource
import com.example.builderslog.model.MuscleGroupElement

class MuscleGroupsAdapter(val context: Context): RecyclerView.Adapter<MuscleGroupsAdapter.ViewHolder>() {
private var list: List<MuscleGroupElement> = DataSource().loadMuscleGroups()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageMG: ImageView =itemView.findViewById(R.id.imgViewMuscleGroup)!!
        val muscleText: TextView = itemView.findViewById(R.id.nameMuscleGroupTxt)!!
        val layoutMG: View? = itemView.findViewById(R.id.muscleGroupLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.muslce_group_tile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        item.img?.let { holder.imageMG.setImageResource(it) }
        item.muscleGroup?.let { holder.muscleText.text = it }

        holder.layoutMG?.setOnClickListener {
            if (holder.layoutMG.tag.equals("unClicked")) {
                holder.layoutMG.setBackgroundResource(R.drawable.muscle_group_white)
                holder.layoutMG.tag = "clicked"
            }
            else {
                holder.layoutMG.setBackgroundResource(R.drawable.muscle_group_transparent)
                holder.layoutMG.tag = "unClicked"
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}