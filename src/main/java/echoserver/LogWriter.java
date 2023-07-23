package echoserver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter {
    private final String logDirectory;

    public LogWriter(String logDirectory) {
        this.logDirectory = logDirectory;
    }

    public void logRequestAndResponse(byte[] requestDump, byte[] responseDump) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = dateFormat.format(new Date());
        String logDir = logDirectory + "/" + currentDate;
        File directory = new File(logDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        int serialNumber = 1;
        File logFile;
        while (true) {
            logFile = new File(directory, "log" + serialNumber + ".txt");
            if (!logFile.exists()) {
                break;
            }
            serialNumber++;
        }

        try (FileOutputStream fos = new FileOutputStream(logFile)) {
            // リクエストをログファイルに書き込む
            fos.write("Request:\n".getBytes());
            fos.write(requestDump);
            fos.write("\n".getBytes());

            // レスポンスをログファイルに書き込む
            fos.write("Response:\n".getBytes());
            fos.write(responseDump);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}