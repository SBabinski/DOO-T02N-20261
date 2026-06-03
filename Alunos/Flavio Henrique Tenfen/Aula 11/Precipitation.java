public class Precipitation {

    public final double amountMm;

    public final double probabilityPercent;

    public Precipitation(double amountMm, double probabilityPercent) {
        this.amountMm           = amountMm;
        this.probabilityPercent = probabilityPercent;
    }

    public boolean isRelevant() {
        return amountMm > 0 || probabilityPercent > 20;
    }

    public String formattedAmount() {
        return String.format("%.1f mm", amountMm).replace('.', ',');
    }

    public String formattedProbability() {
        return String.format("%.0f%% de probabilidade de chuva", probabilityPercent);
    }
}
