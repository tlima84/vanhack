package com.vanhack.api.core.repository.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "vh_order")
public class Order {

    @Id
    @GeneratedValue(generator = "order_uuid_generator")
    @GenericGenerator(name = "order_uuid_generator", strategy = "uuid2")
    @Column(name = "order_id")
    private UUID id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ORDER_PRODUCT", joinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID"))
    private List<Product> productList;

    @Column(name = "order_status")
    private String status;

    @Column(name = "order_value")
    private Double value;

}
