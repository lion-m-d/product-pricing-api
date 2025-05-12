package com.ws.domain.price.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String expectedMessage = "Resource not found";
        NotFoundException exception = new NotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    void testExceptionThrown() {
        String expectedMessage = "Item not found";

        Exception exception = assertThrows(NotFoundException.class, () -> {
            throw new NotFoundException(expectedMessage);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }
}
