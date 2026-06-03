import java.time.LocalDate;

public class Demo {
    public static void executarDemo(Locadora locadora) {
      Clientes cliente1 = new Clientes();
        cliente1.cpf = "12345678901";
        cliente1.nome = "João Silva";
        locadora.adicionarCliente(cliente1);

        Clientes cliente2 = new Clientes();
        cliente2.cpf = "98765432100";
        cliente2.nome = "Maria Oliveira";
        locadora.adicionarCliente(cliente2);

        Carro carro = new Carro();
        carro.placa = "ABC1234";
        carro.valorLocacao = 50.0;
        carro.arCondicionado = true;    
        locadora.cadastrarVeiculo(carro);

        Moto moto = new Moto();
        moto.placa = "XYZ5678";
        moto.valorLocacao = 30.0;
        moto.cilindradas = 150;
        locadora.cadastrarVeiculo(moto);

        Locacao locacao1 = new Locacao();
        locacao1.clientes = cliente1;
        locacao1.veiculo = carro;
        locacao1.dataLocacao = LocalDate.of(2026, 4, 22);
        locacao1.diasLocacao = 3;
        locacao1.valorTotal = carro.valorLocacao * 3;
        locacao1.devolvido = false;
        locadora.adicionarLocacao(locacao1);

        Locacao locacao2 = new Locacao();
        locacao2.clientes = cliente2;
        locacao2.veiculo = moto;
        locacao2.dataLocacao = LocalDate.of(2026, 4, 22);
        locacao2.diasLocacao = 2;
        locacao2.valorTotal = moto.valorLocacao * 2;
        locacao2.devolvido = false;
        locadora.adicionarLocacao(locacao2);

        System.out.println("\n=== LOCAÇÕES ATIVAS ===");
        locadora.listarPendentes();
    }
}
