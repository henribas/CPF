package com.github.henribas.aplicacao.pessoafisica;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;

import com.github.henribas.aplicacao.endereco.Endereco;
import com.github.henribas.cpf.CPF;

public interface PessoaFisica {
    
    public String nome();
    public Sexo sexo();
    public CPF cpf();
    public String email();
    public String celular();
    public LocalDate dataNascimento();
    public int idade();
    public Endereco endereco();

    public enum Sexo {
        
        MASCULINO ("M", "Masculino"),
	    FEMININO  ("F", "Feminino");
        
        private String codigo;
        private String descricao;
        
        public String codigo() {
            return codigo;
        }

        public String descricao() {
            return descricao;
        }

        private Sexo(String codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }
        
        public static String descricao(String codigo) {
            validarCodigo(codigo);
            
            for (Sexo sexo : Sexo.values()) {
                if (sexo.codigo().equals(codigo)) {
                    return sexo.descricao();
                }
            }
            
            throw new IllegalArgumentException("Não foi encontrada descrição com o código informado: " + codigo + ". Informe corretamente o código.");
        }

        private static void validarCodigo(String codigo) {
            if (StringUtils.isBlank(codigo)) {
                throw new IllegalArgumentException("Informe o código.");
            }
        }
        
        public static Sexo de(String codigo) {
            validarCodigo(codigo);

            for (Sexo sexo : Sexo.values()) {
                if (sexo.codigo().equals(codigo)) {
                    return sexo;
                }
            }
            
            throw new IllegalArgumentException("Não foi encontrado sexo com o código informado: " + codigo + ". Informe corretamente o código.");
        }
    }

    public static PessoaFisica de(final String nome, final Sexo sexo, final CPF cpf) {
        return new PessoaFisicaNormal.Builder(nome, sexo, cpf).build();
    }

    public static PessoaFisica de(
        final String nome, 
        final Sexo sexo, 
        final CPF cpf, 
        final String email,
        final String celular,
        final LocalDate dataNascimento,
        final Endereco endereco) {

        return new PessoaFisicaNormal.Builder(nome, sexo, cpf)
            .email(email)
            .celular(celular)
            .dataNascimento(dataNascimento)
            .endereco(endereco)
            .build();
    }

}
