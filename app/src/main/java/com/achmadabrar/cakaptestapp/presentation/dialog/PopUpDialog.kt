package com.achmadabrar.cakaptestapp.presentation.dialog

import android.content.Context
import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDialog
import com.achmadabrar.cakaptestapp.R
import kotlinx.android.synthetic.main.layout_popup_dialog.*

private const val NAME = "name"
private const val PHONE = "phone"
private const val EMAIL = "email"
private const val CREATED_AT = "createdAt"

class PopUpDialog(
    context: Context?,
    val listener: Listener
) : AppCompatDialog(context, R.style.Cakap_AlertDialogTheme) {

    var SELECTED_BUTTON = "name"
    var IS_ASC = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_popup_dialog)

        cb_name.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) SELECTED_BUTTON = NAME
        }

        cb_email.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) SELECTED_BUTTON = EMAIL
        }

        cb_phone.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                SELECTED_BUTTON = PHONE
            }
        }

        cb_created_at.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) SELECTED_BUTTON = CREATED_AT else  SELECTED_BUTTON = NAME //default
        }

        cb_asc.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) IS_ASC = true
        }

        cb_desc.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) IS_ASC = false else IS_ASC = true //deafult
        }


        btn_submit_filter.setOnClickListener {
            listener.onClickSubmitButton(this, SELECTED_BUTTON, IS_ASC)
        }

    }

    interface Listener {
        fun onClickSubmitButton(dialog: PopUpDialog, selectedButton: String, isAsc: Boolean)
    }
}