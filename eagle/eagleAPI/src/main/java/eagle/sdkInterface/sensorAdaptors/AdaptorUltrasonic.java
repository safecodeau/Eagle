package eagle.sdkInterface.sensorAdaptors;

/**
 * Ultrasonic Adaptor Interface
 *
 * @author Nicholas Alards [7178301@student.swin.edu.au]
 * @version 0.0.1
 * @since 09/04/2015
 * <p/>
 * Date Modified	26/05/2015 - Nicholas
 */
public abstract class AdaptorUltrasonic extends SensorAdaptor {
    private float calibrationOffset = 0;

    public AdaptorUltrasonic(String adaptorManufacturer, String adaptorModel, String adaptorVersion) {
        super(adaptorManufacturer, adaptorModel, adaptorVersion);
    }

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

    @Override
    public String toString() {
        float data = getCalibratedData();
        StringBuilder sb = new StringBuilder();
        sb.append("Distance: ");
        sb.append(data);
        return sb.toString();
    }
}