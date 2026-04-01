# Paradigmas da Programação

Os paradigmas da programação representam as diferentes formas de compreender e estruturar um código de algum software. Entre eles, existem 2 termos principais que foram abordados durante a aula, são eles: paradigma imperativo e declarativo, que se diferenciam pela sua forma de expressar a lógica da resolução de algum problema.

O paradigma imperativo é utilizado em linguagens como o Java, que é uma linguagem de programação orientada a objetos e precisa de uma descrição passo a passo bem explícita do que precisa ser feito para atingir determinado objetivo. Desse modo, o programador controla e determina a sequência lógica de como o programa deverá ser executado, atribuindo as instruções necessárias, como estruturas condicionais e laços de repetição. Nesse caso, o foco está em como o problema será resolvido de fato.

Já o paradigma declarativo, é utilizado em linguagens como o Prolog, onde o programador descreve o que deve ser verdadeiro, atribuir fatos e regras, sem ter um fluxo de de execução. O próprio sistema fica responsável por encontrar soluções com base nas instruções fornecidas. Nesse caso, o foco está em descrever a lógica do problema.

## Comparação Java x Prolog

### Em Java:

```java
int n = 4;

if (n % 2 == 0){

System.out.println("Par");
}else{
    System.out.println("Ímpar");
}
```

Em Java podemos observar que é necessário instruír passo a passo para que atinga seu objetivo final, ou seja, são necessários a atribuição de uma variável e uma estrutura condicional if/else para que, dependendo do resultado, tenha determinada saída. Se mostra de forma bem clara as etapas de entrada, processamento e saída de dados.

### Em Prolog:

```Prolog
par(N) :- N mod 2 =:= 0.

"Consulta:
?- par(4)."
```

Já o Prolog tem uma abordagem diferente, pois não utiliza o procedimento, mas sim uma regra lógica. O interpretador analisa essa regra e retorna true ou false, sem que o programador controle o fluxo de execução.

## Conclusão

Portanto, ambas as linguagens demonstram as diferenças entre os paradigmas imperativo e declarativo. O primeiro foca em como será resolvido o problema e sua execução passo a passo, enquanto o outro prioriza a criação de regras e lógica, mas atingem o objetivo final da mesma forma.
