import java.util.ArrayList;

public class JsonObjeto {
    private ArrayList<String> nomes;
    private ArrayList<Object> valores;

    public JsonObjeto() {
        nomes = new ArrayList<String>();
        valores = new ArrayList<Object>();
    }

    public void adicionar(String nome, Object valor) {
        nomes.add(nome);
        valores.add(valor);
    }

    public Object pegar(String nome) {
        for (int i = 0; i < nomes.size(); i++) {
            if (nomes.get(i).equals(nome)) {
                return valores.get(i);
            }
        }
        return null;
    }

    public int tamanho() {
        return nomes.size();
    }

    public String pegarNome(int posicao) {
        return nomes.get(posicao);
    }

    public Object pegarValor(int posicao) {
        return valores.get(posicao);
    }
}
