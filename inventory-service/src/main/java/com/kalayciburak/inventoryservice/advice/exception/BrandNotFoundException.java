package com.kalayciburak.inventoryservice.advice.exception;

import com.kalayciburak.commonpackage.advice.exception.CustomRuntimeException;

import static com.kalayciburak.commonpackage.util.constant.ExceptionCodes.Inventory.BRAND_NOT_FOUND;

public class BrandNotFoundException extends CustomRuntimeException {
    public BrandNotFoundException(String message) {
        super(BRAND_NOT_FOUND, message);
    }
}