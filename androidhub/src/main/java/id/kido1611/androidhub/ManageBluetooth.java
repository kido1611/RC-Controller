package id.kido1611.androidhub;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by kido1611 on 5/22/16.
 */
public class ManageBluetooth extends Thread{

    BluetoothSocket mSocket;

    private InputStream mmInStream;
    private OutputStream mmOutStream;

    public ManageBluetooth(BluetoothSocket socket){
        mSocket = socket;
        try {
            mmOutStream = mSocket.getOutputStream();
            mmInStream = mSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        byte[] buffer;  // buffer store for the stream
        int bytes; // bytes returned from read()

        while (true) {
            try {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                buffer = new byte[1024];
                // Read from the InputStream
                bytes = mmInStream.read(buffer);
                // Send the obtained bytes to the UI activity
                //mHandler.obtainMessage(ARDUINO_MSG_RECEIVE, bytes, -1, buffer).sendToTarget();
            } catch (IOException e) {
                break;
            }
        }
    }
}
