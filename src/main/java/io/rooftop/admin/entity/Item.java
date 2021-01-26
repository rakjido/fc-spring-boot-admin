package io.rooftop.admin.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"orderDetailList"})
@Entity
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private Integer price;

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
