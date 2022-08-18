package com.github.henribas.aplicacao.endereco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public enum UF {
    
    RO(11, "RO", "Rondônia"),
	AC(12, "AC", "Acre"),
	AM(13, "AM", "Amazonas"),
	RR(14, "RR", "Roraima"),
	PA(15, "PA", "Pará"),
	AP(16, "AP", "Amapá"),
	TO(17, "TO", "Tocantins"),
	MA(21, "MA", "Maranhão"),
	PI(22, "PI", "Piauí"),
	CE(23, "CE", "Ceará"),
	RN(24, "RN", "Rio Grande do Norte"),
	PB(25, "PB", "Paraíba"),
	PE(26, "PE", "Pernambuco"),
	AL(27, "AL", "Alagoas"),
	SE(28, "SE", "Sergipe"),
	BA(29, "BA", "Bahia"),
	MG(31, "MG", "Minas Gerais"),
	ES(32, "ES", "Espírito Santo"),
	RJ(33, "RJ", "Rio de Janeiro"),
	SP(35, "SP", "São Paulo"),
	PR(41, "PR", "Paraná"),
	SC(42, "SC", "Santa Catarina"),
	RS(43, "RS", "Rio Grande do Sul"),
	MS(50, "MS", "Mato Grosso do Sul"),
	MT(51, "MT", "Mato Grosso"),
	GO(52, "GO", "Goiás"),
	DF(53, "DF", "Distrito Federal");
	
	private Integer codigoIbge;
	private String sigla;
	private String nome;
	
	public Integer codigoIbge() {
		return codigoIbge;
	}

	public String sigla() {
		return sigla;
	}

	public String nome() {
		return nome;
	}

	private UF(Integer codigoIbge, String sigla, String nome) {
		this.codigoIbge = codigoIbge;
		this.sigla = sigla;
		this.nome = nome;
	}
	
	public static String nome(String sigla) {
        validarSigla(sigla);

       	for (UF uf : UF.values()) {
       		if (uf.sigla().equals(sigla)) {
       			return uf.nome();
       		}
    	}

        throw new IllegalArgumentException("Não foi encontrado nome da UF com a sigla informada: " + sigla + ". Informe corretamente a sigla.");
    }
	
	private static void validarSigla(String sigla) {
        if (StringUtils.isBlank(sigla)) {
            throw new IllegalArgumentException("Informe a sigla.");
        }
    }

    public static String nome(Integer codigoIbge) {
        validarCodigoIbge(codigoIbge);

       	for (UF uf : UF.values()) {
       		if (uf.codigoIbge().equals(codigoIbge)) {
       			return uf.nome();
       		}
    	}

       	throw new IllegalArgumentException("Não foi encontrado nome da UF com o código IBGE informado: " + codigoIbge + ". Informe corretamente o código do IBGE.");
    }
	
	private static void validarCodigoIbge(Integer codigoIbge) {
        if (codigoIbge == null || codigoIbge <= 0) {
            throw new IllegalArgumentException("Informe o código IBGE.");
        }
    }

    public static UF de(String sigla) {
        validarSigla(sigla);

        for (UF uf : UF.values()) {
            if(uf.sigla().equals(sigla)) {
                return uf;
            }
        }

        throw new IllegalArgumentException("Não foi encontrada UF com a sigla informada: " + sigla + ". Informe corretamente a sigla.");
    }
	
	public static UF de(Integer codigoIbge) {
        validarCodigoIbge(codigoIbge);

        for (UF uf : UF.values()) {
            if (uf.codigoIbge().equals(codigoIbge)) {
                return uf;
            }
        }
        
        throw new IllegalArgumentException("Não foi encontrada UF com o código IBGE informado: " + codigoIbge + ". Informe corretamente o código do IBGE.");
    }
	
	public static List<UF> ufs() {
	    List<UF> ufs = new ArrayList<>(Arrays.asList(UF.values()));
        Collections.sort(ufs, new UFSortByNome());
	    
        return ufs;
    }

    public static Map<String, String> map(List<UF> ufs) {
        Collections.sort(ufs, new UFSortByNome());
        Map<String, String> map = new LinkedHashMap<>();
        for (UF uf : ufs) {
            map .put(uf.sigla(), uf.nome());
        }
        
        return map;
    }

	public static boolean validar(String sigla) {
		if (StringUtils.isBlank(sigla)) {
			return false;
		}

		try {
			UF.de(sigla);
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}

	public static boolean validar(UF uf) {
		if (uf == null) {
			return false;
		}

		return validar(uf.sigla);
	}

}
