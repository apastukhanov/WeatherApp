package weather;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
            json = new JSONObject(f);
        }
        return json;
    }

    private void writeJson(JSONObject json) {
        String outputJson = json.toString();
        System.out.println(outputJson);
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
        System.out.println("json after read");
        System.out.println(json.toString());
        String outputString = (new WeatherPrinter()).formatWeather(weather);
        json.put(this.dateStampString, outputString);
        System.out.println("json after put ->");
        System.out.println(json.toString());
        writeJson(json);
    }
}
