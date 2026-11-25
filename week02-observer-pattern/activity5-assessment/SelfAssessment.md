# Week 2 Final Self-Assessment

## Learning Outcome 1: Design and Implement the Observer Pattern

**1. UML diagram**

```
        <<interface>>                         <<interface>>
          Subject                      observers   Observer
   +registerObserver(o)  <>---------------------> +update(...)
   +removeObserver(o)                                  ^
   +notifyObservers()                                  |
            ^                                          |
            |                                +---------+----------+
      WeatherData                            |                    |
     (ConcreteSubject)           CurrentConditionsDisplay   StatisticsDisplay
   -observers: List<Observer>      (ConcreteObserver)       (ConcreteObserver)
   -temperature, humidity, pressure
   +setMeasurements(...)
```
Subject = interface publishers implement. Observer = interface subscribers implement. ConcreteSubject = `WeatherData`. ConcreteObservers = the display classes.

**2. Subject interface from memory**
```java
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
```

**3. Why does the Subject need a list of observers?** So it can notify every interested party without knowing who they are; observers can be added or removed at run time.

## Learning Outcome 2: Evaluate Legacy java.util.Observer

**4. Three problems with Observable:** it is a class (single inheritance), the notification order is unspecified, `setChanged()` is easy to forget so observers silently get nothing (also: pull model, not thread-safe).

**5. Why deprecated?** Because of those design flaws; better alternatives exist (custom observers, `PropertyChangeListener`, JavaFX properties, `Flow`).

**6. Pull model and why harder?** The Subject only signals a change and observers call getters to fetch data. It needs a cast to the concrete subject type, extra calls, and the data may change between notification and read.

## Learning Outcome 3: Create a Custom Event Notification System

**7. Methods a custom Subject must provide:** `registerObserver()`, `removeObserver()` and `notifyObservers()`.

**8. How does the Subject notify all observers?** It loops through its list of observers and calls each one's `update()` method, passing the new data.

**9. Preventing memory leaks:** call `removeObserver()` when an observer is no longer needed, so the Subject no longer holds a reference that keeps it alive (or use weak references).

## Learning Outcome 4: Compare Different Mechanisms

**10. Four mechanisms:** custom Observer, `java.util.Observer`/`Observable`, `PropertyChangeListener`, JavaFX Properties/Bindings.

**11. Best for JavaFX?** JavaFX Properties and Bindings.

**12. Deprecated?** `java.util.Observer` / `Observable` (since Java 9).

**Score:** 12/12 - Excellent.

---

## One-Page Summary of the Observer Pattern

The Observer pattern lets one object (the Subject) keep a list of other objects (Observers) and automatically notify them whenever its state changes. The Subject only knows the `Observer` interface, so observers can be added or removed without changing the Subject. That loose coupling makes code easier to extend and test. In the weather station, `WeatherData` notified three displays; adding `TextLogger` to the data model needed no change to the model. Java's old `Observable`/`Observer` classes were deprecated because of single inheritance, unspecified ordering and a forgettable `setChanged()`. Alternatives are `PropertyChangeListener` for JavaBeans/Swing and JavaFX Properties for modern GUIs.

## Final Reflection

The Observer pattern is a design where a Subject automatically notifies a list of registered Observers when its state changes. It matters because it removes tight coupling: the data source does not need to know what displays or loggers depend on it. It is the basis of event-driven programming, since an event source, its listeners and the handlers called on an event are the same Subject-Observer relationship that Swing's `addActionListener` uses. The event queue then delivers events in order, and each listener reacts. For my own plain Java project I would use a custom Observer because it is simple, typed, and fully under my control; for a JavaFX project I would use properties and bindings instead. I would never use `java.util.Observable` in new code.
