package com.myapp.socialmedia.myapp.testingapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapp.socialmedia.myapp.testingapp.R
import com.myapp.socialmedia.myapp.testingapp.model.ImagesDataClass
import kotlinx.android.synthetic.main.layout_images.view.*

class ImageAdapter(val images: List<ImagesDataClass>): RecyclerView.Adapter<ImageAdapter.ImageViewHolder> (){

    class ImageViewHolder(val view: View):RecyclerView.ViewHolder(view)

    //create the view holder by inflating layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
       return ImageViewHolder(
           LayoutInflater
               .from(parent.context)
               .inflate(
                   R.layout.layout_images,
                   parent,
                   false
               )
       )
    }
        //return size of the list
    override fun getItemCount()= images.size

        //bind the actual data that we want to bind to viewholder
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
            holder.view.textViewAuthor.text= image.author

            Glide.with(holder.view.context)
                .load(image.download_url)
                .into(holder.view.imageView)
    }
}