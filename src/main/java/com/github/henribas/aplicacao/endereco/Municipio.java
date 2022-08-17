package com.github.henribas.aplicacao.endereco;

public interface Municipio {
    
    public Integer codigoIbge();
    public String nome();
    public UF uf();

    public static Municipio de(final Integer codigoIbge, final String nome, final UF uf) {
        return MunicipioBasico.de(codigoIbge, nome, uf);
    }

}
