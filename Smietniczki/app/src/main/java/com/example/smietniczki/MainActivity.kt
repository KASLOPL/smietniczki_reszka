package com.example.smietniczki

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSegregacja = findViewById<Button>(R.id.button_1)
        btnSegregacja.setOnClickListener {
            val intent = Intent(this, SegregActivity::class.java)
            startActivity(intent)
        }

        val btnDaty = findViewById<Button>(R.id.button2)
        btnDaty.setOnClickListener {
            val intent = Intent(this, DatyActivity::class.java)
            startActivity(intent)
        }

        val btnPunkty = findViewById<Button>(R.id.button3)
        btnPunkty.setOnClickListener {
            val intent = Intent(this, PunktyActivity::class.java)
            startActivity(intent)
        }

        val btnCamera = findViewById<Button>(R.id.btn_camera)
        btnCamera.setOnClickListener {

            if(ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }else{
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }else{
                Toast.makeText(
                    this,
                    "DAJ MNIE TĄ PERMISJEEEEE!!!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CAMERA_REQUEST_CODE){
                val thumbnail: Bitmap = data!!.extras!!.get("data") as Bitmap

                // Tworzymy intencję do otwarcia CameraActivity
                val intent = Intent(this, CameraActivity::class.java)
                intent.putExtra("image", thumbnail) // Przekazujemy obrazek
                startActivity(intent) // Uruchamiamy nowe activity
            }
        }
    }
}
