# Limitations of java.util.Observer and java.util.Observable

## Limitation 1: Single Inheritance
Explain: Why is it a problem that Observable is a CLASS, not an INTERFACE?

Your answer: Java lets a class extend only one parent. A class that extends `Observable` can never extend anything else, so an existing class (say, one that already extends `JFrame` or a business-model base class) cannot become a Subject. An interface would have let any class add the behaviour while keeping its own parent.

## Limitation 2: Cannot Change the Notification Order
Explain: In what order are observers notified? Can you control this?

Your answer: The order is unspecified. In practice observers are notified in the reverse of the order they were added, but the documentation does not promise this and you cannot set priorities. Code that depends on one observer running before another is therefore fragile.

## Limitation 3: setChanged() is Confusing
Explain: Why must you call setChanged() before notifyObservers()?

Your answer: `notifyObservers()` does nothing unless the internal "changed" flag is true, and `setChanged()` is `protected`, which also forces you to subclass. It lets a Subject batch several updates and notify once, but it is easy to forget, and then observers silently never get notified - no error, no warning.

## Limitation 4: Pull Model by Default
Explain: How does the observer get the data? Is this efficient?

Your answer: `notifyObservers()` with no argument passes nothing, so each observer must cast the `Observable` to the right type and call getters to pull the data it needs. This is extra code and a risky cast, and the data may have changed again by the time it is read. A push model (data passed in `update()`) is simpler for the observer; the optional `Object arg` is untyped and needs casting too.

## Limitation 5: Deprecated Since Java 9
Explain: Why did Java deprecate these classes?

Your answer: The design has too many weaknesses: `Observable` is a class (single inheritance), the notification order is unspecified, `setChanged()` is easy to forget, observers must pull data with casts, and the API is not thread-safe and cannot be extended safely. Modern alternatives (`java.beans.PropertyChangeListener`, JavaFX properties, `java.util.concurrent.Flow`, or a custom Observer) fix these problems.

## Self-Check
1. **Why can't you extend another class if you already extend Observable?** Because Java allows a class to extend only one superclass, and `Observable` uses that single slot.
2. **Push vs pull model?** In push, the Subject sends the changed data as arguments to `update()`. In pull, the Subject only says "something changed" and each observer calls getters to fetch what it needs.
3. **Why did Java deprecate these classes?** See Limitation 5: single inheritance, unspecified order, forgettable `setChanged()`, pull-only design, and no thread safety or extensibility.
