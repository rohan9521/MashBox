package com.example.mashbox.UI

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mashbox.R


class ShowImagesAdapter:RecyclerView.Adapter<ShowImagesViewHolder>() {
    var list = listOf<FileLocationEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowImagesViewHolder {
        val layoutInflator  = LayoutInflater.from(parent.context)
        val view  = layoutInflator.inflate(R.layout.show_music_file,parent,false)
        val showImagesViewHolder =  ShowImagesViewHolder(view)
        return  showImagesViewHolder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ShowImagesViewHolder, position: Int) {
      holder.textView.text = list[position].location

    }
}
class ShowImagesViewHolder(view:View):RecyclerView.ViewHolder(view){
    val textView = view.findViewById<TextView>(R.id.textView)
}