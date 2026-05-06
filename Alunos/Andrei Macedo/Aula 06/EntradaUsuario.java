import java.util.Scanner;

public class EntradaUsuario {

    private final Scanner leia;
    Processamento proc = new Processamento();

    public EntradaUsuario(){
        this.leia = new Scanner(System.in);
    }

    public void chamar_menu(){
        System.out.println("Seja bem-vindo! Vamos cadastrar sua loja.");
        System.out.println("Digite o nome da loja:");
        String nome = leia.nextLine();
        System.out.println("Digite a razão social:");
        String razao = leia.nextLine();
        System.out.println("Digite o CNPJ da loja:");
        String cnpj = leia.nextLine();
        System.out.println("Digite o endereço da loja:");
        String endereco = leia.nextLine();
        proc.CadastrarLoja(nome, razao, cnpj, endereco);
        System.out.println("Loja cadastrada! Boas vendas.");

        int op = 0;
         do{
            System.out.println(" === Digite a opção desejada: === ");
            System.out.println("[1] Para realizar venda.");
            System.out.println("[2] Mostrar histórico de vendas.");
            System.out.println("[3] Filtrar data.");
            System.out.println("[4] Cadastrar funcionário.");
            System.out.println("[5] Cadastrar cliente.");
            System.out.println("[6] Mostrar funcionários e clientes.");
            System.out.println("[7] Para sair.");
            op = leia.nextInt();
            leia.nextLine();
            validar_escolha(op);
        }while (op != 7);
        
        fecharScanner();
    }

    public void validar_escolha(int op){
        switch (op) {
            case 1:
                System.out.println("Digite a Quantidade de plantas à serem vendidas:");
                int quantidade_venda = leia.nextInt();
                System.out.println("Digite o valor unitário da planta:");
                double valor_unitario = leia.nextDouble();
                System.out.println("Valor da compra sem desconto aplicado:" + quantidade_venda * valor_unitario);
                System.out.println("Digite o valor pago pelo cliente:");
                double pagamento = leia.nextDouble();
                System.out.println("Agora vamos salvar a data da compra!");
                System.out.println("Para isso, precisamos que você digite o dia da compra:");
                int dia = leia.nextInt();
                System.out.println("Agora o mês:");
                int mes = leia.nextInt();
                System.out.println("Por fim o ano:");
                int ano = leia.nextInt();
                proc.realizar_venda(quantidade_venda, valor_unitario, pagamento, ano, mes, dia);
                break;
            
            case 2:
                proc.mostrar_historico();
                break;

            case 3:
                System.out.println("Para filtrar por data, precisamos que você digite o dia da compra:");
                int Dia = leia.nextInt();
                System.out.println("Agora o mês:");
                int Mes = leia.nextInt();
                System.out.println("Por fim o ano:");
                int Ano = leia.nextInt();
                proc.ProcessarData(Ano, Mes, Dia);
                break;

            case 4:
                System.out.println("Para cadastrar um funcionário digite seu nome:");
                String nome = leia.nextLine();
                System.out.println("Digite a idade:");
                int idade = leia.nextInt();
                leia.nextLine();
                System.out.println("Digite o nome da loja:");
                String nome_loja = leia.nextLine();
                System.out.println("Digite o endereço do funcionário:");
                String endereco = leia.nextLine();
                System.out.println("Digite o salário base:");
                double salario = leia.nextDouble();
                proc.CadastrarFunc(nome, idade, nome_loja, endereco, salario);
                break;

            case 5:
                System.out.println("Para cadastrar um cliente digite seu nome:");
                String nome_cliente = leia.nextLine();
                System.out.println("Digite a idade:");
                int idade_cliente = leia.nextInt();
                leia.nextLine();
                System.out.println("Digite o endereço do cliente:");
                String endereco_cliente = leia.nextLine();
                proc.CadastrarCliente(nome_cliente, idade_cliente, endereco_cliente);
                break;

            case 6:
                proc.chamar_listas();
                break;

            case 7:
                System.out.println("Obrigado por usar o sistema.");
                break;

            default:
                System.out.println("Digite uma opção válida!");
                break;
        }
    }

    public void fecharScanner(){
        leia.close();
    }
}
