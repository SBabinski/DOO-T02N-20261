import java.util.List;
import java.util.ArrayList;
import Objetos.Planta;
import Objetos.Vendedor;
import Objetos.Loja;
import Objetos.Cliente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Populadores {

    static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void popularObjetos(){
        popularPlantas();
        popularLojasVendedoresClientes();
    }

    public static void popularPlantas(){
        String dataUm = "10/10/2025";
        String dataDois = "16/09/2024";
        String dataTres = "01/04/2026";

        LocalDate primeiraData = LocalDate.parse(dataUm, formatador);
        LocalDate segundaData = LocalDate.parse(dataDois, formatador);
        LocalDate terceiraData = LocalDate.parse(dataTres, formatador);



        Planta plantaUm = new Planta("Rosa", 15.00, 12, primeiraData);
        Planta plantaDois = new Planta("Margarida", 20.00, 30, segundaData);
        Planta plantaTres = new Planta("Lirio", 12.50, 16, terceiraData);

        Vendas.registro.add(plantaUm);
        Vendas.registro.add(plantaDois);
        Vendas.registro.add(plantaTres);
    }

    public static void popularLojasVendedoresClientes(){
        Loja lojaUm = new Loja(
        "My Plant - Matriz",
        "Minha Planta",
        "83.472.915/0001-27",
        "Capanema",
        "Sao Cristovao",
        "Tamoios");

        Loja lojaDois = new Loja(
        "My Plant - Filial 01",
        "Minha Planta",
        "83.472.915/0002-08",
        "Planalto",
        "Centro",
        "Guaruja");

        Loja lojaTres = new Loja(
        "My Plant - Filial 02",
        "Minha Planta",
        "83.472.915/0003-99",
        "Cascavel",
        "Cancelli",
        "Teresina");

        List<Double> salariosUm = new ArrayList<>();
        salariosUm.add(2500.00);
        salariosUm.add(2555.00);
        salariosUm.add(2387.00);

        List<Double> salariosDois = new ArrayList<>();
        salariosDois.add(2500.00);
        salariosDois.add(2500.00);
        salariosDois.add(2500.00);

        List<Double> salariosTres = new ArrayList<>();
        salariosTres.add(2500.00);
        salariosTres.add(2750.00);
        salariosTres.add(2400.00);

        List<Double> salariosQuatro = new ArrayList<>();
        salariosQuatro.add(2500.00);
        salariosQuatro.add(2555.00);
        salariosQuatro.add(2900.00);

        Vendedor vendedorUm = new Vendedor(
            "Jose", 
            37, 
            lojaDois.getNomeFantasia(), 
            lojaDois.getCidade(), 
            "Alamedas", 
            "Tuatui", 
            2500.00, 
            salariosUm);

            Vendedor vendedorDois = new Vendedor(
            "Carlos", 
            70, 
            lojaUm.getNomeFantasia(), 
            lojaUm.getCidade(), 
            "Juadema", 
            "Anasui", 
            2700.00, 
            salariosDois);

            Vendedor vendedorTres = new Vendedor(
            "Januario", 
            49, 
            lojaUm.getNomeFantasia(), 
            lojaUm.getCidade(), 
            "Arroio Negro", 
            "Forecast", 
            2800.00, 
            salariosTres);

            Vendedor vendedorQuatro = new Vendedor(
            "Vilagran", 
            22, 
            lojaTres.getNomeFantasia(), 
            lojaTres.getCidade(), 
            "Capela", 
            "Brando Gado", 
            2655.00, 
            salariosQuatro);

            Cliente clienteUm = new Cliente(
            "Joao Cancelier", 
            19, 
            "Cascavel", 
            "Centro", 
            "Girara");

            Cliente clienteDois = new Cliente(
            "Joao Cavalheiro", 
            18, 
            "Capitao Leonidas Marques", 
            "Batel", 
            "Rua das Flores");

            Cliente clienteTres = new Cliente(
            "Pedro Marchi", 
            20, 
            "Capitao Leonidas Marques", 
            "Vila A", 
            "Avenida Paraná");

            Cliente clienteQuatro = new Cliente(
            "Otavio Schwenk", 
            19, 
            "Capanema", 
            "Jardim Porto Alegre", 
            "Rua dos Pioneiros");

            Cliente clienteCinco = new Cliente(
            "Samuel Babinski", 
            26, 
            "Cascavel", 
            "Centro", 
            "Rua Itabira");

            lojaUm.adicionarVendedor(vendedorUm);
            lojaUm.adicionarVendedor(vendedorDois);
            lojaDois.adicionarVendedor(vendedorTres);
            lojaTres.adicionarVendedor(vendedorQuatro);

            lojaUm.adicionarCliente(clienteDois);
            lojaUm.adicionarCliente(clienteTres);
            lojaDois.adicionarCliente(clienteQuatro);
            lojaTres.adicionarCliente(clienteCinco);
            lojaTres.adicionarCliente(clienteUm);

        Gestao.lojas.add(lojaUm);
        Gestao.lojas.add(lojaDois);
        Gestao.lojas.add(lojaTres);

        Gestao.funcionarios.add(vendedorUm);
        Gestao.funcionarios.add(vendedorDois);
        Gestao.funcionarios.add(vendedorTres);
        Gestao.funcionarios.add(vendedorQuatro);

        Gestao.clientes.add(clienteUm);
        Gestao.clientes.add(clienteDois);
        Gestao.clientes.add(clienteTres);
        Gestao.clientes.add(clienteQuatro);
        Gestao.clientes.add(clienteCinco);
    }
}