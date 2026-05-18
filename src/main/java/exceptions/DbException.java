package exceptions;

import enums.ErrorCodes;

public class DbException extends ValidacaoServico {
    public DbException() {
        super(ErrorCodes.DB_EXCEPTION, "Erro no DB");
    }
}
