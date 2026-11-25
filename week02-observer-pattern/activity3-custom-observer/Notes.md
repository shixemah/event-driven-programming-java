# Activity 3: Tight Coupling vs Observer Pattern

## Identify the coupling problem
1. **Adding a third display class:** Yes, `DataModel.setValue()` must be edited to create and call the new display, so the model changes every time the UI changes.
2. **LabelDisplay deleted or renamed:** `DataModel` no longer compiles, because it names `LabelDisplay` directly. A UI change breaks the data model.
3. **Test DataModel in isolation?** Not easily. It always creates and calls the display classes, so they must exist and their output is mixed into every test.

## Comparison

| Aspect | Tightly Coupled | Observer Pattern |
|---|---|---|
| Adding a new display | Modify `DataModel` | Just register a new observer (e.g. `TextLogger`) |
| Testing DataModel in isolation | Hard - needs the display classes | Easy - no display classes required, or use a test observer |
| Removing a display | Modify `DataModel` | Just don't register it, or call `removeObserver()` |
| Dependencies of DataModel | Every concrete display | Only the `DataObserver` interface |

## Files
`TightlyCoupledSystem.java` is the "before" program (it has its own `main` and nested classes). The other seven classes (`DataSubject`, `DataObserver`, `DataModel`, `LabelDisplay`, `BarChartDisplay`, `TextLogger`, `RefactoredMain`) are the "after" system.
