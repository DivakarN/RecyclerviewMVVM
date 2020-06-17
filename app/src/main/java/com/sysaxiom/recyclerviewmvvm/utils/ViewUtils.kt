package com.sysaxiom.recyclerviewmvvm.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.sysaxiom.recyclerviewmvvm.R

fun Context.longToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.shortToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ProgressBar.show(activity: Activity){
    visibility = View.VISIBLE
    activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun ProgressBar.hide(activity: Activity){
    visibility = View.GONE
    activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun TextView.show(){
    visibility = View.VISIBLE
}

fun TextView.hide(){
    visibility = View.GONE
}

fun View.longSnackbar(message: String, length: Int): Snackbar {
    return Snackbar.make(this, message, Snackbar.LENGTH_LONG)
}

fun View.shortSnackbar(message: String, length: Int): Snackbar {
    return Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
}

fun View.indefiniteSnackbar(message: String, length: Int): Snackbar {
    return Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
}

fun Snackbar.showSnackbar() {
     show()
}

fun Snackbar.hideSnackbar() {
    dismiss()
}

interface AlertDialogListener {
    fun onPositive()
    fun onNegative()
}

fun Context.alertDialog(title: String,
    message: String,
    positiveButtonText: String?,
    negativeButtonText: String?,
    cancelable: Boolean?,
    icon: Drawable?,
    listener : AlertDialogListener
) {
    try {
        val builder = AlertDialog.Builder(this)
        if(icon!=null){
            builder.setIcon(icon)
        }
        builder.setTitle(title)
        builder.setMessage(message)
        if(cancelable!=null){
            builder.setCancelable(cancelable)
        }
        if(positiveButtonText!=null){
            builder.setPositiveButton(positiveButtonText,{ dialogInterface, i ->  listener.onPositive() })
        }
        if(negativeButtonText!=null){
            builder.setNegativeButton(negativeButtonText,{ dialogInterface, i ->  listener.onNegative() })
        }
        val alertDialog : AlertDialog = builder.create()
        alertDialog.show()
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
    } catch (e: Exception) {
        Log.d("ViewHandler", e.toString())
    }
}

fun Context.initiazeTextview(constraintLayout : ConstraintLayout) : TextView{
    val textView = TextView(this)
    val layoutParams = ConstraintLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT)
    textView.setTextColor(resources.getColor(R.color.colorBlack,null))
    layoutParams.bottomToBottom = constraintLayout.bottom
    layoutParams.topToTop = constraintLayout.top
    layoutParams.leftToLeft = constraintLayout.left
    layoutParams.rightToRight = constraintLayout.right
    textView.layoutParams = layoutParams
    constraintLayout.addView(textView)
    return textView
}

fun Context.initiazeProgressBar(mainConstraintLayout : ConstraintLayout) : ProgressBar{
    val progressBar = ProgressBar(this)
    progressBar.isIndeterminate = true
    progressBar.indeterminateTintList = ColorStateList.valueOf(Color.BLACK)
    val layoutParams = ConstraintLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutParams.bottomToBottom = mainConstraintLayout.bottom
    layoutParams.topToTop = mainConstraintLayout.top
    layoutParams.leftToLeft = mainConstraintLayout.left
    layoutParams.rightToRight = mainConstraintLayout.right
    progressBar.layoutParams = layoutParams
    mainConstraintLayout.addView(progressBar)
    return progressBar
}

fun customView(backgroundColor: Int, borderColor: Int) : GradientDrawable {
    val shape = GradientDrawable()
    shape.shape = GradientDrawable.RECTANGLE
    shape.cornerRadius = 8f
    shape.setColor(backgroundColor)
    shape.setStroke(4, borderColor)
    return shape
}
