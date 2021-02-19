package com.example.kotlinbuttonnavi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MainActivity"

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    //    TODO:使用都市はユーザーが自分で選べるようにする
//    var CITY: String = "tokyo,jp"
//    val LAT: String = "35.689889"
//    val LON: String = "139.847066"
//    val API: String = "c2a219ef0c9aa522f4d7e55389de631d" // Use API key

    //    TODO:画面の切り替えいらん？1画面で一旦全部終わらせるか考えとく
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("test", "onCreate: start")
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        //APIでJSON取得＆加工
//        weatherTask().execute()
//        fiveDaysWeatherTask().execute()

    }


    //Current weather
//    inner class weatherTask() : AsyncTask<String, Void, String>() {
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            Log.d(TAG, "weatherTask onPreExecute: start")
//            /* Showing the ProgressBar, Making the main design GONE */
////            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
////            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
////            findViewById<TextView>(R.id.errorText).visibility = View.GONE
//            Log.d(TAG, "weatherTask onPreExecute: finish")
//        }
//
//        //        TODO:未来5日分のデータ欲しい
//        override fun doInBackground(vararg params: String?): String? {
//            var response: String?
//            Log.d(TAG, "weatherTask doInBackground: start")
//            try {
////                TODO:言語設定もそのうちユーザーが選択できるようにしたい
//                response =
//                    //Current weather
//                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&lang=ja&appid=$API").readText(
//                        Charsets.UTF_8
//                    )
//            } catch (e: Exception) {
//                response = null
//            }
//            Log.d(TAG, "weatherTask doInBackground: end")
//            return response
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            Log.d(TAG, "weatherTask onPostExecute: start")
//            try {
//                /* Extracting JSON returns from the API */
//                val jsonObj = JSONObject(result)
//                val jsonArray: JSONArray? = jsonObj.optJSONArray("list")
//                if (jsonArray != null) {
//                    Log.d(TAG, "onPostExecute: jsonArray.length() = " + jsonArray.length())
//                } else {
//                    Log.d(TAG, "onPostExecute: null")
//                }
//
//                //当日天気
//                val main = jsonObj.getJSONObject("main")
//                Log.d(TAG, "onPostExecute: main = $main")
//                val sys = jsonObj.getJSONObject("sys")
//                Log.d(TAG, "onPostExecute: sys = $sys")
//                val wind = jsonObj.getJSONObject("wind")
//                Log.d(TAG, "onPostExecute: wind = $wind")
//                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
//                Log.d(TAG, "onPostExecute: weather = $weather")
//                val updatedAt: Long = jsonObj.getLong("dt")
//                // アップデート時間いる？
//                val updatedAtText =
//                    "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
//                        Date(updatedAt * 1000)
//                    )
//                val temp = main.getString("temp") + "°C"
//                Log.d(TAG, "onPostExecute: temp = $temp")
//                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
//                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
//                val sunrise: Long = sys.getLong("sunrise")
//                val sunset: Long = sys.getLong("sunset")
//                val windSpeed = wind.getString("speed")
//                val weatherDescription = weather.getString("description")
//                val address = jsonObj.getString("name") + ", " + sys.getString("country")
//
//                /* Populating extracted data into our views */
//                findViewById<TextView>(R.id.address).text = address
////                findViewById<TextView>(R.id.updated_at).text =  updatedAtText
//                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
//                findViewById<TextView>(R.id.temp).text = temp
////                findViewById<TextView>(R.id.temp_min).text = tempMin
////                findViewById<TextView>(R.id.temp_max).text = tempMax
////                findViewById<TextView>(R.id.sunrise).text =
////                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
////                findViewById<TextView>(R.id.sunset).text =
////                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
////                findViewById<TextView>(R.id.wind).text = windSpeed
//
//                /* Views populated, Hiding the loader, Showing the main design */
//                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
//                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
//
//            } catch (e: Exception) {
//                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
//                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
//            }
//            Log.d(TAG, "weatherTask onPostExecute: end")
//        }
//
//    }
//
//    //5 day weather forecast
//    inner class fiveDaysWeatherTask() : AsyncTask<String, Void, String>() {
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            Log.d(TAG, "fiveDaysWeatherTask onPreExecute: start")
//            /* Showing the ProgressBar, Making the main design GONE */
////            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
////            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
////            findViewById<TextView>(R.id.errorText).visibility = View.GONE
//            Log.d(TAG, "fiveDaysWeatherTask onPreExecute: end")
//        }
//
//        //        TODO:未来5日分のデータ欲しい
//        override fun doInBackground(vararg params: String?): String? {
//            var response: String?
//            Log.d(TAG, "fiveDaysWeatherTask doInBackground: start")
//            try {
////                TODO:言語設定もそのうちユーザーが選択できるようにしたい
//                response =
//                        //5 day weather forecast
//                    URL("https://api.openweathermap.org/data/2.5/forecast?q=$CITY&units=metric&lang=ja&appid=$API").readText(
//                        Charsets.UTF_8
//                    )
//            } catch (e: Exception) {
//                response = null
//            }
//            Log.d(TAG, "fiveDaysWeatherTask doInBackground: end")
//            return response
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            Log.d(TAG, "fiveDaysWeatherTask onPostExecute: start")
//            try {
//                /* Extracting JSON returns from the API */
//                val jsonObj = JSONObject(result)
//                val jsonArray: JSONArray? = jsonObj.optJSONArray("list")
//                if (jsonArray != null) {
//                    Log.d(TAG, "onPostExecute: jsonArray.length() = " + jsonArray.length())
//                } else {
//                    Log.d(TAG, "onPostExecute: jsonArray = null")
//                }
//
//                //当日天気
//                val listToday = jsonObj.getJSONArray("list").getJSONObject(1)
//                Log.d(TAG, "onPostExecute: listToday = $listToday")
//                val main = listToday.getJSONObject("main")
//                Log.d(TAG, "onPostExecute: main = $main")
//                val weather = listToday.getJSONArray("weather").getJSONObject(0)
//                Log.d(TAG, "onPostExecute: weather = $weather")
//                val wind = listToday.getJSONObject("wind")
//                Log.d(TAG, "onPostExecute: wind = $wind")
//                val city = jsonObj.getJSONObject("city")
//                Log.d(TAG, "onPostExecute: city = $city")
//
//                val updatedAt: Long = listToday.getLong("dt")
//                val updatedAtText = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(updatedAt * 1000))
//                val temp = main.getString("temp") + "°C"
//                Log.d(TAG, "onPostExecute: temp = $temp")
//                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
//                Log.d(TAG, "onPostExecute: tempMin = $tempMin")
//                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
//                Log.d(TAG, "onPostExecute: tempMax = $tempMax")
//                val sunrise: Long = city.getLong("sunrise")
//                val sunset: Long = city.getLong("sunset")
//                val windSpeed = wind.getString("speed")
//                val weatherDescription = weather.getString("description")
//                val address = city.getString("name") + ", " + city.getString("country")
//
//                /* Populating extracted data into our views */
////                findViewById<TextView>(R.id.address).text = address
//                findViewById<TextView>(R.id.laterTime3).text = updatedAtText
////                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
//                findViewById<TextView>(R.id.laterTemp3).text = temp
////                findViewById<TextView>(R.id.temp_min).text = tempMin
////                findViewById<TextView>(R.id.temp_max).text = tempMax
////                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
////                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
////                findViewById<TextView>(R.id.wind).text = windSpeed
//
//                //TODO:日数分取得してリストに入れたい
////                if (jsonArray != null) {
////                    for (i in 0 until jsonArray.length()) {
////                        Log.d(TAG, "onPostExecute: i = $i")
//                //何個目のjsonデータか判別
////                        val jsonObj = jsonArray.getJSONObject(i)
//
////                    }
////                }
//
//                /* Views populated, Hiding the loader, Showing the main design */
////                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
////                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
//
//            } catch (e: Exception) {
////                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
////                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
//            }
//            Log.d(TAG, "fiveDaysWeatherTask onPostExecute: end")
//        }
//
//    }
//
//    //One Call API
//    inner class dailyWeatherTask() : AsyncTask<String, Void, String>() {
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            /* Showing the ProgressBar, Making the main design GONE */
//            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
//            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
//            findViewById<TextView>(R.id.errorText).visibility = View.GONE
//        }
//
//        override fun doInBackground(vararg params: String?): String? {
//            var response: String?
//            try {
//                //        TODO:未来5日分のデータ欲しい
////                TODO:言語設定もそのうちユーザーが選択できるようにしたい
////                TODO:現在地取得をユーザーで設定できるようにする
//                response =
//                        //One Call API
//                    URL("https://api.openweathermap.org/data/2.5/onecall?lat=$LAT&lon=$LON&exclude=daily&units=metric&lang=ja&appid=$API").readText(
//                        Charsets.UTF_8
//                    )
//            } catch (e: Exception) {
//                response = null
//            }
//            return response
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            try {
//                /* Extracting JSON returns from the API */
//                val jsonObj = JSONObject(result)
//
//                //当日天気
//                val current = jsonObj.getJSONObject("current")
//                Log.d(TAG, "onPostExecute: current = $current")
//                val weather = current.getJSONArray("weather").getJSONObject(0)
//                Log.d(TAG, "onPostExecute: weather = $weather")
//                val temp = current.getString("temp") + "°C"
//                Log.d(TAG, "onPostExecute: temp = $temp")
//                val windSpeed = current.getString("wind_speed")
//                Log.d(TAG, "onPostExecute: windSpeed = $windSpeed")
//                val weatherDescription = weather.getString("description")
//                Log.d(TAG, "onPostExecute: weatherDescription = $weatherDescription")
//
////                val jsonArray: JSONArray? = jsonObj.getJSONArray("daily")
////                if (jsonArray != null) {
////                    Log.d(TAG, "onPostExecute: jsonArray.length() = " + jsonArray.length())
////                } else {
////                    Log.d(TAG, "onPostExecute: jsonArray = null")
////                }
//                //TODO:日数分取得してリストに入れたい
////                if (jsonArray != null) {
////                    for (i in 0 until jsonArray.length()) {
////                        Log.d(TAG, "onPostExecute: i = $i")
////                        //何個目のjsonデータか判別
////                        val jsonObj = jsonArray.getJSONObject(i)
////
////                    }
////                }
//
////                val daily = jsonObj.getJSONObject("daily")
////                Log.d(TAG, "onPostExecute: daily = $daily")
////                val dailyTemp = daily.getJSONObject("temp")
////                Log.d(TAG, "onPostExecute: dailyTemp = $dailyTemp")
////                val tempMin = "Min Temp: " + dailyTemp.getString("min") + "°C"
////                Log.d(TAG, "onPostExecute: tempMin = $tempMin")
////                val tempMax = "Max Temp: " + dailyTemp.getString("max") + "°C"
////                Log.d(TAG, "onPostExecute: tempMax = $tempMax")
////                val sunrise: Long = daily.getLong("sunrise")
////                Log.d(TAG, "onPostExecute: sunrise = $sunrise")
////                val sunset: Long = daily.getLong("sunset")
////                Log.d(TAG, "onPostExecute: sunset = $sunset")
////                val address = jsonObj.getString("timezone")
////                Log.d(TAG, "onPostExecute: address = $address")
//
////                val wind = jsonObj.getJSONObject("wind")
////                Log.d(TAG, "onPostExecute: wind = $wind")
//
//
//                /* Populating extracted data into our views */
////                findViewById<TextView>(R.id.address).text = address
//                //                findViewById<TextView>(R.id.updated_at).text =  updatedAtText
//                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
//                findViewById<TextView>(R.id.temp).text = temp
////                findViewById<TextView>(R.id.temp_min).text = tempMin
////                findViewById<TextView>(R.id.temp_max).text = tempMax
////                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
////                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
////                findViewById<TextView>(R.id.wind).text = windSpeed
//
//                /* Views populated, Hiding the loader, Showing the main design */
////                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
////                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
//
//            } catch (e: Exception) {
////                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
////                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
//            }
//
//        }
//
//    }

}