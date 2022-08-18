package com.github.henribas.aplicacao.endereco;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EnderecoTest {
    
    @Test
    void deveInformarCep() {
        String cep = null;
        Municipio curitiba = Municipio.de(4106902, "4106902", UF.PR);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Endereco.de(cep, curitiba);
        });
    
        String mensagemEsperada = "Informe o CEP.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void deveInformarMunicipio() {
        String cep = "80530900";
        Municipio curitiba = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Endereco.de(cep, curitiba);
        });
    
        String mensagemEsperada = "Informe o município.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void enderecoCompleto() {
        String cep = "80530900";
        String logradouro = "Rua das Tibiras";
        String numero = "70A";
        String bairro = "Jurerê Internacional";
        String complemento = "Apartamento 950, bloco A";
        Municipio municipio = Municipio.de(4205407, "Florianópolis", UF.SC);
        Endereco endereco = Endereco.de(cep, logradouro, numero, bairro, complemento, municipio);
    
        assertTrue(
            cep.equals(endereco.cep()) &&
            logradouro.equals(endereco.logradouro()) &&
            numero.equals(endereco.numero()) && 
            bairro.equals(endereco.bairro()) &&
            complemento.equals(endereco.complemento()) &&
            municipio.equals(endereco.municipio()));
    }

}
