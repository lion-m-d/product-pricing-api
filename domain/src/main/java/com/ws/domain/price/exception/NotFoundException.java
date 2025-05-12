package com.ws.domain.price.exception;

/**
 * Custom exception thrown when a requested resource is not found.
 * <p>This exception is typically used to indicate that a certain resource,
 * such as a price or product, could not be found in the system. It extends
 * {@link RuntimeException} to allow for unchecked exceptions that do not
 * require explicit handling.</p>
 * @see RuntimeException
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code NotFoundException} with the specified detail message.
     *
     * @param message the detail message explaining why the exception was thrown
     */
    public NotFoundException(String message) {
        super(message);
    }
}
