package br.edu.ifg.carrocasweb.writter;

import java.io.File;

public interface IFileManager {

    void salvarArquivo();
    File recuperarArquivo();
    void deletarArquivo();

}
