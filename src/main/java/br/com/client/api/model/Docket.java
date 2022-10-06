package br.com.client.api.model;

import br.com.client.api.generic.GenericEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "dockets")
public class Docket implements Serializable, GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(name = "theme", nullable = false)
    private String theme;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docket")
    private Set<Session> sessions = new HashSet<>();

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Docket docket = (Docket) o;
        return id != null && Objects.equals(id, docket.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
