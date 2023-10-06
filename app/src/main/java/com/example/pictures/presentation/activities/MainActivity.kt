package com.example.pictures.presentation.activities

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
    private val RESULT = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //todo: Realizo la petición de la fotos
        mainViewModel.getPhotos()

        //todo: Valido al iniciar la actividad si hay fotos que no se hayan enviado la petición para eliminar
        mainViewModel.deletePhotosSaved()

        //todo: Valido los estados que me provea el viewmodel
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
                        startActivityForResult(intent, RESULT)
                    }
                    binding.recyclerPhotos.layoutManager = LinearLayoutManager(this)
                    binding.recyclerPhotos.adapter = adapter

                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //todo: Obtengo el item a eliminar cuando se da eliminar en el detalle,
        // para continuar la lógica desde la vista main
        if (requestCode == RESULT) {
            val id = data?.getIntExtra("id_item", 0)
            if (id != null) {
                adapter.deleteItem(id)
                mainViewModel.deletePhoto(id)
            }

        }
    }
}