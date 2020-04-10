package com.example.coronatracker

import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.os.Build
import android.os.ParcelUuid
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*
private const val TAG = "Peripheral"
class BLEServer (private  var bluetoothAdapter: BluetoothAdapter){

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun start(){
         var advertiseSettings = AdvertiseSettings.Builder()
             .setConnectable(false)
             .setTimeout(180000).build()
        val parcelUuid = ParcelUuid.fromString("1615b5e4-5399-4842-935b-32e8c184090c")
        val byteArray = "1615b5e486876".toByteArray(Charsets.UTF_8)
        var  advertiseData = AdvertiseData.Builder()
             .addServiceData(parcelUuid ,byteArray).build()
        println(advertiseData.toString())
        bluetoothAdapter.bluetoothLeAdvertiser
            .startAdvertising(advertiseSettings,advertiseData,advertiseCallback)
    }


    private val advertiseCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object: AdvertiseCallback() {
        override fun onStartFailure(errorCode: Int) {
            Log.w(TAG, String.format("Advertisement failure (code %d)", errorCode))
        }
        override fun onStartSuccess(settingsInEffect: AdvertiseSettings?) {
            Log.i(TAG, "Advertisement started")
        }
    }


}