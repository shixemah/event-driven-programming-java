# Activity 5: Student Portal Mockup

Screenshot: `../screenshots/act5_student_portal.png`. Customised with my own name, the dates updated to November 2026, and the stats cards. (Note: the guide's template used past October dates and "John Doe".)

**Q1. How many layout managers?** Four: BorderLayout (main frame, header, status bar), GridLayout (sidebar and stat cards), BoxLayout (vertical main content) and FlowLayout implicitly inside default panels.

**Q2. Why `Box.createVerticalStrut(20)`?** It inserts an invisible 20-pixel spacer between components, which is cleaner than empty panels or margins.

**Q3. Purpose of `setPreferredSize()` on the sidebar?** It fixes the sidebar width at 180 px; otherwise BorderLayout.WEST would shrink it to fit its contents.
