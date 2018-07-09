package com.nick.mowen.notificationbanner.listener

import android.view.View
import com.nick.mowen.notificationbanner.view.NotificationBanner
import com.nick.mowen.notificationbanner.data.BannerInfo

interface BannerClickListener {

    /**
     * Listener to react to banner click action
     *
     * @param view [NotificationBanner] that was clicked
     * @param info [BannerInfo] that was shown
     */
    fun onClick(view: View, info: BannerInfo)
}