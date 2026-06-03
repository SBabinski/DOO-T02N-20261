# Paradigmas Imperativo e Declarativo

Os paradigmas de programação representam diferentes formas de pensar e estruturar soluções computacionais. Entre eles, destacam-se o paradigma imperativo e o paradigma declarativo, que possuem abordagens bastante distintas na resolução de problemas.

O paradigma imperativo, utilizado em linguagens como Java, baseia-se na descrição explícita dos passos que o programa deve executar. Ou seja, o desenvolvedor define como o problema será resolvido, controlando o fluxo de execução por meio de estruturas como loops, condicionais e variáveis mutáveis.

Já o paradigma declarativo, comum em linguagens como Prolog, foca em descrever o que deve ser resolvido, sem especificar detalhadamente os passos. Nesse modelo, o programador define regras e fatos, e o próprio mecanismo da linguagem se encarrega de encontrar a solução por meio de inferência lógica.

------------------

# Comparação entre Java e Prolog

Código em Java (Imperativo)

O trecho fornecido em Java implementa um menu interativo utilizando um laço do-while. O funcionamento ocorre da seguinte forma:

- O programa exibe repetidamente um menu de opções ao usuário.
- Lê a opção digitada por meio de um scanner.
- Caso a opção seja diferente de 4, chama o método validarOpc(opc) para tratar a escolha.
- Caso seja 4, exibe a mensagem de saída.
- O laço continua até que o usuário escolha sair.

Neste caso, o código descreve passo a passo o fluxo de execução, controlando explicitamente:
- Entrada de dados
- Estrutura de repetição
- Condições de parada

Isso caracteriza claramente o paradigma imperativo, pois o programador define exatamente como o sistema deve operar.

public static void chamarMenu() {
		int opc=0;
		do {
			System.out.println("\n");
			System.out.println("[Menu]");
			System.out.println("[1] - Calcular Preço Total");
			System.out.println("[2] - Calcular Troco");
			System.out.println("[3] - Registro");
			System.out.println("[4] - Sair");
			System.out.println("Digite a Opção:");
			opc = scanner.nextInt();
			scanner.nextLine();
			if(opc != 4) {
				validarOpc(opc);
			} else {
				System.out.println("Saindo...");
			}
		} while (opc!=4);
	}

------------------

# Prolog (Declarativo)

Prolog:

menu :-
    write('\n[Menu]\n'),
    write('[1] - Calcular Preco Total\n'),
    write('[2] - Calcular Troco\n'),
    write('[3] - Registro\n'),
    write('[4] - Sair\n'),
    write('Digite a Opcao: '),
    read(Opc),
    processar(Opc).

processar(4) :-
    write('Saindo...').

processar(Opc) :-
    Opc \= 4,
    validar_opc(Opc),
    menu.