# Activity 1: JList and Custom Renderers

Screenshots: `../screenshots/act1_contacts_*.png` (Alice and Charlie green, Diana orange, Bob and Edward grey; selecting Diana shows "Selected: Diana - Online/Away" in the box underneath). I used " - " instead of the guide's em dash so the file compiles on Windows without an encoding flag.

**Q1. What does a custom renderer do?** It controls how each JList item appears: instead of plain text you can add icons, colours, fonts or multi-part layouts.
**Q2. How often is the renderer called?** For every visible row, repeatedly (on every repaint and while scrolling), so renderer code must be fast and must not build heavy objects.
**Q3. Why `!e.getValueIsAdjusting()`?** Without it the listener fires several times for one click while the selection is changing; the check makes it run once, when the selection is final.
