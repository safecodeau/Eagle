package eagle.sdkInterface.sensorAdaptors.accelerometerAdaptors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import eagle.sdkInterface.sensorAdaptors.AdaptorAccelerometer;
import eagle.sdkInterface.sensorAdaptors.sensorAdaptorCallbacks.SensorAdaptorCallback;

/**
 * Android Accelerometer Adaptor
 *
 * @author Nicholas Alards [7178301@student.swin.edu.au]
 * @version 0.0.1
 * @since 09/04/2015
 * <p/>
 * Date Modified	26/05/2015 - Nicholas
 */

public class AndroidAccelerometer extends AdaptorAccelerometer implements SensorEventListener {
    private Context context = null;
    private float[] accelerometerData = null;

    public AndroidAccelerometer() {
        super("Android", "Accelerometer", "0.0.1");
    }

    @Override
    public boolean connectToSensor() {
        if (this.context == null)
            return false;
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
            return true;
        } else
            return false;
    }

    //TODO Following Method Need Proper Implementation
    @Override
    public boolean disconnectFromSensor() {
        return false;
    }


    @Override
    public boolean setAndroidContext(Object object) {
        if (object instanceof Context) {
            this.context = (Context) object;
            return true;
        } else
            return false;
    }

    @Override
    public boolean isConnectedToSensor() {
        if (context == null)
            return false;
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
            return true;
        else
            return false;
    }

    @Override
    public float[] getData() {
        return accelerometerData;
    }

    @Override
    public boolean isDataReady() {
        if (accelerometerData == null)
            return false;
        else
            return true;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if(accelerometerData==null)
                accelerometerData = new float[3];
            accelerometerData[0] = event.values[0];
            accelerometerData[1] = event.values[1];
            accelerometerData[2] = event.values[2];
        }
        for (SensorAdaptorCallback currentSensorAdaptorCallback : sensorAdaptorCallbacks)
            currentSensorAdaptorCallback.onSensorChanged();
    }
}
