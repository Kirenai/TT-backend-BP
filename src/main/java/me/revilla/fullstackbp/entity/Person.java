package me.revilla.fullstackbp.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Person
 *
 * @author Revilla Pool
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons", uniqueConstraints = {
        @UniqueConstraint(name = "unq_identification", columnNames = {"identification"})
})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "identification")
    private String identification;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

}