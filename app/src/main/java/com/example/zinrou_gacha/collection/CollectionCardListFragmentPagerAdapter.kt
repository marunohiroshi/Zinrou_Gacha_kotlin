package com.example.zinrou_gacha.collection

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.zinrou_gacha.CardData

class CollectionCardListFragmentPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

//    override fun getItemCount(): Int {
//        // すべて、市民、人狼、その他
//        // を表示するページ
//        return 4
//    }

//    override fun createFragment(position: Int): Fragment {
//        return when (position) {
//            0 -> CollectionCardListFragment.newInstance(CardData.getCollectionCardData())
//
//            1 -> CollectionCardListFragment.newInstance(CardData.getCollectionCardData().filter {
//                it.cardCamp == "市民"
//            } as MutableList<CardData>)
//
//            2 -> CollectionCardListFragment.newInstance(CardData.getCollectionCardData().filter {
//                it.cardCamp == "人狼"
//            } as MutableList<CardData>)
//
//            3 -> CollectionCardListFragment.newInstance(CardData.getCollectionCardData().filter {
//                (it.cardCamp == "妖狐|その他|恋人|サンタ|ゾンビ")
////                    .and(it.cardCamp == "その他").and(it.cardCamp == "恋人")
////                    .and(it.cardCamp == "サンタ").and(it.cardCamp == "ゾンビ")
//            } as MutableList<CardData>)
//
//            else -> CollectionCardListFragment.newInstance(CardData.getCollectionCardData())
//        }
//    }

    override fun getCount(): Int {
        // すべて、市民、人狼、その他
        // を表示するページ
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CollectionCardListFragment.newInstance(CardData.getCollectionCardData())

            1 -> CollectionCardListFragment.newInstance(CardData.getCollectionCardData().filter {
                it.cardCamp == "市民"
            } as MutableList<CardData>)

            2 -> CollectionCardListFragment.newInstance(CardData.getCollectionCardData().filter {
                it.cardCamp == "人狼"
            } as MutableList<CardData>)

            3 -> CollectionCardListFragment.newInstance(CardData.getCollectionCardData().filter {
                (it.cardCamp == "妖狐|その他|恋人|サンタ|ゾンビ")
//                    .and(it.cardCamp == "その他").and(it.cardCamp == "恋人")
//                    .and(it.cardCamp == "サンタ").and(it.cardCamp == "ゾンビ")
            } as MutableList<CardData>)

            else -> CollectionCardListFragment.newInstance(CardData.getCollectionCardData())
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "全陣営"
            1 -> "市民"
            2 -> "人狼"
            3 -> "その他"
            else -> null
        }
    }


}