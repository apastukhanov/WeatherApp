package weather;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class HistoryPlainTextWriter implements HistoryWriter{

    private Path filePath;
    private SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private Date date;
    private String dateStampString;

    public HistoryPlainTextWriter (String filePath) {
        Environment env = new Environment();
        this.filePath = Paths.get(env.getEnv("PROJECT_FOLDER") + "/" + filePath);
        this.date = new Date(System.currentTimeMillis());
        this.dateStampString = formatter.format(this.date);
    }

    public void save(Weather weather) {
        String outputString = (new WeatherPrinter()).formatWeather(weather);
        outputString = this.dateStampString + "\n" + outputString + "\n";
        try {
            Files.writeString(
                   this.filePath, outputString, CREATE, APPEND
            );
        } catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
