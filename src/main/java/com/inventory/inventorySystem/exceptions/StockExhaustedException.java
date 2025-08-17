package com.inventory.inventorySystem.exceptions;

public class StockExhaustedException extends RuntimeException {
    public StockExhaustedException(String message) {
        super(message);
    }
}
