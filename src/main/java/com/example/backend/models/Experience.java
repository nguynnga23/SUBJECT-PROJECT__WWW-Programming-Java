package com.example.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id", nullable = false)
    private long id;
    @Column(name = "to_date")

    private LocalDate toDate;
    @Column(name = "from_date")

    private LocalDate fromDate;
    @Column(name = "company")

    private String companyName;
    @Column(name = "role")
    private String role;
    @Column(name = "work_desc")

    private String workDescription;

    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate can;

}
