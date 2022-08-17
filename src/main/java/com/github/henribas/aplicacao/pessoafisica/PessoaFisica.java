package com.github.henribas.aplicacao.pessoafisica;

import java.time.LocalDate;

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
            for (Sexo sexo : Sexo.values()) {
                if (sexo.codigo().equals(codigo)) {
                    return sexo.descricao();
                }
            }
            return null;
        }
        
        public static Sexo de(String codigo) {
            for (Sexo sexo : Sexo.values()) {
                if (sexo.codigo().equals(codigo)) {
                    return sexo;
                }
            }
            return null;
        }
    }

    public static PessoaFisica de(String nome, Sexo sexo, CPF cpf) {
        return new PessoaFisicaNormal.Builder(nome, sexo, cpf).build();
    }

}
