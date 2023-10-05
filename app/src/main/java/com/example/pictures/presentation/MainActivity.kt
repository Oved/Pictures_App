package com.example.pictures.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pictures.presentation.adapter.PhotosAdapter
import com.example.pictures.databinding.ActivityMainBinding
import com.example.pictures.presentation.viewmodel.MainViewModel
import com.example.pictures.presentation.viewmodel.UiStates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PhotosAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getPhotos(this@MainActivity)

        mainViewModel.uiStates.observe(this) {
            when (it) {
                is UiStates.Failure -> {
                    binding.progress.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                    binding.recyclerPhotos.visibility = View.GONE
                }

                UiStates.Progress -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    binding.recyclerPhotos.visibility = View.GONE
                }

                is UiStates.Success -> {
                    binding.progress.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                    binding.recyclerPhotos.visibility = View.VISIBLE
                    adapter = PhotosAdapter(this, it.listPhotos) { photo ->
                        val intent = Intent(this@MainActivity, DetailPhotoActivity::class.java)
                        intent.putExtra("photo", photo)
                        startActivity(intent)
                    }
                    binding.recyclerPhotos.layoutManager = LinearLayoutManager(this)
                    binding.recyclerPhotos.adapter = adapter

                }
            }
        }

    }
}