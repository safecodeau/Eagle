package au.edu.swin.sparrow.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import au.edu.swin.sparrow.R;
import eagle.sdkInterface.sensorAdaptors.AdaptorUltrasonic;

/**
 * Created by cameron on 8/29/15.
 */
public class UltrasonicFragment extends SensorFragment {

    AdaptorUltrasonic ultrasonic = null;

    private Activity activity;

    private TextView sensorOutput1DataTextView = null;

    public static UltrasonicFragment newInstance() {
        UltrasonicFragment fragment = new UltrasonicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public UltrasonicFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_sensor_1_output, container, false);

        activity = getActivity();

        TextView sensorOutputTitleTextView = (TextView) view.findViewById(R.id.textViewSensorOutputTitle);
        TextView sensorOutput1TitleTextView = (TextView) view.findViewById(R.id.textViewSensorOutput1Title);

        sensorOutputTitleTextView.setText(getResources().getString(R.string.ultrasonic));
        sensorOutput1TitleTextView.setText(getResources().getString(R.string.distance_cm_));

        sensorOutput1DataTextView = (TextView) view.findViewById(R.id.textViewSensorOutput1Data);

        return view;
    }

    public void setUltrasonicAdaptor(AdaptorUltrasonic Ultrasonic) {
        this.ultrasonic = Ultrasonic;
    }

    @Override
    public void updateData() {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (ultrasonic != null && ultrasonic.isConnectedToSensor() && ultrasonic.isDataReady())
                        sensorOutput1DataTextView.setText(String.valueOf(ultrasonic.getData()));
                    else
                        sensorOutput1DataTextView.setText("");
                }
            });
        }
    }
}
