package com.kalayciburak.inventoryservice.advice.exception;

import com.kalayciburak.commonpackage.advice.exception.CustomRuntimeException;

import static com.kalayciburak.commonpackage.util.constant.ExceptionCodes.Inventory.CITY_NOT_FOUND;

public class CityNotFoundException extends CustomRuntimeException {
    public CityNotFoundException(String message) {
        super(CITY_NOT_FOUND, message);
    }
}