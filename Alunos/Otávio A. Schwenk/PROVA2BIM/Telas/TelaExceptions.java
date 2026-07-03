package Telas;

class SenhaDivergenteException extends Exception {
    public SenhaDivergenteException(){
        super("A senha está divergente do campo de confirmação");
    }
}
