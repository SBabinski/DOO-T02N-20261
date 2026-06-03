public class Item {
    private int id;
    private String nome;
    private String tipo;
    private double valor;

    public Item () {

    }

    public Item (int id, String nome, String tipo, double Valor) {
        setId(id);
        setNome(nome);
        setTipo(tipo);
        setValor(Valor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0){
            this.id = id;
        }else {
            System.out.println("Digite um id válido:");
            int novoId = Main.scan.nextInt();
            Main.scan.nextLine();
            setId(novoId);
        }
    }

    public String getNome() {
        return nome;
    }

    public final void setNome(String nome) {
        if ((nome != null) && (!nome.isBlank())){
            this.nome = nome;
        }
        else {
            System.out.println("Digite um nome válido:");
            String novoNome = Main.scan.nextLine();
            setNome(novoNome);
        }
    }

    public String getTipo() {
        return tipo;
    }

    public final void setTipo(String tipo) {
        if ((tipo != null) && (!tipo.isBlank())){
            this.tipo = tipo;
        }
        else {
            System.out.println("Digite um tipo válido:");
            String novoTipo = Main.scan.nextLine();
            setTipo(novoTipo);
        }
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor > 0){
            this.valor = valor;
        }else {
            System.out.println("Digite um valor válido:");
            double novoValor = Main.scan.nextDouble();
            Main.scan.nextLine();
            setValor(novoValor);
        }
    }

    public String gerarDescicao() {
        return String.format("ID: %d | Item: %s | Tipo: %s | Valor: %.2f \n", id, nome, tipo, valor);
    }

}
