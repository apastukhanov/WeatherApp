package weather;

class Celsius {
    private double celsius;

    public Celsius () {

    }
    public Celsius (double celsius) {
        this.celsius = celsius;
    }


    public double getCelsius() {
        return celsius;
    }


    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }

    public String toString () {
        return String.format("%.1f\u00b0C", this.celsius);
    }
}