public class Demo {

    public static void carregar(Banco banco) {

        Cliente cliente1 = new Cliente("Gabriel", "813872", "0912");
        Cliente cliente2 = new Cliente("Lucas", "943523", "7324");

        banco.getClientes().add(cliente1);
        banco.getClientes().add(cliente2);

        Carro carroDemo = new  Carro("BSY-7234", 15, true);

        banco.getCarros().add(carroDemo);

        Moto motoDemo = new Moto("DJK-9982", 8, 150);

        banco.getMotos().add(motoDemo);

    }
}