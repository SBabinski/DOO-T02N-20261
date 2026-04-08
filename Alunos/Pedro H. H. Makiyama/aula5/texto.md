# Paradigmas Imperativo e Declarativo
## Paradigma Imperativo
### Definição
O Paradigma Imperativo se trata de um paradigma de programação fundamentado no conceito da Máquina de Turing, uma abstração matemática que corresponde ao conjunto de funções computáveis, no qual são passadas instruções para que o computador realize de forma sequencial, focando, assim, no "como" e se associando ao tempo verbal imperativo, que é usado para expressar ordens, comandos.
### Funcionamento
Se baseia em 3 fundamentos:
- Descrição de estados de uma máquina abstrata por meio dos valores de um conjunto de variáveis;
- Expressões formadas pelo resultado de operações ou relações entre esses valores, ou seja, mecanismos para reconhecer esses estados;
- Comandos de atribuição e controle.
### Linguagens de Programação Imperativas
Algumas das principais linguagens de programação imperativas são: C, C++, C#, Java, JavaScript, Pascal, Fortran, Python, Go e Rust.
### Subparadigmas da Programação Imperativa
Estes são alguns do principais subparadigmas da Programação Imperativa:
- Programação Procedural
  - A mais comum dentro do Paradigma Imperativo;
  - Se caracteriza pela organização do código em procedimentos/funções;
  - Visa a divisão do código em partes reutilizáveis;
  - Exemplos: C e Pascal.
- Programação Estruturada
  - Evolução da Programação Procedural, visando maior organização e compreensão;
  - Evita uso de certos comandos (goto, por exemplo);
  - Usa estruturas bem definidas (sequência, decisão (if/else), repetição (for/while));
  - Exemplos: C, Pascal e Fortran.
- Programação Orientada a Objetos
  - Embora seja um paradigma próprio, ainda pode ser considerada imperativa;
  - Organiza o código em objetos e classes;
  - Utiliza conceitos como encapsulamento, polimorfismo e herança;
  - Exemplos: Java, C++ e Python.
- Programação Modular
  - Divide o programa em módulos independentes;
  - Cada módulo possui uma responsabilidade específica;
  - Visa facilitar a manutenção e testes;
  - Java, C++, Rust e Go.
- Programação Baseada em Eventos
  - O fluxo do programa depende de eventos (cliques, teclas, ações do usuário);
  - Muito utilizada em interfaces gráficas e sistemas interativos;
  - Exemplos: JavaScript e C#.
### Exemplo de Programa do Paradigma Imperativo
O programa básico em Java a seguir calcula a média entre dois valores:
```java
public class Exemplo{ // Nome da classe
  public static void main(String[] args){ // Função main
      // Declaração das variáveis que serão utilizadas
      double valor1, valor2, media;

      // Inicialização das variáveis
      valor1 = 4.5; 
      valor2 = 5.5; 
      media = (valor1 + valor2) / 2; // Expressão para se obter a média

      return; // Termina a execução do programa
  }
}
```
## Paradigma Declarativo
### Definição
O Paradigma de Declarativo é um paradigma de programação que, diferentemente do Paradigma Imperativo, foca no "o quê", assim, nele, os programas descrevem os resultados esperados, não citando explicitamente os passos a serem tomados para se chegar a eles.
### Funcionamento
De maneira geral, programas que fazem parte do Paradigma Declarativo funcionam da seguinte forma:
- O programador define, em alto nível, com grau elevado de abstração, o que deve ser executado, ou seja, o resultado esperado;
- Estes programas tendem a evitar efeitos colaterais, assim, não alterando estados e sendo mais previsíveis (referential transparency).
### Linguagens de Programação Declarativas
Algumas das principais linguagens de programação do Paradigma Declarativo são: JavaScript, HTML, CSS, SQL, MiniZinc, Oz, XQuery, Haskell, Scala, RegEx, XSLT, Prolog, Datalog, Elixir e Lisp.
### Subparadigmas da Programação Declarativa
Estes são alguns do principais subparadigmas da Programação Declarativa:
- Programação Funcional
  - Baseada em funções puras, sem efeitos colaterais;
  - Imutabilidade de dados, ou seja, sem alteração de estado;
  - Muito ligada à ideia de referential transparency;
  - Exemplos: Haskell, Lisp e Elixir.
- Programação Lógica
  - O programador define as regras e fatos;
  - O sistema encontra a solução por meio de inferência automática;
  - Execução com backtracking (tentativa e erro controlada);
  - Exemplos: Prolog e Datalog.
- Programação Baseada em Consultas
  - Manipulação de dados por consultas declarativas;
  - Operações baseadas em conjuntos (set-based);
  - Abstração do como acessar os dados;
  - Exemplos: SQL e XQuery.
- Programação Baseada em Restrições
  - Definição de restrições ao invés de algoritmos;
  - Uso de solvers para encontrar soluções válidas;
  - Aplicação em problemas de otimização e busca;
  - Exemplos: MiniZinc e Oz.
### Exemplo de Programa do Paradigma Declarativo
O programa básico em Prolog a seguir também calcula a média entre dois valores:
```prolog
% Estabelecemos a relação que define a média
media(Valor1, Valor2, Media) :-
    Media is (Valor1 + Valor2) / 2.
```
Para usá-lo, em vez de "rodar o programa", faz-se uma pergunta:
```prolog
% Qual a média entre 10 e 20?
?- media(10, 20, Media).
```
Resposta:
```prolog
Media = 15.0.
```
## Conclusão
Nota-se, assim, que os Paradigmas Imperativo e Declarativo, embora pertencentes a um mesmo "universo", apresentam diferenças fundamentais, de modo que nenhum seja objetivamente melhor do que o outro, mas, sim, que cada um tenha, por meio de suas características individuais, vantagens e desvantagens, cabendo, então, ao programador decidir qual a escolha mais apropriada para a realização de seus objetivos.


