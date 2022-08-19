package me.revilla.fullstackbp.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Account
 *
 * @author Revilla Pool
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(name = "unq_account_number", columnNames = {"account_number"})
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "initial_balance")
    private Integer initialBalance;

    @Column(name = "state")
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}