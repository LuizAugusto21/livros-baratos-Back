package com.example.livros_baratos_back.model;

public enum EnumCondicao {
    NOVO,
    SEMINOVO,
    USADO;


    public static EnumCondicao fromString(String condicao) {
        try {
            return EnumCondicao.valueOf(condicao.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Condição inválida: " + condicao);
        }
    }
}
