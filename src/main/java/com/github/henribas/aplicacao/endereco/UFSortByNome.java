package com.github.henribas.aplicacao.endereco;

import java.util.Comparator;

final class UFSortByNome implements Comparator<UF> {
    
        @Override
        public int compare(UF uf1, UF uf2) {
            return uf1.nome().compareTo(uf2.nome());
        }

}
