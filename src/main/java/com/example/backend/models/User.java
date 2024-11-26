package com.example.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String password;

    // Quan hệ 1-1 với Candidate (có thể null nếu User là Company)
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "can_id", unique = true, nullable = true)
    private Candidate candidate;

    // Quan hệ 1-1 với Company (có thể null nếu User là Candidate)
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "comp_id", unique = true, nullable = true)
    private Company company;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))
    private Collection< Role > roles;

    public String getEmail() {
        if (candidate != null) {
            return candidate.getEmail();
        } else if (company != null) {
            return company.getEmail();
        }
        return null;
    }
}
