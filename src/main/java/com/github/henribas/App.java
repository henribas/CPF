package com.github.henribas;

import com.github.henribas.cpf.CPF;

public class App 
{
    public static void main( String[] args )
    {
        CPF cpf = CPF.de("725.478.824-26");
        CPF.validar(cpf);
    }
}
