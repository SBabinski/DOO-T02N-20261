# Paradigmas de Programação: Imperativo vs Declarativo

Djonattan Schneider Tasarz
T02N


### Introdução aos Paradigmas

Vou falar sobre dois jeitos diferentes de programar: imperativo e declarativo. Cada um tem sua lógica e funciona melhor em situações específicas.

O imperativo é aquele onde a gente manda o computador fazer passo a passo tudo que precisa ser feito. Tem variáveis que mudam de valor, loops, ifs, tudo bem detalhado sobre COMO fazer.

Já o declarativo é diferente. A gente só fala O QUE quer que aconteça e deixa o programa descobrir o como. É mais direto pro resultado final.

### Comparando Java com Prolog

Vou mostrar dois códigos que fazem a mesma coisa: achar os familiares de uma pessoa numa árvore genealógica.

### Java (Imperativo)

```java
import java.util.*;

public class ArvoreGenealogica {
    static class Pessoa {
        String nome;
        List<Pessoa> pais;
        List<Pessoa> filhos;
        
        Pessoa(String nome) {
            this.nome = nome;
            this.pais = new ArrayList<>();
            this.filhos = new ArrayList<>();
        }
    }
    
    public static void main(String[] args) {
        Pessoa joao = new Pessoa("João");
        Pessoa maria = new Pessoa("Maria");
        Pessoa pedro = new Pessoa("Pedro");
        Pessoa ana = new Pessoa("Ana");
        Pessoa lucas = new Pessoa("Lucas");
        
        joao.filhos.add(pedro);
        maria.filhos.add(pedro);
        pedro.filhos.add(lucas);
        ana.filhos.add(lucas);
        
        List<Pessoa> familiaresPedro = new ArrayList<>();
        familiaresPedro.addAll(buscarPais(pedro));
        familiaresPedro.addAll(buscarFilhos(pedro));
        
        System.out.println("Familiares de Pedro:");
        for (Pessoa f : familiaresPedro) {
            System.out.println("- " + f.nome);
        }
    }
    
    static List<Pessoa> buscarPais(Pessoa pessoa) {
        List<Pessoa> pais = new ArrayList<>();
        for (Pessoa p : pessoa.pais) {
            pais.add(p);
            pais.addAll(buscarPais(p));
        }
        return pais;
    }
    
    static List<Pessoa> buscarFilhos(Pessoa pessoa) {
        List<Pessoa> filhos = new ArrayList<>();
        for (Pessoa f : pessoa.filhos) {
            filhos.add(f);
            filhos.addAll(buscarFilhos(f));
        }
        return filhos;
    }
}
```

### Prolog (Declarativo)

```prolog
pai(joao, pedro).
pai(pedro, lucas).
mae(maria, pedro).
mae(ana, lucas).

familiar(X, Y) :- pai(X, Y).
familiar(X, Y) :- mae(X, Y).
familiar(X, Y) :- familiar(Z, Y), familiar(X, Z).
familiar(X, Y) :- familiar(Y, Z), familiar(X, Z).

?- familiar(X, pedro).
```

### Como cada um funciona

No Java eu tive que criar uma classe Pessoa, fazer listas pra guardar pais e filhos, escrever funções recursivas pra buscar em cima e em baixo da árvore, usar for pra percorrer tudo. É bem manual, controlamos cada passo.

No Prolog é bem mais simples. Só escrevi quem é pai de quem e quem é mãe de quem. Depois uma regra dizendo que familiar pode ser pai, mãe ou familiar de familiar. E pronto. Quando pergunto "quem é familiar do Pedro?" o Prolog acha sozinho.

### Diferenças principais

| Java | Prolog |
|------|--------|
| Eu controlo tudo | O programa resolve sozinho |
| Mais código | Poucas linhas |
| Melhor pra performance | Melhor pra lógica |
| Difícil de mudar árvore | Só adiciono fatos novos |

### Conclusão

Cada paradigma tem seu lugar. Java é bom quando preciso de velocidade e controlo total. Prolog é ótimo pra quando tenho regras lógicas e consultas complexas. Depende do problema que vou resolver.

