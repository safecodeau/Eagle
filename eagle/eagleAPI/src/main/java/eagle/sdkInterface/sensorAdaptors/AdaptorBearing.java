package eagle.sdkInterface.sensorAdaptors;

/**
 * Bearing Adaptor Interface
 *
 * @author Nicholas Alards [7178301@student.swin.edu.au]
 * @version 0.0.1
 * @since 04/10/2015
 * <p/>
 * Date Modified	0410/2015 - Nicholas
 */
public abstract class AdaptorBearing extends SensorAdaptor {

    public static final int AXISX = 0;
    public static final int AXISY = 1;
    public static final int AXISZ = 2;
    public static final int AZIMUTH = 2;

    private float[] calibrationOffset = null;

    protected AdaptorAccelerometer adaptorAccelerometer;
    protected AdaptorMagnetic adaptorMagnetic;

    public AdaptorBearing(String adaptorManufacturer, String adaptorModel, String adaptorVersion) {
        super(adaptorManufacturer, adaptorModel, adaptorVersion);
    }

    public void setAccelerometerMagnetic(AdaptorAccelerometer adaptorAccelerometer, AdaptorMagnetic adaptorMagnetic){
        this.adaptorAccelerometer=adaptorAccelerometer;
        this.adaptorMagnetic=adaptorMagnetic;
    }

    public abstract boolean connectToSensor();

    public abstract float[] getData();

    public float[] getCalibratedData() {
        float[] value = getData();
        if (value == null || getCalibrationOffset() == null || value.length < 3)
            return null;
        else {
            float[] calibratedData = new float[3];
            calibratedData[AXISX] = value[AXISX] - getCalibrationOffset()[AXISX];
            calibratedData[AXISY] = value[AXISY] - getCalibrationOffset()[AXISY];
            calibratedData[AZIMUTH] = value[AZIMUTH] - getCalibrationOffset()[AZIMUTH];
            return calibratedData;
        }
    }

    public float[] getCalibrationOffset() {
        return calibrationOffset;
    }

    public boolean setCalibrationOffset(float[] calibrationOffset) {
        this.calibrationOffset = calibrationOffset;
        return true;
    }
}