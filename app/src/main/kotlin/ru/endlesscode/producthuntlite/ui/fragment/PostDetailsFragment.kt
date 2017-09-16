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

package ru.endlesscode.producthuntlite.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.post_details_fragment.*
import ru.endlesscode.producthuntlite.R
import ru.endlesscode.producthuntlite.api.PostData
import ru.endlesscode.producthuntlite.inflate
import ru.endlesscode.producthuntlite.load
import ru.endlesscode.producthuntlite.mvp.presenter.PostDetailsPresenter
import ru.endlesscode.producthuntlite.mvp.view.PostDetailsView

class PostDetailsFragment : MvpAppCompatFragment(), PostDetailsView {

    companion object {
        fun instance(post: PostData): PostDetailsFragment {
            val args = Bundle()
            args.putSerializable(PostDetailsPresenter.POST_DATA, post)

            val fragment = PostDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var presenter: PostDetailsPresenter

    private val linkFab by lazy { link_fab }
    private val title by lazy { post_title }
    private val desc by lazy { post_desc }
    private val screenshot by lazy { post_screenshot }

    @ProvidePresenter
    fun providePresenter() = PostDetailsPresenter(
            arguments.getSerializable(PostDetailsPresenter.POST_DATA) as PostData
    )

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = container?.inflate(R.layout.post_details_fragment)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linkFab.setOnClickListener { presenter.linkFabClicked() }
    }

    override fun showPost(post: PostData) {
        title.text = post.name
        desc.text = post.desc
        screenshot.load(post.screenshotUrl.px300)
    }
}