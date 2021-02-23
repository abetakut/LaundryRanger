package com.example.kotlinbuttonnavi.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinbuttonnavi.R
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

val LAT: String = "35.689889"
val LON: String = "139.847066"
val API: String = "c2a219ef0c9aa522f4d7e55389de631d" // Use API key
var CITY: String? = ""
var LANG: String? = "ja"
var ICON: String? = ""

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"

    //変数の初期化
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recommendTalk: TextView
    private lateinit var homeImage: ImageView
    private lateinit var currentIcon: ImageView
    private lateinit var addressTextView: TextView
    private lateinit var updatedAtText: TextView
    private lateinit var statusTextView: TextView
    private lateinit var tempTextView: TextView
    private lateinit var tempMin: TextView
    private lateinit var tempMax: TextView
    private lateinit var windSpeed: TextView
    private lateinit var loaderProgressBar: ProgressBar
    private lateinit var mainContainerRelativeLayout: RelativeLayout
    private lateinit var errorTextTextView: TextView
    private lateinit var laterTime3TextView: TextView
    private lateinit var laterTime6TextView: TextView
    private lateinit var laterTime9TextView: TextView
    private lateinit var laterTime12TextView: TextView
    private lateinit var laterTime15TextView: TextView
    private lateinit var laterTime18TextView: TextView
    private lateinit var laterTime21TextView: TextView
    private lateinit var laterTime24TextView: TextView
    private lateinit var laterTemp3TextView: TextView
    private lateinit var laterTemp6TextView: TextView
    private lateinit var laterTemp9TextView: TextView
    private lateinit var laterTemp12TextView: TextView
    private lateinit var laterTemp15TextView: TextView
    private lateinit var laterTemp18TextView: TextView
    private lateinit var laterTemp21TextView: TextView
    private lateinit var laterTemp24TextView: TextView
    private lateinit var laterImage3ImageView: ImageView
    private lateinit var laterImage6ImageView: ImageView
    private lateinit var laterImage9ImageView: ImageView
    private lateinit var laterImage12ImageView: ImageView
    private lateinit var laterImage15ImageView: ImageView
    private lateinit var laterImage18ImageView: ImageView
    private lateinit var laterImage21ImageView: ImageView
    private lateinit var laterImage24ImageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: start")
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //設定フラグメントからデータの受け取り
        val dataStore: SharedPreferences = this.requireActivity().getSharedPreferences("DataStore", Context.MODE_PRIVATE)
        val str = dataStore.getString("Input", "675-0011")
        CITY = str
        CITY = "$CITY,jp"
        Log.d(TAG, "onCreateView: CITY = $CITY")

        //APIでJSON取得＆加工
        //Current weather
        weatherTask().execute()
        //5 day weather forecast
        fiveDaysWeatherTask().execute()
        //画像取得設定
//        getImageTask().execute()

        Log.d(TAG, "onCreateView: end")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: start")
        Log.d(TAG, "onViewCreated: set variable")

        addressTextView = view.findViewById(R.id.address)
        recommendTalk = view.findViewById(R.id.recommendText)
        updatedAtText = view.findViewById(R.id.updated_at)
        homeImage = view.findViewById(R.id.sentakun_image)
        currentIcon = view.findViewById(R.id.currentWeatherIcon)
        statusTextView = view.findViewById(R.id.status)
        tempTextView = view.findViewById(R.id.temp)
        loaderProgressBar = view.findViewById(R.id.loader)
        mainContainerRelativeLayout = view.findViewById(R.id.mainContainer)
        errorTextTextView = view.findViewById(R.id.errorText)
        laterTime3TextView = view.findViewById(R.id.laterTime3)
        laterTime6TextView = view.findViewById(R.id.laterTime6)
        laterTime9TextView = view.findViewById(R.id.laterTime9)
        laterTime12TextView = view.findViewById(R.id.laterTime12)
        laterTime15TextView = view.findViewById(R.id.laterTime15)
        laterTime18TextView = view.findViewById(R.id.laterTime18)
        laterTime21TextView = view.findViewById(R.id.laterTime21)
        laterTime24TextView = view.findViewById(R.id.laterTime24)
        laterTemp3TextView = view.findViewById(R.id.laterTemp3)
        laterTemp6TextView = view.findViewById(R.id.laterTemp6)
        laterTemp9TextView = view.findViewById(R.id.laterTemp9)
        laterTemp12TextView = view.findViewById(R.id.laterTemp12)
        laterTemp15TextView = view.findViewById(R.id.laterTemp15)
        laterTemp18TextView = view.findViewById(R.id.laterTemp18)
        laterTemp21TextView = view.findViewById(R.id.laterTemp21)
        laterTemp24TextView = view.findViewById(R.id.laterTemp24)
        laterImage3ImageView = view.findViewById(R.id.laterImage3)
        laterImage6ImageView = view.findViewById(R.id.laterImage6)
        laterImage9ImageView = view.findViewById(R.id.laterImage9)
        laterImage12ImageView = view.findViewById(R.id.laterImage12)
        laterImage15ImageView = view.findViewById(R.id.laterImage15)
        laterImage18ImageView = view.findViewById(R.id.laterImage18)
        laterImage21ImageView = view.findViewById(R.id.laterImage21)
        laterImage24ImageView = view.findViewById(R.id.laterImage24)

        Log.d(TAG, "onViewCreated: end")
    }

    fun setImage(string: String, imageView: ImageView): Int {
        Log.d(TAG, "setImage: start")
        var wetherFlag: Int = 0
        when (string) {
            "01d" -> imageView.setImageResource(R.drawable.icon_01d)
            "01n" -> imageView.setImageResource(R.drawable.icon_01n)
            "02d" -> imageView.setImageResource(R.drawable.icon_02d)
            "02n" -> imageView.setImageResource(R.drawable.icon_02n)
            "03d" -> imageView.setImageResource(R.drawable.icon_03d)
            "03n" -> imageView.setImageResource(R.drawable.icon_03n)
            "04d" -> imageView.setImageResource(R.drawable.icon_04d)
            "04n" -> imageView.setImageResource(R.drawable.icon_04n)

            "09d" -> {
                imageView.setImageResource(R.drawable.icon_09d)
                wetherFlag++
            }
            "09n" -> {
                imageView.setImageResource(R.drawable.icon_09n)
                wetherFlag++
            }
            "10d" -> {
                imageView.setImageResource(R.drawable.icon_10d)
                wetherFlag++
            }
            "10n" -> {
                imageView.setImageResource(R.drawable.icon_10n)
                wetherFlag++
            }
            //thunderstorm
            "11d" -> {
                imageView.setImageResource(R.drawable.icon_11d)
                wetherFlag++
            }
            "11n" -> {
                imageView.setImageResource(R.drawable.icon_11n)
                wetherFlag++
            }
            //snow
            "13d" -> {
                imageView.setImageResource(R.drawable.icon_13d)
                wetherFlag += 13
            }
            "13n" -> {
                imageView.setImageResource(R.drawable.icon_13n)
                wetherFlag += 13
            }
            //mist
            "50d" -> {
                imageView.setImageResource(R.drawable.icon_50d)
                wetherFlag++
            }
            "50n" -> {
                imageView.setImageResource(R.drawable.icon_50n)
                wetherFlag++
            }
        }
        Log.d(TAG, "setImage: wetherFlag = $wetherFlag :end")
        return wetherFlag
    }

    //Current weather
    inner class weatherTask() : AsyncTask<String, Void, String>() {
        private val TAG = "HomeFragment:weatherTask"

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "onPreExecute: start")
            /* Showing the ProgressBar, Making the main design GONE */
            Log.d(TAG, "onPreExecute: end")
        }

        override fun doInBackground(vararg params: String?): String? {
            Log.d(TAG, "doInBackground: start")
            var response: String?
            try {
//                TODO:言語設定もそのうちユーザーが選択できるようにしたい
                response =
                        //Current weather
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&lang=$LANG&appid=$API").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            Log.d(TAG, "doInBackground: end")
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.d(TAG, "onPostExecute: start")
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val jsonArray: JSONArray? = jsonObj.optJSONArray("list")
                if (jsonArray != null) {
                    Log.d(TAG, "onPostExecute: jsonArray.length() = " + jsonArray.length())
                } else {
                    Log.d(TAG, "onPostExecute: null")
                }

                //当日天気
                val main = jsonObj.getJSONObject("main")
                Log.d(TAG, "onPostExecute: main = $main")
                val sys = jsonObj.getJSONObject("sys")
                Log.d(TAG, "onPostExecute: sys = $sys")
                val wind = jsonObj.getJSONObject("wind")
                Log.d(TAG, "onPostExecute: wind = $wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                Log.d(TAG, "onPostExecute: weather = $weather")
                val updatedAt: Long = jsonObj.getLong("dt")
                //画像icon id
                val weatherIcon = weather.getString("icon")
//                ICON = weatherIcon.toString()
                Log.d(TAG, "onPostExecute: weatherIcon = $weatherIcon")
                // アップデート時間いる？
                val updatedAtTime = "更新時刻：\n" + SimpleDateFormat(
                    "M/d k:m",
                    Locale.ENGLISH
                ).format(Date(updatedAt * 1000))
                val temp = main.getString("temp") + "°C"
                Log.d(TAG, "onPostExecute: temp = $temp")
                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val address = jsonObj.getString("name")

                /* Populating extracted data into our views */
                addressTextView.text = address
                updatedAtText.text = updatedAtTime
                setImage(weatherIcon, currentIcon)
                statusTextView.text = weatherDescription.capitalize()
                tempTextView.text = temp

                /* Views populated, Hiding the loader, Showing the main design */
                loaderProgressBar.visibility = View.GONE
                mainContainerRelativeLayout.visibility = View.VISIBLE

            } catch (e: Exception) {
                loaderProgressBar.visibility = View.GONE
                errorTextTextView.visibility = View.VISIBLE
            }
            Log.d(TAG, "onPostExecute: end")
        }

    }

    //5 day weather forecast
    inner class fiveDaysWeatherTask() : AsyncTask<String, Void, String>() {
        private val TAG = "HomeFragment:fiveDaysWeatherTask"

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "onPreExecute: start")
            /* Showing the ProgressBar, Making the main design GONE */
            Log.d(TAG, "onPreExecute: end")
        }

        override fun doInBackground(vararg params: String?): String? {
            Log.d(TAG, "doInBackground: start")
            var response: String?
            try {
//                TODO:言語設定もそのうちユーザーが選択できるようにしたい
                response =
                        //5 day weather forecast each 3 hour
                    URL("https://api.openweathermap.org/data/2.5/forecast?q=$CITY&units=metric&lang=$LANG&appid=$API").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            Log.d(TAG, "doInBackground: end")
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.d(TAG, "onPostExecute: start")
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val jsonArray: JSONArray? = jsonObj.optJSONArray("list")
                if (jsonArray != null) {
                    Log.d(TAG, "onPostExecute: jsonArray.length() = " + jsonArray.length())
                } else {
                    Log.d(TAG, "onPostExecute: jsonArray = null")
                }

                val timeListArray = arrayOf(
                    laterTime3TextView,
                    laterTime6TextView,
                    laterTime9TextView,
                    laterTime12TextView,
                    laterTime15TextView,
                    laterTime18TextView,
                    laterTime21TextView,
                    laterTime24TextView
                )

                val tempListArray = arrayOf(
                    laterTemp3TextView,
                    laterTemp6TextView,
                    laterTemp9TextView,
                    laterTemp12TextView,
                    laterTemp15TextView,
                    laterTemp18TextView,
                    laterTemp21TextView,
                    laterTemp24TextView
                )

                val iconListArray = arrayOf(
                    laterImage3ImageView,
                    laterImage6ImageView,
                    laterImage9ImageView,
                    laterImage12ImageView,
                    laterImage15ImageView,
                    laterImage18ImageView,
                    laterImage21ImageView,
                    laterImage24ImageView
                )

                var listToday: JSONObject
                var main: JSONObject
                var updatedAt: Long
                var updatedAtText: String
                var temp: String
                var weather: JSONObject
                //画像icon id
                var weatherIcon: String
                var weatherCheck = IntArray(8)

                //3時間おきの時間と気温の取得と反映
                for (i in timeListArray.indices) {
                    Log.d(TAG, "onPostExecute: for loop $i")
                    listToday = jsonObj.getJSONArray("list").getJSONObject(i)
                    Log.d(TAG, "onPostExecute: list getJSONObject( $i )")
                    main = listToday.getJSONObject("main")
                    updatedAt = listToday.getLong("dt")
                    updatedAtText =
                        SimpleDateFormat("k:mm", Locale.ENGLISH).format(Date(updatedAt * 1000));
                    temp = main.getString("temp") + "°C"
                    Log.d(TAG, "onPostExecute: check")
                    timeListArray[i].text = updatedAtText
                    Log.d(TAG, "onPostExecute: list( $i ) time = $updatedAtText")
                    weather = listToday.getJSONArray("weather").getJSONObject(0)
                    weatherIcon = weather.getString("icon")
                    Log.d(TAG, "onPostExecute: weatherIcon = $weatherIcon")
                    //画像のあてはめ
                    weatherCheck[i] = setImage(weatherIcon, iconListArray[i])
                    tempListArray[i].text = temp
                    Log.d(TAG, "onPostExecute: list( $i ) temp = $temp")
                }

                when {
                    //直近が雨
                    weatherCheck[0] == 1 -> {
                        recommendTalk.text = "雨やなぁ、休もっか"
                    }
                    //雪のとき
                    weatherCheck[0] == 13 -> {
                        recommendTalk.text = "雪かぁ、休もっか"
                    }
                    //24時間雨じゃない
                    weatherCheck.sum() == 0 -> {
                        recommendTalk.text = "洗濯物干しどきやね"
                    }
                    //雪のとき
                    weatherCheck.sum() > 13 -> {
                        recommendTalk.text = "雪降りそう、また今度にしよか"
                    }
                    //すぐじゃないけど雨予報
                    else -> {
                        recommendTalk.text = "雨降りそう、また今度にしよか"
                    }
                }
                /* Views populated, Hiding the loader, Showing the main design */
                loaderProgressBar.visibility = View.GONE
                mainContainerRelativeLayout.visibility = View.VISIBLE

            } catch (e: Exception) {
                loaderProgressBar.visibility = View.GONE
                errorTextTextView.visibility = View.VISIBLE
            }
            Log.d(TAG, "onPostExecute: end")
        }

    }

    inner class getImageTask() : AsyncTask<String, Void, String>() {
        private val TAG = "HomeFragment:getImageTask"

        override fun doInBackground(vararg params: String?): String? {
            Log.d(TAG, "doInBackground: start")
            var iconUrl: String?
            try {
                //天気アイコン画像取得URL
                //ToDo:Picassoのライセンス記載
                iconUrl = URL("http://openweathermap.org/img/w/$ICON.png").toString()
            } catch (e: Exception) {
                iconUrl = null
            }
            Log.d(TAG, "doInBackground: end")
            return iconUrl

        }

        override fun onPostExecute(result: String?) {
            Log.d(TAG, "onPostExecute: start")

            //画像の設定
            Picasso.get().load(result).into(homeImage)

            Log.d(TAG, "onPostExecute: end")

        }
    }

}