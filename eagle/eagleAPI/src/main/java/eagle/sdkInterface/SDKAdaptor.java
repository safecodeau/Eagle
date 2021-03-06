package eagle.sdkInterface;

import java.util.ArrayList;

import eagle.navigation.positioning.Angle;
import eagle.navigation.positioning.Position;
import eagle.navigation.positioning.PositionDisplacement;
import eagle.navigation.positioning.PositionGPS;
import eagle.navigation.positioning.PositionMetric;
import eagle.network.ScriptingEngine;
import eagle.sdkInterface.sensorAdaptors.AdaptorAccelerometer;
import eagle.sdkInterface.sensorAdaptors.AdaptorBarometer;
import eagle.sdkInterface.sensorAdaptors.AdaptorBearing;
import eagle.sdkInterface.sensorAdaptors.AdaptorCamera;
import eagle.sdkInterface.sensorAdaptors.AdaptorGPS;
import eagle.sdkInterface.sensorAdaptors.AdaptorGyroscope;
import eagle.sdkInterface.sensorAdaptors.AdaptorLIDAR;
import eagle.sdkInterface.sensorAdaptors.AdaptorMagnetic;
import eagle.sdkInterface.sensorAdaptors.AdaptorRPLIDAR;
import eagle.sdkInterface.sensorAdaptors.AdaptorUltrasonic;

/**
 * Abstract SDKAdaptor Class
 *
 * @author Nicholas Alards [7178301@student.swin.edu.au]
 * @version 0.0.1
 * @since 09/04/2015
 * <p/>
 * Date Modified	26/05/2015 - Nicholas
 */
public abstract class SDKAdaptor {

    private ArrayList<AdaptorAccelerometer> accelerometers = new ArrayList<>();
    private ArrayList<AdaptorBarometer> barometers = new ArrayList<>();
    private ArrayList<AdaptorBearing> bearings = new ArrayList<>();
    private ArrayList<AdaptorCamera> cameras = new ArrayList<>();
    private ArrayList<AdaptorGPS> gps = new ArrayList<>();
    private ArrayList<AdaptorGyroscope> gyroscopes = new ArrayList<>();
    private ArrayList<AdaptorLIDAR> lidars = new ArrayList<>();
    private ArrayList<AdaptorMagnetic> magnetics = new ArrayList<>();
    private ArrayList<AdaptorRPLIDAR> rplidars = new ArrayList<>();
    private ArrayList<AdaptorUltrasonic> ultrasonics = new ArrayList<>();

    final public String adaptorName;
    final public String adaptorManufacturer;
    final public String adaptorModel;
    final public String sdkVersion;
    final public String adaptorVersion;

    private Position homePosition = null;

    final public ScriptingEngine scriptingEngine = new ScriptingEngine(this);

    final public SDKAdaptorTaskStack sdkAdaptorStack;

    private double maxFlightSpeed = 0;
    private double maxYawAngularVelocity = 0;
    private double maxAscentSpeed = 0;
    private double maxDecentSpeed = 0;

    //TODO create way to set current assigned position
    private Position currentPositionAssigned = null;

    public SDKAdaptor(String adaptorManufacturer, String adaptorModel, String sdkVersion, String adaptorVersion, double maxFlightSpeed, double maxRotateSpeed, double maxAscentSpeed, double maxDecentSpeed) {
        this.adaptorName = adaptorManufacturer + " " + adaptorModel;
        this.adaptorManufacturer = adaptorManufacturer;
        this.adaptorModel = adaptorModel;
        this.sdkVersion = sdkVersion;
        this.adaptorVersion = adaptorVersion;
        this.maxFlightSpeed = maxFlightSpeed;
        this.maxYawAngularVelocity = maxRotateSpeed;
        this.maxAscentSpeed = maxAscentSpeed;
        this.maxDecentSpeed = maxDecentSpeed;
        sdkAdaptorStack = new SDKAdaptorTaskStack(this);
    }

    public abstract void loadDefaultSensorAdaptors(AdaptorLoader adaptorLoader);

    public abstract boolean connectToDrone();

    public abstract boolean disconnectFromDrone();

    public abstract boolean isConnectedToDrone();

    public abstract boolean standbyDrone();

    public abstract boolean resumeDrone();

    public abstract boolean shutdownDrone();

    public String getAdaptorVersion() {
        return adaptorVersion;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public String getAdaptorName() {
        return adaptorName;
    }

    public String getAdaptorManufacturer() {
        return adaptorManufacturer;
    }

    public String getAdaptorModel() {
        return adaptorModel;
    }

    public double getMaxFlightSpeed() {
        return maxFlightSpeed;
    }

    public double getMaxYawAngularVelocity() {
        return maxYawAngularVelocity;
    }

    public double getMaxAscentSpeed() {
        return maxAscentSpeed;
    }

    public double getMaxDecentSpeed() {
        return maxDecentSpeed;
    }

    public abstract void flyTo(final SDKAdaptorCallback sdkAdaptorCallback, PositionMetric positionMetric, double speed);

    public abstract void flyTo(final SDKAdaptorCallback sdkAdaptorCallback, PositionMetric positionMetric);

    public void flyTo(final SDKAdaptorCallback sdkAdaptorCallback, PositionGPS position, double speed) {
        if (sdkAdaptorCallback != null)
            sdkAdaptorCallback.onResult(false, "flyTo(SDKAdaptorCallback,PositionGPS,double) not implemented in adaptor");
    }

    public void flyTo(final SDKAdaptorCallback sdkAdaptorCallback, PositionGPS position) {
        if (sdkAdaptorCallback != null)
            sdkAdaptorCallback.onResult(false, "flyTo(SDKAdaptorCallback,PositionGPS) not implemented in adaptor");
    }

    public abstract void flyTo(final SDKAdaptorCallback sdkAdaptorCallback, PositionDisplacement position, double speed);

    public abstract void flyTo(final SDKAdaptorCallback sdkAdaptorCallback, PositionDisplacement position);

    final public void changeLongitudeDisplacement(final SDKAdaptorCallback sdkAdaptorCallback, double longitude, double speed) {
        flyTo(sdkAdaptorCallback, new PositionDisplacement(0, longitude, 0, new Angle(0), new Angle(0), new Angle(0)), speed);
    }

    final public void changeLongitudeDisplacement(final SDKAdaptorCallback sdkAdaptorCallback, double longitude) {
        flyTo(sdkAdaptorCallback, new PositionDisplacement(0, longitude, 0, new Angle(0), new Angle(0), new Angle(0)));
    }

    final public void changeLatitudeDisplacement(final SDKAdaptorCallback sdkAdaptorCallback, double latitude, double speed) {
        flyTo(sdkAdaptorCallback, new PositionDisplacement(latitude, 0, 0, new Angle(0), new Angle(0), new Angle(0)), speed);
    }

    final public void changeLatitudeDisplacement(final SDKAdaptorCallback sdkAdaptorCallback, double latitude) {
        flyTo(sdkAdaptorCallback, new PositionDisplacement(latitude, 0, 0, new Angle(0), new Angle(0), new Angle(0)));
    }

    final public void changeAltitudeDisplacement(final SDKAdaptorCallback sdkAdaptorCallback, double altitude, double speed) {
        flyTo(sdkAdaptorCallback, new PositionDisplacement(0, 0, altitude, new Angle(0), new Angle(0), new Angle(0)), speed);
    }

    final public void changeAltitudeDisplacement(final SDKAdaptorCallback sdkAdaptorCallback, double altitude) {
        flyTo(sdkAdaptorCallback, new PositionDisplacement(0, 0, altitude, new Angle(0), new Angle(0), new Angle(0)));
    }

    final public void changeYawDisplacement(final SDKAdaptorCallback sdkAdaptorCallback, Angle yaw, double speed) {
        flyTo(sdkAdaptorCallback, new PositionDisplacement(0, 0, 0, new Angle(0), new Angle(0), yaw), speed);
    }

    final public void changeYawDisplacement(final SDKAdaptorCallback sdkAdaptorCallback, Angle yaw) {
        flyTo(sdkAdaptorCallback, new PositionDisplacement(0, 0, 0, new Angle(0), new Angle(0), yaw));
    }

    final public void changeLongitudeMetric(final SDKAdaptorCallback sdkAdaptorCallback, double longitude, double speed) {
        flyTo(sdkAdaptorCallback, new PositionMetric(0, longitude, 0, new Angle(0), new Angle(0), new Angle(0)), speed);
    }

    final public void changeLongitudeMetric(final SDKAdaptorCallback sdkAdaptorCallback, double longitude) {
        flyTo(sdkAdaptorCallback, new PositionMetric(0, longitude, 0, new Angle(0), new Angle(0), new Angle(0)));
    }

    final public void changeLatitudeMetric(final SDKAdaptorCallback sdkAdaptorCallback, double latitude, double speed) {
        flyTo(sdkAdaptorCallback, new PositionMetric(latitude, 0, 0, new Angle(0), new Angle(0), new Angle(0)), speed);
    }

    final public void changeLatitudeMetric(final SDKAdaptorCallback sdkAdaptorCallback, double latitude) {
        flyTo(sdkAdaptorCallback, new PositionMetric(latitude, 0, 0, new Angle(0), new Angle(0), new Angle(0)));
    }

    final public void changeAltitudeMetric(final SDKAdaptorCallback sdkAdaptorCallback, double altitude, double speed) {
        flyTo(sdkAdaptorCallback, new PositionMetric(0, 0, altitude, new Angle(0), new Angle(0), new Angle(0)), speed);
    }

    final public void changeAltitudeMetric(final SDKAdaptorCallback sdkAdaptorCallback, double altitude) {
        flyTo(sdkAdaptorCallback, new PositionMetric(0, 0, altitude, new Angle(0), new Angle(0), new Angle(0)));
    }

    final public void changeYawMetric(final SDKAdaptorCallback sdkAdaptorCallback, Angle yaw, double speed) {
        flyTo(sdkAdaptorCallback, new PositionMetric(0, 0, 0, new Angle(0), new Angle(0), yaw), speed);
    }

    final public void changeYawMetric(final SDKAdaptorCallback sdkAdaptorCallback, Angle yaw) {
        flyTo(sdkAdaptorCallback, new PositionMetric(0, 0, 0, new Angle(0), new Angle(0), yaw));
    }

    final public void changeLongitudeGPS(final SDKAdaptorCallback sdkAdaptorCallback, double longitude, double speed) {
        if (getPositionAssigned() != null) {
            this.currentPositionAssigned = new PositionGPS(getPositionAssigned().getLatitude(), longitude, getPositionAssigned().getAltitude(), new Angle(0), new Angle(0), getPositionAssigned().getYaw());
            flyTo(sdkAdaptorCallback, (PositionGPS) this.currentPositionAssigned, speed);
        }
    }

    final public void changeLongitudeGPS(final SDKAdaptorCallback sdkAdaptorCallback, double longitude) {
        if (getPositionAssigned() != null) {
            this.currentPositionAssigned = new PositionGPS(getPositionAssigned().getLatitude(), longitude, getPositionAssigned().getAltitude(), new Angle(0), new Angle(0), getPositionAssigned().getYaw());
            flyTo(sdkAdaptorCallback, (PositionGPS) this.currentPositionAssigned);
        }
    }

    final public void changeLatitudeGPS(final SDKAdaptorCallback sdkAdaptorCallback, double latitude, double speed) {
        if (getPositionAssigned() != null) {
            this.currentPositionAssigned = new PositionGPS(latitude, getPositionAssigned().getLongitude(), getPositionAssigned().getAltitude(), new Angle(0), new Angle(0), getPositionAssigned().getYaw());
            flyTo(sdkAdaptorCallback, (PositionGPS) this.currentPositionAssigned, speed);
        }
    }

    final public void changeLatitudeGPS(final SDKAdaptorCallback sdkAdaptorCallback, double latitude) {
        if (getPositionAssigned() != null) {
            this.currentPositionAssigned = new PositionGPS(latitude, getPositionAssigned().getLongitude(), getPositionAssigned().getAltitude(), new Angle(0), new Angle(0), getPositionAssigned().getYaw());
            flyTo(sdkAdaptorCallback, (PositionGPS) this.currentPositionAssigned);
        }
    }

    final public void changeAltitudeGPS(final SDKAdaptorCallback sdkAdaptorCallback, double altitude, double speed) {
        if (getPositionAssigned() != null) {
            this.currentPositionAssigned = new PositionGPS(getPositionAssigned().getLatitude(), getPositionAssigned().getLongitude(), altitude, new Angle(0), new Angle(0), getPositionAssigned().getYaw());
            flyTo(sdkAdaptorCallback, (PositionGPS) this.currentPositionAssigned, speed);
        }
    }

    final public void changeAltitudeGPS(final SDKAdaptorCallback sdkAdaptorCallback, double altitude) {
        if (getPositionAssigned() != null) {
            this.currentPositionAssigned = new PositionGPS(getPositionAssigned().getLatitude(), getPositionAssigned().getLongitude(), altitude, new Angle(0), new Angle(0), getPositionAssigned().getYaw());
            flyTo(sdkAdaptorCallback, (PositionGPS) this.currentPositionAssigned);
        }
    }

    final public void changeYawGPS(final SDKAdaptorCallback sdkAdaptorCallback, Angle yaw, double speed) {
        if (getPositionAssigned() != null) {
            this.currentPositionAssigned = new PositionGPS(getPositionAssigned().getLatitude(), getPositionAssigned().getLongitude(), getPositionAssigned().getAltitude(), new Angle(0), new Angle(0), yaw);
            flyTo(sdkAdaptorCallback, (PositionGPS) this.currentPositionAssigned, speed);
        }
    }

    final public void changeYawGPS(final SDKAdaptorCallback sdkAdaptorCallback, Angle yaw) {
        if (getPositionAssigned() != null) {
            this.currentPositionAssigned = new PositionGPS(getPositionAssigned().getLatitude(), getPositionAssigned().getLongitude(), getPositionAssigned().getAltitude(), new Angle(0), new Angle(0), yaw);
            flyTo(sdkAdaptorCallback, (PositionGPS) this.currentPositionAssigned);
        }
    }

    public void goHome(final SDKAdaptorCallback sdkAdaptorCallback, double speed) {
        if (homePosition instanceof PositionMetric && currentPositionAssigned instanceof PositionMetric) {
            flyTo(sdkAdaptorCallback, new PositionMetric(homePosition.add(new PositionDisplacement(0, 0, 1, new Angle(0)))), speed);
            flyTo(sdkAdaptorCallback, (PositionMetric) homePosition, speed);
        } else if (homePosition instanceof PositionGPS && currentPositionAssigned instanceof PositionGPS) {
            this.currentPositionAssigned = new PositionGPS(homePosition.add(new PositionDisplacement(0, 0, 1, new Angle(0))));
            flyTo(sdkAdaptorCallback, (PositionGPS) currentPositionAssigned, speed);
            this.currentPositionAssigned = new PositionGPS(homePosition);
            flyTo(sdkAdaptorCallback, (PositionGPS) homePosition, speed);
        } else if (sdkAdaptorCallback != null)
            sdkAdaptorCallback.onResult(false, "Unknown Home Position Type");

    }

    public void goHome(final SDKAdaptorCallback sdkAdaptorCallback) {
        if (homePosition instanceof PositionMetric && currentPositionAssigned instanceof PositionMetric) {
            flyTo(sdkAdaptorCallback, new PositionMetric(homePosition.add(new PositionDisplacement(0, 0, 1, new Angle(0)))));
            flyTo(sdkAdaptorCallback, (PositionMetric) homePosition);
        } else if (homePosition instanceof PositionGPS && currentPositionAssigned instanceof PositionGPS) {
            this.currentPositionAssigned = new PositionGPS(homePosition.add(new PositionDisplacement(0, 0, 1, new Angle(0))));
            flyTo(sdkAdaptorCallback, (PositionGPS) currentPositionAssigned);
            this.currentPositionAssigned = new PositionGPS(homePosition);
            flyTo(sdkAdaptorCallback, (PositionGPS) homePosition);
        } else if (sdkAdaptorCallback != null)
            sdkAdaptorCallback.onResult(false, "Unknown Home Position Type");

    }

    public Position getPositionAssigned() {
        return currentPositionAssigned;
    }

    public void setPositionAssigned(Position position) throws InvalidPositionTypeException {
        if (!(position instanceof PositionGPS)&&!(position instanceof PositionMetric))
            throw new InvalidPositionTypeException("PositionDisplacement isn't a valid assigned position");
        currentPositionAssigned = position;
    }

    public abstract Position getPositionInFlight();

    public void setHomePosition(Position position) throws InvalidPositionTypeException {
        if (position instanceof PositionDisplacement)
            throw new InvalidPositionTypeException("PositionDisplacement isn't a valid home position");
        homePosition = position;
    }

    public Position getHomePosition() {
        return homePosition;
    }

    public void addSensorAdaptorAccelerometer(AdaptorAccelerometer adaptorAccelerometer) {
        if (adaptorAccelerometer != null)
            this.accelerometers.add(adaptorAccelerometer);
    }

    public void addSensorAdaptorBarometer(AdaptorBarometer adaptorBarometer) {
        this.barometers.add(adaptorBarometer);
    }

    public void addSensorAdaptorBearing(AdaptorBearing adaptorBearing) {
        if (adaptorBearing != null)
            this.bearings.add(adaptorBearing);
    }

    public void addSensorAdaptorCamera(AdaptorCamera adaptorCamera) {
        if (adaptorCamera != null)
            this.cameras.add(adaptorCamera);
    }

    public void addSensorAdaptorGPS(AdaptorGPS adaptorGPS) {
        if (adaptorGPS != null)
            this.gps.add(adaptorGPS);
    }

    public void addSensorAdaptorGyroscope(AdaptorGyroscope adaptorGyroscope) {
        if (adaptorGyroscope != null)
            this.gyroscopes.add(adaptorGyroscope);
    }

    public void addSensorAdaptorLIDAR(AdaptorLIDAR lidar) {
        if (lidar != null)
            this.lidars.add(lidar);
    }

    public void addSensorAdaptorMagnetic(AdaptorMagnetic magnetic) {
        if (magnetic != null)
            this.magnetics.add(magnetic);
    }

    public void addSensorAdaptorRPLIDAR(AdaptorRPLIDAR adaptorRPLIDAR) {
        if (adaptorRPLIDAR != null)
            this.rplidars.add(adaptorRPLIDAR);
    }

    public void addSensorAdaptorUltrasonic(AdaptorUltrasonic adaptorUltrasonic) {
        if (adaptorUltrasonic != null)
            this.ultrasonics.add(adaptorUltrasonic);
    }

    public ArrayList<AdaptorAccelerometer> getAccelerometers() {
        return accelerometers;
    }

    public ArrayList<AdaptorBarometer> getBarometers() {
        return barometers;
    }

    public ArrayList<AdaptorBearing> getBearings() {
        return bearings;
    }

    public ArrayList<AdaptorCamera> getCameras() {
        return cameras;
    }

    public ArrayList<AdaptorGPS> getGPSs() {
        return gps;
    }

    public ArrayList<AdaptorGyroscope> getGyroscopes() {
        return gyroscopes;
    }

    public ArrayList<AdaptorLIDAR> getLidars() {
        return lidars;
    }

    public ArrayList<AdaptorMagnetic> getMagnetics() {
        return magnetics;
    }

    public ArrayList<AdaptorRPLIDAR> getRplidars() {
        return rplidars;
    }

    public ArrayList<AdaptorUltrasonic> getUltrasonics() {
        return ultrasonics;
    }
//TODO Add Remove Adaptor Functions

    public boolean setAndroidContext(Object object) {
        return false;
    }

    public boolean setController(Object object) {
        return false;
    }

    public abstract void delay(int milliseconds);

    public class InvalidPositionTypeException extends Exception {
        public InvalidPositionTypeException(String message) {
            super("Invalid Position Type: " + message);
        }
    }
}