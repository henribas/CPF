package com.github.henribas.aplicacao.endereco;

import org.apache.commons.lang3.StringUtils;

final class EnderecoCompleto implements Endereco {

    private final String cep;
    private final String logradouro;
    private final String numero;
    private final String bairro;
    private final String complemento;
    private final Municipio municipio;

    public static class Builder {
        private final String cep;
        private final Municipio municipio;
        private String logradouro;
        private String numero;
        private String bairro;
        private String complemento;

        public Builder(final String cep, final Municipio municipio) {
            this.cep = cep;
            this.municipio = municipio;
        }

        public Builder logradouro(final String logradouro) {
            this.logradouro = logradouro;
            return this;
        }
        
        public Builder numero(final String numero) {
            this.numero = numero;
            return this;
        }

        public Builder bairro(final String bairro) {
            this.bairro = bairro;
            return this;
        }

        public Builder complemento(final String complemento) {
            this.complemento = complemento;
            return this;
        }

        public EnderecoCompleto build() {
            return new EnderecoCompleto(this);
        }

    }

    private EnderecoCompleto(Builder builder) {
        if (StringUtils.isBlank(builder.cep)) {
            throw new IllegalArgumentException("Informe o CEP.");
        }

        if (builder.municipio == null) {
            throw new IllegalArgumentException("Informe o município.");
        }

        this.cep         = builder.cep;
        this.municipio   = builder.municipio;
        this.logradouro  = builder.logradouro;
        this.numero      = builder.numero;
        this.bairro      = builder.bairro;
        this.complemento = builder.complemento;
    }

    @Override
    public String cep() {
        return cep;
    }

    @Override
    public String logradouro() {
        return logradouro;
    }

    @Override
    public String numero() {
        return numero;
    }

    @Override
    public String bairro() {
        return bairro;
    }

    @Override
    public String complemento() {
        return complemento;
    }

    @Override
    public Municipio municipio() {
        return municipio;
    }

    @Override
    public String toString() {
        return "EnderecoPadrao [bairro=" + bairro + ", cep=" + cep + ", complemento=" + complemento + ", logradouro="
                + logradouro + ", municipio=" + municipio + ", numero=" + numero + "]";
    }
    
}
