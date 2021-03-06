package ece.ing3.java.projet.exceptions;

/**
 * Exception thrown when a database operation failed.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class DatabaseException extends Exception {
	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * <p>
	 * Note that the detail message associated with cause is not automatically incorporated in this exception's detail message.
	 *
	 * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
	 * @param cause   the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method). (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public DatabaseException( String message, Throwable cause ) {
		super( message, cause );
	}

	/**
	 * Constructs a new exception with the specified detail message. The cause is not initialized, and may subsequently be initialized by a call to {@link Throwable#initCause(Throwable)}
	 *
	 * @param message the detail message. The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
	 */
	public DatabaseException( String message ) {
		super( message );
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message of {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code cause}). This constructor is useful for exceptions that are little more than wrappers for other throwables (for example, {@link java.security.PrivilegedActionException})
	 *
	 * @param cause the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method). (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public DatabaseException( Throwable cause ) {
		super( cause );
	}
}
