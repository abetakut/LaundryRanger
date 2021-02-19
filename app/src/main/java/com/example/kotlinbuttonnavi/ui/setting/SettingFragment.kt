package com.example.kotlinbuttonnavi.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinbuttonnavi.R
import com.example.kotlinbuttonnavi.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    private lateinit var settingViewModel: SettingViewModel
    val bundle = Bundle()
    val homeFragment = HomeFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingViewModel =
            ViewModelProviders.of(this).get(SettingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_setting, container, false)
        val textView: TextView = root.findViewById(R.id.text_setting)
        settingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        //ToDo:設定画面で都市設定
        //ToDo:文字入力完了ボタンをGboardに設定
//        button_setCity.setOnClickListener {
//            if (editText_City.text == null){
//                textView_cityName.text = editText_City.toString()
//                bundle.putString("BUNDLE_KEY_CITY", textView_cityName.toString())
//                homeFragment.arguments = bundle
//                parentFragmentManager.beginTransaction().add(R.id.navigation_home, homeFragment).commit()
//            }
//        }

        return root
    }

}