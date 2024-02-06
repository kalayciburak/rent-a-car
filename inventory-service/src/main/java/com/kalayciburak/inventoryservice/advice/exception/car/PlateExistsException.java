package com.kalayciburak.inventoryservice.advice.exception.car;

import com.kalayciburak.commonpackage.advice.exception.CustomRuntimeException;

import static com.kalayciburak.commonpackage.util.constant.ExceptionCodes.Inventory.PLATE_EXISTS;

public class PlateExistsException extends CustomRuntimeException {
    public PlateExistsException(String message) {
        super(PLATE_EXISTS, message);
    }
}
