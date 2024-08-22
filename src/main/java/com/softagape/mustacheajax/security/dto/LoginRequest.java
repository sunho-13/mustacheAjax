package com.softagape.mustacheajax.security.dto;

import com.softagape.mustacheajax.commons.dto.BaseNullRequest;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest extends BaseNullRequest {
    @Size(min = 8, max = 10, message = "�α���ID�� 8~10�� �̾�� �մϴ�.")
    private String loginId;
    @Size(min = 8, max = 20, message = "��ȣ�� 8~20�� �̾�� �մϴ�.")
    private String password;
}
