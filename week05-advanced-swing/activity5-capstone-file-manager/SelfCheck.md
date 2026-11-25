# Activity 5: Capstone - File Manager

Screenshots: `../screenshots/act5_filemanager_*.png`. **They show a small sample folder I made for testing (`testhome`), not your own files.** On your computer the tree starts at your real home folder (`C:\Users\<you>`).

## Tests run
- Tree on the left, table on the right, toolbar (Home, Up Folder, Refresh), menus (File, Help) and a status bar.
- Selecting `Documents` in the tree filled the table with CV.pdf (50.8 KB), Notes.txt, Reports (Folder) and Resume.docx; the status bar read "4 items in Documents".
- Double-clicking the `Reports`-style folder row opened it; Up Folder went back; Home returned to the start.

## Two changes to the guide's code
1. **Depth cap (`MAX_TREE_DEPTH = 3`).** The guide builds the whole folder tree of your home folder before the window appears. A real home folder (Documents, AppData, OneDrive...) can contain tens of thousands of folders, which makes start-up very slow. The cap keeps start-up fast; change the constant if you want a deeper tree.
2. **Tree renderer.** The guide's tree displays the full path on every node. I added a small `DefaultTreeCellRenderer` that shows just the folder name (this is the Outcome 2 "custom renderer" in action).

I also sort the folder listings so they are in a predictable order.

**Q1. How do the tree and table communicate?** Through a `TreeSelectionListener`: selecting a folder calls `loadFolderContents(folder)`, which fills the table model.
**Q2. Why `tableModel.setRowCount(0)` first?** To clear the previous folder's rows; otherwise the old files would stay.
**Q3. What does JSplitPane do?** Splits the window into two resizable panes with a draggable divider.
