package br.edu.utfpr.dungeontable.exception;

public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String formattedMessage;

    public BusinessException(ErrorCode errorCode, Object... args) {
        super(errorCode.formatMessage(args));
        this.errorCode = errorCode;
        this.formattedMessage = errorCode.formatMessage(args);
    }

    public String getCodeDescription() {
        return errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return formattedMessage;
    }
}
