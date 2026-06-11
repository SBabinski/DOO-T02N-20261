public class Humidity {

    public final double percent;

    public Humidity(double percent) {
        this.percent = percent;
    }

    public String formatted() {
        return Double.isNaN(percent) ? "—" : Math.round(percent) + " %";
    }
}
