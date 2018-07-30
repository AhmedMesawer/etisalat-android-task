package com.etisalat.sampletask

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.android.synthetic.main.toolbar_foods.*

class KotlinActivity : AppCompatActivity() {

    private val imageCaptureRequestCode = 1
    private val cameraPermissionRequestCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        setSupportActionBar(toolbar)
        photo.setOnClickListener { capturePhoto() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == imageCaptureRequestCode && resultCode == Activity.RESULT_OK) {
            val bitmap = data?.extras?.get("data") as Bitmap
            photo.setImageBitmap(bitmap)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            cameraPermissionRequestCode -> {
                if (grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else {
                    Toast.makeText(this, "need camera permission", Toast.LENGTH_LONG)
                            .show()
                }
            }
            else -> return
        }
    }

    private fun capturePhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
                        cameraPermissionRequestCode)
            }
        } else {
            openCamera()
        }
    }

    private fun openCamera() {
        val camIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (camIntent.resolveActivity(packageManager) != null)
            startActivityForResult(camIntent, imageCaptureRequestCode)
    }
}
