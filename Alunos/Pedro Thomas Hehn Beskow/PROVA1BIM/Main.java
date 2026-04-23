import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final Scanner objScan = new Scanner(System.in);
    private static final List<Locacao> lstLocacao = new ArrayList<>();
    private static final List<Cliente> lstClientes = new ArrayList<>();
    private static final List<Veiculo> lstVeiculo = new ArrayList<>();
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        
        int intOpcao;
        do {
            ExibirMenu();
            System.out.println("Opção:");
            intOpcao = objScan.nextInt();
            objScan.nextLine();
            System.out.println();

            switch (intOpcao) {
                case 1 -> CadastrarCliente();
                case 2 -> CadastrarVeiculo();
                case 3 -> CadastrarLocacao();
                case 4 -> DevolverLocacao();
                case 5 -> ListarLocacoesAtivas();
                case 6 -> FazerDemonstracao();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.\n");
            }
        } while (intOpcao != 0);

        objScan.close();

    }

    public static void CadastrarCliente(){
        System.out.println("Insira o nome do Cliente: ");
        String strNomeCLiente = objScan.nextLine();

        System.out.println("Insira o CPF do Cliente: ");
        String strCpfCliente = objScan.nextLine();
        if(strCpfCliente.length() != 11){
                System.out.println("CPF INVALIDO!");
                return;
        }

        System.out.println("Insira a CNH do CLiente: ");
        String strCnhCliente = objScan.nextLine(); 
        if(strCnhCliente.length() != 9){
                System.out.println("CNH INVALIDA!");
                return;
        }

        Cliente objCliente = new Cliente(strNomeCLiente, strCpfCliente, strCnhCliente);

        lstClientes.add(objCliente);

        System.out.println("Cliente Cadastrado com Sucesso!");
    }

     public static void CadastrarVeiculo(){
        System.out.println("É um carro ou uma moto? 1 - carro / 2 - moto");
        int intOpcao = objScan.nextInt();

        objScan.nextLine();

        switch (intOpcao){
        case 1 -> CadastrarCarro();
        case 2 -> CadastrarMoto();
        default -> System.out.println("Opção inválida. Tente novamente.\n"); 
        }
    }

    public static void CadastrarMoto(){
        System.out.println("Insira a placa da Moto: ");
        String strPlaca = objScan.nextLine();

        System.out.println("Insira o Valor da Diaria da Moto: ");
        float fltValor;
        if (objScan.hasNextFloat()) {
            fltValor = objScan.nextFloat();
        } else {
            System.out.println("Entrada inválida.");
            return;
        }

        objScan.nextLine();

        System.out.println("insira a cilindrada da moto:");
        String strCilindrada = objScan.nextLine();

        Moto objMoto = new Moto(strPlaca, fltValor, strCilindrada);

        lstVeiculo.add(objMoto);

        System.out.println("Moto Cadastrada com Sucesso!");
    }

    public static void CadastrarCarro(){
        System.out.println("Insira a placa do Carro: ");
        String strPlaca = objScan.nextLine();

        System.out.println("Insira o Valor da Diaria do carro: ");
        float fltValor;
        if (objScan.hasNextFloat()) {
            fltValor = objScan.nextFloat();
        } else {
            System.out.println("Entrada inválida.");
            return;
        }

        objScan.nextLine();

        System.out.println("O Carro tem ar? 1 - Sim / 2 - Não: ");
        String strTemAr = objScan.nextLine(); 
        if(!"1".equals(strTemAr) && !"2".equals(strTemAr)){
                System.out.println("Entrada inválida.");
                return;
        }

        Carro objCarro = new Carro(strPlaca, fltValor, ("1".equals(strTemAr) ? true : false));

        lstVeiculo.add(objCarro);

        System.out.println("Carro Cadastrado com Sucesso!");
    }

    public static void CadastrarLocacao(){
        int intOpcaoCliente;
        int intOpcaoVeiculo;       
        System.out.println("Selecione o Cliente que deseja Alocar um veiculo:");
        int intContador = 0;
        for (Cliente objCliente : lstClientes) {
            System.out.println(intContador + " - " + objCliente.getStrNomeCliente() + " - CPF: " + objCliente.getStrCpfCliente());
            intContador++;
        }
        System.out.print("Opção: ");
        intOpcaoCliente = objScan.nextInt();

        if(intOpcaoCliente > intContador || intOpcaoCliente < 0){
                System.out.println("Opção invalida!");
                return;
        }

        objScan.nextLine();

        intContador = 0;
        System.out.println("Selecione o Veiculo:");
        for (Veiculo objVeiculo : lstVeiculo) {
            if(objVeiculo instanceof Carro){
                System.out.println(intContador + " - Carro / Placa:" + objVeiculo.getStrPlaca() + " / Valor Diaria: " + objVeiculo.getFltValorDiaria() + " / Ar Condicionado: " + ((Carro) objVeiculo).getBlnTemAr());
            }
            else{
                System.out.println(intContador + " - Moto / Placa:" + objVeiculo.getStrPlaca() + " / Valor Diaria: " + objVeiculo.getFltValorDiaria() + " / Cilindrada: " + ((Moto) objVeiculo).getStrCilindrada());
            }

            intContador++;
        }
        intOpcaoVeiculo = objScan.nextInt();

        if(intOpcaoVeiculo > intContador || intOpcaoVeiculo < 0){
                System.out.println("Opção invalida!");
                return;
        }

        objScan.nextLine();
        
        System.out.println("Daqui quantos dias o cliente vai ter que efetuar a devolução do carro?");
        int intDevolucao = objScan.nextInt();
        
        LocalDate dtaHoje = LocalDate.now(); 

        LocalDate dtaDevolucao = dtaHoje.plusDays(intDevolucao); 

        Locacao objLocacao = new Locacao(lstClientes.get(intOpcaoCliente), lstVeiculo.get(intOpcaoVeiculo), dtaHoje, dtaDevolucao, true);   
        if(lstLocacao.size() == 10){
                System.out.println("Lista cheia no momento! Verifique as locações ativas");
        }
        else{
                lstLocacao.add(objLocacao); 
        }
    }

    public static void ListarLocacoesAtivas(){
        System.out.println("___Locações Ativas___");

        int intContador = 0;

        for (Locacao objLocacao : lstLocacao) {
            if(objLocacao.getBlnStatus()){
                System.out.println(intContador + " - " + objLocacao.ApresentaLocacao(objLocacao.getBlnStatus(), objLocacao.getDtaDataDevolucao(), objLocacao.getDtaDataRetirada(), objLocacao.getObjCliente(), objLocacao.getObjVeiculo()));
                intContador++;
            }
        }
    }

public static void DevolverLocacao(){
        System.out.println("___Locações Ativas___");

        // Verifica se há locações ativas antes de continuar
        long locacoesAtivas = lstLocacao.stream()
                .filter(Locacao::getBlnStatus)
                .count();

        if(locacoesAtivas == 0){
                System.out.println("Nenhuma locação ativa para devolver.");
                return;
        }

        ListarLocacoesAtivas();

        System.out.print("Selecione o número da locação para devolver: ");
        int intOpcao = objScan.nextInt();
        objScan.nextLine();

        // Valida se o índice existe e se a locação está ativa
        if(intOpcao < 0 || intOpcao >= lstLocacao.size()){
                System.out.println("Opção inválida!");
                return;
        }

        Locacao objLocacao = lstLocacao.get(intOpcao);

        if(!objLocacao.getBlnStatus()){
                System.out.println("Esta locação já foi encerrada!");
                return;
        }

        // Calcula o valor total com base nos dias reais de uso
        LocalDate dtaHoje = LocalDate.now();
        long lngDiasUsados = objLocacao.getDtaDataRetirada().until(dtaHoje, java.time.temporal.ChronoUnit.DAYS);

        // Garante mínimo de 1 dia cobrado
        if(lngDiasUsados < 1) lngDiasUsados = 1;

        float fltValorTotal = lngDiasUsados * objLocacao.getObjVeiculo().getFltValorDiaria();

        // Encerra a locação
        objLocacao.setBlnStatus(false);

        System.out.println("Locação encerrada com sucesso!");
        System.out.println("Cliente: " + objLocacao.getObjCliente().getStrNomeCliente());
        System.out.println("Veículo: " + objLocacao.getObjVeiculo().getStrPlaca());
        System.out.println("Dias utilizados: " + lngDiasUsados);
        System.out.printf("Valor total a pagar: R$ %.2f%n", fltValorTotal);
}

public static void FazerDemonstracao(){
    System.out.println("___Iniciando Demonstração___\n");

    Cliente objCliente1 = new Cliente("João Silva", "12345678901", "123456789");
    Cliente objCliente2 = new Cliente("Maria Souza", "98765432100", "987654321");
    lstClientes.add(objCliente1);
    lstClientes.add(objCliente2);
    System.out.println("Cliente 1 cadastrado: " + objCliente1.getStrNomeCliente());
    System.out.println("Cliente 2 cadastrado: " + objCliente2.getStrNomeCliente());

    Carro objCarro = new Carro("ABC-1234", 150.00f, true);
    Moto objMoto  = new Moto("XYZ-9876", 80.00f, "600cc");
    lstVeiculo.add(objCarro);
    lstVeiculo.add(objMoto);
    System.out.println("\nCarro cadastrado: Placa " + objCarro.getStrPlaca() + " | Diária R$ 150,00 | Ar: Sim");
    System.out.println("Moto cadastrada:  Placa " + objMoto.getStrPlaca()  + " | Diária R$  80,00 | Cilindrada: 600cc");

    // Cadastro de Locações
    LocalDate dtaHoje = LocalDate.now();

    Locacao objLocacao1 = new Locacao(objCliente1, objCarro, dtaHoje, dtaHoje.plusDays(5), true);
    Locacao objLocacao2 = new Locacao(objCliente2, objMoto,  dtaHoje, dtaHoje.plusDays(3), true);
    lstLocacao.add(objLocacao1);
    lstLocacao.add(objLocacao2);
    System.out.println("\nLocação 1: " + objCliente1.getStrNomeCliente() + " → Carro " + objCarro.getStrPlaca() + " por 5 dias");
    System.out.println("Locação 2: " + objCliente2.getStrNomeCliente() + " → Moto "  + objMoto.getStrPlaca()  + " por 3 dias");

    System.out.println("\n___Demonstração Concluída!___\n");
}

    public static void ExibirMenu(){
        System.out.println("______________Menu______________");
        System.out.println("| 1 - Cadastrar Cliente");
        System.out.println("| 2 - Cadastrar Veiculo");
        System.out.println("| 3 - Cadastrar Locação");
        System.out.println("| 4 - Devolução Locação");
        System.out.println("| 5 - Listar Locações Ativas");
        System.out.println("| 6 - Fazer Demonstração");
        System.out.println("________________________________");
    } 

    public static LocalDate InserirLocalDate (String pInput){
        try {
            // Tenta converter
            return LocalDate.parse(pInput, FMT);
        } catch (DateTimeParseException e) {
            // Se falhar, retorna null ou lança uma exceção personalizada
            System.out.println("Erro: A data '" + pInput + "' não está no formato correto dd/MM/yyyy ou é uma data inválida.");
            return null;
        }
    }

}