package eagle.sdkInterface.sdkAdaptors.Flyver;

import eagle.navigation.positioning.Angle;
import eagle.navigation.positioning.PositionMetric;
import eagle.navigation.positioning.PositionGPS;
import eagle.sdkInterface.AdaptorLoader;
import eagle.sdkInterface.SDKAdaptor;

/** F450Flamewheel SDKAdaptor
 * @since     09/04/2015
 * <p>
 * Date Modified	26/05/2015 - Nicholas
 * @version 0.0.1
 * @author          Nicholas Alards [7178301@student.swin.edu.au]
 * @author          Cameron Cross [7193432@student.swin.edu.au]*/
public class F450Flamewheel extends SDKAdaptor {
    public static String adapterVersion;

    //TODO Create method implementations

    public F450Flamewheel(){
        super("FLyver","F450 Flamewheel","alpha","0.0.1");
    }
    public void loadDefaultSensorAdaptors(AdaptorLoader adaptorLoader){
        addSensorAdaptorAccelerometer(adaptorLoader.getSensorAdaptorAccelerometer("AndroidAccelerometer"));
        addSensorAdaptorAltimeter(adaptorLoader.getSensorAdaptorAltimeter("AndroidAltimeter"));
        addSensorAdaptorCamera(adaptorLoader.getSensorAdaptorCamera("AndroidCamera"));
        addSensorAdaptorCompass(adaptorLoader.getSensorAdaptorCompass("AndroidCompass"));
        addSensorAdaptorGyroscope(adaptorLoader.getSensorAdaptorGyroscope("AndroidGyroscope"));
    }

    public boolean connectToDrone(){return false;}
    public boolean disconnectFromDrone(){return false;}
    public boolean isConnectedToDrone(){return false;}

    public boolean standbyDrone(){return false;}
    public boolean resumeDrone(){return false;}
    public boolean shutdownDrone(){return false;}

    @Override
    public boolean flyToRelative(PositionMetric position, double speed) {
        return false;
    }

    @Override
    public boolean flyToRelative(PositionMetric position) {
        return false;
    }

    public boolean flyToGPS(PositionGPS positionGPS, double speed){return false;}
    public boolean flyToGPS(PositionGPS positionGPS){return false;}

    public PositionMetric getPositionInFlight(){
        //TODO CREATE BELOW IMPLEMENTATION
        return new PositionMetric(0,0,0,new Angle(0),new Angle(0),new Angle(0));
    }

    @Override
    public void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void updateCurrentPosition(){}


}