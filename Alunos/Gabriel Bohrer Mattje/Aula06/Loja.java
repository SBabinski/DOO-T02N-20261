package Aula06;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Loja {
    String nomeFantasia;
    String razaoSocial;
    String cnpj; //Vulgo cpnj kkkk
    String cidade;
    String bairro;
    String rua;
    
    List<Vendedor>vendedores = new ArrayList<>();
    List<Cliente>clientes = new ArrayList<>();
    private List<Venda> vendas = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial,
                String cnpj, String cidade, String bairro,
                String rua){
        
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial =razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void apresentarse(){
        System.out.println("Nome Fantasia: "+nomeFantasia);
        System.out.println("Cidade "+cidade+" Bairro "+bairro+" Rua "+rua);
    }

    public void contarClientes(){
        System.out.println("Quantidade de clientes: "+clientes.size());
    }

    public void contarVendedores(){
        System.out.println("Quantidade de vendedores: "+vendedores.size());
    }

    public void adicionarVendas(Venda venda){
        vendas.add(venda);
    }

    public void mostrarVendas(){

        if(vendas.isEmpty()){
            System.out.println("Nenhuma venda registrada!");
            return;
        }

        int contador=1;

        for(Venda venda : vendas){
        System.out.println("---------------------");
        System.out.println("Venda " + contador++);
        System.out.println("Quantidade: " + venda.getQuantidade());
        System.out.println("Total: " + venda.getTotal());
        System.out.println("Data: " + venda.getData());
        }
    }

        public void buscarVendasPorData(LocalDate dataBusca){

        int totalVendasDia = 0;
        boolean encontrou = false;

        for(Venda venda : vendas){

            if(venda.getData().equals(dataBusca)){
                encontrou = true;
                totalVendasDia++;

                System.out.println("---------------------");
                System.out.println("Quantidade: " + venda.getQuantidade());
                System.out.println("Total: " + venda.getTotal());
            }
        }

        if(!encontrou){
            System.out.println("Nenhuma venda encontrada nessa data.");
        } else {
            System.out.println("Total de vendas no dia: " + totalVendasDia);
        }
    }
}
