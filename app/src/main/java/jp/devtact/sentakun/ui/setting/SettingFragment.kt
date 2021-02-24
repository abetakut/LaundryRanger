package jp.devtact.sentakun.ui.setting

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import jp.devtact.sentakun.R
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting.view.*

class SettingFragment : Fragment() {
    private val TAG = "SettingFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: start")
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        val dataStore: SharedPreferences = this.requireActivity().getSharedPreferences("DataStore", Context.MODE_PRIVATE)

        view.button_setCity.setOnClickListener{
            Log.d(TAG, "onCreateView: button click")
            //設定PostCodeデータの保存
            val stringText = editText_City.text.toString()
            val editor = dataStore.edit()
            editor.putString("Input",stringText)
            editor.apply()
            Log.d(TAG, "onCreateView: set PostCode data")
            //ホームフラグメントへ移動
            view.findNavController().navigate(R.id.navigation_home)
        }

        Log.d(TAG, "onCreateView: end")
        return view
    }

}