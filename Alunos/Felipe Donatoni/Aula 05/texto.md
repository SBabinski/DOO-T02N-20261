## Paradigmas de Programação: Imperativo e Declarativo

### Paradigma Imperativo

No paradigma imperativo o programador descreve passo a passo como o problema deve ser resolvido, controlando cada instrução. Java é um exemplo desse paradigma.

### Paradigma Declarativo

No paradigma declarativo o programador descreve o que quer obter, sem se preocupar em como chegar lá. O Prolog é um exemplo desse paradigma.

### Comparação de Código

### java

```java
String nome = "Felipe";
int idade = 19;

if (idade >= 18) {
    System.out.println(nome + "é maior de idade");
} else {
    System.out.println(nome + "é menor de idade");
}
```

O programa armazena o nome e a idade, verifica a condição com **if** e imprime a mensagem correspondente. O fluxo é controlado pelo programador em cada etapa.

### prolog

```prolog
maior(felipe, 19) :- 19 >= 18.
```

Apenas declaramos a regra. O Prolog verifica automaticamente se a condição é verdadeira e retorna **true**.

## Conclusão

A diferença principal entre os dois paradigmas é que no imperativo o programador controla cada passo da execução, enquanto no declarativo apenas declara as regras e o sistema resolve.