package au.edu.swin.sparrow.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import au.edu.swin.sparrow.R;
import eagle.sdkInterface.sensorAdaptors.AdaptorBearing;
import eagle.sdkInterface.sensorAdaptors.sensorAdaptorCallbacks.SensorAdaptorCallback;

/**
 * Created by cameron on 8/29/15.
 */
public class BearingFragment extends SensorFragment {

    AdaptorBearing bearing = null;

    private Activity activity;

    private TextView sensorOutput1DataTextView = null;
    private TextView sensorOutput2DataTextView = null;
    private TextView sensorOutput3DataTextView = null;

    public static BearingFragment newInstance() {
        BearingFragment fragment = new BearingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public BearingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_sensor_3_output, container, false);

        activity = getActivity();

        TextView sensorOutputTitleTextView = (TextView) view.findViewById(R.id.textViewSensorOutputTitle);
        TextView sensorOutput1TitleTextView = (TextView) view.findViewById(R.id.textViewSensorOutput1Title);
        TextView sensorOutput2TitleTextView = (TextView) view.findViewById(R.id.textViewSensorOutput2Title);
        TextView sensorOutput3TitleTextView = (TextView) view.findViewById(R.id.textViewSensorOutput3Title);

        sensorOutputTitleTextView.setText(getResources().getString(R.string.bearing));
        sensorOutput1TitleTextView.setText(getResources().getString(R.string.x_axis_));
        sensorOutput2TitleTextView.setText(getResources().getString(R.string.y_axis_));
        sensorOutput3TitleTextView.setText(getResources().getString(R.string.azimuth_));

        sensorOutput1DataTextView = (TextView) view.findViewById(R.id.textViewSensorOutput1Data);
        sensorOutput2DataTextView = (TextView) view.findViewById(R.id.textViewSensorOutput2Data);
        sensorOutput3DataTextView = (TextView) view.findViewById(R.id.textViewSensorOutput3Data);

        return view;
    }

    public void setMagneticAccelerometerAdaptors(AdaptorBearing adaptorBearing) {
        this.bearing = adaptorBearing;
        this.bearing.addSensorAdaptorCallback(new SensorAdaptorCallback() {
            @Override
            public void onSensorChanged() {
                updateData();
            }
        });
    }

    @Override
    public void updateData() {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (bearing != null && bearing.isConnectedToSensor() && bearing.isDataReady()) {
                        sensorOutput1DataTextView.setText(String.valueOf(bearing.getData()[0]));
                        sensorOutput2DataTextView.setText(String.valueOf(bearing.getData()[1]));
                        sensorOutput3DataTextView.setText(String.valueOf(bearing.getData()[2]));
                    } else {
                        sensorOutput1DataTextView.setText("");
                        sensorOutput2DataTextView.setText("");
                        sensorOutput3DataTextView.setText("");
                    }
                }
            });
        }
    }
}
