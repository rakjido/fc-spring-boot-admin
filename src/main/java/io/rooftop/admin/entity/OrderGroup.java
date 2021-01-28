package io.rooftop.admin.entity;

import io.rooftop.admin.dto.OrderType;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class OrderGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    private String revAddress;

    private String revName;

//    카드/현금
    private String paymentType;

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

//    OderGroup N : 1 User
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //    OrderGroup 1 : N OrderDetail
    @OneToMany(mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}
