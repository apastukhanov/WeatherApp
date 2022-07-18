package com.pae.ioUtils;

import com.pae.utils.Environment;
import com.pae.weather.Weather;
import com.pae.weather.WeatherPrinter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryJsonWriter implements HistoryWriter {

    private Path filePath;
    private SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Date date;
    private String dateStampString;


    public HistoryJsonWriter(String filePath) {
        Environment env =  new Environment();
        this.filePath = Paths.get(env.getEnv("PROJECT_FOLDER") + "/" + filePath);
        this.date = new Date(System.currentTimeMillis());
        this.dateStampString = formatter.format(this.date);
    }

    private JSONObject readJson () {
        JSONObject json = new JSONObject();
        File f = new File(String.valueOf(this.filePath));
        if (f.exists()) {
            try {
            byte[] bytes = Files.readAllBytes(this.filePath);
            String fileContent = new String (bytes);
            json = new JSONObject(fileContent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return json;
    }

    private void writeJson(JSONObject json) {
        String outputJson = json.toString();
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(String.valueOf(this.filePath)));
            writer.write(outputJson);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Weather weather) {
        JSONObject json = readJson();
        String outputString = (new WeatherPrinter()).formatWeather(weather);
        json.put(this.dateStampString, outputString);
        writeJson(json);
    }
}
