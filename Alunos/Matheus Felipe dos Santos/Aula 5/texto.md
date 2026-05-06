# Paradigmas de Programação: Imperativo e Declarativo

Os paradigmas de programação são formas diferentes de escrever e organizar um código. Dois dos principais são o paradigma imperativo e o declarativo.

O paradigma imperativo é baseado em dizer exatamente o que o programa deve fazer, passo a passo. Nesse tipo de programação, o desenvolvedor controla toda a execução, utilizando variáveis, condições e repetições. A linguagem Java, que foi utilizada na calculadora da Dona Gabrielinha, é um exemplo desse paradigma.

No nosso código da calculadora, por exemplo, foi necessário pedir a quantidade de plantas, o preço, calcular o total e verificar se havia desconto. Tudo isso foi feito passo a passo, controlando o fluxo do programa com estruturas como if e switch.

Um exemplo simplificado em Java seria:

if (quantidade > 10) {
    total = total - (total * 0.05);
}

Nesse caso, estamos dizendo exatamente como o programa deve calcular o desconto.

Já o paradigma declarativo funciona de forma diferente. Em vez de dizer como fazer, o programador descreve o que deseja obter como resultado. A linguagem Prolog é um exemplo desse paradigma, pois utiliza regras e lógica para resolver problemas.

Um exemplo em Prolog seria:

desconto(Quantidade, Total, Resultado) :-
    Quantidade > 10,
    Resultado is Total * 0.95.

Aqui, apenas declaramos a regra do desconto. O próprio sistema decide como aplicar essa regra.

Comparando os dois, o Java exige mais controle e detalhamento, enquanto o Prolog é mais direto e focado na lógica do problema. O paradigma imperativo é mais comum no desenvolvimento de sistemas, enquanto o declarativo é muito usado em áreas como inteligência artificial.

Portanto, entender esses dois paradigmas é importante para escolher a melhor forma de resolver cada tipo de problema.