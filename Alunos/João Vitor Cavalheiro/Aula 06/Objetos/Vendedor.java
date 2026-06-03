public class Vendedor {
    String nomeV;
    int idadeV;
    String cidadeV;
    String bairroV;
    String ruaV;
    String loja;
    double salarioBase;
    double[] salarioRecebido = new double[3];

    public Vendedor(String nomeV, 
        int idadeV, 
        String loja,
        String cidadeV, 
        String bairroV, 
        String ruaV, 
        double salarioBase, 
        double[] salarioRecebido) {

        this.nomeV=nomeV;
        this.idadeV=idadeV;
        this.loja=loja;
        this.cidadeV=cidadeV;
        this.bairroV=bairroV;
        this.ruaV=ruaV;
        this.salarioBase=salarioBase;
        this.salarioRecebido=salarioRecebido;

    }

    public String getNomeV() {
        return nomeV;
    }

    public void setNomeV(String nomeV) {
        this.nomeV = nomeV;
    }

    public int getIdadeV() {
        return idadeV;
    }

    public void setIdadeV(int idadeV) {
        this.idadeV = idadeV;
    }

    public String getCidadeV() {
        return cidadeV;
    }

    public void setCidadeV(String cidadeV) {
        this.cidadeV = cidadeV;
    }

    public String getBairroV() {
        return bairroV;
    }

    public void setBairroV(String bairroV) {
        this.bairroV = bairroV;
    }

    public String getRuaV() {
        return ruaV;
    }

    public void setRuaV(String ruaV) {
        this.ruaV = ruaV;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double[] getSalarioRecebido() {
        return salarioRecebido;
    }

    public void setSalarioRecebido(double[] salarioRecebido) {
        this.salarioRecebido = salarioRecebido;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public void apresentarVendedor(){
        System.out.println("=======================");
        System.out.println("----------Vendedor----------");
        System.out.println("Nome: "+getNomeV());
        System.out.println("Idade: "+getIdadeV());
        System.out.println("Trabalha na Loja: "+getLoja());
        System.out.println("Com um salário de  base de R$"+getSalarioBase());
        System.out.println("Sua média salárial dos 3 últimos meses é R$"+ calcularSalario());
        System.out.println("Caso atinja a meta de venda ganha uma bonificação de R$"+calcularBonus());
        System.out.println("=======================a");
    }

    public double calcularSalario(){
        double soma;
        soma = getSalarioRecebido()[0]+getSalarioRecebido()[1]+getSalarioRecebido()[2];
        double media = soma/3;
        return media;
    }

    public double calcularBonus(){
        double bonus= getSalarioBase()*0.2;
        return bonus;
    }

}