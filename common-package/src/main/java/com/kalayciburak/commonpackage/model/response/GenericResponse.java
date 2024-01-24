package com.kalayciburak.commonpackage.model.response;

import com.kalayciburak.commonpackage.util.constant.Messages;
import com.kalayciburak.commonpackage.util.constant.StatusCodes;
import com.kalayciburak.commonpackage.util.constant.Types;

import java.util.List;

public class GenericResponse {
    public static <T> ResponseItem<T> createSuccessResponse(T data, String message) {
        var code = determineSuccessStatusCode(message);
        int size = data instanceof List<?> ? ((List<?>) data).size() : 1;

        return new ResponseItem<>(Types.Response.SUCCESS, code, data, message, true, size);
    }

    public static <T> ResponseItem<T> createSuccessResponse(T data, int size, String message) {
        var code = determineSuccessStatusCode(message);

        return new ResponseItem<>(Types.Response.SUCCESS, code, data, message, true, size);
    }

    public static <T> ResponseItem<T> createNotFoundResponse(String message) {
        return new ResponseItem<>(Types.Response.SUCCESS, StatusCodes.STATUS_NOT_FOUND, null, message, true, 0);
    }

    private static String determineSuccessStatusCode(String message) {
        return (message.equals(Messages.Entity.SAVED) || message.equals(Messages.Entities.SAVED))
                ? StatusCodes.STATUS_CREATED
                : StatusCodes.STATUS_OK;
    }
}