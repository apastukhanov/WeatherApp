import weather.*;

public class WeatherApplication {
    public static void main(String[] args) {
        Coordinates coord = new Coordinates();
        coord.getGPSCoordinates();

        Weather weather = (new Weather()).getWeather();
        HistoryWriter writerPT = new HistoryPlainTextWriter("history/historyRequests.txt");
        writerPT.save(weather);
        HistoryWriter writerJson = new HistoryJsonWriter("history/historyRequests.json");
        writerJson.save(weather);
        String wp = (new WeatherPrinter()).formatWeather(weather);
        System.out.println(wp);

    }
}
