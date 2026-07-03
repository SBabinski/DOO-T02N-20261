package fag;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class Persistencia {

    private static final String ARQUIVO = "dados.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void salvar(Usuario usuario) {
        try {
            FileWriter writer = new FileWriter(ARQUIVO);
            gson.toJson(usuario, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Usuario carregar() {
        try {
            FileReader reader = new FileReader(ARQUIVO);
            Usuario usuario = gson.fromJson(reader, Usuario.class);
            reader.close();
            return usuario;
        } catch (Exception e) {
            return null;
        }
    }
}