package com.github.henribas.aplicacao.endereco;

public interface Endereco {
    
    public String cep();
    public String logradouro();
    public String numero();
    public String bairro();
    public String complemento();
    public Municipio municipio();

}
