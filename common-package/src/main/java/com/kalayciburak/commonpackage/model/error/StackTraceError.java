package com.kalayciburak.commonpackage.model.error;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StackTraceError extends ReferenceError {
    private String fileName;
    private String className;
    private String methodName;
    private int lineNumber;

    public StackTraceError(StackTraceElement element) {
        this.fileName = element.getFileName();
        this.className = element.getClassName();
        this.methodName = element.getMethodName();
        this.lineNumber = element.getLineNumber();
    }
}