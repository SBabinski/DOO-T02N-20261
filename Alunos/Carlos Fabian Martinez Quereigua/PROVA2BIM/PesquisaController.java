package controller;

import model.Serie;
import service.SeriesAPIServico;

import java.util.List;

public class PesquisaController {

    public PesquisaController(){}

    public List<Serie> buscaSerie(String nome){
        return SeriesAPIServico.buscaSerie(nome);
    }

}
