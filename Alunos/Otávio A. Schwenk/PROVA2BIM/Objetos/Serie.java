package Objetos;
import java.util.ArrayList;
import java.util.List;

public class Serie {

    private int id;
    private String nome;
    private double notaGeral;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String descricao;
    private String idioma;
    private String emissora;
    private List<String> generos;

    public Serie(){
    }

    public Serie(int id, String nome, double notaGeral, String estado, String dataEstreia){
        setId(id);
        setNome(nome);
        setNotaGeral(notaGeral);
        setEstado(estado);
        setDataEstreia(dataEstreia);
    }

    public Serie(int id, String nome, double notaGeral, String estado, String idioma, String dataEstreia,
                 String dataTermino, String emissora, String descricao, List<String> generos){
        setId(id);
        setNome(nome);
        setNotaGeral(notaGeral);
        setEstado(estado);
        setIdioma(idioma);
        setDataEstreia(dataEstreia);
        setDataTermino(dataTermino);
        setEmissora(emissora);
        setDescricao(descricao);
        setGeneros(generos);
    }

    public void setId(int id){
        if(id > 0){
            this.id = id;
        }else{
            this.id = 0;
        }
    }

    public void setNome(String nome){
        if(nome != null && !nome.isBlank()){
            this.nome = nome;
        }else{
            throw new IllegalArgumentException("Nome da série não localizado");
        }
    }

    public void setNotaGeral(double notaGeral){
        if(notaGeral >= 0 && notaGeral <= 10){
            this.notaGeral = notaGeral;
        }else{
            this.notaGeral = 0.0;
        }
    }

    public void setEstado(String estado){
        if(estado != null && !estado.isBlank()){
            this.estado = estado;
        }else{
            this.estado = "Desconhecido";
        }
    }

    public void setIdioma(String idioma){
        if(idioma != null && !idioma.isBlank()){
            this.idioma = idioma;
        }else{
            this.idioma = "Desconhecido";
        }
    }

    public void setDataEstreia(String dataEstreia){
        if(dataEstreia != null && !dataEstreia.isBlank()){
            this.dataEstreia = dataEstreia;
        }else{
            this.dataEstreia = null;
        }
    }

    public void setDataTermino(String dataTermino){
        if(dataTermino != null && !dataTermino.isBlank()){
            this.dataTermino = dataTermino;
        }else{
            this.dataTermino = null;
        }
    }

    public void setEmissora(String emissora){
        if(emissora != null && !emissora.isBlank()){
            this.emissora = emissora;
        }else{
            this.emissora = "Desconhecida";
        }
    }

    public void setDescricao(String descricao){
        if(descricao != null && !descricao.isBlank()){
            this.descricao = descricao;
        }else{
            this.descricao = "Sem descrição";
        }
    }

    public void setGeneros(List<String> generos){
        if(generos != null){
            this.generos = generos;
        }else{
            this.generos = new ArrayList<>();
        }
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public double getNotaGeral(){
        return notaGeral;
    }

    public String getEstado(){
        return estado;
    }

    public String getIdioma(){
        return idioma;
    }

    public String getDataEstreia(){
        return dataEstreia;
    }

    public String getDataTermino(){
        return dataTermino;
    }

    public String getEmissora(){
        return emissora;
    }

    public String getDescricao(){
        return descricao;
    }

    public List<String> getGeneros(){
        return generos;
    }
}