package kr.bora.api.integrate.exception;

public class FileRequestFileNotException extends RuntimeException{
    public FileRequestFileNotException(){
        super();
    }
    public FileRequestFileNotException(String msg) {
        super(msg);
    }

}
