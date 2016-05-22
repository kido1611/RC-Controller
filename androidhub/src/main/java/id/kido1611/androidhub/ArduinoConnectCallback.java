package id.kido1611.androidhub;

import android.bluetooth.BluetoothSocket;

/**
 * Created by kido1611 on 5/22/16.
 */
public interface ArduinoConnectCallback {

    void onConnect(BluetoothSocket socket);
    void onFailed();
}
