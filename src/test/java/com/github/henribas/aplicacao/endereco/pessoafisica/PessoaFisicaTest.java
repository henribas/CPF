package com.github.henribas.aplicacao.endereco.pessoafisica;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.github.henribas.aplicacao.pessoafisica.PessoaFisica;
import com.github.henribas.aplicacao.pessoafisica.PessoaFisica.Sexo;
import com.github.henribas.cpf.CPF;

class PessoaFisicaTest {
    
    @Test
    void deveInformarNome() {
        String nome = null;
        Sexo sexo = Sexo.MASCULINO;
        CPF cpf = CPF.de("18163358866");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PessoaFisica.de(nome, sexo, cpf);
        });
    
        String mensagemEsperada = "Informe o nome.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void deveInformarSexo() {
        String nome = "Titus Pullo";
        Sexo sexo = null;
        CPF cpf = CPF.de("18163358866");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PessoaFisica.de(nome, sexo, cpf);
        });
    
        String mensagemEsperada = "Informe o sexo.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void deveInformarCpf() {
        String nome = "Titus Pullo";
        Sexo sexo = Sexo.MASCULINO;
        CPF cpf = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PessoaFisica.de(nome, sexo, cpf);
        });
    
        String mensagemEsperada = "Informe o CPF.";
        String mensagemAtual = exception.getMessage();
    
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void pessoaNormal() {
        String nome = "Titus Pullo";
        Sexo sexo = Sexo.MASCULINO;
        CPF cpf = CPF.de("18163358866");
    
        PessoaFisica pf = PessoaFisica.de(nome, sexo, cpf);

        assertTrue(nome.equals(pf.nome()) && sexo.equals(pf.sexo()) && cpf.equals(pf.cpf()));
    }

}
