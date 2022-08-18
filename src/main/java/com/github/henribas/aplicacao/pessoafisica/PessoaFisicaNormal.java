package com.github.henribas.aplicacao.pessoafisica;

import java.time.LocalDate;
import java.time.Period;

import org.apache.commons.lang3.StringUtils;

import com.github.henribas.aplicacao.endereco.Endereco;
import com.github.henribas.cpf.CPF;

final class PessoaFisicaNormal implements PessoaFisica {

    private final String nome;
    private final Sexo sexo;
    private final CPF cpf;
    private final String email;
    private final String celular;
    private final LocalDate dataNascimento;
    private final Endereco endereco;

    public static class Builder {
        private final String nome;
        private final Sexo sexo;
        private final CPF cpf;
        private String email;
        private String celular;
        private LocalDate dataNascimento;
        private Endereco endereco;

        public Builder(final String nome, final Sexo sexo, final CPF cpf ) {
            this.nome = nome;
            this.sexo = sexo;
            this.cpf = cpf;
        }

        public Builder email(final String email) {
            this.email = email;
            return this;
        }

        public Builder celular(final String celular) {
            this.celular = celular;
            return this;
        }

        public Builder dataNascimento(final LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public Builder endereco(final Endereco endereco) {
            this.endereco = endereco;
            return this;
        }

        public PessoaFisicaNormal build() {
            return new PessoaFisicaNormal(this);
        }

    }

    private PessoaFisicaNormal(Builder builder) {
        if (StringUtils.isBlank(builder.nome)) {
            throw new IllegalArgumentException("Informe o nome.");
        }

        if (builder.sexo == null) {
            throw new IllegalArgumentException("Informe o sexo.");
        }

        if (builder.cpf == null) {
            throw new IllegalArgumentException("Informe o CPF.");
        }

        nome            = builder.nome;
        sexo            = builder.sexo;
        cpf             = builder.cpf;
        email           = builder.email;
        celular         = builder.celular;
        dataNascimento  = builder.dataNascimento;
        endereco        = builder.endereco;
    }

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public CPF cpf() {
        return cpf;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String celular() {
        return celular;
    }

    @Override
    public Sexo sexo() {
        return sexo;
    }

    @Override
    public LocalDate dataNascimento() {
        return dataNascimento;
    }

    @Override
    public int idade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    @Override
    public Endereco endereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return "PessoaFisicaNormal [celular=" + celular + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento
                + ", email=" + email + ", endereco=" + endereco + ", nome=" + nome + ", sexo=" + sexo + "]";
    }
    
}
