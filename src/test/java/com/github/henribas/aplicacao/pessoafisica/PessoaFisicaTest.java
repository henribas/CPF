package com.github.henribas.aplicacao.pessoafisica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.github.henribas.aplicacao.endereco.Endereco;
import com.github.henribas.aplicacao.endereco.Municipio;
import com.github.henribas.aplicacao.endereco.UF;
import com.github.henribas.aplicacao.pessoafisica.PessoaFisica.Sexo;
import com.github.henribas.cpf.CPF;

import nl.jqno.equalsverifier.EqualsVerifier;

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
    void pessoaMinima() {
        String nome = "Titus Pullo";
        Sexo sexo = Sexo.MASCULINO;
        CPF cpf = CPF.de("18163358866");
    
        PessoaFisica pf = PessoaFisica.de(nome, sexo, cpf);

        assertTrue(nome.equals(pf.nome()) && sexo.equals(pf.sexo()) && cpf.equals(pf.cpf()));
    }

    @Test
    void pessoaCompleta() {
        String nome = "Titus Pullo";
        Sexo sexo = Sexo.MASCULINO;
        CPF cpf = CPF.de("18163358866");
        String email = "email@empresa.com.br";
        String celular = "70 99999 7070";
        LocalDate dataNascimento = LocalDate.of(1980, 7, 15);
        Endereco endereco = Endereco.de("80530900", Municipio.de(1100304, "Vilhena", UF.RO));
    
        PessoaFisica pf = PessoaFisica.de(nome, sexo, cpf, email, celular, dataNascimento, endereco);

        assertTrue(
            nome.equals(pf.nome()) && 
            sexo.equals(pf.sexo()) && 
            cpf.equals(pf.cpf()) &&
            email.equals(pf.email()) &&
            celular.equals(pf.celular()) &&
            dataNascimento.equals(pf.dataNascimento()) &&
            endereco.equals(pf.endereco()));
    }

    @Test
    void deveCalcularCorretamenteIdadeComBaseNaDataNascimento() {
        String nome = "Indiferente";
        Sexo sexo = Sexo.MASCULINO;
        CPF cpf = CPF.de("18163358866");
        String email = "email@empresa.com.br";
        String celular = "77 88888 7070";
        LocalDate dataNascimento = LocalDate.of(1980, 7, 15);
        Endereco endereco = Endereco.de("80530900", Municipio.de(1100304, "Vilhena", UF.RO));
    
        PessoaFisica pf = PessoaFisica.de(nome, sexo, cpf, email, celular, dataNascimento, endereco);

        assertEquals(42, pf.idade());
    }

    @Test
    void deveSerIgual() {
        String nome = "Igual";
        Sexo sexo = Sexo.MASCULINO;
        CPF cpf = CPF.de("18163358866");
        String email = "email@empresa.com.br";
        String celular = "77 88888 7070";
        LocalDate dataNascimento = LocalDate.of(1980, 7, 15);
        Endereco endereco = Endereco.de("80530900", Municipio.de(1100304, "Vilhena", UF.RO));
    
        PessoaFisica pf1 = PessoaFisica.de(nome, sexo, cpf, email, celular, dataNascimento, endereco);
        PessoaFisica pf2 = PessoaFisica.de(nome, sexo, cpf, email, celular, dataNascimento, endereco);

        assertEquals(pf1, pf2);
    }

    @Test
    void toStringDeveSerIgual() {
        String nome = "Igual";
        Sexo sexo = Sexo.MASCULINO;
        CPF cpf = CPF.de("18163358866");
        String email = "email@empresa.com.br";
        String celular = "77 88888 7070";
        LocalDate dataNascimento = LocalDate.of(1980, 7, 15);
        Endereco endereco = Endereco.de("80530900", Municipio.de(1100304, "Vilhena", UF.RO));
    
        PessoaFisica pessoaFisica = PessoaFisica.de(nome, sexo, cpf, email, celular, dataNascimento, endereco);

        String toString = "PessoaFisicaNormal [celular=" + celular + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento
        + ", email=" + email + ", endereco=" + endereco + ", nome=" + nome + ", sexo=" + sexo + "]";
        
        assertEquals(toString, pessoaFisica.toString());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.forClass(PessoaFisicaNormal.class).withOnlyTheseFields("cpf").verify();
    }

}
