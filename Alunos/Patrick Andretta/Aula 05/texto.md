# Paradigmas imperativos e declarativos

Patrick Andretta  
T02N  
25/03/2026

## Definição

Um paradigma representa um modelo, um padrão. Quando trazemos esse tema para a programação o que temos é um conjunto de regras, principios e conceitos que definem como aquela linguagem deve ser escrita e organizada. 

### Paradigma Imperativo

Dirigido por comandos, seu propósito é descrever **como** o programa funciona. Exemplos: for, while, if, else, do, String, int, function.

- Imperativa estruturada   
 Seu foco é a otimização do código utilizando blocos para tomada de decisão, condicionais e laços de repetição são exmplos

 - Imperativa procedural  
 Baseada em chamadas de procedimentos, também conhecidos como funções, são listas de instruções que mostram ao computador o que aquele programa deve fazer, a maioria das primeiras linguagens de programação são procedurais

### Paradigma Declarativo 

Focado na lógica do programa , descrever **o que** o programa faz. Exemplo em SQL (banco de dados): Ao usar **SELECT nome FROM usuarios WHERE idade > 18**, declaramos quais dados gostariamos, sem especificar **como** o banco de dados deve buscá-los.

- Declarativa funcional  
Neste tipo partimos do ponto incial que tudo são funções, diferente do imperativo, ele evita alterações de estado e efeitos colaterais, facilitando testes, manutenção e permitindo transformar dados criando novos, sem alterar os originais.

- Declarativa lógica  
Lógica se define por **organização e planejamento das instruções, assertivas etc. em um algoritmo**, a principal vantagem aqui é que definimos qual parte do problema queremos resolver e o sistema encontra a melhor solução. Esta se difere bastante daquilo que estamos acostumados no dia a dia, seus 3 pilares são

- Declaração em vez de Instrução: Não dizem "faça isso, depois aquilo". Declaramos verdades e regras. O computador é quem se vira para encontrar o caminho da solução.  
- Base de Conhecimento: O programa é, na verdade, um banco de dados de fatos e relações.  
- Motor de Inferência: É o "cérebro" da linguagem. Quando perguntamos algo ao programa, esse motor vasculha os fatos e regras para deduzir se a resposta é verdadeira ou falsa, ou para encontrar quais valores preenchem aquela condição.

## Comparação de código Java x Prolog

Irei utilizar como exemplo um algoritmo que pergunte a idade do usuario e retorne se ele é menor ou maior de idade.

```JAVA```

```
  import java.util.Scanner;

  public class VerificarIdade {
      public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt();

        if (idade >= 18) {
          System.out.println("Você é maior de idade.");
        } else {
          System.out.println("Você é menor de idade.");
        }
        scanner.close();
    }
  }
```

Resumo do fluxo do programa.  

É imperativo, escrevemos um passo a passo, Ler idade, verificar se é de maior, mostrar resultado

## Exemplo em prolog

```PROLOG```

````
  main :-
    read(Idade),
    
    ( Idade >= 18 ->
        write('Voce é maior de idade.')
    ;
        write('Voce é menor de idade.')
    ),
    halt.
  :- main.
````


Resumo do fluxo do programa.  

É declarativo, lê a idade, verifica a condição, decide o resultado, o exibe na tela, finaliza o programa.  

˜->˜ Indica o que fazer em caso da afirmação ser verdadeira.     
˜;˜ indica o que fazer caso a condição for falsa. halt encerra a execução

## Conclusão

Os paradigmas imperativo e declarativo representam formas diferentes de programar. O imperativo descreve como resolver o problema passo a passo, enquanto o declarativo define o que deve ser feito.

A comparação entre Java e Prolog mostra isso claramente: Java segue um fluxo controlado pelo programador, enquanto Prolog utiliza regras e lógica para chegar ao resultado.

Assim, cada paradigma tem sua utilidade, e entender ambos ajuda a escolher a melhor abordagem para cada problema.