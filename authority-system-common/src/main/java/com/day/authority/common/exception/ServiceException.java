package com.day.authority.common.exception;

/**
 * @author DayXhYao
 * @date 2023/3/11 10:47
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }


}
