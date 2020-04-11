package com.example.coronatracker

import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.os.Build
import android.os.ParcelUuid
import android.util.Log
import androidx.annotation.RequiresApi

private const val TAG = "Central"

class BLEClient (private  var bluetoothAdapter: BluetoothAdapter) {
    @RequiresApi(Build.VERSION_CODES.M)
    fun start() {

        val scanSettings = ScanSettings.Builder()
            .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
            .setMatchMode(ScanSettings.MATCH_MODE_STICKY).build()
        val scanFilter = ScanFilter.Builder()
            .setServiceUuid(ParcelUuid.fromString("1615b5e4-5399-4842-935b-32e8c184090c"))
            .build()
        bluetoothAdapter.bluetoothLeScanner.startScan(mutableListOf(scanFilter),scanSettings,scanCallback)

    }

    private val scanCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ScanCallback() {
        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
            super.onBatchScanResults(results)
            for (el in results!!.iterator()) {
                Log.i(TAG, String.format("Scanner give result (code %s)", el.toString()))//should print one of these
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Log.i(TAG, String.format("Scanner failure (code %d)", errorCode))
        }

        override fun onScanResult(callbackType: Int, result: ScanResult?) {//should print one of these
            super.onScanResult(callbackType, result)

            Log.i(TAG, String.format("Scanner give result (code %s)", result!!.toString()))
        }
    }
}