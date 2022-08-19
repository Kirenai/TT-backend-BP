package me.revilla.fullstackbp.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Customer
 *
 * @author Revilla Pool
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "password")
    private String password;

    @Column(name = "state")
    private Boolean state;

    @OneToOne(orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "customer_id")
    private Person person;

}