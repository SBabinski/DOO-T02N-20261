public class Cliente {
    private String strNomeCliente;
    private String strCpfCliente;
    private String strCnhCliente;

    public Cliente(String pNome, String pCpf, String pCnh) {
        this.strNomeCliente = pNome;
        this.strCpfCliente = pCpf;
        this.strCnhCliente = pCnh;
    }



    public String getStrCnhCliente() {
        return strCnhCliente;
    }

    public String getStrCpfCliente() {
        return strCpfCliente;
    }

    public String getStrNomeCliente() {
        return strNomeCliente;
    }

    public void setStrCnhCliente(String strCnhCliente) {
        this.strCnhCliente = strCnhCliente;
    }

    public void setStrCpfCliente(String strCpfCliente) {
        this.strCpfCliente = strCpfCliente;
    }

    public void setStrNomeCliente(String strNomeCliente) {
        this.strNomeCliente = strNomeCliente;
    }

    public String ApresentaCliente(String pCliente, String pCpf, String pCnh){
        return String.format("Cliente: %s \nCPF: %s \nCNH: %s", pCliente, pCpf, pCnh);
    }
}
