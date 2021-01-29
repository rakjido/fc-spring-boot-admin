package io.rooftop.admin.network.response;

import io.rooftop.admin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserOrderInfoApiResponseDto {
    private UserApiResponseDto userApiResponse;
}
