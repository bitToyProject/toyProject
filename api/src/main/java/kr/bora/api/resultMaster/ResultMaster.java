package kr.bora.api.resultMaster;


import lombok.Builder;

public class ResultMaster {
    private String resultCode;
    private String resultMessage;
    private String resultOutput;

    @Builder
    public ResultMaster of(String resultCode, String resultMessage, String resultOutput){
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultOutput = resultOutput;
    }
}
