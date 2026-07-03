package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Usuario;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class PersistenciaService {

	private static final String PASTA =
	        "usuarios/";

    private static final Gson gson =
            new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

    public static void salvar(Usuario usuario) {

        try {

            new File(PASTA).mkdirs();

            FileWriter writer =
                    new FileWriter(
                            PASTA +
                            usuario.getNome() +
                            ".json"
                    );

            gson.toJson(usuario, writer);

            writer.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Usuario carregar(String nome) {

        try {

            FileReader reader =
                    new FileReader(
                            PASTA +
                            nome +
                            ".json"
                    );

            Usuario usuario =
                    gson.fromJson(
                            reader,
                            Usuario.class
                    );

            reader.close();

            return usuario;

        } catch(Exception e) {

            return new Usuario(nome);
        }
    }
}