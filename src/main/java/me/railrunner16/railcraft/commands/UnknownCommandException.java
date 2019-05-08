package me.railrunner16.railcraft.commands;

public class UnknownCommandException extends Exception {
	public UnknownCommandException() {
		super();
	}

	public UnknownCommandException(String message) {
		super(message);
	}

	public UnknownCommandException(String message, Throwable t) {
		super(message, t);
	}

	public UnknownCommandException(Throwable t) {
		super(t);
	}
}
