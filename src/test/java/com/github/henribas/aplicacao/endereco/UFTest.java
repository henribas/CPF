package com.github.henribas.aplicacao.endereco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UFTest {
    
    @Test
    void aSiglaNaoPodeSerNula() {
        String sigla = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UF.de(sigla);
        });
    
        String mensagemEsperada = "Informe a sigla.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "      "})
    void aSiglaNaoPodeSerVazia(String sigla) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UF.de(sigla);
        });
    
        String mensagemEsperada = "Informe a sigla.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void naoPodeSerUmaSiglaInexistente() {
        String sigla = "RX";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UF.de(sigla);
        });
    
        String mensagemEsperada = "Não foi encontrada UF com a sigla informada: " + sigla + ". Informe corretamente a sigla.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void deveSerUmaSiglaExistente() {
        String sigla = "PR";
        UF uf = UF.de(sigla);
    
        assertEquals(sigla, uf.sigla());
    }
    
    @Test
    void oCodigoIbgeNaoPodeSerNulo() {
        Integer codigoIbge = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UF.de(codigoIbge);
        });
    
        String mensagemEsperada = "Informe o código IBGE.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1257, 0})
    void oCodigoIbgeNaoPodeSerMenorNemIgualZero(Integer codigoIbge) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UF.de(codigoIbge);
        });
    
        String mensagemEsperada = "Informe o código IBGE.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void naoPodeSerUmCodigoIbgeInexistente() {
        Integer codigoIbge = 999;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UF.de(codigoIbge);
        });
    
        String mensagemEsperada = "Não foi encontrada UF com o código IBGE informado: " + codigoIbge + ". Informe corretamente o código do IBGE.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void deveRetornarNomeEquivalenteAoCodigoIbge() {
        assertEquals("Rondônia", UF.nome(11));
    }

    @Test
    void deveRetornarNomeEquivalenteASigla() {
        assertEquals("Santa Catarina", UF.nome("SC"));
    }

}
