package com.example.wirelesswagon;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Button mToggleButton;
    private Button mStartDiagnosticActivityButton;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothSocket mBluetoothSocket = null;

    private TextView mDeviceDiscoveredStatusTextView;
    private String mDeviceMACAddress;
    private BluetoothDevice mDevice;

    private TextView mConnectionStatusTextView;

    private TextView mDeviceNameTextView;
    private UUID mMyAPPsUUID;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private int rssi = 0;


    //private final BluetoothSocket InspectorSocket;

    private static final String DEVICE_NAME = "InspectorGadget";
    private static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG = "WirelessWagonActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

        //This app doesn't work without blue tooth. Ask user to enable it.
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mMyAPPsUUID = UUID.randomUUID();

        mDeviceNameTextView = (TextView)findViewById(R.id.DeviceName);

        //Now let's set our string that detects if the device was paired.
        mDeviceDiscoveredStatusTextView = (TextView)findViewById(R.id.PairedStatus);
        mConnectionStatusTextView = (TextView)findViewById(R.id.ConnectionStatus);

        mStartDiagnosticActivityButton = (Button)findViewById(R.id.StartDiagnosticActivityButton);
        mStartDiagnosticActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startDiagnosticActivity(); }
        });

        mToggleButton = (Button)findViewById(R.id.ToggleConnectionButton);
        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //t.start();
                startConnection();
            }
        });

        //we'll set up this broadcast receiver to determine when bluetooth state changes for convenience of button appearence and what not
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);

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
        mBluetoothAdapter.cancelDiscovery();
    }

    private int toggleConnectionStatus() {

        return 0;
    }

    final Thread t = new Thread(new Runnable() {

        @Override
        public void run() {
            startConnection();
        }
    });

    private int startDiagnosticActivity() {
        return 0;
    }

    private void evaluateIfDeviceIsDiscovered() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        String deviceName = null;
        String deviceHardwareAddress = null; // MAC address

        if (pairedDevices.size() > 0) {
            boolean deviceFoundInPairedList = false;

            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                deviceName = device.getName();
                deviceHardwareAddress = device.getAddress(); // MAC address

                if (deviceName.contains(DEVICE_NAME) )
                {
                    deviceFoundInPairedList = true;
                    mDevice = device;
                    mDeviceMACAddress = deviceHardwareAddress;
                    mDeviceNameTextView.setText(deviceName);

                    //we'll set up one last broadcast receiver to be explicitly used in the determining rssi once connection is established
                    registerReceiver(rssiReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));

                    break;
                }
            }

            if (deviceFoundInPairedList) {
                mDeviceDiscoveredStatusTextView.setText(R.string.status_yes);
                mDeviceNameTextView.setText(deviceName);
            }
            else {
                mDeviceDiscoveredStatusTextView.setText(R.string.status_no);
            }
        }
        else {
            mDeviceDiscoveredStatusTextView.setText(R.string.status_no);
        }
    }

    private final BroadcastReceiver rssiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (mDevice.ACTION_FOUND.equals(action)) {

                sendRSSIValue(intent.getShortExtra(mDevice.EXTRA_RSSI, Short.MIN_VALUE));

            }
        }
    };

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        mToggleButton.setEnabled(false);
                        mStartDiagnosticActivityButton.setEnabled(false);
                        break;
                    case BluetoothAdapter.STATE_ON:
                        mToggleButton.setEnabled(true);
                        evaluateIfDeviceIsDiscovered();
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        mConnectionStatusTextView.setText(R.string.status_yes);
                        break;
                    case BluetoothAdapter.STATE_DISCONNECTED:
                        mConnectionStatusTextView.setText(R.string.status_no);
                        break;
                }
            }
        }
    };

    private void startConnection() {

        //first we connect to the bluetooth device:
        int temp = 0;
        do {
        //while (!mBluetoothSocket.isConnected() || mBluetoothSocket == null) {
        //while (mBluetoothSocket == null) {
            try {

                BluetoothDevice hc05 = mBluetoothAdapter.getRemoteDevice(mDeviceMACAddress);
                mBluetoothSocket = hc05.createInsecureRfcommSocketToServiceRecord(myUUID);
                //mBluetoothSocket = hc05.createInsecureRfcommSocketToServiceRecord(myUUID);
                System.out.println(mBluetoothSocket);
                mBluetoothSocket.connect();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!mBluetoothSocket.isConnected() || mBluetoothSocket == null);

        do{
            boolean disc = mBluetoothAdapter.startDiscovery();
            System.out.println(disc);
            mBluetoothSocket.
        } while (!mBluetoothAdapter.isDiscovering());

        //now we should be connected. Start Discovery so that we can get RSSI
        //boolean disc = mBluetoothAdapter.startDiscovery();

        //With discovery having been started, wait for an rssi value
        //while(true) {}

    }

    private void sendRSSIValue(int _rssi) {

        try { // Converting the string to bytes for transferring
            rssi = _rssi;
            mBluetoothSocket.getOutputStream().write(Integer.toString(_rssi).getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}