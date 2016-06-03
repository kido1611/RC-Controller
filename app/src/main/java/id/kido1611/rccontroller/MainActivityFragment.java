package id.kido1611.rccontroller;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private Button mSpeed3, mSpeed2, mSpeed1, mNetral, mReverse, mLeft, mRight, mForward;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mSpeed3 = (Button) rootView.findViewById(R.id.btnSpeed3);
        mSpeed2 = (Button) rootView.findViewById(R.id.btnSpeed2);
        mSpeed1 = (Button) rootView.findViewById(R.id.btnSpeed1);
        mNetral = (Button) rootView.findViewById(R.id.btnNetral);
        mReverse = (Button) rootView.findViewById(R.id.btnReverse);
        mLeft = (Button) rootView.findViewById(R.id.btnLeft);
        mRight = (Button) rootView.findViewById(R.id.btnRight);
        mForward = (Button) rootView.findViewById(R.id.btnForward);

        mSpeed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCode("3");
            }
        });
        mSpeed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCode("2");
            }
        });
        mSpeed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCode("1");
            }
        });
        mNetral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCode("n");
            }
        });
        mReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCode("r");
            }
        });
        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCode("z");
            }
        });
        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCode("x");
            }
        });
        mForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCode("k");
            }
        });

        return rootView;
    }

    private void sendCode(String code){
        ((MainActivity)getActivity()).sendString(code);
    }
}
