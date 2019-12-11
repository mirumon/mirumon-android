package com.redbox.mirumon.main.presentation.util

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.redbox.mirumon.R

class ConfirmDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = activity?.layoutInflater
        val view = inflater?.inflate(R.layout.dialog_confirm, null)

        val adb = AlertDialog.Builder(activity)
            .setView(view)
            .setTitle(R.string.confirm_dialog_message)
            .setPositiveButton(
                R.string.confirm_ok
            ) { _, _ ->
                val intent = Intent()
                intent.putExtra("confirmed", "ok")
                targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
                this.dismiss()
            }

        return adb.create()
    }
}