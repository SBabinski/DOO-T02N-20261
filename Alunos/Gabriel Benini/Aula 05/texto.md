Paradigmas de Programação: Imperativo e Declarativo
Na aula teórica de hoje, foram explorados dois paradigmas fundamentais de programação: o imperativo e o declarativo. O paradigma imperativo, 
representado pela linguagem Java, baseia-se na descrição explícita de como uma tarefa deve ser executada. Ele enfatiza sequências de comandos, 
controle de fluxo (laços, condicionais e atribuições), modificações de estado das variáveis e gerenciamento direto da memória. Em contraste, o paradigma declarativo, exemplificado pela linguagem Prolog, 
concentra-se no que deve ser alcançado, por meio da definição de fatos, regras lógicas e relações. Aqui, o programador não especifica o algoritmo passo a passo; 
cabe ao mecanismo de inferência do interpretador (baseado em unificação, resolução e backtracking) derivar a solução automaticamente.
Para ilustrar as diferenças conceituais e práticas entre esses paradigmas, compararemos dois trechos de código que resolvem o mesmo problema: calcular o 
fatorial de um número inteiro não negativo. O fatorial de n (denotado n!) é definido matematicamente como o produto de todos os inteiros positivos de 1 até n, com 0! = 1.


Código em Java (Paradigma Imperativo)
Javapublic class Fatorial {
    public static int calcularFatorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Número deve ser não negativo");
        }
        int resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }
}

No trecho Java, o desenvolvedor define explicitamente o algoritmo imperativo: inicializa-se uma variável acumuladora (resultado) com valor 1 e, por meio de um laço for, 
realiza-se a multiplicação iterativa de 2 até n. O controle de fluxo é gerenciado diretamente pelo programador, que determina a ordem das operações, as condições de parada e as atualizações de estado. 
A execução é determinística e procedural, refletindo o estilo “faça isto, depois aquilo”.

Código em Prolog (Paradigma Declarativo)
prologfatorial(0, 1).  % Caso base: 0! = 1

fatorial(N, F) :- 
    N > 0, 
    N1 is N - 1, 
    fatorial(N1, F1), 
    F is N * F1.
No código Prolog, não há laços, atribuições mutáveis nem controle de fluxo explícito. Em vez disso, definem-se regras lógicas e um fato base: o fatorial de 0 é 1, e o fatorial de N é N multiplicado pelo 
fatorial de N-1. Quando se consulta o interpretador com ?- fatorial(5, F)., o Prolog aplica recursão implícita via unificação de variáveis e resolução de objetivos, explorando o espaço de soluções até 
satisfazer as regras. O mecanismo interno cuida da computação, sem que o programador precise especificar “como” percorrer os passos.
Comparação e Reflexões
Ambos os códigos atingem o objetivo idêntico — o cálculo correto do fatorial —, porém revelam filosofias opostas. O Java imperativo exige que o programador antecipe e codifique todos os passos algorítmicos, 
tornando o código mais verboso, porém eficiente em termos de desempenho previsível e controle fino (ideal para aplicações de sistemas embarcados ou computação de alto desempenho). 
Já o Prolog declarativo promove uma abstração maior: o código é conciso, próximo da definição matemática do problema e mais fácil de manter ou estender para problemas complexos de 
busca ou lógica (como inteligência artificial ou sistemas de regras).
Essa comparação evidencia as vantagens e limitações de cada paradigma. O imperativo favorece a eficiência computacional e a depuração passo a passo, enquanto o declarativo destaca a expressividade 
e a separação entre lógica e implementação, reduzindo erros de controle de fluxo. A escolha entre eles depende da natureza do problema: algoritmos sequenciais versus problemas de inferência e relacionamentos. 
Tal reflexão reforça a importância de compreender múltiplos paradigmas para uma formação computacional sólida e versátil.