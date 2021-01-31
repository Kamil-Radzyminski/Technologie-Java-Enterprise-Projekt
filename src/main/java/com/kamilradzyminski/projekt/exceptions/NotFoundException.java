package com.kamilradzyminski.projekt.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Nie znaleziono " + id);
    }
}
