package exceptions;

import enums.ErrorCodes;

public class TransacaoException extends ValidacaoServico{
    public TransacaoException(String msg) {
        super(ErrorCodes.TRANSACTION_ERROR,"Falha na transacao");
    }
}
