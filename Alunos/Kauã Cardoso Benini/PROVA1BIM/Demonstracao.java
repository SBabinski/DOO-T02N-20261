import java.time.LocalDate;

public class Demonstracao {

    public static void demonstracao(){

        // CLIENTES

        Cliente c1 = new Cliente();
        c1.setNome("kaua");
        c1.setCpf(123);
        c1.setCnh(1111);

        Cliente c2 = new Cliente();
        c2.setNome("gabriel");
        c2.setCpf(456);
        c2.setCnh(2222);

        Lista.clientes.add(c1);
        Lista.clientes.add(c2);

        // VEICULOS

        Carro car = new Carro();
        car.setPlaca("ABC-1234");
        car.setPreco(100);
        car.setAr(true);

        Moto moto = new Moto();
        moto.setPlaca("XYZ-9999");
        moto.setPreco(50);
        moto.setClindradas(300);

        Lista.veiculos.add(car);
        Lista.veiculos.add(moto);

        // LOCACAO DEVOLVIDA

        Locacao l1 = new Locacao();
        l1.setCliente(c1);
        l1.setVeiculo(car);
        l1.setData(LocalDate.of(2000, 4, 20));
        l1.setDevolvido(true);

        // LOCACAO PENDENTE

        Locacao l2 = new Locacao();
        l2.setCliente(c2);
        l2.setVeiculo(moto);
        l2.setData(LocalDate.of(2000, 4, 20));
        l2.setDevolvido(false);

        Lista.locacao.add(l1);
        Lista.locacao.add(l2);

        // MOSTRAR RESULTADOS

        System.out.println("==== Locacoes Pendentes ====");
        listarPendentes();

        System.out.println("=====================================================");

        System.out.println("==== Todas as Locacoes ====");
        Locacao.listarLocacoes();
    }

    public static void listarPendentes(){

        for (int i = 0; i < Lista.locacao.size(); i++) {
            Locacao l = Lista.locacao.get(i);

            if (!l.isDevolvido()) {

                System.out.println("[" + i + "]");
                System.out.println("Cliente: " + l.getCliente().getNome());
                System.out.println("Veiculo: " + l.getVeiculo().getPlaca());
                System.out.println("Data locacao: " + l.getData());

                System.out.println("Status: Pendente");
                System.out.println("------------------------");
            }
        }
    }
}