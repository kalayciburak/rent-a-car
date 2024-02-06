package com.kalayciburak.inventoryservice.advice.exception;

import com.kalayciburak.commonpackage.advice.exception.CustomRuntimeException;

import static com.kalayciburak.commonpackage.util.constant.ExceptionCodes.Inventory.LOOKUP_NOT_FOUND;

public class LookupNotFoundException extends CustomRuntimeException {
    public LookupNotFoundException(String message) {
        super(LOOKUP_NOT_FOUND, message);
    }
}
