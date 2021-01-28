package io.rooftop.admin.network.request;

import io.rooftop.admin.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserApiRequestDto {

    private Long id;

    private String account;

    private String password;

    private String email;

    private String phoneNumber;

    private UserStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

}
