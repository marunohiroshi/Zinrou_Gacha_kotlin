package com.example.zinrou_gacha.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.zinrou_gacha.BaseFragment
import com.example.zinrou_gacha.CardData
import com.example.zinrou_gacha.R
import com.example.zinrou_gacha.databinding.SelectCollectionFragmentBinding
import java.util.*

class SelectCollectionMenuFragment : BaseFragment() {
    private lateinit var binding: SelectCollectionFragmentBinding

    companion object {
        fun newInstance(): SelectCollectionMenuFragment {
            return SelectCollectionMenuFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.select_collection_fragment, container, false)
        binding.setOnClick() {
            when(it.id) {
                binding.selectedCardCollectionCardView.id -> transitionTo(CollectionCardListFragment.newInstance(CardData.getCollectionCardData()))
                binding.cardBackDesignCollectionCardView.id -> transitionTo(CollectionCardListFragment.newInstance(CardData.getCollectionCardData()))
                binding.allCardCollectionCardView.id -> transitionTo(CollectionCardListFragment.newInstance(CardData.values().toMutableList()))
            }
        }
        return binding.root
    }
}