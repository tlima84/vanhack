package com.vanhack.api.core.repository.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "vh_product")
public class Product {

    @Id
    @GeneratedValue(generator = "product_uuid_generator")
    @GenericGenerator(name = "product_uuid_generator", strategy = "uuid2")
    private UUID id;

    @Column
    private String slug;

    @Column
    private String name;
    //TODO change to bigdecimal to next step of mvp!
    @Column
    private Double value;

}
