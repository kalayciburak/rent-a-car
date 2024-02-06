package com.kalayciburak.inventoryservice.advice.exception;

import com.kalayciburak.commonpackage.advice.exception.CustomRuntimeException;

import static com.kalayciburak.commonpackage.util.constant.ExceptionCodes.Inventory.LOCATION_NOT_FOUND;

public class LocationNotFoundException extends CustomRuntimeException {
    public LocationNotFoundException(String message) {
        super(LOCATION_NOT_FOUND, message);
    }
}