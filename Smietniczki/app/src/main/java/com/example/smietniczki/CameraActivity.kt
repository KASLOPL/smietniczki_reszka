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

class CameraActivity : AppCompatActivity() {

    companion object {
        private const val CAMERA_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)

        val bitmap: Bitmap? = intent.getParcelableExtra("image")

        val imageView = findViewById<ImageView>(R.id.iv_image)
        imageView.setImageBitmap(bitmap)

        // Przycisk do ponownego robienia zdjęcia
        val btnRetake = findViewById<Button>(R.id.button6)
        btnRetake.setOnClickListener {
            // Uruchom aparat ponownie
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }

        // Przycisk do zamknięcia aktywności
        val textView3 = findViewById<TextView>(R.id.textView3)
        textView3.setOnClickListener {
            finish()
        }

        val btnSend = findViewById<Button>(R.id.button5)
        btnSend.setOnClickListener {
            val intent = Intent(this, PointsActivity::class.java)
            startActivity(intent)
        }

    }

    // Obsługuje wynik po wykonaniu zdjęcia
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST_CODE) {
            val thumbnail: Bitmap = data!!.extras!!.get("data") as Bitmap
            val imageView = findViewById<ImageView>(R.id.iv_image)
            imageView.setImageBitmap(thumbnail) // Ustawiamy zrobione zdjęcie w ImageView

            // Możesz dodać dodatkową logikę, jeśli chcesz przekazać to zdjęcie dalej
        }
    }
}
