package com.nick.mowen.notificationbanner.listener

import android.view.View
import com.nick.mowen.notificationbanner.data.BannerInfo

interface BannerClickListener {

    fun onClick(view: View?, info: BannerInfo)
}