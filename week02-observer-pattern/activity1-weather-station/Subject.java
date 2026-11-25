// SUBJECT INTERFACE: operations every "publisher" must support.
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
