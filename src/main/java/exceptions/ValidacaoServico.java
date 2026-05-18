package exceptions;


import enums.ErrorCodes;

public class ValidacaoServico extends RuntimeException {
    ErrorCodes errorCode;

    public ValidacaoServico(ErrorCodes errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }
}
