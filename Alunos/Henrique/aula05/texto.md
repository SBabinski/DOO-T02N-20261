Paradigmas de Programação: Imperativo vs Declarativo

Introdução
Os paradigmas de programação representam diferentes formas de pensar e estruturar a solução de problemas computacionais. Entre os principais, destacam-se o paradigma imperativo e o declarativo, que diferem essencialmente na forma como o programador expressa a solução.
Paradigma Imperativo
No paradigma imperativo, o programador descreve como o problema deve ser resolvido, especificando passo a passo as instruções que o computador deve executar. Isso envolve o uso de variáveis, estruturas de controle e a manipulação explícita do estado do programa.
Exemplo em Java:

int soma = 0;
for (int i = 1; i <= 10; i++) {
    if (i % 2 == 0) {
        soma += i;
    }
}
System.out.println(soma);

Paradigma Declarativo
No paradigma declarativo, o foco está em o que deve ser resolvido, e não em como resolver. O programador descreve o resultado esperado, enquanto o sistema se encarrega de determinar a melhor forma de alcançá-lo.
Exemplo em Prolog:

par(X) :- X mod 2 =:= 0.

soma_pares(N, Soma) :-
    findall(X, (between(1, N, X), par(X)), Lista),
    sum_list(Lista, Soma).


Comparação
A principal diferença entre os paradigmas está na abordagem: o imperativo detalha o passo a passo da execução, enquanto o declarativo descreve apenas o resultado esperado. No exemplo apresentado, o Java percorre explicitamente os números e realiza a soma, enquanto o Prolog define regras e utiliza mecanismos internos para alcançar o resultado.
Conclusão
Os paradigmas imperativo e declarativo são fundamentais na programação e oferecem diferentes níveis de abstração. A escolha entre eles depende do problema, do controle desejado e das necessidades do sistema.