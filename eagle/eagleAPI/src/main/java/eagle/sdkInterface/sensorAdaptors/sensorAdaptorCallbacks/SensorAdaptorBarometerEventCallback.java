package eagle.sdkInterface.sensorAdaptors.sensorAdaptorCallbacks;

/**
 * Sensor Adaptor Bearing Event Callback Class
 *
 * @author Nicholas Alards [7178301@student.swin.edu.au]
 * @version 0.0.1
 * @since 30/09/2015
 * <p/>
 * Date Modified	30/09/2015 - Nicholas
 */
public interface SensorAdaptorBarometerEventCallback {
    void onSensorEvent(float getCalibratedData);
}
