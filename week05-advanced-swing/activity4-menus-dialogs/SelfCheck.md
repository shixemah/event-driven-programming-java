# Activity 4: Menus, Toolbars and Dialogs (Mini Editor)

Screenshots: `../screenshots/act4_editor_*.png`.

## Tests run
- Typed text -> word count updated live (7 words).
- Help > About -> information dialog.
- File > Save As -> real `JFileChooser` dialog; saved `week5_notes.txt` (the file contents were checked afterwards).
- New -> cleared the editor; File > Open -> reloaded the saved file; title and status bar showed "Opened: week5_notes.txt".
- File > Exit -> "Exit without saving?" confirmation.

## A fix to the guide's code
In the guide, the `saveAction` lambda calls `saveAsAction` **before** `saveAsAction` is declared, so the code does not compile ("cannot find symbol"). I declared Save As first, then Save.

**Q1. JMenu vs JMenuItem?** `JMenu` is a top-level menu in the bar (File); `JMenuItem` is a clickable entry inside it (Save).
**Q2. showOpenDialog vs showSaveDialog?** Open is for choosing an existing file to read; Save is for choosing where to write a file.
**Q3. What does `showConfirmDialog` return?** An int: `YES_OPTION`, `NO_OPTION`, `CANCEL_OPTION` or `CLOSED_OPTION`; compare with `==`.
