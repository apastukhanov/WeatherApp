public class WeatherApplication {
    public static void main(String[] args) {
        Coordinates coord = new Coordinates();
        coord.getGPSCoordinates();

        Weather weather = (new Weather()).getWeather();
        HistoryWriter writer = new HistoryPlainTextWriter("history/historyRequests.txt");
        writer.save(weather);
        String wp = (new WeatherPrinter()).formatWeather(weather);
        System.out.println(wp);

    }
}
