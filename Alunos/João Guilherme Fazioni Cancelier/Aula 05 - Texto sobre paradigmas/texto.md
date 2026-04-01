# Paradigmas
Paradigmas são como regras, definições e conceitos que definem a maneira como o código será escrito e organizado. Estão ligados ao estilo de escrita e organização do código, definindo como o desenvolvedor pode conceituar e modelar problemas complexos a serem resolvidos.

Os paradigmas possuem duas abordagens: os paradigmas imperativos e os declarativos.


## Paradigmas Imperativos
O imperativo é mais focado em dar **instruções** do que deve ser executado, ou seja, uma lista de comandos a serem seguidos, orientando o que deve ser feito e como.



## Paradigmas Declarativos
Já o declarativo foca na **lógica** do que deve ser alcançado, voltando-se para o **resultado final** em vez de detalhar cada passo do fluxo de controle.

---
### Exemplo Paradigmas Imperativos

```Java```
```java
public class Main
{
	public static void main(String[] args) {
		double nota = 4;
		if(nota>=7){
		    System.out.printf("Aprovado");
		}else if((nota <7) && (nota >=5)){
		    System.out.printf("Recuperação.  procure melhorar");
		}else{
            System.out.printf("Reprovado");
		}
	}
}
```
#### Descrição:
- double nota = 4;: Declara uma variável do tipo real (decimal) chamada nota e atribui o valor 4.

- **if(nota >= 7)**: Inicia o primeiro teste: verifica se a nota é maior ou igual a 7.
System.out.printf("Aprovado");: Se o teste acima for verdadeiro, imprime "Aprovado" no console.
- **else if((nota < 7) && (nota >= 5))**: Caso o primeiro teste falhe, verifica se a nota está entre 5 e 6.9.   
System.out.printf("Recuperação. procure melhorar"): Se esta condição for a verdadeira, imprime a mensagem de recuperação.
- **else** : Caso nenhuma das condições anteriores seja atendida (nota menor que 5).
System.out.printf("Reprovado");: Imprime "Reprovado" no console.

---
### Exemplo Paradigmas Declarativos
```Prolog```
```prolog
/* Regras de Avaliação */
status(Nota, 'Aprovado') :- 
    Nota >= 7.

status(Nota, 'Recuperacao. procure melhorar') :- 
    Nota < 7, 
    Nota >= 5.

status(Nota, 'Reprovado') :- 
    Nota < 5.

:- initialization(main).

main :-
    Nota = 4, % Define a nota aqui 
    status(Nota, Resultado),
    write(Resultado),
    nl,
    halt.
```
#### Descrição:
- **status(Nota, 'Aprovado')** :- Nota >= 7.: Define a Regra 1: O fato "Aprovado" só é verdadeiro se a premissa (Nota >= 7) for satisfeita.
- **status(Nota, 'Recuperacao...') :- Nota < 7, Nota >= 5.**: Define a Regra 2: Estabelece uma relação lógica de intervalo (entre 5 e 7) para o status de recuperação.
- **status(Nota, 'Reprovado') :- Nota < 5.**: Define a Regra 3: Estabelece a condição lógica para a reprovação.
- **:- initialization(main).**: Define a Regra de Inicialização: Indica qual parte do código deve ser disparada primeiro.
- **main :-**: Início da Regra de Execução: Agrupa os fatos que o programa deve processar.
- **Nota = 4**: Unificação: Define o dado de entrada que será testado contra as regras.
- **status(Nota, Resultado)**: Busca de Solução: O sistema varre as regras de status para encontrar qual delas é logicamente verdadeira para o valor 4.
- **write(Resultado)**: Ação de saída: Exibe o resultado que a lógica encontrou.
- **nl, halt.**: Regras de encerramento: Finaliza a linha e para a execução do sistema.

## Comparação
### No Código Java (Imperativo)
O objetivo é atingido através de um fluxo de controle explícito.
- **Como funciona:** O programa executa uma sequência de testes (if, else if, else). Ele "pergunta" para a variável nota seu valor e, dependendo da resposta, o ponteiro de execução é desviado para um bloco específico de comando (System.out.printf).
- **Lógica:** É uma tomada de decisão processual. O desenvolvedor precisa prever a ordem exata das verificações para que a lógica não falhe (por exemplo, testar o >= 7 antes do >= 5).

### No Código Prolog (Declarativo)

O objetivo é atingido através da satisfação de regras lógicas.
- **Como funciona:** Você não diz ao computador para "testar" a nota. Você define o que é um status de 'Aprovado' ou 'Reprovado'. Quando você chama status(Nota, Resultado), o motor do Prolog tenta "casar" o valor da nota com uma das definições (regras) que você escreveu.
- **Lógica:** É uma declaração de verdade. Se a nota for 4, o sistema ignora as regras que não são verdadeiras para esse valor e retorna apenas a que faz sentido lógico. O foco é na relação entre o dado e sua classificação.

### Resumo 
- **No Java:** ele toma uma ação de acordo com a nota.
- **No Prolog:** ele relaciona a nota com um resultado já existente nas regras.