package com.example.kotlinbuttonnavi.ui.setting

import Communicator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinbuttonnavi.R
import kotlinx.android.synthetic.main.fragment_setting.view.*

class SettingFragment : Fragment() {
    private val TAG = "SettingFragment"

    private lateinit var settingViewModel: SettingViewModel
    private lateinit var communicator: Communicator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: start")
        settingViewModel = ViewModelProviders.of(this).get(SettingViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        val textView: TextView = view.findViewById(R.id.text_setting)
        settingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        communicator = activity as Communicator

        //ToDo:設定画面で都市設定
        //ToDo:文字入力完了ボタンをGboardに設定
        view.button_setCity.setOnClickListener{
            Log.d(TAG, "onCreateView: button click")
            communicator.passDataCom(view.editText_City.text.toString())
        }

        Log.d(TAG, "onCreateView: end")
        return view
    }

}