package io.rooftop.admin.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"itemList", "category"})
@Entity
public class Partner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;

    private String address;

    private String callCenter;

    private String partnerNumber;

    private String businessNumber;

    private String ceoName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

//   Partner N : 1 Category
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

//    Partner 1 : N Item
    @OneToMany(mappedBy = "partner")
    private List<Item> itemList;
}
