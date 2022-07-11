package weather;

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
        this.filePath = Paths.get(filePath);
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
        } catch (Exception ex){
            System.out.println(ex.getStackTrace());
        }

    }
}
