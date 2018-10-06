package jp.co.penguin.gourmetsearch.util

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import jp.co.penguin.gourmetsearch.R

class AlertDialogFragment: DialogFragment() {

    companion object {
        private var listener: AlertDialogListener? = null
        private var title: String = ""
        private var message: String = ""

        fun newInstance(title: String, message: String, lintener: AlertDialogListener): AlertDialogFragment {
            val fragment = AlertDialogFragment()
            this.title = title
            this.message = message
            this.listener = lintener
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.btn_ok) { dialog, which ->
                    listener?.onClickPositive()
                }
                .setNegativeButton(R.string.btn_cancel) { dialog, which ->
                    listener?.onClickNegative()
                }
        return builder.create()
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }

    interface AlertDialogListener {
        fun onClickPositive()
        fun onClickNegative()
    }
}