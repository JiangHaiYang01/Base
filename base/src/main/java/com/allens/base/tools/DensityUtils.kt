package com.allens.base.tools

import android.content.res.Resources
import android.util.TypedValue


//px2dp
fun Float.dp() =
    this / Resources.getSystem().displayMetrics.density

//dp2px
fun Float.px() =
    this * Resources.getSystem().displayMetrics.density

fun Float.sp2px() =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this, Resources.getSystem().displayMetrics
    )

fun Float.px2sp() =
    (this / Resources.getSystem().displayMetrics.scaledDensity)

object DensityUtils {
    fun dp2px(dp: Float): Float = dp.px()

    fun px2dp(px: Float): Float = px.dp()

    fun sp2px(sp: Float): Float = sp.sp2px()

    fun px2sp(px: Float): Float = px.px2sp()
}