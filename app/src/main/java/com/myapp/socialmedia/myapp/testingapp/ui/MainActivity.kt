package com.myapp.socialmedia.myapp.testingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.socialmedia.myapp.testingapp.network.ImagesApi
import com.myapp.socialmedia.myapp.testingapp.model.ImagesDataClass
import com.myapp.socialmedia.myapp.testingapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.image_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}