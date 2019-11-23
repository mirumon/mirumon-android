package com.redbox.mirumon.main.presentation.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.redbox.mirumon.R
import kotlinx.android.synthetic.main.recycler_button.view.btn_txt_tv
import kotlinx.android.synthetic.main.recycler_button.view.icon_iv
import kotlinx.android.synthetic.main.recycler_button.view.open_iv

class RecyclerButton constructor(
    context: Context,
    attributeSet: AttributeSet
) : ConstraintLayout(context, attributeSet) {

    var buttonIcon: ImageView? = null
    var buttonText: TextView? = null
    var stateOpened: Boolean = false

    init {
        val inflater =
            getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.recycler_button, this)
        buttonIcon = icon_iv
        buttonText = btn_txt_tv
        setAttrs(attributeSet)
    }

    fun setActionListener(listener: () -> Unit) {
        open_iv.setOnClickListener {
            listener()
            openList()
        }
    }

    private fun setAttrs(attributeSet: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.RecyclerButton,
            0,
            0
        )
        setButtonText(attributes.getString(R.styleable.RecyclerButton_recyclerButtonText))
        setIcon(attributes.getDrawable(R.styleable.RecyclerButton_recyclerButtonIcon))
    }

    private fun setButtonText(text: String?) {
        buttonText?.text = text
    }

    private fun setIcon(drawable: Drawable?) {
        buttonIcon?.setImageDrawable(drawable)
    }

    private fun openList() {
        if (!stateOpened) {
            open_iv.rotation = 180F
            stateOpened = true
        } else {
            open_iv.rotation = 0F
            stateOpened = false
        }
    }
}