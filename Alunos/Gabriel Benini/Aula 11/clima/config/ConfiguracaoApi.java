package clima.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfiguracaoApi {

    private static final String VAR_AMBIENTE    = "VISUALCROSSING_API_KEY";
    private static final String ARQUIVO_CONFIG  = "config.properties";
    private static final String CHAVE_PROPRIEDADE = "visualcrossing.api.key";

    private ConfiguracaoApi() {}

    public static String obterChaveApi() {
        String chave = System.getenv(VAR_AMBIENTE);
        if (chave != null && !chave.isBlank()) return chave.trim();

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(ARQUIVO_CONFIG)) {
            props.load(fis);
            chave = props.getProperty(CHAVE_PROPRIEDADE);
            if (chave != null && !chave.isBlank()) return chave.trim();
        } catch (IOException ignored) {}

        return "FQM6NM9FYYWQNV2NXNNNRDRAX";
    }
}