
public class CurrentTemp {

    public final double value;

    public CurrentTemp(double value) {
        this.value = value;
    }

    public String formatted() {
        return Double.isNaN(value) ? "—" : Math.round(value) + "°C";
    }

    public String formattedNumber() {
        return Double.isNaN(value) ? "—" : String.valueOf(Math.round(value));
    }
}
