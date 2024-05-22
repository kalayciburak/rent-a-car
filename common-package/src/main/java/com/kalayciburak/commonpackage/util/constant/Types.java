package com.kalayciburak.commonpackage.util.constant;

public class Types {
    public static final String MAPPER_COMPONENT_MODEL = "spring";

    public static class Response {
        public static final String SUCCESS = "SUCCESS";
        public static final String ERROR = "ERROR";
        public static final String WARNING = "WARNING";
        public static final String INFO = "INFO";
    }

    public static class Exception {
        public static final String DEFAULT = "ERROR: EXCEPTION";
        public static final String RUNTIME = "ERROR: RUNTIME_EXCEPTION";
        public static final String NOT_FOUND = "ERROR: ENTITY_NOT_FOUND_EXCEPTION";
        public static final String VALIDATION = "ERROR: VALIDATION_EXCEPTION";
        public static final String REQUEST_PARAMETER = "ERROR: REQUEST_PARAMETER_EXCEPTION";
        public static final String MEDIA_TYPE = "ERROR: MEDIA_TYPE_EXCEPTION";
        public static final String DATA_INTEGRITY = "ERROR: DATA_INTEGRITY_EXCEPTION";
        public static final String WRITING_OUTPUT = "ERROR: WRITING_OUTPUT_EXCEPTION";
        public static final String TYPE_MISMATCH = "ERROR: TYPE_MISMATCH_EXCEPTION";
        public static final String HANDLER = "ERROR: HANDLER_EXCEPTION";
        public static final String FEIGN = "ERROR: FEIGN_EXCEPTION";
        public static final String UNAUTHORIZED = "ERROR: UNAUTHORIZED_EXCEPTION";
        public static final String CONNECTION = "ERROR: CONNECTION_EXCEPTION";
        public static final String JSON_PROCESSING = "ERROR: JSON_PROCESSING_EXCEPTION";
        public static final String KEYCLOAK = "ERROR: KEYCLOAK_EXCEPTION";
        public static final String ILLEGAL_ARGUMENT = "ERROR: ILLEGAL_ARGUMENT_EXCEPTION";
    }
}