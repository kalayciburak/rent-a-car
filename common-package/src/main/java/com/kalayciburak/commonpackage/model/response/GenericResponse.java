package com.kalayciburak.commonpackage.model.response;

import com.kalayciburak.commonpackage.util.constant.StatusCodes;
import com.kalayciburak.commonpackage.util.constant.Types;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;

import static com.kalayciburak.commonpackage.util.constant.Keywords.creationKeywords;

public class GenericResponse {
    public static <T> ResponseItem<T> createSuccessResponse(T data, String message) {
        var code = determineSuccessStatusCode(message);
        int size = getSize(data);

        return new ResponseItem<>(Types.Response.SUCCESS, code, data, message, true, size);
    }

    public static <T> ResponseItem<T> createSuccessResponse(String code, T data, String message) {
        int size = getSize(data);

        return new ResponseItem<>(Types.Response.SUCCESS, code, data, message, true, size);
    }

    public static <T> ResponseItem<T> createNotFoundResponse(String message) {
        return new ResponseItem<>(Types.Response.SUCCESS, StatusCodes.STATUS_NOT_FOUND, null, message, true, 0);
    }

    private static String determineSuccessStatusCode(String message) {
        return creationKeywords.stream()
                               .map(String::toLowerCase)
                               .anyMatch(message.toLowerCase()::contains)
                ? HttpStatus.CREATED.toString()
                : HttpStatus.OK.toString();
    }

    private static int getSize(Object data) {
        return switch (data) {
            case List<?> objects -> objects.size();
            case Page<?> objects -> objects.getSize();
            case Set<?> objects -> objects.size();
            case null, default -> 1;
        };
    }
}