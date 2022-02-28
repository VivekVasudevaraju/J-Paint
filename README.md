# JPaint
JPaint Application 
Depaul University 
SE 450 Object Oriented Software Development
Professor Daniel Walker
Summer 2021

This application is a "Microsoft Paint"-like application in Java called JPaint.

Features:
-	Pick a shape (Ellipse, Triangle, Rectangle):
	- Selecting ellipse, rectangle or triangle will cause a click and drag event on the canvas to draw that shape
-	Pick a primary color
	-	Get the primary color from the UI
-	Pick a secondary color
	-	Get the secondary color from the UI
-	Click + drag to draw a shape
	-	Click + drag while in draw mode to draw a shape. Click on the canvas and drag. This will draw the selected shape within those boundaries
-	Select shading type 
	-	Outline Only:
		-	Only shape outline will be drawn. Use primary color to draw this
	-	Filled-In:
		-	Only the inside of the shape will be drawn; There will be no visible outline. Use primary color to draw this
	-	Outline and Filled-In:
		-	Both the inside and the outline will be drawn. Use primary color for the inside and secondary color for the outline.
-	Select shapes
	-	Click a shape (while in pointer mode) to select it
	-	Click and drag to select multiple shapes within those boundaries
	-	Click canvas to deselect
	-	Does not need to be undoable
-	Move shapes
	-	Clicking and dragging moves selected shapes, after shapes have been selected in select mode. Needs to be undoable and redoable
-	Copy
	-	Copies selected shapes. Does not need to be undoable and redoable
-	Paste
	-	If there is anything copied, paste it somewhere on the screen. Needs to be undoable and redoable
	-	Note: chosen method was to paste slightly offset from original location
-	Delete	
	-	Deletes selected shapes. Needs to be undoable and redoable
-	Undo
- Redo
- Group 
  - Group selected shapes. Group should act as a single unit. Needs to be undoable and redoable
- Ungroup
  - Grouped shapes should be ungrouped. Needs to be undoable and redoable


GUI framework provided, but some minimal updates required.
Also required to write handlers for click and drag events.

Requirements:
-	The application must use at least five different design patterns 
	-	Note: chosen patterns are Factory Method, Command Pattern, Strategy Pattern, Observer Pattern, Composite Pattern	 
