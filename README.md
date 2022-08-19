# CPF
Validação e geração de CPF com modelos sugeridos de classes e relacionamentos.

O Cadastro de Pessoa Física ou CPF é composto por 11 dígitos numéricos geralmente apresentados com uma máscara no formato: NNN.NNN.NNN-NN onde N representa os números do CPF. O foco aqui está na validação do CPF. Para mais informações sobre o CPF o endereço da Wikipedia pode ser consultado: https://pt.wikipedia.org/wiki/Cadastro_de_Pessoas_F%C3%ADsicas
Os dois últimos números do CPF são dígitos verificadores e no artigo da Wikipedia temos: "Os dígitos verificadores são calculados através de um algoritmo que soma o produto de cada dígito que compõe o CPF por um peso e calcula o resto da divisão dessa soma por 11. Pela característica, esse algoritmo é conhecido como "módulo 11"."
Um exemplo prático pode facilitar o entendimento. Partindo de um CPF fictício válido e de número 725.478.824-26 pode ser aplicada a regra acima para melhor entendimento.
Cada dígito do CPF é multiplicado por um fator. De 10 até 2, começando pela esquerda, como mostra a tabela abaixo.

| CPF sem dígito verificador | 7 |  2 |  5 |  4 |  7 |  8 |  8 | 2 | 4 |
| -------------------------- | - |  - |  - |  - |  - |  - |  - | - | - |
| Multiplicador              |10 |  9 |  8 |  7 |  6 |  5 |  4 | 3 | 2 |
| Resultado da multiplicação |70 | 18 | 40 | 28 | 42 | 40 | 32 | 6 | 8 |

O resultado da multiplicação é somado: 70 + 18 + 40 + 28 + 42 + 40 + 32 + 6 + 8 = 284.

A soma é dividida por 11 (mencionado no artigo, lembra?): 284 / 11 = 25 com o resto 9 (prova: 25 x 11 = 275 e 275 + 9 = 284).

Subtraindo o resto de 11 temos: 11 - 9 = 2. Este é o primeiro dígito verificador.

É necessário repetir o processo acima, mas desta vez incluindo o dígito verificador encontrado. Portanto, cada dígito do CPF é multiplicado por um fator. De 11 até 2, começando pela esquerda, como mostra a tabela abaixo.

| CPF com 1 dígito verificador | 7 |  2 |  5 |  4 |  7 |  8 |  8 | 2 |  4 | 2 |
| ---------------------------- | - |  - |  - |  - |  - |  - |  - | - |  - | - |
| Multiplicador                |11 | 10 |  9 |  8 |  7 |  6 |  5 | 4 |  3 | 2 |
| Resultado da multiplicação   |77 | 20 | 45 | 32 | 49 | 48 | 40 | 8 | 12 | 4 |

O resultado da multiplicação é somado: 77 + 20 + 45 + 32 + 49 + 48 + 40 + 8 + 12 + 4 = 335.

A soma é dividida por 11: 335 / 11 = 30 com o resto 5 (prova: 30 x 11 = 330 e 330 + 5 = 335).

Subtraindo o resto de 11 temos: 11 - 5 = 6. Este é o segundo dígito verificador.


## Algoritmo

De posse da teoria, pode ser iniciada a codificação de um algoritmo que represente um CPF.

Algumas escolhas importantes devem ser mencionadas, pois impactam no design e no uso da API de CPF. São elas:

1. O CPF fará parte de uma API interna para uso nas aplicações.
2. Terá uma interface pública com métodos estáticos e uma implementação privada (ou mais, se necessário).

O que compõe um CPF?

Um CPF tem um número, formatação e validação. O código abaixo representa um CPF e será argumentado na sequência.

```
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
```

A interface tem um atributo: o número do CPF. Tem os métodos, todos estáticos, para formatar, para remover formatação, para validar e mais três métodos para criação de um objeto CPF.

Outra decisão de design importante: é melhor armazenar o CPF (na base de dados) como uma sequência de onze caracteres numéricos sem formatação (geralmente char(11) ou character(11), o nome pode variar com o banco de dados utilizado). Por exemplo, o CPF 134.056.334-78 é armazenado como 13405633478. O método para remover a formatação serve para isso. Um CPF pode ser criado com ou sem formatação, mas durante a sua criação, é sempre removida a formatação.

Como nesta API somente a interface do CPF é pública, o implementador tem toda liberdade na codificação, pois é transparente aos usuários da API.

O código a seguir é uma implementação do CPF.

```
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
            numeroSemFormatacao.substring(0, 3) + "." +
            numeroSemFormatacao.substring(3, 6) + "." +
            numeroSemFormatacao.substring(6, 9) + "-" +
            numeroSemFormatacao.substring(9, 11);
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

        return osDigitosVerificadoresSaoIguais(numeros(numero));
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

            return (true);
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
        int[] multiplicadoresCPFSemDigitoVerificador = { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
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
        int[] multiplicadoresCPFComUmDigitoVerificador = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
        int[] resultadoMultiplicacaoCPFComUmDigitoVerificador = new int[10];

        for (int i = 0; i < 10; i++) {
            resultadoMultiplicacaoCPFComUmDigitoVerificador[i] = numeros[i]
                    * multiplicadoresCPFComUmDigitoVerificador[i];
        }

        int somaMultiplicacao = 0;
        for (int i = 0; i < 10; i++) {
            somaMultiplicacao = somaMultiplicacao + resultadoMultiplicacaoCPFComUmDigitoVerificador[i];
        }

        int resto = somaMultiplicacao % 11;

        return 11 - resto;
    }

    private static boolean osDigitosVerificadoresSaoIguais(final int[] numeros) {
        int primeiroDigitoVerificador = numeros[9];
        int segundoDigitoVerificador = numeros[10];

        return (primeiroDigitoVerificador == calcularPrimeiroDigitoVerificador(numeros)) &&
               (segundoDigitoVerificador == calcularSegundoDigitoVerificador(numeros));
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
```
