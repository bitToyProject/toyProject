package kr.bora.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -868765630229614668L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}