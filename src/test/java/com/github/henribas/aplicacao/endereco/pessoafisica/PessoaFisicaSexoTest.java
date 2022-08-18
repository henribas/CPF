package com.github.henribas.aplicacao.endereco.pessoafisica;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.henribas.aplicacao.pessoafisica.PessoaFisica.Sexo;

class PessoaFisicaSexoTest {
    
    @Test
    void oCodigoDaDescricaoNaoPodeSerNulo() {
        String codigo = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sexo.descricao(codigo);
        });
    
        String mensagemEsperada = "Informe o código.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "      "})
    void oCodigoDaDescricaoNaoPodeSerVazio(String codigo) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sexo.descricao(codigo);
        });
    
        String mensagemEsperada = "Informe o código.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void deveExistirDescricaoCorrespondenteAoCodigo() {
        String codigo = "I";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sexo.descricao(codigo);
        });
    
        String mensagemEsperada = "Não foi encontrada descrição com o código informado: " + codigo + ". Informe corretamente o código.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void oCodigoNaoPodeSerNulo() {
        String codigo = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sexo.de(codigo);
        });
    
        String mensagemEsperada = "Informe o código.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "      "})
    void oCodigoNaoPodeSerVazio(String codigo) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sexo.de(codigo);
        });
    
        String mensagemEsperada = "Informe o código.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void deveExistirSexoCorrespondenteAoCodigo() {
        String codigo = "I";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sexo.de(codigo);
        });
    
        String mensagemEsperada = "Não foi encontrado sexo com o código informado: " + codigo + ". Informe corretamente o código.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    /*@Test
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
    }*/
    
}
