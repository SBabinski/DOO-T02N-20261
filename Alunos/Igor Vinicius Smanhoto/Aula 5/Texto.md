# Aula 5 - Texto sobre paradigmas
---
## Diferença entre Paradigma imperativo e Paradigma declarativo
- Paradigma Imperativo
  - O *paradigma imperativo* é uma forma de programação voltada ao **como** uma tarefa deve ser executada. Nesse modelo, o programador descreve **etapa por etapa** as instruções que o computador precisa seguir, controlando diretamente a ordem de execução e utilizando bastante variáveis.
  - Características Principais:
    - Execução em sequência
    - Alteração de estados durante o processamento
    - Ênfase no "Como"
    - Uso de estruturas de controle (`for`, `while`, `if`)
  - Sub-paradigmas:
    - Estruturada: prioriza a organização, a legibilidade e a manutenção do código. Usa sequência, decisão e repetição para construir algoritmos de forma mais clara, quebrando problemas grandes em partes menores. (Cobol, PHP)
    - Procedural: organiza o programa em procedimentos ou funções reutilizáveis. (C, Pascal)
    - Orientado a Objetos (OO): organiza o software em objetos que possuem atributos e comportamentos. (Java, C++, Python)

- Paradigma Declarativo
  - O *paradigma declarativo* é uma abordagem centrada em **descrever** o que o programa deve alcançar, sem detalhar cada passo da execução. Nesse estilo, o foco está nas regras, relações ou expressões, produzindo códigos mais objetivos, legíveis e com menos efeitos colaterais. Exemplos comuns são SQL, HTML, CSS e Prolog.
  - Características Principais:
    - Foco no "O quê"
    - Imutabilidade
    - Redução de efeitos colaterais
    - Maior nível de abstração
  - Sub-paradigmas:
    - Funcional: constrói programas a partir da composição de funções puras, ou seja, funções que para a mesma entrada sempre produzem a mesma saída e não alteram dados externos. Valoriza imutabilidade e previsibilidade. (JS, Scala)
    - Lógico: baseia-se em fatos e regras, permitindo que o sistema deduza respostas a partir de consultas. É bastante utilizado em inteligência artificial e bancos de dados dedutivos. (Prolog)

---
### Cálculo de média com duas notas:
Em java:
````java
import java.util.Scanner;

public class MediaNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a primeira nota: ");
        double nota1 = scanner.nextDouble();

        System.out.print("Digite a segunda nota: ");
        double nota2 = scanner.nextDouble();

        double media = (nota1 + nota2) / 2;

        System.out.println("A média é: " + media);

        scanner.close();
    }
}
````
- Neste exemplo, o programa segue uma sequência bem definida de ações:
  - Solicita os valores ao usuário.
  - Guarda as notas em variáveis.
  - Calcula a média aritmética.
  - Mostra o resultado na tela.
  - Nesse caso, o fluxo de execução é controlado explicitamente pelo programador, que determina cada etapa que deve acontecer.
```Prolog
media :-
    write('Digite a primeira nota: '),
    read(N1),
    write('Digite a segunda nota: '),
    read(N2),
    M is (N1 + N2) / 2,
    write('A media e: '), write(M), nl.
````
- Em Prolog, o programa é expresso por meio de uma regra chamada media, que estabelece as ações e relações necessárias para obter o resultado:
  - Recebe os valores informados pelo usuário.
  - Define a média por meio de uma relação matemática.
  - Exibe o valor calculado.
  - Mesmo apresentando uma ordem de execução, a lógica em Prolog continua mais voltada à descrição da solução do que ao controle minucioso do fluxo, como ocorre       em Java.

## Conclusão

- Em síntese, os paradigmas imperativo e declarativo representam maneiras diferentes de desenvolver soluções computacionais. O paradigma imperativo se concentra na descrição detalhada das etapas que o programa deve seguir, enquanto o declarativo prioriza a definição do resultado desejado e das relações envolvidas. A comparação entre Java e Prolog mostra que, embora ambos possam resolver o mesmo problema, cada linguagem adota uma lógica distinta para chegar à solução. Dessa forma, o estudo desses paradigmas é importante para ampliar a compreensão sobre programação e permitir a escolha da abordagem mais adequada para cada tipo de problema.
