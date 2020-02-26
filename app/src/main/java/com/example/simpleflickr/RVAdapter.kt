package com.example.simpleflickr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpleflickr.model.FlickrResponse.Photos
import kotlinx.android.synthetic.main.activity_details.view.*

class UserAdapter(val photoList: List<Photos>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_details, parent, false))

    override fun getItemCount() = photoList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.populateItem(photoList[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun populateItem(result: Photos) {
            itemView.tvInfo1.text = "${result.page}"
            itemView.tvInfo2.text = result.pages
            Glide
                .with(itemView)
                .load(result.photo)
                .into(itemView.ivFlickrDetail)

        }
    }
}