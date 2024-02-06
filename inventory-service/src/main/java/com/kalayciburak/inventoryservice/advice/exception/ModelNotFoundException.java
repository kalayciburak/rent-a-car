package com.kalayciburak.inventoryservice.advice.exception;

import com.kalayciburak.commonpackage.advice.exception.CustomRuntimeException;

import static com.kalayciburak.commonpackage.util.constant.ExceptionCodes.Inventory.MODEL_NOT_FOUND;

public class ModelNotFoundException extends CustomRuntimeException {
    public ModelNotFoundException(String message) {
        super(MODEL_NOT_FOUND, message);
    }
}