package com.nick.mowen.notificationbanner.sample.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.nick.mowen.library.data.BannerInfo
import com.nick.mowen.library.listener.BannerClickListener
import com.nick.mowen.library.view.NotificationBanner
import com.nick.mowen.notificationbanner.sample.R

class MainActivity : AppCompatActivity() {

    private lateinit var banner: NotificationBanner
    private lateinit var name: EditText
    private lateinit var text: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        banner.listener = object : BannerClickListener {
            override fun onClick(info: BannerInfo) {
                Toast.makeText(this@MainActivity, "${info.name} and ${info.text} clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindViews() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        banner = findViewById(R.id.entry_banner)
        name = findViewById(R.id.entry_name)
        text = findViewById(R.id.entry_text)
    }

    fun notify(@Suppress("UNUSED_PARAMETER") view: View?) {
        val icon = BitmapFactory.decodeResource(resources, R.drawable.ic_unloaded_contact)
        val name = name.text.toString()
        val text = text.text.toString()
        banner.notifyMessage(BannerInfo(icon, name, text))
    }
}