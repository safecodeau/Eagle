package eagle.logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Java Logger
 *
 * @author Cameron Cross
 * @author Nicholas Alards [7178301@student.swin.edu.au]
 * @author Cameron Cross
 * @version 0.0.1
 * @since 8/13/15
 * <p/>
 * Date Modified	02/10/15 - Nicholas
 */
public class Log {

    private static ArrayList<String> log = new ArrayList<>();
    private static HashMap<String, Vector<LogCallback>> logCallBack = new HashMap<>();
    private static Vector<LogCallback> verboseLogCallback = new Vector<>();
    private static int logLimit = 10000;


    public synchronized static void log(String tag, String message) {
        while (log.size() > logLimit) {
            log.remove(0);
        }
        log.add(tag + ": " + message);
        for (LogCallback vLogCallback : verboseLogCallback)
            vLogCallback.onLogEntry(tag, message);
        if (logCallBack.containsKey(tag)) {
            for (LogCallback logcallback : logCallBack.get(tag))
                logcallback.onLogEntry(tag, message);
        }
    }

    public synchronized static void addCallback(String tag, LogCallback callback) {
        if (logCallBack.containsKey(tag))
            logCallBack.get(tag).add(callback);
        else if (!logCallBack.containsKey(tag)) {
            Vector<LogCallback> initial = new Vector<>();
            initial.add(callback);
            logCallBack.put(tag, initial);
        }
        Log.log("LogCallback", "NEW CALLBACK ADDED TO " + tag);
    }

    public synchronized static void addVerboseCallback(LogCallback callback) {
        verboseLogCallback.add(callback);
    }

    public synchronized static void removeCallback(String tag, LogCallback callback) {
        if (logCallBack.containsKey(tag)) {
            logCallBack.get(tag).remove(callback);
            Log.log("LogCallback", "CALLBACK REMOVED FROM " + tag);
        }
    }

    public synchronized static ArrayList<String> getLog() {
        return (ArrayList<String>) log.clone();
    }

    public synchronized boolean setLogLimit(int logLimit){
        if(logLimit>0){
            Log.logLimit =logLimit;
            return true;
        }else
            return false;
    }

    public synchronized static void writeLogToFile(String filename) throws IOException {
        PrintWriter output = null;
        try {
            File file = new File(filename);
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    if (!file.getParentFile().mkdirs())
                        throw new IOException("Failed To Create Folder");
                }
                if (!file.createNewFile())
                    throw new IOException("Failed To Create File");
            }
            output = new PrintWriter(new FileOutputStream(file));
            for (String message : log) {
                output.println(message);
            }
        } finally {
            if (output != null)
                output.close();
        }
    }
}
