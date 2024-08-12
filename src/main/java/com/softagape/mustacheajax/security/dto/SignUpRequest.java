package com.softagape.mustacheajax.security.dto;

import com.softagape.mustacheajax.member.IMember;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest implements IMember {
    private Long id;
    private String name;
    private String loginId;
    private String password;
    private String email;
    private String role;
}
