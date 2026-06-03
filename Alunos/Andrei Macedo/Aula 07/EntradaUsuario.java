import java.util.Scanner;

public class EntradaUsuario {

    private final Scanner leia;
    private ProcessaPedido proc = new ProcessaPedido();

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
        System.out.println("Digite o estado em que a unidade fica:");
        String estado = leia.nextLine();
        System.out.println("Digite a cidade em que a unidade fica:");
        String cidade = leia.nextLine();
        System.out.println("Digite o bairro em que a unidade fica:");
        String bairro = leia.nextLine();
        System.out.println("Digite o número da unidade:");
        int num = leia.nextInt();
        leia.nextLine();
        System.out.println("Digite um complemento:");
        String complemento = leia.nextLine();
        proc.CadastrarLoja(nome, razao, cnpj, estado, cidade, bairro, num, complemento);
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
            System.out.println("[7] Para criar um pedido.");
            System.out.println("[8] Para sair.");
            op = leia.nextInt();
            leia.nextLine();
            validar_escolha(op);
        }while (op != 8);
        
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
                System.out.println("Digite o salário base:");
                double salario = leia.nextDouble();
                System.out.println("Agora vamos adicionar um endereço:");
                System.out.println("Digite o estado em que o vendedor mora:");
                leia.nextLine();
                String estado = leia.nextLine();
                System.out.println("Digite a cidade:");
                String cidade = leia.nextLine();
                System.out.println("Digite o bairro:");
                String bairro = leia.nextLine();
                System.out.println("Digite o número:");
                int num = leia.nextInt();
                leia.nextLine();
                System.out.println("Digite um complemento:");
                String complemento = leia.nextLine();
                proc.CadastrarFunc(nome, idade, salario, estado, cidade, bairro, num, complemento);
                break;

            case 5:
                System.out.println("Para cadastrar um cliente digite seu nome:");
                String nome_cliente = leia.nextLine();
                System.out.println("Digite a idade:");
                int idade_cliente = leia.nextInt();
                leia.nextLine();
                System.out.println("Digite o CPF:");
                String cpf = leia.nextLine();
                leia.nextLine();
                System.out.println("Agora vamos adicionar um endereço:");
                System.out.println("Digite o estado em que o cliente mora:");
                String estadoCliente = leia.nextLine();
                System.out.println("Digite a cidade:");
                String cidadeCliente = leia.nextLine();
                System.out.println("Digite o bairro:");
                String bairroCliente = leia.nextLine();
                System.out.println("Digite o número:");
                int numCliente = leia.nextInt();
                leia.nextLine();
                System.out.println("Digite um complemento:");
                String complementoCliente = leia.nextLine();
                proc.CadastrarCliente(nome_cliente, idade_cliente, cpf, estadoCliente, cidadeCliente, bairroCliente, numCliente, 
                complementoCliente);
                break;

            case 6:

                proc.chamar_listas();
                break;

            case 7:
                System.out.println("Digite o ID do pedido:");
                int id = leia.nextInt();
                System.out.println("Digite a data de criação do pedido, digite dia, mês e depois ano:");
                int diaCriacao = leia.nextInt();
                int mesCriacao = leia.nextInt();
                int anoCriacao = leia.nextInt();
                System.out.println("Digite a data de pagamento do pedido, digite dia, mês e depois ano:");
                int diaPagamento = leia.nextInt();
                int mesPagamento = leia.nextInt();
                int anoPagamento = leia.nextInt();
                System.out.println("Digite a data de vencimento do pedido, digite dia, mês e depois ano:");
                int diaVencimento = leia.nextInt();
                int mesVencimento = leia.nextInt();
                int anoVencimento = leia.nextInt();
                System.out.println("Selecione o vendedor e cliente pelo número: ");
                proc.chamar_listas();
                System.out.println("Selecione o vendedor: ");
                int escolhaVendedor = leia.nextInt();
                System.out.println("Selecione o cliente: ");
                int escolhaCliente = leia.nextInt();
                Pedido.exibir_itens(); 
                System.out.println("Escolha o produto a ser vendido:");
                int pedidoEscolhido = leia.nextInt();
                System.out.println("Quantidade de itens:");
                int qntd = leia.nextInt();   
                proc.processar(id, diaCriacao, mesCriacao, anoCriacao, diaPagamento, mesPagamento, anoPagamento, diaVencimento,
                mesVencimento, anoVencimento, escolhaVendedor, escolhaCliente, pedidoEscolhido, qntd);
                break;

            case 8:
                System.out.println("Obrigado por usar o sistema.");
                break;
            
            case 9:
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
