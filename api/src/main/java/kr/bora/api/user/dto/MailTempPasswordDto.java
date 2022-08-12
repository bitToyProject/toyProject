package kr.bora.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailTempPasswordDto {

    private String toAddress;
    private String title;
    private String message;
    private String fromAddress;
}
