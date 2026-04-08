public class Testes {
    public static void main(String[] args) {
        Lojas loja1 = new Lojas();
        loja1.setNomeFantasia("Lojinha");
        loja1.setRazaoSocial("LOJINHARAZAOSOCIAL");
        loja1.setCnpj("12345676543211");
        loja1.setCidade("Cafelandia");
        loja1.setBairro("Bairro nobre");
        loja1.setRua("Rua rapida");

        Lojas loja2 = new Lojas();
        loja2.setNomeFantasia("Lojão");
        loja2.setRazaoSocial("LOJÃORAZAOSOCIAL");
        loja2.setCnpj("19283740594837");
        loja2.setCidade("Cascavel");
        loja2.setBairro("Bairro pobre");
        loja2.setRua("Rua devagar");

       



        Vendedor vend1 = new Vendedor();
        vend1.setNome("Leo");
        vend1.setIdade(18);
        vend1.setLoja(loja2);
        vend1.setSalarioBase(1200);
        vend1.receberSalario(1200);
        vend1.receberSalario(1250);
        vend1.receberSalario(1300);
        vend1.apresentarse();
        System.out.println(vend1.calcularMediaSalarial());
        System.out.println(vend1.calcularBonusSalarial());
        loja2.vendedores.add(vend1);

        Vendedor vend2 = new Vendedor();
        vend2.setNome("Patrick");
        vend2.setIdade(22);
        vend2.setLoja(loja1);
        vend2.setSalarioBase(1500);
        vend2.receberSalario(1500);
        vend2.receberSalario(1550);
        vend2.receberSalario(1600);
        vend2.apresentarse();
        System.out.println(vend2.calcularMediaSalarial());
        System.out.println(vend2.calcularBonusSalarial());
        loja1.vendedores.add(vend2);
        
        Clientes cliente10 = new Clientes();
        cliente10.setNome("Eduardo");
        cliente10.setIdade(30);
        cliente10.setCidade("NY");
        cliente10.setBairro("bairro country");
        cliente10.setRua("rua da lapa");

        Clientes cliente20 = new Clientes();
        cliente20.setNome("Jemer");
        cliente20.setIdade(45);
        cliente20.setCidade("Fortaleza");
        cliente20.setBairro("Bairro cinema");
        cliente20.setRua("Rua abelha"); 

        loja1.clientes.add(cliente20);
        loja2.clientes.add(cliente10);

        
        Main.dadosLojas.add(loja1);
        Main.dadosLojas.add(loja2);
        Main.listarDados();
    }


    
}
