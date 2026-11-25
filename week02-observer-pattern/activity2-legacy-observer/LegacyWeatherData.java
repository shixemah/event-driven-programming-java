import java.util.Observable;

// LEGACY SUBJECT using java.util.Observable (deprecated).
// We must EXTEND Observable - a key limitation, since Java has single inheritance.
@SuppressWarnings("deprecation")
public class LegacyWeatherData extends Observable {
    private float temperature;
    private float humidity;

    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        setChanged();        // required before notifying
        notifyObservers();   // no data passed - pull model
    }

    public float getTemperature() { return temperature; }
    public float getHumidity() { return humidity; }
}
