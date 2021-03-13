package com.example.zinrou_gacha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.zinrou_gacha.collection.CollectionCardListAdapter
import com.example.zinrou_gacha.collection.CollectionCardListFragment
import com.example.zinrou_gacha.collection.CollectionCardListFragmentPagerAdapter
import com.example.zinrou_gacha.gacha.SelectGachaFragment
import com.example.zinrou_gacha.collection.SelectCollectionMenuFragment
import com.example.zinrou_gacha.databinding.ActivityMainBinding
import com.example.zinrou_gacha.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mCurrentFragment: BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //BottomNavigationViewタップ時の挙動
        //TODO ガチャ挙動時はタップできないようにする　
        // https://qiita.com/k_yamao/items/b201286573700f89e7c0
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(
            object : BottomNavigationView.OnNavigationItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    when (item.itemId) {
                        R.id.bottom_navigation_gacha -> {
                            transitionTo(SelectGachaFragment.newInstance())
                            return true
                        }
                        R.id.bottom_navigation_collection -> {
                            transitionTo(SelectCollectionMenuFragment.newInstance())
                            return true
                        }
                        R.id.bottom_navigation_settings -> {
                            transitionTo(SettingsFragment.newInstance())
                            return true
                        }
                    }
                    return false
                }
            }
        )

        if (savedInstanceState == null) {
            val newFragment = SelectGachaFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.container, newFragment, SelectGachaFragment.TAG)
                .commitNow()
            mCurrentFragment = newFragment
        }

        //ViewPager作成
        val pagerAdapter = CollectionCardListFragmentPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
    }

    /**
     * バックキータップ時挙動
     */
    //TODO BottomNavigationViewの各画面をまたがないようにする
    override fun onBackPressed() {
//        val fragmentManager = supportFragmentManager
//        fragmentManager.popBackStack()
        super.onBackPressed()
    }

    fun transitionTo(newFragment: BaseFragment): Int {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, newFragment, newFragment::class.simpleName)
        fragmentTransaction.addToBackStack(newFragment::class.simpleName)
        return fragmentTransaction.commit()
        //TODO 出来ればFragmentの遷移いい感じに
        //https://github.com/DesarrolloAntonio/FragmentTransactionExtended
    }
}