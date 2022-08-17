package com.github.henribas.aplicacao.endereco;

public interface Endereco {
    
    public String cep();
    public String logradouro();
    public String numero();
    public String bairro();
    public String complemento();
    public Municipio municipio();

    public static Endereco de(final String cep, final Municipio municipio) {
        return new EnderecoCompleto.Builder(cep, municipio).build();
    }

    public static Endereco de(
        final String cep, 
        final String logradouro,
        final Municipio municipio) {

        return new EnderecoCompleto.Builder(cep, municipio)
            .logradouro(logradouro)
            .build();
    }

    public static Endereco de(
        final String cep, 
        final String logradouro,
        final String numero,
        final String bairro,
        final String complemento,
        final Municipio municipio) {

        return new EnderecoCompleto.Builder(cep, municipio)
            .logradouro(logradouro)
            .numero(numero)
            .bairro(bairro)
            .complemento(complemento)
            .build();
    }

}
