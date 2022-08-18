package com.github.henribas.aplicacao.pessoafisica;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void deveRetornarDescricaoCorretamente() {
        assertEquals("Masculino", Sexo.MASCULINO.descricao());
    }

    @Test
    void deveRetornarSexoEquivalenteAoCodigo() {
        String codigo = "M";
        assertEquals(Sexo.MASCULINO, Sexo.de(codigo));
    }

    @Test
    void deveRetornarDescricaoEquivalenteAoCodigo() {
        String codigo = "M";
        assertEquals("Masculino", Sexo.descricao(codigo));
    }
    
}
