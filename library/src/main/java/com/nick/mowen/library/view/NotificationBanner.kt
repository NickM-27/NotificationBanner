package com.nick.mowen.library.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.nick.mowen.library.R
import com.nick.mowen.library.data.BannerInfo
import com.nick.mowen.library.listener.BannerClickListener

class NotificationBanner : MaterialCardView {

    private lateinit var avatar: ImageView
    private lateinit var name: TextView
    private lateinit var text: TextView
    private val animatorSet = AnimatorSet()
    private val translate = ObjectAnimator.ofFloat(this, "TranslationY", -60f, 1f).setDuration(300)
    private val fade = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f).setDuration(300)
    lateinit var info: BannerInfo
    var listener: BannerClickListener? = null
    var hideAfterClick = true

    constructor(context: Context) : super(context) {
        bindViews(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        bindViews(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        bindViews(context)
    }

    private fun bindViews(context: Context) {
        translate.interpolator = AccelerateDecelerateInterpolator()
        fade.interpolator = OvershootInterpolator()
        val view = LayoutInflater.from(context).inflate(R.layout.view, this)
        avatar = view.findViewById(R.id.banner_profile)
        name = view.findViewById(R.id.banner_name)
        text = view.findViewById(R.id.banner_text)
        setOnTouchListener(BannerGestureListener(context, this, object : BannerGestureListener.ClickListener {

            override fun onClick(view: View?) {
                if (hideAfterClick) {
                    handler.removeCallbacks(hideAction)
                    visibility = View.INVISIBLE
                }

                listener?.onClick(info)
            }

            override fun onFlingUp(view: View?) {
                handler.removeCallbacks(hideAction)
                hideAction.run()
            }
        }))
    }

    private fun show() {
        handler.removeCallbacks(hideAction)
        visibility = View.VISIBLE
        animatorSet.playTogether(translate, fade)
        animatorSet.start()
        handler.postDelayed(hideAction, 7000)
    }

    private val hideAction = Runnable {
        visibility = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            animatorSet.reverse()
            View.INVISIBLE
        } else
            View.INVISIBLE
    }

    fun notifyMessage() {
        if (!::info.isInitialized)
            throw IllegalStateException("BannerInfo has not been set")

        avatar.setImageBitmap(info.avatar)
        name.text = info.name
        text.text = info.text
        show()
    }

    fun notifyMessage(info: BannerInfo) {
        this.info = info
        avatar.setImageBitmap(info.avatar)
        name.text = info.name
        text.text = info.text
        show()
    }

    companion object {

        class BannerGestureListener(context: Context, view: View?, clickListener: ClickListener?) : View.OnTouchListener {

            private val gestureDetector: GestureDetector

            init {
                gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {

                    override fun onSingleTapUp(e: MotionEvent): Boolean {
                        if (view != null && clickListener != null)
                            clickListener.onClick(view)

                        return true
                    }

                    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                        return if (clickListener != null && velocityY < -100) {
                            clickListener.onFlingUp(view)
                            true
                        } else
                            super.onFling(e1, e2, velocityX, velocityY)
                    }
                })
            }

            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                gestureDetector.onTouchEvent(p1)
                return true
            }

            interface ClickListener {

                fun onClick(view: View?)
                fun onFlingUp(view: View?)
            }
        }
    }
}