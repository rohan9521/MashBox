package com.example.mashbox.UI

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mashbox.R
import kotlinx.android.synthetic.main.show_music_file.view.*


class ShowImagesAdapter(private val click: onClick) :RecyclerView.Adapter<ShowImagesViewHolder>() {
    var list = listOf<FileLocationEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowImagesViewHolder {
        val layoutInflator  = LayoutInflater.from(parent.context)
        val view  = layoutInflator.inflate(R.layout.show_music_file,parent,false)
        val showImagesViewHolder =  ShowImagesViewHolder(view)
        view.button.setOnClickListener{
            click.onClickListener(list[showImagesViewHolder.adapterPosition])
        }

        return  showImagesViewHolder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ShowImagesViewHolder, position: Int) {
      holder.textView.text = list[position].location

    }
}
class ShowImagesViewHolder(view:View):RecyclerView.ViewHolder(view){
    val textView = view.findViewById<TextView>(R.id.textView)
    val button = view.findViewById<Button>(R.id.button)
}
interface onClick{
    fun onClickListener(fileLocationEntity: FileLocationEntity)
}