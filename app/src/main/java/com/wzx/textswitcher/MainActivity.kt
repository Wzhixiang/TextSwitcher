package com.wzx.textswitcher

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.ViewSwitcher
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 文本切换使用
 * @see android.widget.TextSwitcher
 *
 * 使用场景：广播、广告
 *
 * 需要在setFactory(View.OnClickListener)方法中设置TextView样式
 *
 * 通过setText(CharSequence)方法设置需展示文本
 *
 * 对进场动画和出场动画有需求，了解setInAnimation()、setOutAnimation()
 *
 */
class MainActivity : AppCompatActivity(), ViewSwitcher.ViewFactory, View.OnClickListener {

    var times = 0

    override fun onClick(v: View?) {
        times++
        textSwitcher.setText("广告$times")
    }

    override fun makeView(): View {
        val textView = TextView(this)
        textView.textSize = 14f
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
        val leftDrawable = ContextCompat.getDrawable(this, R.drawable.ic_broadcast_accent_24dp)
        leftDrawable!!.setBounds(0, 0, leftDrawable.intrinsicWidth, leftDrawable.intrinsicHeight)
        /**
         * 设置TextView的Drawable时，需要先对Drawable设置bound
         */
        textView.setCompoundDrawables(leftDrawable, null, null, null)
        textView.compoundDrawablePadding = 16
        textView.gravity = Gravity.CENTER_VERTICAL
        val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER_VERTICAL
        textView.layoutParams = layoutParams
        return textView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textSwitcher.setFactory(this)

        nextBTN.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()

        textSwitcher.setText("广告$times")
    }
}
