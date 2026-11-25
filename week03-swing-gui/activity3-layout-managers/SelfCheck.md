# Activity 3: Layout Managers

Files: `FlowLayoutDemo`, `BorderLayoutDemo`, `GridLayoutDemo`, `BoxLayoutDemo` (the fourth layout named in the activity's purpose), and `CombinedLayoutDemo` (the nested "app shell"). Screenshots are in `../screenshots/act3_*.png`.

| Layout | What I observed | Typical use |
|---|---|---|
| FlowLayout | Buttons go left to right and wrap to the next row when the window is narrower | Toolbars, rows of buttons |
| BorderLayout | NORTH/SOUTH stretch horizontally, EAST/WEST vertically, CENTER takes the rest | Main window shell (header, sidebar, footer) |
| GridLayout | Equal-sized cells in a fixed grid | Calculator keypad, photo gallery |
| BoxLayout | Components stack in a single row or column; struts and glue control spacing | Vertical forms, stacked content |

**Q1. GridLayout over FlowLayout?** When cells should be equal-sized in a fixed grid (calculator, gallery). FlowLayout lets components keep natural sizes and wrap.

**Q2. Why nest layout managers?** No single layout fits a whole screen. Panels each with their own layout can be combined, which is how real applications are built (the app shell uses Border + Flow + Grid).

**Q3. What is different about BorderLayout.CENTER?** It expands to fill all the remaining space; the other four regions take only what they need.
