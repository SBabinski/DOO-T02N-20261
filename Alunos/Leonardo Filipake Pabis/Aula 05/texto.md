# Aula 5 - Texto sobre paradigmas
---
## Diferença entre Paradigma imperativo e Paradigma declarativo
- Paradigma Imperativo
  - O *paradigma imperativo* é um estilo de programação focado no **como** realizar uma tarefa, descrevendo **passo a passo** as instruções que o computador deve seguir, com forte uso de variáveis e controlando a ordem exata de execução.
  - Características Principais:
    - Sequência de Comandos
    - Mudança de estados (variáveis são alteradas)
    - Foco no "Como"
    - Estruturas de Controle (for, while)
  - Sub-paradigmas:
    - Estruturada: focado na organização, clareza e manutenção do código. Utiliza sequência, decisão (if/else) e iteração (loops), para desenvolver algoritmos de forma hierárquica, dividindo problemas complexos em menores. (Cobol, PHP)
    - Procedural: Organiza o código em procedimentos ou funções (ex: C, Pascal)
    - Orientado a Objetos (OO): Organiza o código em objetos que contêm estado e métodos (ex: Java, C++, Python)
- Paradigma Declarativo
  - O *paradigma declarativo*  é uma abordagem de programação focada em **descrever** o que o programa deve realizar, em vez de como fazê-lo (passo a passo). Ele utiliza lógica, relações e expressões funcionais, resultando em código mais conciso, legível e sem efeitos colaterais. Exemplos comuns incluem SQL, HTML, CSS, Prolog.
  - Características Principais:
    - Foco no "O quê"
    - Imutabilidade
    - Sem Efeitos Colaterais (consistêntes)
    - Alto Nível de Abstração
  - Sub-paradigmas:
    - Funcional: é um paradigma declarativo que constrói software através da composição de funções puras (com mesma entrada, retorna mesma saída e não muda variáveis externas), evitando dados mutáveis e efeitos colaterais. Foca no "que" resolver em vez de "como", utilizando imutabilidade e funções de primeira classe para criar códigos mais previsíveis, testáveis e fáceis de paralelizar. (JS, Scala)
    - Lógico: A programação lógica é um paradigma declarativo baseado na lógica formal, onde o programador define fatos e regras em vez de passos procedimentais. O sistema utiliza um motor de inferência para deduzir respostas a consultas, sendo amplamente aplicado em inteligência artificial e bancos de dados dedutivos. (Prolog)
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
Neste exemplo, o programa segue uma sequência clara de passos:
- Solicita as notas ao usuário.
- Armazena os valores em variáveis.
- Realiza o cálculo da média.
- Exibe o resultado.

Todo o fluxo de execução é explicitamente controlado pelo programador.

---

Em Prolog:
````Prolog
media :-
    write('Digite a primeira nota: '),
    read(N1),
    write('Digite a segunda nota: '),
    read(N2),
    M is (N1 + N2) / 2,
    write('A media e: '), write(M), nl.
````
Em Prolog, o programa é definido como uma regra (media) que descreve a sequência de ações e relações:
- Lê os valores informados pelo usuário.
- Define a média como uma relação matemática.
- Exibe o resultado.

Apesar de haver uma sequência de comandos, o foco ainda está na definição das relações e não no controle detalhado do fluxo como no Java.

---