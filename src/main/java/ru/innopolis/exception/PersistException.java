package ru.innopolis.exception;

public class PersistException extends RuntimeException {

    private static final long serialVersionUID = 1898976679841553548L;

    private String errorCode;
    private String errorMessage;

    public PersistException() {
        super();
    }

    public PersistException(String errorMessage) {
        super(errorMessage);
    }

    public PersistException(String errorCode, String errorMessage) {
        super(errorCode + ": " + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public PersistException(String errorMessage, Throwable rootCause) {
        super(errorMessage, rootCause);
    }

    public PersistException(String errorMessage, Throwable cause, boolean enableSuppression,
                            boolean writableStackTrace) {
        super(errorMessage, cause, enableSuppression, writableStackTrace);
    }

    public PersistException(Throwable cause) {
        super(cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
