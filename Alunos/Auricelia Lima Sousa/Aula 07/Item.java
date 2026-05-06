public class Item{
int id;
String nome;
String tipo;
double valor;

public Item(int id, String nome, String tipo, double valor){

    this.id = id;
    this.nome = nome;
    this.tipo = tipo;
    this.valor = valor;
}

public void gerarDescricao(){
        System.out.println("Dados do item");
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + this.nome);
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Valor: R$ " + this.valor);
        System.out.println();
}





}