package weather;

public enum WeatherType {
    THUNDERSTORM("Гроза"),
    DRIZZLE("Изморось"),
    RAIN ("Дождь"),
    SNOW ("Снег"),
    CLEAR ("Ясно"),
    FOG ("Туман"),
    CLOUDS ("Облачно");

    public final String label;
    private WeatherType(String label) {
        this.label=label;
    }
}
