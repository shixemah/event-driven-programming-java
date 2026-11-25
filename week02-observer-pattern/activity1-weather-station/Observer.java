// OBSERVER INTERFACE
// Any class that wants to be notified of changes in the Subject must implement this.
public interface Observer {
    // Called by the Subject whenever its state changes (push model).
    void update(float temperature, float humidity, float pressure);
}
