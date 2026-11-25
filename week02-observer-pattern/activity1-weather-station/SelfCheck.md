# Activity 1 Self-Check: Weather Station

1. **Role of the Subject interface?** It defines the contract every publisher must follow (`registerObserver`, `removeObserver`, `notifyObservers`), so observers and other code depend on an abstraction rather than one concrete class.
2. **`notifyObservers()` with no observers?** Nothing happens. The loop runs zero times, so no `update()` is called and no error occurs.
3. **`registerObserver()` vs `removeObserver()`?** `registerObserver()` adds an observer to the list so it receives future notifications; `removeObserver()` takes it out so it stops receiving them.
4. **Why is the observers list an instance variable?** It must persist between calls: observers registered once have to still be in the list when `setMeasurements()` is called later, and each Subject object needs its own list.
