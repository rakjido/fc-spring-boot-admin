package io.rooftop.admin.entity;

import io.rooftop.admin.dto.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Accessors(chain = true)
@Entity
@ToString(exclude = {"orderGroup"})
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserStatus status ;

    private String account;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @OneToMany(mappedBy = "user")
    private List<OrderGroup> orderGroupList;
}
