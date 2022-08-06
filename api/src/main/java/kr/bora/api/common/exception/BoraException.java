package kr.bora.api.common.exception;


public class BoraException extends RuntimeException {

    private final ErrorCode code;

    public BoraException(ErrorCode code, String message){
        super(code.getMessage() + ": "+message);
        this.code = code ;
    }
    public BoraException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }


    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public ErrorCode getCode(){
        return this.code;
    }
}
