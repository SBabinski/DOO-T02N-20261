import java.util.ArrayList;

public class Loja{

String nomeFantasia;
String razaoSocial;
String cnpj;
Endereco endereco;

ArrayList <Vendedor> vendedores = new ArrayList<>();
ArrayList <Cliente> clientes = new ArrayList<>();
ArrayList <Gerente> gerentes = new ArrayList<>();
public Loja (String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco){

this.nomeFantasia = nomeFantasia;
this.razaoSocial = razaoSocial;
this.cnpj = cnpj;
this.endereco = endereco;

}
public void adicionarClientes(Cliente mostrar){

clientes.add(mostrar);
}
 public void adicionarVendedor(Vendedor recebe){
    vendedores.add(recebe);
 }
public void adicionarGerente(Gerente receber){
    gerentes.add(receber);
}
 public void contarClientes(){
    System.out.println("Total Clientes: " + clientes.size());
 }
public void contarVendedores(){
    System.out.println("Total Vendedores: " + vendedores.size());
}
 public void contarGerentes(){
    System.out.println("Total Gerentes: " + gerentes.size());
 }
public void apresentarse(){
    System.out.println("Empresa: " + this.nomeFantasia);
    System.out.println("Cnpj: " + this.cnpj );
    this.endereco.apresentarseLogadouro();
}


}