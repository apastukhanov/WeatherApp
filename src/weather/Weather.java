package weather;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

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


    public Weather getWeather() {
        Coordinates coord = new Coordinates();
        coord = coord.getGPSCoordinates();
        String respOpenWeather  = getOpenWeatherResponse(coord.getLatitude(),
                coord.getLongitude());
        Weather weather = parseOpenWeatherResponse(respOpenWeather);
        return weather;

    }

    private String getOpenWeatherResponse (double latitude,
                                           double longitude) {
        String url =  String.format(this.env.getEnv("OPENWEATHER_URL"),
                latitude,
                longitude,
                this.env.getEnv("OPENWEATHER_API"));

        try {
            JSONObject json =  JsonReader.readJsonFromUrl(url);
            return json.toString();
        }
        catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        return "";
    }

    private Weather parseOpenWeatherResponse (String respOpenWeather) {
        JSONObject json = new JSONObject(respOpenWeather);
        Celsius temp = parseTemperature(json);
        WeatherType weatherType = parseWeatherType(json);
        Date sunrise = parseSunTime(json, "sunrise");
        Date sunset = parseSunTime(json, "sunset");
        String city = parseCity(json);

        return new Weather(temp,
                weatherType,
                sunrise,
                sunset,
                city);
    }

    private Celsius parseTemperature(JSONObject json) {
        Celsius temp = new Celsius(Double.parseDouble(
                String.valueOf(
                        json.getJSONObject("main").get("temp"))));
        return temp;
    }

    private WeatherType parseWeatherType(JSONObject json) {
        String weatherTypeId = String.valueOf(json.getJSONArray(
                "weather").getJSONObject(0).get("id"));

        HashMap<String, WeatherType> weatherTypeDict = new HashMap<>();
        weatherTypeDict.put("1", WeatherType.THUNDERSTORM);
        weatherTypeDict.put("3", WeatherType.DRIZZLE);
        weatherTypeDict.put("5", WeatherType.RAIN);
        weatherTypeDict.put("6", WeatherType.SNOW);
        weatherTypeDict.put("7", WeatherType.FOG);
        weatherTypeDict.put("800", WeatherType.CLEAR);
        weatherTypeDict.put("80", WeatherType.CLOUDS);

        for (String key : weatherTypeDict.keySet()) {
            if (weatherTypeId.startsWith(key))
                return weatherTypeDict.get(key);
        }
        return WeatherType.valueOf("ясно");
    }

    private Date parseSunTime(JSONObject json, String time) {
        Timestamp timestamp = new Timestamp(Long.parseLong(
                String.valueOf(json.getJSONObject("sys").get(time)))*1000);
        Date date = new Date(timestamp.getTime());
        return date;
    }

    private String parseCity (JSONObject json) {
        return String.valueOf(json.get("name"));
    }

    public String toString() {
        return String.format("weather.Weather[%s, %s, %s, %s, %s]",
                this.temperature,
                this.weatherType,
                this.sunrise,
                this.sunset,
                this.city);
    }
    public Celsius getTemperature() {
        return temperature;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public String getCity() {
        return city;
    }
}
