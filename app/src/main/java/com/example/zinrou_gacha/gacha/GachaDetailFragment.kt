package com.example.zinrou_gacha.gacha

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.zinrou_gacha.BaseFragment
import com.example.zinrou_gacha.CardData
import com.example.zinrou_gacha.R
import com.example.zinrou_gacha.databinding.GachaDetailFragmentBinding

class GachaDetailFragment(selectedCardData: CardData): BaseFragment() {
    private lateinit var binding: GachaDetailFragmentBinding
    private var mSelectedCardData = selectedCardData
    companion object {
        fun newInstance(selectedCardData: CardData): GachaDetailFragment {
            return GachaDetailFragment(selectedCardData)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.gacha_detail_fragment, container, false)
        binding.cardName.text = mSelectedCardData.cardName
        binding.cardFeature.text = mSelectedCardData.cardEffect
        binding.cardCamp.text = "【" + mSelectedCardData.cardCamp + "】"
        binding.selectedCard.setImageResource(mSelectedCardData.cardDrawable)
        Glide.with(activity!!).asGif().load(R.raw.card_detail_background).into(binding.cardBackgroundAnimation)
        return binding.root
    }


}