package queenapp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode
public abstract class QueenEntity {
    @Id
    @Getter @Setter
    @SequenceGenerator(name = "entityPkSeq", sequenceName = "entity_pk_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entityPkSeq")
    protected int id;

    @Getter @Setter
    @Column(length = 30, nullable = false, unique = true)
    protected String title;

    @Getter @Setter
    @Column(length = 5000)
    protected String description;

    // Todo: should this be optional?
    @Getter @Setter
    @ManyToOne @JoinColumn(name = "owner_id")
    protected QueenUser owner;
}
