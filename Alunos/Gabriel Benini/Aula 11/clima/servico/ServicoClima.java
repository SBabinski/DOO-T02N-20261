package clima.servico;

import clima.api.ClienteApiVisualCrossing;
import clima.config.ConfiguracaoApi;
import clima.modelo.DadosClima;
import clima.modelo.ErroClima;

public class ServicoClima {

    private final ClienteApiVisualCrossing cliente;

    public ServicoClima() throws ErroClima {
        String chave = ConfiguracaoApi.obterChaveApi();
        if (chave == null) {
            throw new ErroClima(
                    "Chave de API não encontrada.\n" +
                            "Defina a variável de ambiente VISUALCROSSING_API_KEY\n" +
                            "ou crie o arquivo config.properties com:\n" +
                            "visualcrossing.api.key=SUA_CHAVE", 0);
        }
        this.cliente = new ClienteApiVisualCrossing(chave);
    }

    public DadosClima consultarClima(String cidade) throws ErroClima {
        if (cidade == null || cidade.isBlank())
            throw new ErroClima("Informe o nome de uma cidade.", 0);
        return cliente.buscarClima(cidade.trim());
    }
}