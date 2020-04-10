package com.example.coronatracker

import android.bluetooth.BluetoothManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private var bleServer: BLEServer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStart() {
        super.onStart()

        val bluetoothManager = getSystemService(android.content.Context.BLUETOOTH_SERVICE)
                as BluetoothManager
        var bluetoothAdapter = bluetoothManager.adapter

        bleServer = BLEServer(bluetoothAdapter)
        bleServer?.start()
    }
}
