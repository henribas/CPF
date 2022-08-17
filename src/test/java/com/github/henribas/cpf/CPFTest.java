package com.github.henribas.cpf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CPFTest {

    @Test
    void naoPodeSerNumeroNulo() {
        String cpfNulo = null;
        assertFalse(CPF.valido(cpfNulo));
    }

    @Test
    void naoPodeSerCPFNulo() {
        CPF cpf = null;
        assertFalse(CPF.valido(cpf));
    }

    @Test
    void naoPodeSerNumeroVazio() {
        assertFalse(CPF.valido(""));
    }

    @Test
    void naoPodeSerEspacosEmBranco() {
        assertFalse(CPF.valido("      "));
    }

    @Test
    void naoPodeSerTudoZeroSemFormatacao() {
        assertFalse(CPF.valido("00000000000"));
    }

    @Test
    void naoPodeSerTudoZeroComFormatacao() {
        assertFalse(CPF.valido("000.000.000-00"));
    }

    @Test
    void naoPodeSerTudoUmSemFormatacao() {
        assertFalse(CPF.valido("11111111111"));
    }

    @Test
    void naoPodeSerTudoUmComFormatacao() {
        assertFalse(CPF.valido("111.111.111-11"));
    }

    @Test
    void naoPodeSerTudoDoisSemFormatacao() {
        assertFalse(CPF.valido("22222222222"));
    }

    @Test
    void naoPodeSerTudoDoisComFormatacao() {
        assertFalse(CPF.valido("222.222.222-22"));
    }

    @Test
    void naoPodeSerTudoTresSemFormatacao() {
        assertFalse(CPF.valido("33333333333"));
    }

    @Test
    void naoPodeSerTudoTresComFormatacao() {
        assertFalse(CPF.valido("333.333.333-33"));
    }

    @Test
    void naoPodeSerTudoQuatroSemFormatacao() {
        assertFalse(CPF.valido("44444444444"));
    }

    @Test
    void naoPodeSerTudoQuatroComFormatacao() {
        assertFalse(CPF.valido("444.444.444-44"));
    }

    @Test
    void naoPodeSerTudoCincoSemFormatacao() {
        assertFalse(CPF.valido("55555555555"));
    }

    @Test
    void naoPodeSerTudoCincoComFormatacao() {
        assertFalse(CPF.valido("555.555.555-55"));
    }

    @Test
    void naoPodeSerTudoSeisSemFormatacao() {
        assertFalse(CPF.valido("66666666666"));
    }

    @Test
    void naoPodeSerTudoSeisComFormatacao() {
        assertFalse(CPF.valido("666.666.666-66"));
    }

    @Test
    void naoPodeSerTudoSeteSemFormatacao() {
        assertFalse(CPF.valido("77777777777"));
    }

    @Test
    void naoPodeSerTudoSeteComFormatacao() {
        assertFalse(CPF.valido("777.777.777-77"));
    }

    @Test
    void naoPodeSerTudoOitoSemFormatacao() {
        assertFalse(CPF.valido("88888888888"));
    }

    @Test
    void naoPodeSerTudoOitoComFormatacao() {
        assertFalse(CPF.valido("888.888.888-88"));
    }

    @Test
    void naoPodeSerTudoNoveSemFormatacao() {
        assertFalse(CPF.valido("99999999999"));
    }

    @Test
    void naoPodeSerTudoNoveComFormatacao() {
        assertFalse(CPF.valido("999.999.999-99"));
    }

    @Test
    void temQueTerOnzeCaracteresSemFormatacao() {
        assertFalse(CPF.valido("123456789.-"));
    }

    @Test
    void temQueTerCatorzeCaracteresComFormatacao() {
        assertFalse(CPF.valido("12345678901234"));
    }

    @Test
    void temQueTerDigitoVerificadorValido() {
        assertFalse(CPF.valido("725.478.824-12"));
    }

    @Test
    void temQueTerFormatarCorretamente() {
        assertFalse(CPF.valido("7.2547-8824.12"));
    }

    @Test
    void cpfSemFormatacaoValido() {
        assertTrue(CPF.valido("72547882426"));
    }

    @Test
    void cpfComFormatacaoValido() {
        assertTrue(CPF.valido("725.478.824-26"));
    }

    @Test
    void deveValidarPorPadrao() {
        String numeroInvalido = "930.537.259-91";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CPF.de(numeroInvalido);
        });
    
        String mensagemEsperada = "Informe um número de CPF válido.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void deveIgnorarValidacaoQuandoInformado() {
        String numeroInvalido = "93053725991";
        CPF invalido = CPF.de(numeroInvalido, false);
    
        assertEquals(numeroInvalido, invalido.numero());
    }

}
