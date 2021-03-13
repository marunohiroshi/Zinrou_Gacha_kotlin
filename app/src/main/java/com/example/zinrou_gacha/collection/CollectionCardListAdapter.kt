package com.example.zinrou_gacha.collection

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.zinrou_gacha.CardData
import com.example.zinrou_gacha.R
import com.example.zinrou_gacha.databinding.CollectionCardRecyclerviewItemBinding

class CollectionCardListAdapter(context: Context, displayCardList: MutableList<CardData>) : RecyclerView.Adapter<CollectionCardListAdapter.Holder>() {
    private lateinit var binding: CollectionCardRecyclerviewItemBinding
    private val inflater = LayoutInflater.from(context)
    private var mSelectedCardList: MutableList<CardData> = displayCardList
    private lateinit var mClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = DataBindingUtil.inflate(inflater, R.layout.collection_card_recyclerview_item, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        when (mSelectedCardList[position].cardCamp) {
            "市民" -> holder.binding.cardViewBackgroundColor = Color.BLUE
            "人狼" -> holder.binding.cardViewBackgroundColor = Color.RED
            else ->
                holder.binding.cardViewBackgroundColor = Color.GRAY
        }
        holder.binding.cardName = mSelectedCardList[position].cardName
        holder.binding.cardDesign = mSelectedCardList[position].cardDrawable
        holder.itemView.setOnClickListener {
            mClickListener.onItemClickListener(it, position)
        }
        //DataBindingの非同期処理を即座に実行させる
        //上から下にスクロールするときのちらつきをなくすため
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return mSelectedCardList.size
    }

    interface OnItemClickListener {
        fun onItemClickListener(view: View, position: Int)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.mClickListener = listener
    }

    //インナークラス
    class Holder(val binding: CollectionCardRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)
}