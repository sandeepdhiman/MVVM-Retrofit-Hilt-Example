package com.example.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.model.Album
import com.example.mygridview.R
import com.squareup.picasso.Picasso

class GridViewAdapter(private val list: ArrayList<Album>,
                      private val context: Context
) : BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var courseTV: TextView
    private lateinit var courseIV: ImageView
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
        var convertView = convertView

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertView == null) {

            convertView = layoutInflater!!.inflate(R.layout.gridview_item, null)
        }

        courseIV = convertView!!.findViewById(R.id.idIVCourse)
        courseTV = convertView!!.findViewById(R.id.idTVCourse)


        Picasso.get()
            .load(list.get(position).url)
            .into(courseIV);

        courseTV.setText(list.get(position).title)
        // at last we are returning our convert view.
        return convertView
    }
    fun addData(lists: List<Album>){
        list.addAll(lists)
    }
}