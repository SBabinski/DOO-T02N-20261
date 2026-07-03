package controller;

import model.User;

public class UsuarioController {

    public void criarUsuario(String nome){
        User usuario = new User(nome);
    }



}
