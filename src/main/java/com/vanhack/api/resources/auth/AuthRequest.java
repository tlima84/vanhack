package com.vanhack.api.resources.auth;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AuthRequest {
    private String userName;
    private String passWord;
}
