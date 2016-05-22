package id.kido1611.androidhub;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import id.kido1611.androidhub.models.ListBluetoothAdapter;

/**
 * Created by kido1611 on 5/22/16.
 */
public class DialogBluetooth extends DialogFragment {

    private BluetoothAdapter mBLAdapter;

    public DialogBluetooth(){

    }

    private ArrayList<BluetoothDevice> mItems = new ArrayList<BluetoothDevice>();

    private ListView mPairedList;
    private ListBluetoothAdapter mPairedAdapter;

    private Button mBtnRefresh;

    ArduinoConnectCallback callback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBLAdapter = BluetoothAdapter.getDefaultAdapter();

        if(mBLAdapter==null){
            Toast.makeText(getActivity(), "Tidak ada bluetooth", Toast.LENGTH_LONG).show();
        }

        if (!mBLAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 0);
        }

    }

    public void setCallback(ArduinoConnectCallback cb){
        callback = cb;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_list_bluetooth, container, false);

        mBtnRefresh = (Button)rootView.findViewById(R.id.btnRefresh);
        mPairedList = (ListView)rootView.findViewById(R.id.list_paired);

        mPairedAdapter = new ListBluetoothAdapter(getActivity(), mItems);
        mPairedList.setAdapter(mPairedAdapter);

        refreshList();

        mBtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshList();
            }
        });

        mPairedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BluetoothDevice mDevice = mItems.get(i);
                ConnectArduino mConnArduino = new ConnectArduino(mDevice, new ArduinoConnectCallback() {
                    @Override
                    public void onConnect(BluetoothSocket socket) {
                        if(callback!=null)callback.onConnect(socket);
                        getDialog().dismiss();
                    }

                    @Override
                    public void onFailed() {

                    }
                });
                mConnArduino.start();
            }
        });

        getDialog().setTitle("Pilih bluetooth");

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            if(resultCode==getActivity().RESULT_CANCELED){
                getActivity().finish();
            }else if(resultCode==getActivity().RESULT_OK){
                refreshList();
            }
        }
    }

    private void refreshList(){
        mItems.clear();
        Set<BluetoothDevice> pairedDevices = mBLAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mItems.add(device);
            }
        }
        mPairedAdapter.notifyDataSetChanged();
    }
}