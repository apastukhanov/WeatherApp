import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Coordinates {

    private double latitude;
    private double longitude;


    Coordinates(double latitude, double longtitude) {
        this.latitude=latitude;
        this.longitude=longtitude;
    }

    Coordinates() {
    }

    public String toString() {
        return String.format("Coordinates(latitude=%f,longitude=%f)",
                this.getLatitude(), this.getLongitude());
    }
    public void getGPSCoordinates(){
        Coordinates coord = getWhereAmICoordinates();
        System.out.println(coord);
//        return new Coordinates(1.0, 1.0);
    };

    private Coordinates getWhereAmICoordinates () {
        String whereamiOutput = getWhereAmIOutput();
        Coordinates coord = parseCoordinates(whereamiOutput);
        return coord;
    }
    private Coordinates parseCoordinates (String whereamiOutput) {
        String[] lines = whereamiOutput.split("\n");
        double latitude = parseCoord(lines, "Latitude");
        double longitude = parseCoord(lines, "Longitude");
        return new Coordinates(latitude, longitude);
    }

    private double parseCoord (String[] lines, String coordType) {
        for (String l : lines) {
            if (l.startsWith(coordType)) {
                try {
                    return parseDoubleCoord(l.split(" ")[1]);
                } catch (Exception ex) {
                    System.out.println(ex.getStackTrace());
                }
            }
        }
        return -1.0;
    }

    private double parseDoubleCoord (String value) throws Exception {
        try {
            return  Double.parseDouble(value);
        }
        catch (Exception ex) {
            throw new Exception("Double not parsed");
        }
    }
    private String getWhereAmIOutput() {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("whereami");
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("whereami");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errBR = new BufferedReader(new InputStreamReader(
                process.getErrorStream()));
        StringBuffer sb = new StringBuffer();
        StringBuffer err = new StringBuffer();
        reader.lines().forEach(o -> sb.append(o+"\n"));
        errBR.lines().forEach(e -> err.append(e));
        int errCode = process.exitValue();
        if (errCode!=0) {
            System.out.println("Error:\n"+err.toString());
        } else {
        }
        return sb.toString();
    }

    private Coordinates roundCoordinates() {
        double latitude = Math.round(this.getLatitude()*100)/100;
        double longitude = Math.round(this.getLongitude()*100)/100;
        return new Coordinates(latitude, longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }



}
