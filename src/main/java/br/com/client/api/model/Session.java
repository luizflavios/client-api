package br.com.client.api.model;

import br.com.client.api.generic.GenericEntity;
import lombok.*;
import org.hibernate.Hibernate;

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
@Table(name = "sessions")
public class Session implements Serializable, GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "docket_id", nullable = false)
    private Docket docket;

    @Column(name = "start_of_vote", nullable = false)
    private LocalDateTime startOfVote;

    @Column(name = "end_of_vote", nullable = false)
    private LocalDateTime endOfVote;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
    private Set<Vote> votes = new HashSet<>();

    @Column(nullable = false)
    private Boolean enabled;

    @Transient
    private Boolean disableLastSessionIfExists;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Session session = (Session) o;
        return id != null && Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
