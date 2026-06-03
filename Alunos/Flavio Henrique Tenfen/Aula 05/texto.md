Paradigmas de Programação: Imperativo vs Declarativo

Os paradigmas de programação representam diferentes formas de pensar e estruturar soluções computacionais. Entre os principais, destacam-se o paradigma 
imperativo e o paradigma declarativo, que possuem abordagens fundamentalmente distintas na forma de resolver problemas.

Paradigma Imperativo

O paradigma imperativo é baseado na descrição passo a passo de como um programa deve executar determinada tarefa. O programador define as instruções que o 
computador deve seguir, controlando o fluxo de execução por meio de estruturas como loops, condicionais e variáveis.
A linguagem Java é um exemplo desse paradigma. Nela o desenvolvedor especifica detalhadamente cada operação necessária para alcançar o resultado desejado.

Exemplo:
public class Soma {
    public static void main(String[] args) {
        int soma = 0;
        for (int i = 1; i <= 5; i++) {
            soma += i;
        }
        System.out.println(soma);
    }
}

No exemplo:
- Inicializa uma variável soma com valor 0
- Utiliza um laço for para iterar de 1 até 5
- A cada iteração, adiciona o valor atual à variável
- Ao final, imprime o resultado


Paradigma Declarativo

O paradigma declarativo, por outro lado, foca em que deve ser resolvido, sem especificar exatamente como o processo ocorre. O programador define regras, 
fatos ou expressões, e o sistema se encarrega de encontrar a solução.
A linguagem Prolog é um exemplo típico desse paradigma, sendo amplamente utilizada em lógica e inteligência artificial.

Exemplo:
soma(0, 0).
soma(N, Resultado) :-
    N > 0,
    N1 is N - 1,
    soma(N1, R1),
    Resultado is R1 + N.

Então:
- Define-se uma condição base (soma(0, 0))
- Define-se uma regra recursiva para calcular a soma
- O Prolog resolve automaticamente a consulta
O programador apenas descreve a relação lógica, e o sistema determina como chegar ao resultado.

Comparação entre Java e Prolog

Aos critérios de comparação, no paradigma imperativo representado pela linguagem Java, o foco está em descrever como realizar determinada tarefa, enquanto 
no paradigma declarativo, exemplificado pelo Prolog, o foco está em definir o que deve ser feito.
Em relação ao controle de fluxo, o Java utiliza uma abordagem explícita, por meio de estruturas como loops e condicionais, permitindo ao desenvolvedor 
controlar diretamente a execução do programa. Por outro lado, o Prolog apresenta um controle de fluxo implícito, baseado em mecanismos de inferência 
lógica.

Quanto à complexidade mental, o paradigma imperativo exige um raciocínio mais procedural, no qual o programador precisa pensar passo a passo. Já no 
paradigma declarativo, o pensamento é mais abstrato e orientado à lógica.
Enquanto o código em Java exige que o programador detalhe cada etapa da execução, o código em Prolog abstrai esse processo, permitindo que o sistema 
resolva o problema com base em regras declaradas.

-> No imperativo, o desenvolvedor controla o processo
-> No declarativo, o desenvolvedor define o objetivo

Considerações finais

Os paradigmas imperativo e declarativo representam abordagens complementares no desenvolvimento de software. O imperativo oferece maior controle e 
previsibilidade, sendo amplamente utilizado em aplicações tradicionais. Já o declarativo proporciona maior nível de abstração, sendo ideal para problemas 
baseados em lógica, regras e inferência.
Compreender ambos os paradigmas permite ao desenvolvedor escolher a melhor estratégia para cada contexto, aumentando sua capacidade de resolver problemas 
de forma eficiente e escalável.