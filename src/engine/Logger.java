package engine;

import common.Consts;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.time.LocalDateTime;

public class Logger {
    private final boolean fileLog;
    private PrintWriter logOutput;

    public Logger(boolean fileLog){
        this.fileLog = fileLog;
        if(fileLog){
            try {
                File logFile = new File("log.txt");
                logOutput = new PrintWriter(logFile);
                if(logFile.createNewFile()) logOutput.println(timeAppend("New logfile created"));
                else logOutput.println("\n" + timeAppend("New instance started"));
                logOutput.flush();
            } catch (Exception e) {
                System.out.println("Error in accessing log file");
                fileLog = false;
            }

        }
        if(!fileLog){
            try{
                logOutput = new PrintWriter(System.out);
                logOutput.println(timeAppend("Game Initialized"));
                logOutput.flush();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String timeAppend(String inputString){ //Fügt einen Zeitstempel vor einem String hinzu
        String timeString;
        LocalDateTime ltd = LocalDateTime.now(Consts.systemClock);
        timeString = "[" + ltd  + "]:\t";
        return timeString + inputString;
    }

    public void log(String logString){
        logOutput.println(timeAppend(logString));
        logOutput.flush();
    }

}
