# Activity 3: JTree and JTabbedPane

Screenshots: `../screenshots/act3_tree_*.png`. Console output when switching tabs: `Active tab: Appearance (index 1)`, `Active tab: About (index 2)` (I added a `ChangeListener` to show how tab changes are detected). Selecting `CV.pdf` shows "You selected: CV.pdf / Level: 2". I also put the three theme radio buttons in a `ButtonGroup`, which the guide's code left out, so only one theme can be selected at a time.

**Q1. JTree vs JList?** A JList is a flat list; a JTree shows hierarchical data (folders within folders).
**Q2. What does `addTab()` do?** Adds a tab with a title and a content component; clicking the tab shows that component.
**Q3. What is `DefaultMutableTreeNode`?** A tree node that can hold children; "mutable" means children can be added or removed after creation.
