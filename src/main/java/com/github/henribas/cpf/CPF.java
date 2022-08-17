package com.github.henribas.cpf;

public interface CPF {

    public String numero();
    
    public static String formatar(final String numero) {
        return CPFDidatico.formatar(numero);
    }

    public static String removerFormatacao(final String numero) {
	    return CPFDidatico.removerFormatacao(numero);
	}

    public static boolean validar(final String numero) {
        return CPFDidatico.validar(numero);
    }

    public static boolean validar(final CPF cpf) {
        return CPFDidatico.validar(cpf);
    }

    public static CPF de(final String numero) {
        return CPFDidatico.de(numero);
    }

    public static CPF de(final CPF cpf) {
        return CPFDidatico.de(cpf);
    }
    
}
