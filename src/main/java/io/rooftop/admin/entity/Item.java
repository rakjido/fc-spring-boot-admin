package io.rooftop.admin.entity;

import io.rooftop.admin.dto.ItemStatus;
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
@ToString(exclude = {"orderDetailList"})
@Entity
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    private String name;

    private String title;

    private String content;

    private BigDecimal price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    // Item 1 : N OrderDetail
    @OneToMany(mappedBy = "item")
    private List<OrderDetail> orderDetailList;

    // Item N : 1 Partner
    @ManyToOne(fetch = FetchType.LAZY)
    private Partner partner;
}
