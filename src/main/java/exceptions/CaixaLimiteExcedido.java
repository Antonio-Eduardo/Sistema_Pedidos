package exceptions;


import enums.ErrorCodes;
import exceptions.ValidacaoServico;

public class CaixaLimiteExcedido extends ValidacaoServico {
    public CaixaLimiteExcedido() {
        super(ErrorCodes.CAIXA_LIMITE_EXCEDIDO, "Limite do caixa foi excedido, tente em outro caixa.");
    }
}
