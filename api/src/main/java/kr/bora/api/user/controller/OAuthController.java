package kr.bora.api.user.controller;

import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.socialAuth.domain.ProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2")
public class OAuthController {

    private final String REDIRECT_URI = "http://localhost:3000/oauth/redirect";


    @GetMapping("/authorization/{providerType}")
    public ResponseEntity<ApiResponse> oauthLogin(@PathVariable String providerType, @RequestParam(defaultValue = REDIRECT_URI) String params) {

        return ResponseEntity.ok(ApiResponse.success("providerType", providerType));
    }

}
