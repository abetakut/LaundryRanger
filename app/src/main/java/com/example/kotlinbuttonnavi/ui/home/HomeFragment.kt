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
    private lateinit var homeImage: ImageView
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
    private lateinit var laterTemp3TextView: TextView
    private lateinit var laterTime6TextView: TextView
    private lateinit var laterTemp6TextView: TextView
    private lateinit var laterTime9TextView: TextView
    private lateinit var laterTemp9TextView: TextView
    private lateinit var laterTime12TextView: TextView
    private lateinit var laterTemp12TextView: TextView
    private lateinit var laterTime15TextView: TextView
    private lateinit var laterTemp15TextView: TextView
    private lateinit var laterTime18TextView: TextView
    private lateinit var laterTemp18TextView: TextView
    private lateinit var laterTime21TextView: TextView
    private lateinit var laterTemp21TextView: TextView
    private lateinit var laterTime24TextView: TextView
    private lateinit var laterTemp24TextView: TextView

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
        getImageTask().execute()

        Log.d(TAG, "onCreateView: end")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: start")
        Log.d(TAG, "onViewCreated: set variable")

        addressTextView = view.findViewById(R.id.address)
        updatedAtText = view.findViewById(R.id.updated_at)
        homeImage = view.findViewById(R.id.sentakun_image)
        statusTextView = view.findViewById(R.id.status)
        tempTextView = view.findViewById(R.id.temp)
        loaderProgressBar = view.findViewById(R.id.loader)
        mainContainerRelativeLayout = view.findViewById(R.id.mainContainer)
        errorTextTextView = view.findViewById(R.id.errorText)
        laterTime3TextView = view.findViewById(R.id.laterTime3)
        laterTemp3TextView = view.findViewById(R.id.laterTemp3)
        laterTime6TextView = view.findViewById(R.id.laterTime6)
        laterTemp6TextView = view.findViewById(R.id.laterTemp6)
        laterTime9TextView = view.findViewById(R.id.laterTime9)
        laterTemp9TextView = view.findViewById(R.id.laterTemp9)
        laterTime12TextView = view.findViewById(R.id.laterTime12)
        laterTemp12TextView = view.findViewById(R.id.laterTemp12)
        laterTime15TextView = view.findViewById(R.id.laterTime15)
        laterTemp15TextView = view.findViewById(R.id.laterTemp15)
        laterTime18TextView = view.findViewById(R.id.laterTime18)
        laterTemp18TextView = view.findViewById(R.id.laterTemp18)
        laterTime21TextView = view.findViewById(R.id.laterTime21)
        laterTemp21TextView = view.findViewById(R.id.laterTemp21)
        laterTime24TextView = view.findViewById(R.id.laterTime24)
        laterTemp24TextView = view.findViewById(R.id.laterTemp24)

        Log.d(TAG, "onViewCreated: end")
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
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&lang=$LANG&appid=$API").readText(Charsets.UTF_8)
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
                ICON = weatherIcon.toString()
                Log.d(TAG, "onPostExecute: weatherIcon = $weatherIcon")
                // アップデート時間いる？
                val updatedAtTime = "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt * 1000))
                val temp = main.getString("temp") + "°C"
                Log.d(TAG, "onPostExecute: temp = $temp")
                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val address = jsonObj.getString("name") + ", " + sys.getString("country")

                /* Populating extracted data into our views */
                addressTextView.text = address
                updatedAtText.text = updatedAtTime
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
                        //5 day weather forecast
                    URL("https://api.openweathermap.org/data/2.5/forecast?q=$CITY&units=metric&lang=ja&appid=$API").readText(
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

                var listToday: JSONObject
                var main: JSONObject
                var updatedAt: Long
                var updatedAtText: String
                var temp: String

                //3時間おきの時間と気温の取得と反映
                for (i in timeListArray.indices) {
                    Log.d(TAG, "onPostExecute: for loop $i")
                    listToday = jsonObj.getJSONArray("list").getJSONObject(i)
                    Log.d(TAG, "onPostExecute: list getJSONObject( $i )")
                    main = listToday.getJSONObject("main")
                    updatedAt = listToday.getLong("dt")
                    updatedAtText = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(updatedAt * 1000)); temp = main.getString("temp") + "°C"
                    timeListArray[i].text = updatedAtText
                    Log.d(TAG, "onPostExecute: list( $i ) time = $updatedAtText")
                    tempListArray[i].text = temp
                    Log.d(TAG, "onPostExecute: list( $i ) temp = $temp")
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

        override fun onPostExecute(result: String?){
            Log.d(TAG, "onPostExecute: start")

            //画像の設定
            Picasso.get().load(result).into(homeImage)

            Log.d(TAG, "onPostExecute: end")

        }
    }

    }