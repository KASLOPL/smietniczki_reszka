package com.example.smietniczki

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast

class PunktyActivity : AppCompatActivity() {

    companion object {
        private const val CAMERA_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.punkty)

        val textView3 = findViewById<TextView>(R.id.textView3)
        textView3.setOnClickListener {
            finish()
        }

    }



}
