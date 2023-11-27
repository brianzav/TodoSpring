package br.senac.sp.epictask.services;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
