import java.util.ArrayList;

public class Loja{

String nomeFantasia;
String razaoSocial;
String cnpj;
String cidade;
String bairro;
String rua;

ArrayList <Vendedor> vendedores = new ArrayList<>();
ArrayList <Cliente> clientes = new ArrayList<>();

public Loja (String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua){

this.nomeFantasia = nomeFantasia;
this.razaoSocial = razaoSocial;
this.cnpj = cnpj;
this.cidade = cidade;
this.bairro = bairro;
this.rua = rua;

}
public void adicionarClientes(Cliente mostrar){

clientes.add(mostar);
}
 public void adicionarVendedor(Vendedor recebe){
    vendedores.add(recebe);
 }

 public void contarClientes(){
    System.out.println("Total Clientes: " + clientes.size());
 }
public void contarVendedores(){
    System.out.println("Total Vendedores: " + vendedores.size());
}
 
public void apresentarse(){
    System.out.println("Empresa: " + this.nomeFantasia);
    System.out.println("Cnpj: " + this.cnpj );
    System.out.println("Endereco: " + this.cidade + "," + this.bairro + "," + this.rua);
}


}