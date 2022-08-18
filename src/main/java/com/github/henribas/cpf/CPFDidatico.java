package com.github.henribas.cpf;

import org.apache.commons.lang3.StringUtils;

final class CPFDidatico implements CPF {
    
	private static final String CPF_SEM_MASCARA = "^\\d{11}$";
	private static final String CPF_COM_MASCARA = "^\\d{3}.\\d{3}.\\d{3}-\\d{2}$";

	private final String numero;

    private CPFDidatico(final String numero) {
        validar(numero);
		this.numero = removerFormatacao(numero);
	}

    private CPFDidatico(final String numero, final boolean validar) {
        if (validar) {
            validar(numero);
        }
		this.numero = removerFormatacao(numero);
	}

    private CPFDidatico(final CPF cpf) {
        if (!valido(cpf)) {
            throw new IllegalArgumentException("Informe um CPF válido.");
        }
		this.numero = cpf.numero();
	}

    private void validar(final String numero) {
        if (!valido(numero)) {
            throw new IllegalArgumentException("Informe um número de CPF válido.");
        }
    }

	static CPF de(final String numero) {
		return new CPFDidatico(numero);
	}

    static CPF de(final String numero, final boolean validar) {
		return new CPFDidatico(numero, validar);
	}

    static CPF de(final CPF cpf) {
		return new CPFDidatico(cpf);
	}

    static String formatar(final String numero) {
		String numeroSemFormatacao = CPF.removerFormatacao(numero);

        return 
            numeroSemFormatacao.substring(0,3) + "." + 
            numeroSemFormatacao.substring(3,6) + "." + 
            numeroSemFormatacao.substring(6,9) + "-" + 
            numeroSemFormatacao.substring(9,11);
    }

    static String removerFormatacao(final String numero) {
		return numero.replaceAll("\\D", "");
	}

    static boolean valido(final String numero) {
        if (!informouNumero(numero)) {
            return false;
        }

        if (!estaNoPadrao(numero)) {
            return false;
        }
		
        if (numeroInvalido(numero)) {
            return false;
        }

		return digitosVerificadoresIguais(numeros(numero));
    }

    private static boolean informouNumero(final String numero) {
        return StringUtils.isNotBlank(numero);
    }

    private static boolean estaNoPadrao(final String numero) {
        return numero.matches(CPF_SEM_MASCARA) || numero.matches(CPF_COM_MASCARA);
    }

    private static boolean numeroInvalido(final String numero) {
        String numeroSemFormatacao = numero.replaceAll("\\D", "");

        if (numeroSemFormatacao.equals("00000000000") || 
            numeroSemFormatacao.equals("11111111111") ||
			numeroSemFormatacao.equals("22222222222") || 
            numeroSemFormatacao.equals("33333333333") ||
			numeroSemFormatacao.equals("44444444444") || 
            numeroSemFormatacao.equals("55555555555") ||
			numeroSemFormatacao.equals("66666666666") || 
            numeroSemFormatacao.equals("77777777777") ||
			numeroSemFormatacao.equals("88888888888") || 
            numeroSemFormatacao.equals("99999999999")) {

			return(true);
		}

        return false;
    }

	private static int[] numeros(final String numero) {
		String numeroSemFormatacao = numero.replaceAll("\\D", "");
        String[] numerosString = numeroSemFormatacao.split("");
		int[] numeros = new int[11];

		for (int i = 0; i < numerosString.length; i++) {
			numeros[i] = Integer.valueOf(numerosString[i]);
		}
		return numeros;
	}

	private static int calcularPrimeiroDigitoVerificador(final int[] numeros) {
        int[] multiplicadoresCPFSemDigitoVerificador = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] resultadoMultiplicacaoCPFSemDigitoVerificador = new int[9];

        for (int i = 0; i < 9; i++) {
            resultadoMultiplicacaoCPFSemDigitoVerificador[i] = numeros[i] * multiplicadoresCPFSemDigitoVerificador[i];
        }

        int somaMultiplicacao = 0;
        for (int i = 0; i < 9; i++) {
            somaMultiplicacao = somaMultiplicacao + resultadoMultiplicacaoCPFSemDigitoVerificador[i];
        }

        int resto = somaMultiplicacao % 11;
        
		return 11 - resto;
	}

	private static int calcularSegundoDigitoVerificador(final int[] numeros) {
        int[] multiplicadoresCPFComUmDigitoVerificador = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] resultadoMultiplicacaoCPFComUmDigitoVerificador = new int[10];

        for (int i = 0; i < 10; i++) {
            resultadoMultiplicacaoCPFComUmDigitoVerificador[i] = numeros[i] * multiplicadoresCPFComUmDigitoVerificador[i];
        }

        int somaMultiplicacao = 0;
        for (int i = 0; i < 10; i++) {
            somaMultiplicacao = somaMultiplicacao + resultadoMultiplicacaoCPFComUmDigitoVerificador[i];
        }

        int resto = somaMultiplicacao % 11;
        
		return 11 - resto;
	}

	private static boolean digitosVerificadoresIguais(final int[] numeros) {
		return 
			numeros[9] == calcularPrimeiroDigitoVerificador(numeros) && 
			numeros[10] == calcularSegundoDigitoVerificador(numeros);
	}

    static boolean valido(final CPF cpf) {
		if (cpf == null) {
            return false;
        }
        return valido(cpf.numero());
    }

	@Override
	public String numero() {
		return numero;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CPFDidatico other = (CPFDidatico) obj;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        return true;
    }

    @Override
	public String toString() {
		return "CPFDidatico [numero=" + numero + "]";
	}

}
