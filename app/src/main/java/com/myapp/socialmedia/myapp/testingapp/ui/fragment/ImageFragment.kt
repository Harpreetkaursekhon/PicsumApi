package com.myapp.socialmedia.myapp.testingapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.socialmedia.myapp.testingapp.R
import com.myapp.socialmedia.myapp.testingapp.model.ImagesDataClass
import com.myapp.socialmedia.myapp.testingapp.network.ImagesApi
import com.myapp.socialmedia.myapp.testingapp.ui.ImageAdapter
import kotlinx.android.synthetic.main.image_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageFragment : Fragment() {

    companion object {
        fun newInstance() = ImageFragment()
    }

    private lateinit var viewModel: ImageViewModel
    private lateinit var images: List<ImagesDataClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)
        // TODO: Use the ViewModel

        refreshLayout.setOnRefreshListener {
            fetchImages()
        }

        fetchImages()

        btn_type.setOnClickListener() {
            changeType();
        }
    }

    private fun fetchImages() {
        refreshLayout.isRefreshing = true

        ImagesApi()
            .getImages().enqueue(object : Callback<List<ImagesDataClass>> {
                override fun onFailure(call: Call<List<ImagesDataClass>>, t: Throwable) {
                    refreshLayout.isRefreshing = false
                    Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<List<ImagesDataClass>>,
                    response: Response<List<ImagesDataClass>>
                ) {

                    refreshLayout.isRefreshing = false
                    val images = response.body()

                    images?.let {
                        showImages(it)
                    }
                }
            }
            )
    }

    private fun showImages(images: List<ImagesDataClass>) {
        this.images = images
        recyclerViewImagesPicsum.layoutManager = LinearLayoutManager(activity)
        recyclerViewImagesPicsum.adapter = ImageAdapter(images)
    }

    private fun changeType() {
        recyclerViewImagesPicsum.layoutManager = recyclerViewImagesPicsum.getLayoutManager()
        if (recyclerViewImagesPicsum.layoutManager is GridLayoutManager) {
            recyclerViewImagesPicsum.layoutManager = LinearLayoutManager(activity)
            recyclerViewImagesPicsum.adapter = ImageAdapter(images)

        } else if (recyclerViewImagesPicsum.layoutManager is LinearLayoutManager) {
            recyclerViewImagesPicsum.layoutManager = GridLayoutManager(activity, 2)
            recyclerViewImagesPicsum.adapter = ImageAdapter(images)
        }
    }
}