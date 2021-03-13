package com.example.zinrou_gacha.gacha

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.zinrou_gacha.BaseFragment
import com.example.zinrou_gacha.CardData
import com.example.zinrou_gacha.R
import com.example.zinrou_gacha.databinding.SelectGachaBinding
import java.util.*

class SelectGachaFragment : BaseFragment() {
    private lateinit var binding: SelectGachaBinding

    companion object {
        fun newInstance(): SelectGachaFragment {
            return SelectGachaFragment()
        }

        const val TAG = "SelectGachaFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.select_gacha, container, false)
        binding.gachaWord = "ガチャを回す"
//        val customFont = Typeface.createFromAsset(resources.assets, "rcsknmMinAna.ttf")
//        binding.gachaStartWord.typeface = customFont
        binding.gachaStartButton.setOnClickListener {
            //TODO ガチャで当たるカードをRoom or Realm or データベースからランダムに取得
            //TODO アプリを再起動しても当たったカードデータが残っているように
            val randomCardData = randomEnum(CardData::class.java)
            Log.i(TAG, randomCardData.cardName + "が選択されました")
            CardData.addCollectionCardData(randomCardData)
            transitionTo(StartGachaFragment.newInstance(randomCardData))
        }
        return binding.root
    }

    /**
     * CardDataEnumクラスからランダムに一つ要素を取得
     */
    private fun <T : Enum<*>?> randomEnum(clazz: Class<T>): T {
        val random = Random()
        val x: Int = random.nextInt(clazz.enumConstants!!.size)
        return clazz.enumConstants!![x]
    }
}