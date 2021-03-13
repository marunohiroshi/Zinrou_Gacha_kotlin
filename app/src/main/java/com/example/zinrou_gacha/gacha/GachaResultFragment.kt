package com.example.zinrou_gacha.gacha

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.zinrou_gacha.BaseFragment
import com.example.zinrou_gacha.CardData
import com.example.zinrou_gacha.R
import com.example.zinrou_gacha.databinding.GachaResultFragmentBinding
import java.lang.IllegalArgumentException
import com.bumptech.glide.load.resource.gif.GifDrawable

import com.bumptech.glide.load.engine.GlideException

import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import javax.sql.DataSource
import android.animation.AnimatorListenerAdapter

import android.view.animation.AccelerateDecelerateInterpolator

import android.view.animation.DecelerateInterpolator

import android.animation.ObjectAnimator
import android.os.Handler
import androidx.core.view.isVisible


class GachaResultFragment(selectedCardData: CardData) : BaseFragment(){
    private lateinit var binding: GachaResultFragmentBinding
    private var mSelectedCardData = selectedCardData
    private var cardTapCount: Int = 0
    companion object {
        fun newInstance(cardData: CardData): GachaResultFragment {
            return GachaResultFragment(cardData)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.gacha_result_fragment, container, false)
        binding.cardBackDesign.setImageResource(R.drawable.card_design96)
        Glide.with(activity!!).asGif().load(R.raw.card_open_background2).into(binding.backgroundAnimation)
        val oa1 = ObjectAnimator.ofFloat(binding.cardBackDesign, "scaleX", 1f, 0f)
        val oa2 = ObjectAnimator.ofFloat(binding.cardFrontDesign, "scaleX", 0f, 1f)
        oa1.duration = 900
        oa2.duration = 900
        oa1.interpolator = DecelerateInterpolator()
        oa2.interpolator = AccelerateDecelerateInterpolator()
        oa1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                binding.cardFrontDesign.setImageResource(mSelectedCardData.cardDrawable)
                oa2.start()
            }
        })
        binding.cardBackDesign.setOnClickListener {
            if (cardTapCount == 0) {
                Glide.with(activity!!).asGif().load(R.raw.card_open_animation).listener(object : RequestListener<GifDrawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                    override fun onResourceReady(
                        resource: GifDrawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<GifDrawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        resource?.setLoopCount(1)
                        return false
                    }
                }).into(binding.cardOpenAnimation)
                binding.backgroundAnimation.visibility = View.INVISIBLE
                oa1.start()
                cardTapCount++
                //TODO カードオープンのアニメーション中は次の画面に遷移できないようにする
            } else {
                transitionTo(GachaDetailFragment.newInstance(mSelectedCardData))
            }
        }
        return binding.root
    }

    private fun getResourceID(resName: String, resType: String?, context: Context): Int {
        val ResourceID: Int =
            context.getResources().getIdentifier(resName, resType, context.getApplicationInfo().packageName)
        return if (ResourceID == 0) {
            throw IllegalArgumentException("No resource string found with name $resName")
        } else {
            ResourceID
        }
    }

}