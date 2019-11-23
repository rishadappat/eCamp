package com.appat.ecamp.Dialogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.appat.ecamp.GenericModels.LookUp
import com.appat.ecamp.R

class LookUpDialogAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var lookUps: ArrayList<LookUp>

    companion object {
        fun lookUpsAdapter(lookUps: ArrayList<LookUp>): LookUpDialogAdapter {
            val adapter = LookUpDialogAdapter()
            adapter.lookUps = lookUps
            return adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.lookup_item,parent,false)
        return LookUpViewHolder(v)
    }

    override fun getItemCount(): Int {
        return lookUps.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val lookUp = lookUps[position]
        val viewHolder = holder as LookUpViewHolder
        viewHolder.lookUpEnglishName.text = lookUp.nameEn
    }

    inner class LookUpViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val lookUpEnglishName: TextView = itemView.findViewById(R.id.lookUpEnglish)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val container: RelativeLayout = itemView.findViewById(R.id.container)
    }
}