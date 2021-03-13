package com.example.zinrou_gacha.gacha

import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.databinding.DataBindingUtil
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.zinrou_gacha.BaseFragment
import com.example.zinrou_gacha.CardData
import com.example.zinrou_gacha.R
import com.example.zinrou_gacha.databinding.StartGachaFragmentBinding
import kotlinx.android.synthetic.main.gacha_detail_fragment.*

class StartGachaFragment(selectedCardData: CardData) : BaseFragment() {
    private lateinit var binding: StartGachaFragmentBinding
    private var mSelectedCardData = selectedCardData
    companion object {
        fun newInstance(cardData: CardData): StartGachaFragment {
            return StartGachaFragment(cardData)
        }
        const val TAG = "StartGachaFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.start_gacha_fragment, container, false)
        binding.gachaDragon.setImageResource(R.drawable.gacha57)
        binding.gachaButton.setOnClickListener {
//            onGifFinished()
            //TODO GIF画像が終わったタイミングで画面遷移させる
            Glide.with(activity!!).asGif().load(R.raw.gacha_animation).apply(
                RequestOptions.diskCacheStrategyOf(
                    DiskCacheStrategy.NONE
                )
            ).listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    onGifFinished()
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.setLoopCount(1)
                    resource?.registerAnimationCallBack()?.onAnimationEnd(resource)
                    return false
                }
            }).into(binding.gachaDragon)
        }
        return binding.root
    }

    private fun onGifFinished() {
        transitionTo(GachaResultFragment.newInstance(mSelectedCardData))
    }

    fun GifDrawable.registerAnimationCallBack(): Animatable2Compat.AnimationCallback {
        return object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                super.onAnimationEnd(drawable)
                onGifFinished()
            }
        }
    }
}