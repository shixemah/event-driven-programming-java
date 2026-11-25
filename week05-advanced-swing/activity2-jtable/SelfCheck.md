# Activity 2: JTable

Screenshots: `../screenshots/act2_students_*.png`.

## Tests run
- Selected Edward Norton (GPA 2.50 shown in **red** by the custom renderer), clicked Delete Selected, confirmed -> row removed.
- Add Student: entered Grace Hopper / 24 / A / 3.90 through four input dialogs -> new row appeared.
- Name and Grade columns are editable (double-click); ID, Age and GPA are not.

## A fix to the guide's code
The guide computes a new ID as `rowCount + 101`. After deleting a row that gives a **duplicate** ID (my first test produced a second 106 next to Fiona's 106). I changed it to "highest existing ID + 1", so Grace gets 107.

**Q1. TableModel vs JTable?** The model holds the data; `JTable` is the visual component. Changing the model updates the view automatically.
**Q2. Why a JScrollPane?** Without it the column headers do not appear and there are no scrollbars.
**Q3. Make a column non-editable?** Override `isCellEditable(row, column)` in the model and return `false` for that column.
