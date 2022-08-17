package com.github.henribas.cpf;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CPFTest {

    @Test
    void naoPodeSerNumeroNulo() {
        String cpfNulo = null;
        assertFalse(CPF.validar(cpfNulo));
    }

    @Test
    void naoPodeSerCPFNulo() {
        CPF cpf = null;
        assertFalse(CPF.validar(cpf));
    }

    @Test
    void naoPodeSerNumeroVazio() {
        assertFalse(CPF.validar(""));
    }

    @Test
    void naoPodeSerEspacosEmBranco() {
        assertFalse(CPF.validar("      "));
    }

    @Test
    void naoPodeSerTudoZeroSemFormatacao() {
        assertFalse(CPF.validar("00000000000"));
    }

    @Test
    void naoPodeSerTudoZeroComFormatacao() {
        assertFalse(CPF.validar("000.000.000-00"));
    }

    @Test
    void naoPodeSerTudoUmSemFormatacao() {
        assertFalse(CPF.validar("11111111111"));
    }

    @Test
    void naoPodeSerTudoUmComFormatacao() {
        assertFalse(CPF.validar("111.111.111-11"));
    }

    @Test
    void naoPodeSerTudoDoisSemFormatacao() {
        assertFalse(CPF.validar("22222222222"));
    }

    @Test
    void naoPodeSerTudoDoisComFormatacao() {
        assertFalse(CPF.validar("222.222.222-22"));
    }

    @Test
    void naoPodeSerTudoTresSemFormatacao() {
        assertFalse(CPF.validar("33333333333"));
    }

    @Test
    void naoPodeSerTudoTresComFormatacao() {
        assertFalse(CPF.validar("333.333.333-33"));
    }

    @Test
    void naoPodeSerTudoQuatroSemFormatacao() {
        assertFalse(CPF.validar("44444444444"));
    }

    @Test
    void naoPodeSerTudoQuatroComFormatacao() {
        assertFalse(CPF.validar("444.444.444-44"));
    }

    @Test
    void naoPodeSerTudoCincoSemFormatacao() {
        assertFalse(CPF.validar("55555555555"));
    }

    @Test
    void naoPodeSerTudoCincoComFormatacao() {
        assertFalse(CPF.validar("555.555.555-55"));
    }

    @Test
    void naoPodeSerTudoSeisSemFormatacao() {
        assertFalse(CPF.validar("66666666666"));
    }

    @Test
    void naoPodeSerTudoSeisComFormatacao() {
        assertFalse(CPF.validar("666.666.666-66"));
    }

    @Test
    void naoPodeSerTudoSeteSemFormatacao() {
        assertFalse(CPF.validar("77777777777"));
    }

    @Test
    void naoPodeSerTudoSeteComFormatacao() {
        assertFalse(CPF.validar("777.777.777-77"));
    }

    @Test
    void naoPodeSerTudoOitoSemFormatacao() {
        assertFalse(CPF.validar("88888888888"));
    }

    @Test
    void naoPodeSerTudoOitoComFormatacao() {
        assertFalse(CPF.validar("888.888.888-88"));
    }

    @Test
    void naoPodeSerTudoNoveSemFormatacao() {
        assertFalse(CPF.validar("99999999999"));
    }

    @Test
    void naoPodeSerTudoNoveComFormatacao() {
        assertFalse(CPF.validar("999.999.999-99"));
    }

    @Test
    void temQueTerOnzeCaracteresSemFormatacao() {
        assertFalse(CPF.validar("123456789.-"));
    }

    @Test
    void temQueTerCatorzeCaracteresComFormatacao() {
        assertFalse(CPF.validar("12345678901234"));
    }

    @Test
    void temQueTerDigitoVerificadorValido() {
        assertFalse(CPF.validar("725.478.824-12"));
    }

    @Test
    void temQueTerFormatarCorretamente() {
        assertFalse(CPF.validar("7.2547-8824.12"));
    }

    @Test
    void cpfSemFormatacaoValido() {
        assertTrue(CPF.validar("72547882426"));
    }

    @Test
    void cpfComFormatacaoValido() {
        assertTrue(CPF.validar("725.478.824-26"));
    }

}
