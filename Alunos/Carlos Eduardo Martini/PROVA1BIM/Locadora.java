public class Locadora {
    public Locacao[] locacoes = new Locacao[10];
    public int contador = 0;

    public void registrarLocacao(Locacao novaLocacao){
        if(contador < 10){
            locacoes[contador] = novaLocacao;
            contador++;
            System.out.println("nova locação registrada");
        }else{
            System.out.println("erro locaçao lotada");
        }
    }

    public void listarLocacoesSemDevolucao(){
        System.out.println("---Locaçoes que não foram devolvidas---");
        boolean pendente = false;

        for(int i = 0; i < contador; i++){
            if(locacoes[i].devolvido == false){
                locacoes[i].visualizarDados();
                pendente = true;
            }
        }

        if(pendente == false){
            System.out.println("nada pendente");
        }
    }
}
