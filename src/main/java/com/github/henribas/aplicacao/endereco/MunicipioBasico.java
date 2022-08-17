package com.github.henribas.aplicacao.endereco;

import org.apache.commons.lang3.StringUtils;

final class MunicipioBasico implements Municipio {

    private static final String CODIGO_IBGE = "^\\d{7}$";

    private final Integer codigoIbge;
    private final String nome;
    private final UF uf;

    private MunicipioBasico(final Integer codigoIbge, final String nome, final UF uf) {
        validarNome(nome);
        validarUf(uf);
        validarCodigoIbge(codigoIbge, uf);

        this.codigoIbge = codigoIbge;
        this.nome = nome;
        this.uf = uf;
    }

    static Municipio de(final Integer codigoIbge, final String nome, final UF uf) {
        return new MunicipioBasico(codigoIbge, nome, uf);
    }

    private void validarCodigoIbge(Integer codigoIbge, UF uf) {
        if (!informouCodigoIbge(codigoIbge)) {
            throw new IllegalArgumentException("Informe o código IBGE.");
        }

        if (!codigoIbgeEstaNoPadrao(codigoIbge)) {
            throw new IllegalArgumentException("Informe corretamente o código IBGE.");
        }

        if (!codigoIbgeEstaNoMunicipioCorreto(codigoIbge, uf)) {
            throw new IllegalArgumentException("Informe corretamente o código IBGE do município desta UF.");
        }
    }

    private boolean informouCodigoIbge(Integer codigoIbge) {
        return codigoIbge != null && codigoIbge > 0; 
    }

    private boolean codigoIbgeEstaNoPadrao(Integer codigoIbge) {
        return String.valueOf(codigoIbge).matches(CODIGO_IBGE);
    }

    private boolean codigoIbgeEstaNoMunicipioCorreto(Integer codigoIbge, UF uf) {
        String codigoUf = String.valueOf(codigoIbge).substring(0, 2);
        return Integer.valueOf(codigoUf).equals(uf.codigoIbge());
    }

    private void validarNome(String nome) {
        if (StringUtils.isBlank(nome)) {
            throw new IllegalArgumentException("Informe o nome.");
        }
    }

    private void validarUf(UF uf) {
        if (!UF.validar(uf)) {
            throw new IllegalArgumentException("Informe corretamente a UF.");
        }
    }

    @Override
    public Integer codigoIbge() {
        return codigoIbge;
    }

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public UF uf() {
        return uf;
    }

    @Override
    public String toString() {
        return "MunicipioBasico [codigoIbge=" + codigoIbge + ", nome=" + nome + ", uf=" + uf + "]";
    }

}
