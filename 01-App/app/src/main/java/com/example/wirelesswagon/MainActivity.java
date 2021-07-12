package com.example.wirelesswagon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView_DiscoveryStatus;
    private TextView mTextView_RSSIValue;
    private Button mButton_StartDiscovery;

    //non-widget special datatypes/objects
    private BluetoothAdapter btAdapter;


    private static final String DEVICE_NAME = "InspectorGadget";
    private static final int MY_REQUEST_CODE = 1;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG = "WirelessWagonActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

        //setup widgets to their views
        mTextView_DiscoveryStatus = (TextView)findViewById(R.id.textView_DiscoveryStatus);
        mTextView_RSSIValue = (TextView)findViewById(R.id.textView_RSSIValue);
        mButton_StartDiscovery = (Button)findViewById(R.id.button_StartDiscovery);

        //Initialize Bluetooth Adapter
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        //Initialize strings
        mTextView_DiscoveryStatus.setText(R.string.status_no);

        mButton_StartDiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btAdapter.isDiscovering()) {
                    btAdapter.cancelDiscovery();
                }
                else {
                    String permissions[] = {Manifest.permission.ACCESS_FINE_LOCATION};
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {

                        Log.i("info", "No fine location permissions");

                        /*ActivityCompat.requestPermissions(this,
                                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                1)*/
                        ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
                    }

                    btAdapter.cancelDiscovery();
                    btAdapter.startDiscovery();
                }
            }
        });

        //we'll set up this broadcast receiver to determine when bluetooth state changes for convenience of button appearence and what not
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, filter);
    }
    

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");

        if (!btAdapter.isEnabled()) {
            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BT);
        }

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
        btAdapter.cancelDiscovery();
    }




    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        mButton_StartDiscovery.setEnabled(false);
                        break;
                    case BluetoothAdapter.STATE_ON:
                        mButton_StartDiscovery.setEnabled(true);
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        break;
                    case BluetoothAdapter.STATE_DISCONNECTED:
                        break;
                }
            }

            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
                if (name != null) {
                    if (name.contains(DEVICE_NAME)) {
                        short number = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                        mTextView_RSSIValue.setText(Short.toString(number));
                        btAdapter.cancelDiscovery();
                    }
                }
            }

            if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
                mTextView_DiscoveryStatus.setText(R.string.status_yes);
            }
            if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                mTextView_DiscoveryStatus.setText(R.string.status_no);
            }
        }
    };

    private void createLocationRequest() {
        LocationRequest locationRequest = LocationRequest
    }

}