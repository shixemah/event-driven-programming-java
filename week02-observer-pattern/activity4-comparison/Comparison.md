# Comparison of Event Notification Mechanisms in Java

| Feature | Custom Observer | java.util.Observer | PropertyChangeListener | JavaFX Bindings |
|---------|-----------------|--------------------|-----------------------|-----------------|
| **Push or Pull?** | Both possible | Pull (optional untyped `arg`) | Push (carries old + new) | Push (listeners receive old + new value) |
| **Is it deprecated?** | No | Yes (Java 9+) | No | No |
| **Requires inheritance?** | No (interface) | Yes (subject must extend `Observable`) | No | No |
| **Thread-safe?** | Depends on you | No | Yes (`PropertyChangeSupport` is thread-safe) | No - use on the JavaFX Application Thread |
| **Auto-generates boilerplate?** | No | Partly (list handling built in) | Yes (via `PropertyChangeSupport`) | Yes (`SimpleStringProperty` etc.) |
| **Best for...** | Custom systems | Legacy code | JavaBeans / Swing | JavaFX apps |
| **Can have multiple listeners?** | Yes | Yes | Yes | Yes |
| **Can be typed to send any data?** | Yes (you choose the parameters) | No (`Object arg`, needs casts) | Old/new values are `Object` (cast needed) | Yes (typed generics: `ObservableValue<T>`) |
| **Recommended today?** | Yes | No | Only for legacy / Swing and JavaBeans | Yes (in JavaFX) |

## Which One Should You Use?
- For a **plain Java project with custom needs** -> Custom Observer
- For a **JavaFX application** -> JavaFX Bindings
- For **legacy code using JavaBeans** -> PropertyChangeListener
- **Never** -> java.util.Observer

## Self-Check
1. **Which mechanism carries both the old and new value of a property?** PropertyChangeListener (`evt.getOldValue()` / `evt.getNewValue()`).
2. **Which requires a helper class to manage the listener list?** PropertyChangeListener, via `PropertyChangeSupport`.
3. **Which two are recommended for modern Java?** Custom Observer and JavaFX Bindings.
4. **Which is deprecated and should be avoided?** `java.util.Observer` / `Observable`.
