package com.krushit.common.utils;

import com.krushit.common.Message;
import com.krushit.common.exception.ApplicationException;

public class ApplicationUtils {
    public static void validateJsonRequest(String requestContentType) throws ApplicationException {
        if (!Message.APPLICATION_JSON.equals(requestContentType)) {
            throw new ApplicationException(Message.INVALID_CONTENT_TYPE);
        }
    }
}
