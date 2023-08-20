package com.example.mygridview

import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.adapter.GridViewAdapter
import com.example.model.Album
import com.example.util.Status
import com.example.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel : MyViewModel by viewModels()
    private  lateinit var gridViewAdapter: GridViewAdapter
    private lateinit var gridView: GridView
    private lateinit var progressBar :ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)



        setUpUi()


        loadData()// load data from server



    }
    private fun setUpUi(){
        val courseList = ArrayList<Album>()
        progressBar = findViewById(R.id.progressBar)
        gridView = findViewById(R.id.grid_view)
        gridViewAdapter = GridViewAdapter( courseList, this@MainActivity)
        gridView.adapter = gridViewAdapter
    }
    private fun loadData(){
        mainViewModel.photos.observe(this, Observer {
            when(it.status){
                Status.SUCCESS->{
                    progressBar.visibility = View.GONE
                    gridView.visibility = View.VISIBLE
                    it.data?.let { albums ->  renderList(albums) }
                }
                Status.LOADING->{
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR->{
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<Album>) {
        gridViewAdapter.addData(users)
        gridViewAdapter.notifyDataSetChanged()
    }
}