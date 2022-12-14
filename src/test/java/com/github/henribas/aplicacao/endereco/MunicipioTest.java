package com.github.henribas.aplicacao.endereco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import nl.jqno.equalsverifier.EqualsVerifier;

class MunicipioTest {
    
    @Test
    void oCodigoIbgeDeveSerInformado() {
        Integer codigoIbge = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Municipio.de(codigoIbge, "Nulo", UF.AC);
        });
    
        String mensagemEsperada = "Informe o código IBGE.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -4112345})
    void oCodigoIbgeNaoPodeSerMenorNemIgualZero(Integer codigoIbge) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Municipio.de(codigoIbge, "Zero e Negativo", UF.AC);
        });
    
        String mensagemEsperada = "Informe o código IBGE.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void oCodigoIbgeDeveSerInformadoNoPadraoCorreto() {
        Integer codigoIbge = 123;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Municipio.de(codigoIbge, "Vilhena", UF.RO);
        });
    
        String mensagemEsperada = "Informe corretamente o código IBGE.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void oCodigoIbgeNaoPodeSerDiferenteDaUf() {
        Integer codigoIbge = 4112345;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Municipio.de(codigoIbge, "Teste", UF.RO);
        });
    
        String mensagemEsperada = "Informe corretamente o código IBGE do município desta UF.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void oNomeNaoPodeSerNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Municipio.de(4101408, null, UF.PR);
        });
    
        String mensagemEsperada = "Informe o nome.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "      "})
    void oNomeNaoPodeSerVazio(String nome) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Municipio.de(4101408, nome, UF.PR);
        });
    
        String mensagemEsperada = "Informe o nome.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void aUfDeveSerInformada() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Municipio.de(4106902, "Curitiba", null);
        });
    
        String mensagemEsperada = "Informe corretamente a UF.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void codigoIbgeDeveSerEquivalenteAUf() {
        Municipio municipio = Municipio.de(1100205, "Porto Velho", UF.RO);
        
        assertEquals(11, municipio.uf().codigoIbge());
    }

    @Test
    void codigoIbgeDeveSerIgualCodigoIbge() {
        Integer codigoIbge = 1100205;
        Municipio municipio = Municipio.de(codigoIbge, "Porto Velho", UF.RO);
        
        assertEquals(1100205, municipio.codigoIbge());
    }

    @Test
    void nomeDeveSerIgualNome() {
        String nome = "Florianópolis";
        Municipio municipio = Municipio.de(4205407, nome, UF.SC);
        
        assertEquals(nome, municipio.nome());
    }

    @Test
    void deveSerIgual() {
        Municipio municipio1 = Municipio.de(1100205, "Porto Velho", UF.RO);
        Municipio municipio2 = Municipio.de(1100205, "Porto Velho", UF.RO);
        
        assertEquals(municipio1, municipio2);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(MunicipioBasico.class).withOnlyTheseFields("codigoIbge").verify();
    }

}
