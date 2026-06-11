public class DailyTempRange {

    public final double max;

    public final double min;

    public DailyTempRange(double max, double min) {
        this.max = max;
        this.min = min;
    }

    public String formattedMax() {
        return Double.isNaN(max) ? "—" : Math.round(max) + "°C";
    }
    
    public String formattedMin() {
        return Double.isNaN(min) ? "—" : Math.round(min) + "°C";
    }
}
