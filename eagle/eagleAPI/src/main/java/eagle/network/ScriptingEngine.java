package eagle.network;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import eagle.Log;
import eagle.navigation.positioning.Angle;
import eagle.navigation.positioning.Position;
import eagle.navigation.positioning.PositionDisplacement;
import eagle.navigation.positioning.PositionGPS;
import eagle.navigation.positioning.PositionMetric;
import eagle.sdkInterface.SDKAdaptor;
import eagle.sdkInterface.SDKAdaptorCallback;

/**
 * Scripting Engine for drone
 * Should be reworked
 *
 * @author Cameron Cross
 * @version 0.0.1
 * @since 04/09/2015
 * <p/>
 * Date Modified	04/09/2015 - Cameron
 */
public class ScriptingEngine {
    private SDKAdaptor adaptor;

    static final Map<String, String> commands = new HashMap<String, String>() {
        {
            put("CONNECTTODRONE", "CONNECTTODRONE | Connect to the drone");
            put("DISCONNECTFROMDRONE", "DISCONNECTFROMDRONE | Disconnect from the drone");
            put("ISCONNECTEDTODRONE", "ISCONNECTEDTODRONE | Get the status of the connection from the drone");
            put("STANDBYDRONE", "STANDBYDRONE | Standby the drone");
            put("RESUMEDRONE", "RESUMEDRONE | Resume the drone");
            put("SHUTDOWNDRONE", "SHUTDOWNDRONE | Shutdown the drone");
            put("GETADAPTORVERSION", "GETADAPTORVERSION | Get the adaptor version");
            put("GETSDKVERSION", "GETSDKVERSION | Get the SDK version");
            put("GETADAPTORNAME", "GETADAPTORNAME | Get the adaptor name");
            put("GETADAPTORMANUFACTURER", "GETADAPTORMANUFACTURER | Get the adaptor manufacturer");
            put("GETADAPTORMODEL", "GETADAPTORMODEL | Get the adaptor model");
            put("FLYTO", "FLYTO _PositionType_ _longitude_ _latitude_ _altitude_ _Angle_ _[speed]_ | Fly the drone to a given position with type _PositionType_ (GPS, METRIC, DISPLACEMENT)");
            put("CHANGELONGITUDE", "CHANGELONGITUDE _PositionType_ _longitude_ _[speed]_ | Change the longitude");
            put("CHANGELATITUDE", "CHANGELATITUDE _PositionType_ _latitude_ _[speed]_ | Change the latitude");
            put("CHANGEALTITUDE", "CHANGEALTITUDE _PositionType_ _altitude_ _[speed]_ | Change the altitude");
            put("CHANGEYAW", "CHANGEYAW _PositionType_ _yaw_ _[speed]_ | Change the yaw");
            put("GOHOME", "GOHOME | Flys the drone to its home position");
            put("GETPOSITIONASSIGNED", "GETPOSITIONASSIGNED | prints out the drones current position");
            put("GETHOMEPOSITION", "GETHOMEPOSITION | prints the home position of the drone");
            put("SETHOMEPOSITION", "SETHOMEPOSITION _PositionType_ _longitude_ _latitude_ _altitude_ _Angle_ | Set the home position");
            put("GETACCELEROMETERDATA", "GETACCELEROMETERDATA _id_ | Get the values of the accelerometer given by _id_");
            put("GETBEARINGDATA", "GETBEARINGDATA _id_ | Get the values of the bearing given by _id_");
            put("GETGPSDATA", "GETGPSDATA _id_ | Get the values of the gps given by _id_");
            put("GETGYROSCOPEDATA", "GETGYROSCOPEDATA _id_ | Get the values of the gyroscope given by _id_");
            put("GETLIDARDATA", "GETLIDARDATA _id_ | Get the values of the LIDAR given by _id_");
            put("GETMAGNETICDATA", "GETMAGNETICDATA _id_ | Get the values of the magnetic sensor given by _id_");
            put("GETRPLIDARDATA", "GETRPLIDARDATA _id_ | Get the values of the RPLIDAR given by _id_");
            put("GETULTRASONICDATA", "GETULTRASONICDATA _id_ | Get the values of the ultrasonic sensor given by _id_");
            put("DELAY", "DELAY _time_ | Delays for _time_ milliseconds");
            put("HELP", "HELP _[command]_ | Prints a list of commands");
            put("LOG", "LOG _message_ | Write a message to the log");
        }
    };

    public ScriptingEngine(SDKAdaptor sdkAdaptor) {
        this.adaptor = sdkAdaptor;
    }

    /**
     * Execute an instruction on the drone
     * @param instruction
     * @return string containing result of execution
     * @throws InvalidInstructionException
     */
    public String executeInstruction(final String instruction) throws InvalidInstructionException {
        if (adaptor == null) {
            return "NO DRONE ADAPTOR";
        }
        final String[] returnString = {null};
        final String[] ExceptionResult = {null};

        Thread executeThread = new Thread() {
            @Override
            public void run() {
                try {
                    String[] array = instruction.toUpperCase().split(" ");
                    switch (array[0]) {
                        case "CONNECTTODRONE":
                            if (array.length == 1) {
                                if (adaptor.connectToDrone())
                                    returnString[0] = "SUCCESS";
                                else
                                    returnString[0] = "FAIL";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "DISCONNECTFROMDRONE":
                            if (array.length == 1) {
                                if (adaptor.disconnectFromDrone())
                                    returnString[0] = "SUCCESS";
                                else
                                    returnString[0] = "FAIL";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "ISCONNECTEDTODRONE":
                            if (array.length == 1) {
                                if (adaptor.isConnectedToDrone())
                                    returnString[0] = "TRUE";
                                else
                                    returnString[0] = "FALSE";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "STANDBYDRONE":
                            if (array.length == 1) {
                                if (adaptor.standbyDrone())
                                    returnString[0] = "SUCCESS";
                                else
                                    returnString[0] = "FAIL";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "RESUMEDRONE":
                            if (array.length == 1) {
                                if (adaptor.resumeDrone())
                                    returnString[0] = "SUCCESS";
                                else
                                    returnString[0] = "FAIL";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "SHUTDOWNDRONE":
                            if (array.length == 1) {
                                if (adaptor.shutdownDrone())
                                    returnString[0] = "SUCCESS";
                                else
                                    returnString[0] = "FAIL";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETADAPTORVERSION":
                            if (array.length == 1)
                                returnString[0] = adaptor.getAdaptorVersion();
                            else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETSDKVERSION":
                            if (array.length == 1)
                                returnString[0] = adaptor.getSdkVersion();
                            else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETADAPTORNAME":
                            if (array.length == 1)
                                returnString[0] = adaptor.getAdaptorName();
                            else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETADAPTORMANUFACTURER":
                            if (array.length == 1)
                                returnString[0] = adaptor.getAdaptorManufacturer();
                            else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETADAPTORMODEL":
                            if (array.length == 1)
                                returnString[0] = adaptor.getAdaptorModel();
                            else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "FLYTO":
                            if (array.length == 6) {
                                double latitude = Double.parseDouble(array[2]);
                                double longitude = Double.parseDouble(array[3]);
                                double altitude = Double.parseDouble(array[4]);
                                double bearing = Double.parseDouble(array[5]);
                                switch (array[1]) {
                                    case "GPS":
                                    case "G":
                                    case "-G":
                                        adaptor.flyTo(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new PositionGPS(latitude, longitude, altitude, new Angle(0), new Angle(0), new Angle(bearing)));
                                        break;
                                    case "METRIC":
                                    case "M":
                                    case "-M":
                                        adaptor.flyTo(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new PositionMetric(latitude, longitude, altitude, new Angle(0), new Angle(0), new Angle(bearing)));
                                        break;
                                    case "DISPLACEMENT":
                                    case "DISP":
                                    case "D":
                                    case "-D":
                                        adaptor.flyTo(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new PositionDisplacement(latitude, longitude, altitude, new Angle(0), new Angle(0), new Angle(bearing)));
                                        break;
                                    default:
                                        ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                }
                            } else if (array.length == 7) {
                                double latitude = Double.parseDouble(array[2]);
                                double longitude = Double.parseDouble(array[3]);
                                double altitude = Double.parseDouble(array[4]);
                                double bearing = Double.parseDouble(array[5]);
                                double speed = Double.parseDouble(array[6]);
                                switch (array[1]) {
                                    case "GPS":
                                    case "G":
                                    case "-G":
                                        adaptor.flyTo(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new PositionGPS(latitude, longitude, altitude, new Angle(0), new Angle(0), new Angle(bearing)), speed);
                                        break;
                                    case "METRIC":
                                    case "M":
                                    case "-M":
                                        adaptor.flyTo(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new PositionMetric(latitude, longitude, altitude, new Angle(0), new Angle(0), new Angle(bearing)), speed);
                                        break;
                                    case "DISPLACEMENT":
                                    case "DISP":
                                    case "D":
                                    case "-D":
                                        adaptor.flyTo(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new PositionDisplacement(latitude, longitude, altitude, new Angle(0), new Angle(0), new Angle(bearing)), speed);
                                        break;
                                    default:
                                        ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                }
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "CHANGELONGITUDE":
                            if (array.length == 3) {
                                double value = Double.parseDouble(array[2]);
                                switch (array[1]) {
                                    case "GPS":
                                    case "G":
                                    case "-G":
                                        adaptor.changeLongitudeGPS(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value);
                                        break;
                                    case "METRIC":
                                    case "M":
                                    case "-M":
                                        adaptor.changeLongitudeMetric(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value);
                                        break;
                                    case "DISPLACEMENT":
                                    case "DISP":
                                    case "D":
                                    case "-D":
                                        adaptor.changeLongitudeDisplacement(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value);
                                        break;
                                    default:
                                        ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                }
                            } else if (array.length == 4) {
                                double value = Double.parseDouble(array[2]);
                                double speed = Double.parseDouble(array[3]);
                                switch (array[1]) {
                                    case "GPS":
                                    case "G":
                                    case "-G":
                                        adaptor.changeLongitudeGPS(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value, speed);
                                        break;
                                    case "METRIC":
                                    case "M":
                                    case "-M":
                                        adaptor.changeLongitudeMetric(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value, speed);
                                        break;
                                    case "DISPLACEMENT":
                                    case "DISP":
                                    case "D":
                                    case "-D":
                                        adaptor.changeLongitudeDisplacement(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value, speed);
                                        break;
                                    default:
                                        ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                }
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "CHANGELATITUDE":
                            if (array.length == 3) {
                                double value = Double.parseDouble(array[2]);
                                switch (array[1]) {
                                    case "GPS":
                                    case "G":
                                    case "-G":
                                        adaptor.changeLatitudeGPS(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value);
                                        break;
                                    case "METRIC":
                                    case "M":
                                    case "-M":
                                        adaptor.changeLatitudeMetric(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value);
                                        break;
                                    case "DISPLACEMENT":
                                    case "DISP":
                                    case "D":
                                    case "-D":
                                        adaptor.changeLatitudeDisplacement(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value);
                                        break;
                                    default:
                                        ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                }
                            } else if (array.length == 4) {
                                double value = Double.parseDouble(array[2]);
                                double speed = Double.parseDouble(array[3]);
                                switch (array[1]) {
                                    case "GPS":
                                    case "G":
                                    case "-G":
                                        adaptor.changeLatitudeGPS(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value, speed);
                                        break;
                                    case "METRIC":
                                    case "M":
                                    case "-M":
                                        adaptor.changeLatitudeMetric(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value, speed);
                                        break;
                                    case "DISPLACEMENT":
                                    case "DISP":
                                    case "D":
                                    case "-D":
                                        adaptor.changeLatitudeDisplacement(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value, speed);
                                        break;
                                    default:
                                        ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                }
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "CHANGEALTITUDE":
                            if (array.length == 3) {
                                double value = Double.parseDouble(array[2]);
                                switch (array[1]) {
                                    case "GPS":
                                    case "G":
                                    case "-G":
                                        adaptor.changeAltitudeGPS(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value);
                                        break;
                                    case "METRIC":
                                    case "M":
                                    case "-M":
                                        adaptor.changeAltitudeMetric(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value);
                                        break;
                                    case "DISPLACEMENT":
                                    case "DISP":
                                    case "D":
                                    case "-D":
                                        adaptor.changeAltitudeDisplacement(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value);
                                        break;
                                    default:
                                        ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                }
                            } else if (array.length == 4) {
                                double value = Double.parseDouble(array[2]);
                                double speed = Double.parseDouble(array[3]);
                                switch (array[1]) {
                                    case "GPS":
                                    case "G":
                                    case "-G":
                                        adaptor.changeAltitudeGPS(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value, speed);
                                        break;
                                    case "METRIC":
                                    case "M":
                                    case "-M":
                                        adaptor.changeAltitudeMetric(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value, speed);
                                        break;
                                    case "DISPLACEMENT":
                                    case "DISP":
                                    case "D":
                                    case "-D":
                                        adaptor.changeAltitudeDisplacement(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, value, speed);
                                        break;
                                    default:
                                        ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                }
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "CHANGEYAW":
                            if (array.length == 3) {
                                double value = Double.parseDouble(array[2]);
                                switch (array[1]) {
                                    case "GPS":
                                    case "G":
                                    case "-G":
                                        adaptor.changeYawGPS(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new Angle(value));
                                        break;
                                    case "METRIC":
                                    case "M":
                                    case "-M":
                                        adaptor.changeYawMetric(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new Angle(value));
                                        break;
                                    case "DISPLACEMENT":
                                    case "DISP":
                                    case "D":
                                    case "-D":
                                        adaptor.changeYawDisplacement(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new Angle(value));
                                        break;
                                    default:
                                        ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                }
                            } else if (array.length == 4) {
                                double value = Double.parseDouble(array[2]);
                                double speed = Double.parseDouble(array[3]);
                                switch (array[1]) {
                                    case "GPS":
                                    case "G":
                                    case "-G":
                                        adaptor.changeYawGPS(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new Angle(value), speed);
                                        break;
                                    case "METRIC":
                                    case "M":
                                    case "-M":
                                        adaptor.changeYawMetric(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new Angle(value), speed);
                                        break;
                                    case "DISPLACEMENT":
                                    case "DISP":
                                    case "D":
                                    case "-D":
                                        adaptor.changeYawDisplacement(new SDKAdaptorCallback() {
                                            @Override
                                            public void onResult(boolean booleanResult, String stringResult) {
                                                if (booleanResult)
                                                    returnString[0] = "SUCCESS";
                                                else
                                                    returnString[0] = stringResult;
                                            }
                                        }, new Angle(value), speed);
                                        break;
                                    default:
                                        ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                }
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GOHOME":
                            if (array.length == 1) {
                                adaptor.goHome(new SDKAdaptorCallback() {
                                    @Override
                                    public void onResult(boolean booleanResult, String stringResult) {
                                        if (booleanResult)
                                            returnString[0] = "SUCCESS";
                                        else
                                            returnString[0] = stringResult;
                                    }
                                });
                            } else if (array.length == 2) {
                                adaptor.goHome(new SDKAdaptorCallback() {
                                    @Override
                                    public void onResult(boolean booleanResult, String stringResult) {
                                        if (booleanResult)
                                            returnString[0] = "SUCCESS";
                                        else
                                            returnString[0] = stringResult;
                                    }
                                }, Double.parseDouble(array[1]));
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETPOSITIONASSIGNED":
                            if (array.length == 1) {
                                Position pos = adaptor.getPositionAssigned();
                                if (pos == null)
                                    returnString[0] = "ASSIGNED POSITION NOT SET";
                                else
                                    returnString[0] = pos.toString();
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETPOSITIONINFLIGHT":
                            if (array.length == 1) {
                                Position pos = adaptor.getPositionInFlight();
                                if (pos == null)
                                    returnString[0] = "POSITION IN FLIGHT NOT AVAILABLE";
                                else
                                    returnString[0] = pos.toString();
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETHOMEPOSITION":
                            if (array.length == 1) {
                                Position pos = adaptor.getHomePosition();
                                if (pos == null)
                                    returnString[0] = "HOME POSITION NOT SET";
                                else
                                    returnString[0] = pos.toString();
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "SETHOMEPOSITION":
                            if (array.length == 6) {
                                double latitude = Double.parseDouble(array[2]);
                                double longitude = Double.parseDouble(array[3]);
                                double altitude = Double.parseDouble(array[4]);
                                double bearing = Double.parseDouble(array[5]);
                                try {
                                    switch (array[1]) {
                                        case "GPS":
                                        case "G":
                                        case "-G":
                                            adaptor.setHomePosition(new PositionGPS(latitude, longitude, altitude, new Angle(0), new Angle(0), new Angle(bearing)));
                                            break;
                                        case "METRIC":
                                        case "M":
                                        case "-M":
                                            adaptor.setHomePosition(new PositionMetric(latitude, longitude, altitude, new Angle(0), new Angle(0), new Angle(bearing)));
                                            break;
                                        default:
                                            ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                    }
                                } catch (SDKAdaptor.InvalidPositionTypeException e) {
                                    ExceptionResult[0] = "Invalid Position Type:  " + instruction;
                                }
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETACCELEROMETERDATA":
                            if (array.length == 2) {
                                int id = Integer.parseInt(array[1]);
                                if (id >= 0 && id < adaptor.getAccelerometers().size()) {
                                    returnString[0] = adaptor.getAccelerometers().get(id).toString();
                                } else
                                    returnString[0] = "NO SENSOR WITH THAT ID";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETBEARINGDATA":
                            if (array.length == 2) {
                                int id = Integer.parseInt(array[1]);
                                if (id >= 0 && id < adaptor.getBearings().size()) {
                                    returnString[0] = adaptor.getBearings().get(id).toString();
                                } else
                                    returnString[0] = "NO SENSOR WITH THAT ID";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETGYROSCOPEDATA":
                            if (array.length == 2) {
                                int id = Integer.parseInt(array[1]);
                                if (id >= 0 && id < adaptor.getGyroscopes().size()) {
                                    returnString[0] = adaptor.getGyroscopes().get(id).toString();
                                } else
                                    returnString[0] = "NO SENSOR WITH THAT ID";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETGPSDATA":
                            if (array.length == 2) {
                                int id = Integer.parseInt(array[1]);
                                if (id >= 0 && id < adaptor.getGPSs().size()) {
                                    returnString[0] = adaptor.getGPSs().get(id).toString();
                                } else
                                    returnString[0] = "NO SENSOR WITH THAT ID";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETLIDARDATA":
                            if (array.length == 2) {
                                int id = Integer.parseInt(array[1]);
                                if (id >= 0 && id < adaptor.getLidars().size()) {
                                    returnString[0] = adaptor.getLidars().get(id).toString();
                                } else
                                    returnString[0] = "NO SENSOR WITH THAT ID";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETMAGNETICDATA":
                            if (array.length == 2) {
                                int id = Integer.parseInt(array[1]);
                                if (id >= 0 && id < adaptor.getMagnetics().size()) {
                                    returnString[0] = adaptor.getMagnetics().get(id).toString();
                                } else
                                    returnString[0] = "NO SENSOR WITH THAT ID";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETRPLIDARDATA":
                            if (array.length == 2) {
                                int id = Integer.parseInt(array[1]);
                                if (id >= 0 && id < adaptor.getRplidars().size()) {
                                    returnString[0] = adaptor.getRplidars().get(id).toString();
                                } else
                                    returnString[0] = "NO SENSOR WITH THAT ID";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "GETULTRASONICDATA":
                            if (array.length == 2) {
                                int id = Integer.parseInt(array[1]);
                                if (id >= 0 && id < adaptor.getUltrasonics().size()) {
                                    returnString[0] = adaptor.getUltrasonics().get(id).toString();
                                } else
                                    returnString[0] = "NO SENSOR WITH THAT ID";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "DELAY":
                            if (array.length == 2) {
                                int time = Integer.parseInt(array[1]);
                                adaptor.delay(time);
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "HELP":
                            if (array.length == 1) {
                                for (String element : commands.keySet()) {
                                    returnString[0] += element + " ";
                                }
                            } else if (array.length == 2) {
                                if (commands.containsKey(array[1]))
                                    returnString[0] = commands.get(array[1]);
                                else
                                    returnString[0] = "UNKNOWN COMMAND";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        case "LOG":
                            if (array.length >= 1) {
                                StringBuilder sb = new StringBuilder();
                                String parts[] = instruction.split(" ");
                                for (int i = 1; i < parts.length; i++) {
                                    sb.append(parts[i]);
                                    sb.append(" ");
                                }
                                Log.log("ScriptingEngine", sb.toString());
                                returnString[0] = "SUCCESS";
                            } else
                                ExceptionResult[0] = "Wrong Number of Arguments. Type HELP For Mor Information: " + instruction;
                            break;
                        default:
                            ExceptionResult[0] = instruction;
                            break;
                    }
                } catch (NumberFormatException e) {
                    ExceptionResult[0] = instruction;
                }
            }
        };
        executeThread.start();

        while (returnString[0] == null && ExceptionResult[0] == null) {
            try {
                executeThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (ExceptionResult[0] != null)
            throw new InvalidInstructionException(ExceptionResult[0]);
        else if (returnString[0] != null)
            return returnString[0];
        else
            return "FAIL";

    }

    /**
     * Read a set of instructions from a file, execute all of them.
     * @param filename
     */
    public final void runRoutine(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                executeInstruction(line);
            }
        } catch (IOException | InvalidInstructionException e) {
            e.printStackTrace();
        }
    }

    public class InvalidInstructionException extends Exception {
        public InvalidInstructionException(String s) {
            super("Invalid Instruction: " + s);
        }
    }
}