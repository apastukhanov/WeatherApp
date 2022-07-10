import org.json.JSONObject;

import java.util.Date;

public class Weather {
    private Celsius temperature;
    private WeatherType weatherType;
    private Date sunrise;
    private Date sunset;
    private String city;

    private Environment env;


    public Weather() {
        this.env = new Environment();
    }
    public Weather (Celsius temperature,
             WeatherType weatherType,
             Date sunrise,
             Date sunset,
             String city) {

        this.temperature = temperature;
        this.weatherType = weatherType;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.city = city;
        this.env = new Environment();
    }


    public void getWeather() {

        Weather weather = new Weather();
        String output  = getOpenWeatherResponse(55.750196, 37.871329);

    }

    private String getOpenWeatherResponse (double latitude,
                                           double longitude) {
        String url =  String.format(this.env.getEnv("OPENWEATHER_URL"),
                latitude,
                longitude,
                this.env.getEnv("OPENWEATHER_API"));

        System.out.println(url);
        try {
            JSONObject json =  JsonReader.readJsonFromUrl(url);
            System.out.println(json.toString());
        }
        catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }

        return "";

    }

}
