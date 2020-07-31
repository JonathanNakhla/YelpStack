package com.jonathannakhla.yelpstack.ui

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.max


// Inspired by https://github.com/askNilesh/Viewpager2-Transformation/blob/master/app/src/main/java/com/asknilesh/StackSliderTransformer.kt
// and https://stackoverflow.com/a/51106475/1928142
class StackSliderTransformer: ViewPager2.PageTransformer {

    companion object {
        private const val ALPHA = 0.5f
    }

    override fun transformPage(page: View, position: Float) {
        page.apply {
            val pageWidth = width
            elevation = -position
            when {
                position <= -1f -> { // [-Infinity, -1]
                    // This page is way off-screen to the left.
                    alpha = ALPHA
                }
                position <= 0f -> { // (-1, 0]
                    // page move from left to center.

                    alpha = 1f
                    translationX = 0f
                }
                position <= 1f -> { // (0, 1]
                    // page move from right to center.

                    alpha = max(1f - position, ALPHA)

                    translationX = pageWidth * -position
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }

        }

    }
}