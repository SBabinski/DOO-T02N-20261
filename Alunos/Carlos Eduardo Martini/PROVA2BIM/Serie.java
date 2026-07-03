public class Serie {
    private String nomeSerie;
    private String idioma;
    private String genero;   
    private String notGeral;   
    private String estado;
    private String emissora;
    private String datEstre;
    private String datTermi;
    private String urlPoster;  

    public Serie (String nomeSerie, String idioma, String genero, String notGeral, String estado, String emissora, String datEstre, String datTermi, String urlPoster){
        this.nomeSerie = nomeSerie;
        this.idioma = idioma;
        this.genero = genero;
        this.notGeral = notGeral;
        this.estado = estado;
        this.emissora = emissora;
        this.datEstre = datEstre;
        this.datTermi = datTermi;
        this.urlPoster = urlPoster; 
    }

    public String getNomeSerie() { return nomeSerie; }
    public String getIdioma() { return idioma; }
    public String getGeneros() { return genero; }     
    public String getNotGeral() { return notGeral; }   
    public String getEstado() { return estado; }
    public String getEmissora() { return emissora; }
    public String getDataEstreia() { return datEstre; }
    public String getDataTermino() { return datTermi; }
    public String getUrlPoster() { return urlPoster; } 

    public void exibirSerie(){
        System.out.println("nome da série: " +nomeSerie);
        System.out.println("idioma da série: " +idioma);
        System.out.println("genero da série: " +genero);
        System.out.println("nota geral: " +notGeral);
        System.out.println("estado da série: " +estado);
        System.out.println("emissora da série: " +emissora);
        System.out.println("estreia: " +datEstre);
        System.out.println("termino: " +datTermi);
        System.out.println("url do poster: " +urlPoster);
        System.out.println("---------------------------------");
    }
}