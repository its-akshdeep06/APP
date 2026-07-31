class Temperature {
    double celsius;

    void setTemperature(double c) {
        celsius = c;
    }

    void convert() {
        double fahrenheit = (celsius * 9 / 5) + 32;

        System.out.println("Temperature in Celsius: " + celsius + "°C");
        System.out.println("Temperature in Fahrenheit: " + fahrenheit + "°F");
    }

    public static void main(String[] args) {
        Temperature t = new Temperature();

        t.setTemperature(37);
        t.convert();
    }
}