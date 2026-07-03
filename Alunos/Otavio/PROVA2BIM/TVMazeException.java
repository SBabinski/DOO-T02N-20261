public class TVMazeException extends Exception {

    public TVMazeException(String mensagem) {
        super(mensagem);
    }

    public TVMazeException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}