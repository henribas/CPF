package com.github.henribas.cpf;

public interface CPF {

    public String numero();
    
    public static String formatar(final String numero) {
        return CPFDidatico.formatar(numero);
    }

    public static String removerFormatacao(final String numero) {
	    return CPFDidatico.removerFormatacao(numero);
	}

    public static boolean valido(final String numero) {
        return CPFDidatico.valido(numero);
    }

    public static boolean valido(final CPF cpf) {
        return CPFDidatico.valido(cpf);
    }

    public static CPF de(final String numero) {
        return CPFDidatico.de(numero);
    }

    public static CPF de(final String numero, final boolean validar) {
        return CPFDidatico.de(numero, validar);
    }

    public static CPF de(final CPF cpf) {
        return CPFDidatico.de(cpf);
    }
    
}
