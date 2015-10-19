package eagle.sdkInterface.sensorAdaptors.ultrasonicAdaptors;

import eagle.sdkInterface.sensorAdaptors.AdaptorUltrasonic;

import ioio.lib.api.IOIO;
import ioio.lib.api.PulseInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.PulseInput.PulseMode;
import ioio.lib.api.exception.ConnectionLostException;

/**
 * Modtronix MOD-USONIC1 Ultrasonic Adaptor
 *
 * @author Nicholas Alards [7178301@student.swin.edu.au]
 * @author Angela Lau [7160852@student.swin.edu.au]
 * @version 0.0.1
 * @since 14/06/2015
 * <p/>
 * Date Modified	28/08/2015 - Angela
 */
public class HCSR04 extends AdaptorUltrasonic {

    private int triggerPin = -1;
    private int echoPin = -1;
    private DigitalOutput trigger;
    private PulseInput echo;
    private float echoSeconds;
    private float echoDistanceMetres; // Distance measurements in metres
    private IOIO ioio;

    public HCSR04() {
        super("HC-SR04", "HC-SR04", "0.0.1");
    }

    public boolean connectToSensor() {
        if (ioio == null || triggerPin == -1 || echoPin == -1) {
            return false;
        }
        try {
            trigger = ioio.openDigitalOutput(triggerPin);
            echo = ioio.openPulseInput(echoPin, PulseMode.POSITIVE);
        } catch (ConnectionLostException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //TODO Following Method Need Proper Implementation
    @Override
    public boolean disconnectFromSensor() {
        return false;
    }

    public boolean setController(Object object) {
        if (object instanceof IOIO) {
            this.ioio = (IOIO) object;
            return connectToSensor();
        } else
            return false;
    }

    @Override
    public boolean isConnectedToSensor() {
        if (trigger != null && echo != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isDataReady() {
        return true;
    }

    @Override
    public float getData() {
        try {
            trigger.write(false);
            Thread.sleep(5);
            trigger.write(true);
            Thread.sleep(1);
            trigger.write(false);
            echoSeconds = (echo.getDuration() * 1000 * 1000);
            echoDistanceMetres = (echoSeconds / 29 / 2) / 100;
        } catch (ConnectionLostException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return echoDistanceMetres;
    }

    @Override
    public String[] getSensorPinsDescription() {
        String[] temp = new String[2];
        temp[0] = "Trigger Pin";
        temp[1] = "Echo Pin";
        return temp;
    }

    @Override
    public boolean setSensorPins(int[] pins) {
        if (pins != null && pins.length == 2) {
            triggerPin = pins[0];
            echoPin = pins[1];
            return true;
        } else
            return false;
    }
}