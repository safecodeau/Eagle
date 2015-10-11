package eagle.sdkInterface.sdkAdaptors.Flyver;

import android.content.Context;

import eagle.logging.Log;
import eagle.navigation.positioning.Angle;
import eagle.navigation.positioning.Position;
import eagle.navigation.positioning.PositionDisplacement;
import eagle.navigation.positioning.PositionGPS;
import eagle.navigation.positioning.PositionMetric;
import eagle.sdkInterface.AdaptorLoader;
import eagle.sdkInterface.SDKAdaptor;
import eagle.sdkInterface.SDKAdaptorCallback;
import eagle.sdkInterface.sensorAdaptors.AdaptorBearing;
import eagle.sdkInterface.sensorAdaptors.bearingAdaptors.AndroidBearing;
import eagle.sdkInterface.sensorAdaptors.gpsAdaptors.AndroidGPS;
import ioio.lib.api.IOIO;

/**
 * F450Flamewheel SDKAdaptor
 *
 * @author Nicholas Alards [7178301@student.swin.edu.au]
 * @author Cameron Cross [7193432@student.swin.edu.au]
 * @version 0.0.1
 * @since 09/04/2015
 * <p/>
 * Date Modified	26/05/2015 - Nicholas
 */
public class F450Flamewheel extends SDKAdaptor {

    public static String adapterVersion;
    private Context context;
    private IOIO ioio;
    private IOIOController controller;

    //TODO Create method implementations

    public F450Flamewheel() {
        super("Flyver", "F450 Flamewheel", "alpha", "0.0.1", 0, 0);
        controller = new IOIOController();
    }

    public void loadDefaultSensorAdaptors(AdaptorLoader adaptorLoader) {
        addSensorAdaptorAccelerometer(adaptorLoader.getSensorAdaptorAccelerometer("AndroidAccelerometer"));
        addSensorAdaptorCamera(adaptorLoader.getSensorAdaptorCamera("AndroidCamera"));
        addSensorAdaptorGPS(adaptorLoader.getSensorAdaptorGPS("AndroidGPS"));
        addSensorAdaptorGyroscope(adaptorLoader.getSensorAdaptorGyroscope("AndroidGyroscope"));
        addSensorAdaptorMagnetic(adaptorLoader.getSensorAdaptorMagnetic("AndroidMagnetic"));

        AdaptorBearing androidBearing = adaptorLoader.getSensorAdaptorBearing("AndroidBearing");

        androidBearing.setAccelerometerMagnetic(getAccelerometers().get(0), getMagnetics().get(0));
        addSensorAdaptorBearing(androidBearing);
        controller.setBearingAdaptor((AndroidBearing) getBearings().get(0));
        controller.setPositionAdaptor((AndroidGPS) getGPSs().get(0));
    }

    public boolean connectToDrone() {
        if (ioio != null && ioio.getState() != null) {
            Log.log("F450Flamewheel", "connectToDrone SUCCESS");
            return true;
        }
        else{
            Log.log("F450Flamewheel", "connectToDrone FAIL");
            return false;
        }
    }

    public boolean disconnectFromDrone() {
        return false;
    }

    public boolean isConnectedToDrone() {
        return false;
    }

    //TODO CREATE BELOW IMPLEMENTATION
    public Position getPositionInFlight() {
        return null;
    }

    public boolean standbyDrone() {
        return false;
    }

    public boolean resumeDrone() {
        return false;
    }

    public boolean shutdownDrone() {
        return false;
    }

    @Override
    public void flyTo(SDKAdaptorCallback sdkAdaptorCallback, PositionMetric position, double speed) {
        if (sdkAdaptorCallback == null || position == null)
            throw new IllegalArgumentException("Arguments must not be null");
        else
            sdkAdaptorCallback.onResult(false, "flyTo not yet implemented in adaptor");
    }

    @Override
    public void flyTo(SDKAdaptorCallback sdkAdaptorCallback, PositionMetric position) {
        if (sdkAdaptorCallback == null || position == null)
            throw new IllegalArgumentException("Arguments must not be null");
        else
            sdkAdaptorCallback.onResult(false, "flyTo not yet implemented in adaptor");
    }

    @Override
    public void flyTo(SDKAdaptorCallback sdkAdaptorCallback, PositionGPS position, double speed) {
        if (sdkAdaptorCallback == null || position == null)
            throw new IllegalArgumentException("Arguments must not be null");
        else
            sdkAdaptorCallback.onResult(false, "flyTo not yet implemented in adaptor");
    }

    @Override
    public void flyTo(SDKAdaptorCallback sdkAdaptorCallback, PositionGPS position) {
        if (sdkAdaptorCallback == null || position == null)
            throw new IllegalArgumentException("Arguments must not be null");
        else
            sdkAdaptorCallback.onResult(false, "flyTo not yet implemented in adaptor");
    }


    PositionDisplacement posDisp = new PositionDisplacement(0,0,0,new Angle(0));

    @Override
    public void flyTo(SDKAdaptorCallback sdkAdaptorCallback, PositionDisplacement position, double speed) {
        posDisp = posDisp.add(position);
        controller.setDesiredPosition(posDisp);
        if (sdkAdaptorCallback == null || position == null)
            throw new IllegalArgumentException("Arguments must not be null");
        else{
            Log.log("F450Flamewheel", "flyToDisplacement " + position.toString() + " FAIL");
            sdkAdaptorCallback.onResult(true, "flyTo not yet implemented in adaptor");
        }
    }

    @Override
    public void flyTo(SDKAdaptorCallback sdkAdaptorCallback, PositionDisplacement position) {
        flyTo(sdkAdaptorCallback, position, getMaxSpeed());
    }

    @Override
    public void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateCurrentPosition() {
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
    public boolean setController(Object object) {
        if (object instanceof IOIO) {
            this.ioio = (IOIO) object;
            controller.setIOIO(ioio);
            return true;
        } else
            return false;
    }
}