package kr.bora.api.user.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "refresh_token")
@Entity
public class RefreshToken {

    @Id
    @Column(name="refresh_key")
    private String key;
    @Column(name="refresh_value")
    private String value;

    private String username;

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }

}
