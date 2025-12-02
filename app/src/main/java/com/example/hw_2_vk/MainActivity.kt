package com.example.hw_2_vk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_test_activity)


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, ImagesFragment())
            .commit()


    }

}



