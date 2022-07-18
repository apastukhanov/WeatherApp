package com.pae.weather;

import java.text.SimpleDateFormat;

public class WeatherPrinter {
    public String formatWeather (Weather weather) {

        SimpleDateFormat dateOutPutFormat = new SimpleDateFormat("HH:mm");

        String weatherOutputString = String.format(
                "%s, температура %s, \n" +
                "%s\n" +
                "Восход: %s\n"  +
                "Заход: %s\n", weather.getCity(), weather.getTemperature(),
                weather.getWeatherType().label,
                dateOutPutFormat.format(weather.getSunrise()),
                dateOutPutFormat.format(weather.getSunset())
        );
        return weatherOutputString;
    }
}
