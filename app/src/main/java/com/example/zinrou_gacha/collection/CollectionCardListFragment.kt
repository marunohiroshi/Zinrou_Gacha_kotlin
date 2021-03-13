package com.example.zinrou_gacha.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zinrou_gacha.BaseFragment
import com.example.zinrou_gacha.CardData
import com.example.zinrou_gacha.R
import com.example.zinrou_gacha.databinding.CollectionCardListRecyclerviewBinding
import com.example.zinrou_gacha.gacha.GachaDetailFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CollectionCardListFragment(displayCardDataList: MutableList<CardData>): BaseFragment() {
    private lateinit var binding: CollectionCardListRecyclerviewBinding
    private var mDisplayCardDataList: MutableList<CardData> = displayCardDataList

    companion object {
        fun newInstance(displayCardDataList: MutableList<CardData>): CollectionCardListFragment {
            return CollectionCardListFragment(displayCardDataList)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.collection_card_list_recyclerview, container, false)
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.containerRecyclerView.layoutManager = gridLayoutManager
        //RecyclerViewサイズの固定化、パフォーマンス向上
        binding.containerRecyclerView.setHasFixedSize(true)

        val adapter = context?.let { CollectionCardListAdapter(it, mDisplayCardDataList) }

        binding.containerRecyclerView.adapter = adapter
        adapter?.setOnClickListener(object : CollectionCardListAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, position: Int) {
                transitionTo(GachaDetailFragment.newInstance(mDisplayCardDataList[position]))
            }
        })
        //ViewPager作成
//        val pagerAdapter = CollectionCardListFragmentPagerAdapter(fragmentManager!!)
//        binding.viewPager.adapter = pagerAdapter

//        val pagerAdapter =
//            CollectionCardListFragmentPagerAdapter(CollectionCardListFragment.newInstance(CardData.getCollectionCardData()))
//        binding.viewPager.adapter = pagerAdapter

        //TabLayoutのTitle設定
//        val mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab_layout: TabLayout.Tab, position: Int ->
//            tab_layout.text = getTabTitle(position)
//            binding.viewPager.setCurrentItem(tab_layout.position, true)
//        }
//        mediator.attach()

        return binding.root
    }

    /**
     * 引数で渡されたpositionによって
     * TabLayoutのタイトルを返す
     */
    private fun getTabTitle(position: Int): String? {
        return when (position) {
            0 -> "全陣営"
            1 -> "市民"
            2 -> "人狼"
            3 -> "その他"
            else -> null
        }
    }

    private fun makeFragment(viewId: Int, position: Int): String {
        return "android:switcher:$viewId:$position"
    }
}