package model.command;

import java.util.Stack;

public class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<>();
	private static final Stack<IUndoable> redoStack = new Stack<>();

	/**
	 * Add Command to the Undo Stack
	 * @param cmd
	 */
	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}

	/**
	 * Pop the last command from the Undo Stack.
	 * Add the popper command to the Redo Stack
	 * @return boolean
	 */
	public static boolean undo() {
		boolean res = !undoStack.empty();
		if (res) {
			IUndoable c = undoStack.pop();
			redoStack.push(c);
			c.undo();
		}
		return res;
	}

	/**
	 * Pop the last command from the Redo Stack.
	 * Add the popper command to the Undo Stack
	 * @return boolean
	 */
	public static boolean redo() {
		boolean res = !redoStack.empty();
		if (res) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			c.redo();
		}
		return res;
	}

	/**
	 * Get the command list in the Undo Stack
	 * @return Stack<IUndoable> undoStack
	 */
	public static Stack<IUndoable> getUndoStack() {
		return undoStack;
	}

	/**
	 * Get the command list in the Redo Stack
	 * @return Stack<IUndoable> redoStack
	 */
	public static Stack<IUndoable> getRedoStack() {
		return redoStack;
	}
}

