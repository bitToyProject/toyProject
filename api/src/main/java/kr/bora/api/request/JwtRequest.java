package kr.bora.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 263011851808996064L;
    private String username;
    private String password;
}
