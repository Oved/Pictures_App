package com.example.pictures.presentation.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.pictures.R
import com.example.pictures.data.models.PhotoModel
import com.example.pictures.databinding.ActivityDetailPhotoBinding

class DetailPhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPhotoBinding
    private lateinit var photo: PhotoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val intent = intent
        if (intent != null) {
            photo = intent.getParcelableExtra("photo")!!
        }

        //todo: Seteo los datos del objeto enviado desde la main
        binding.tvId.text = "Id ${photo.id}"
        binding.tvAlbumdId.text = "Album Id ${photo.albumId}"
        binding.tvTitle.text = photo.title

        Glide.with(this)
            .load(photo.thumbnailUrl)
            .error(Glide.with(this).load(R.drawable.ic_launcher_foreground))
            .centerCrop()
            .into(binding.imageProfile)

        Glide.with(this)
            .load(photo.url)
            .error(Glide.with(this).load(R.drawable.ic_launcher_foreground))
            .centerCrop()
            .into(binding.frontImage)

        binding.backButton.setOnClickListener { finish() }
        binding.btnDelete.setOnClickListener {
            //todo: Cuando se elimina devuelvo el id del item a borrar
            setResult(Activity.RESULT_OK, Intent().putExtra("id_item", photo.id))
            finish()
        }

    }
}