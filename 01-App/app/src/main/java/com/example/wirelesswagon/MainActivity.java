package com.example.wirelesswagon;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button mToggleButton;
    private BluetoothAdapter mBluetoothAdapter;
    private TextView mDeviceDiscoveredStatus;
    private String mDeviceMACAddress;
    private TextView mDeviceName;

    private static final String DEVICE_NAME = "Inspector Gadget";
    private static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG = "WirelessWagonActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

        //This app doesn't work without blue tooth. Ask user to enable it.
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        mDeviceName = (TextView)findViewById(R.id.DeviceName);

        //Now let's set our string that detects if the device was paired.
        mDeviceDiscoveredStatus = (TextView)findViewById(R.id.PairedStatus);


        mToggleButton = (Button)findViewById(R.id.ToggleConnectionButton);
        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleConnectionStatus();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BT);
        }

        evaluateIfDeviceIsDiscovered();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private int toggleConnectionStatus() {

        return 0;
    }

    private void evaluateIfDeviceIsDiscovered() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            boolean deviceFoundInPairedList = false;

            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address

                if (deviceName == DEVICE_NAME)
                {
                    deviceFoundInPairedList = true;
                    mDeviceMACAddress = deviceHardwareAddress;
                    mDeviceName.setText(deviceName);
                    break;
                }
            }

            if (deviceFoundInPairedList) {
                mDeviceDiscoveredStatus.setText(R.string.status_yes);
            }
            else {
                mDeviceDiscoveredStatus.setText(R.string.status_no);
            }
        }
        else {
            mDeviceDiscoveredStatus.setText(R.string.status_no);
        }
    }
}