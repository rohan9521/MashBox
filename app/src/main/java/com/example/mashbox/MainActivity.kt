package com.example.mashbox

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mashbox.UI.*


class MainActivity : AppCompatActivity(),onClick {
    lateinit private var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val fileLocationDataBase = RoomFileLocationDataBase.getDatabase(context = applicationContext)
        val fileLocationDao  = fileLocationDataBase.getdDao()
        val repository = FileLocationRepository(fileLocationDao)
        val viewModelFactory = PageViewModelFactory(applicationContext,repository)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(PageViewModel::class.java)
        mediaPlayer = MediaPlayer()
        val adapter = ShowImagesAdapter( this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.fileList.observe(this, Observer { list ->
            run {
                adapter.list = list
                Log.d("newList",list.size.toString())
                adapter.notifyDataSetChanged()
            }
        })



    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {

            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClickListener(fileLocationEntity: FileLocationEntity) {
        if(mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer.reset()


        }
        mediaPlayer. setDataSource(fileLocationEntity.location)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }
}