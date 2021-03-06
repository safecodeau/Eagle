package eagle.sdkInterface.sensorAdaptors;

/**
 * AdaptorLIDAR Adaptor Interface
 *
 * @author Nicholas Alards [7178301@student.swin.edu.au]
 * @version 0.0.1
 * @since 09/04/2015
 * <p/>
 * Date Modified	26/05/2015 - Nicholas
 */
public abstract class AdaptorBarometer extends SensorAdaptor {
    private float calibrationOffset = 0;

    public AdaptorBarometer(String adaptorManufacturer, String adaptorModel, String adaptorVersion) {
        super(adaptorManufacturer, adaptorModel, adaptorVersion);
    }

    public abstract boolean connectToSensor();

    public abstract float getData();

    public float getCalibratedData() {
        return getData() - calibrationOffset;
    }

    public float getCalibrationOffset() {
        return calibrationOffset;
    }

    public void setCalibrationOffset(float calibrationOffset) {
        this.calibrationOffset = calibrationOffset;
    }

}