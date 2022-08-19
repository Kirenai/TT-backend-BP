package me.revilla.fullstackbp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Movement
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
@Table(name = "movements")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id", nullable = false)
    private Long movementId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "movement_type")
    private String movementType;

    @Column(name = "value")
    private Integer value;

    @Column(name = "balance")
    private Integer balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}