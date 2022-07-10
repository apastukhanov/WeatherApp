import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Coordinates coord = new Coordinates();
        coord.getGPSCoordinates();

        Double i = 1.5;

        Environment env = new Environment();

        System.out.println(env.getEnv("OPENWEATHER_API"));
        System.out.println(env.getEnv("OPENWEATHER_URL"));
        System.out.println(env.getEnv("USE_ROUNDED_COORDS"));
        System.out.println(env.getEnv("USE_"));

        Weather weather = new Weather(new Celsius(34.2),
                WeatherType.CLEAR,
                new Date(),
                new Date(),
                "lipetsk");

        Celsius temp = new Celsius(34.4);
        System.out.println(temp);

        weather.getWeather();



    }
}
