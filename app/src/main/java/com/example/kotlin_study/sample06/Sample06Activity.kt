package com.example.kotlin_study.sample06

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.kotlin_study.R
import kotlinx.android.synthetic.main.activity_sample06.*
import org.jetbrains.anko.AnkoLogger
import kotlin.concurrent.timer

class Sample06Activity : AppCompatActivity(), AnkoLogger {
    companion object {
        const val REQUEST_READ_EXTERNAL_STORAGE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample06)

        //권한체크
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                //이전에 이미 권한이 거부되었을 때 설명
                AlertFragment(onClickListener = {
                    //권한 요청
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_READ_EXTERNAL_STORAGE
                    )
                }).show(supportFragmentManager, "dialog")

            } else {
                //권한 요청
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_EXTERNAL_STORAGE
                )
            }
        } else {
            //권한이 이미 허용되어있음
            getAllPhotos()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_READ_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    //권한 허용됨
                    getAllPhotos()
                } else {
                    //권한 거부
                    Log.e("권한 거부", "check")
                }
            }
        }
    }

    private fun getAllPhotos() {
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"
        )

        val items = arrayListOf<ViewpagerModel>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val uri =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                items.add(ViewpagerModel(uri))
            }
            cursor.close()
        }

        val adapter = ViewpagerAdapter()
        adapter.items = items
        adapter.notifyDataSetChanged()

        viewpager2_main.adapter = adapter

        //3초 마다 페이지넘기기
        timer(period = 3000) {
            runOnUiThread {
                if (viewpager2_main.currentItem < adapter.itemCount - 1) {
                    viewpager2_main.currentItem = viewpager2_main.currentItem + 1
                } else {
                    viewpager2_main.currentItem = 0
                }
            }
        }
    }
}
