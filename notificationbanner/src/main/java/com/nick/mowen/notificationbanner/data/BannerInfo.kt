package com.nick.mowen.notificationbanner.data

import android.graphics.Bitmap
import androidx.annotation.Keep

@Keep
open class BannerInfo(val avatar: Bitmap, val name: String, val text: String)