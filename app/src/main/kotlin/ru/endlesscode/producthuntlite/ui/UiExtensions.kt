/*
 * This file is part of ProductHuntLite, licensed under the MIT License (MIT).
 *
 * Copyright (c) Osip Fatkullin <osip.fatkullin@gmail.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ru.endlesscode.producthuntlite.ui

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import ru.endlesscode.producthuntlite.R
import ru.endlesscode.producthuntlite.ui.common.InfiniteScrollListener

@Suppress("UNCHECKED_CAST")
fun <T : View> ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false)
        = LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot) as T

fun RecyclerView.addOnScrollListener(threshold: Int = 5, action: () -> Unit): InfiniteScrollListener {
    val listener = InfiniteScrollListener(layoutManager as LinearLayoutManager, threshold, action)
    this.addOnScrollListener(listener)

    return listener
}

fun ImageView.load(url: String?) {
    Log.d("tag", url)
    val pic: Picasso = Picasso.with(context)
    pic.setIndicatorsEnabled(true)
    pic.load(url)
            .fit()
            .centerInside()
            .error(R.mipmap.ic_launcher)
            .into(this)
}

fun Context.convertToPx(dp: Float): Float
        = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

fun FragmentManager.commit(fragment: Fragment) {
    this.beginTransaction()
            .add(R.id.main_activity_content, fragment, fragment.tag)
            .addToBackStack(null)
            .commit()
}

fun RecyclerView.addDivider() {
    val divider = DividerItemDecoration(this.context, (layoutManager as LinearLayoutManager).orientation)
    this.addItemDecoration(divider)
}