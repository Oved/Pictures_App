package com.example.pictures.tools

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object InternetConnection {

    fun isNetworkAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
            } else {
                checkConnection(context)
            }
        }

    fun checkConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        var network = false
        if (networkInfo != null && networkInfo.isConnected) {
            network = true
        }
        val wifi: Boolean = isWifi(context)
        val datos: Boolean = isMobile(context)
        return (wifi || datos || network)
    }

    fun isWifi(context: Context): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (info != null) {
            return info.isConnected
        }
        return false
    }

    fun isMobile(context: Context): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (info != null) {
            return info.isConnected
        }
        return false
    }

}