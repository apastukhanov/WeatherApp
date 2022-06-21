
public class Main {
    public static void main(String[] args) {
        Coordinates coord = new Coordinates();
        coord.getGPSCoordinates();

        Environment env = new Environment();

        System.out.println(env.getEnv("OPENWEATHER_API"));
        System.out.println(env.getEnv("OPENWEATHER_URL"));
        System.out.println(env.getEnv("USE_ROUNDED_COORDS"));
        System.out.println(env.getEnv("USE_"));



    }
}
