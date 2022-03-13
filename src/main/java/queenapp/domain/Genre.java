package queenapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public enum Genre {
    ART_ROCK,
    AVANT_POP,
    GLAM_ROCK,
    HARD_ROCK,
    HEAVY_METAL,
    OPERA,
    POWER_POP,
    PROGRESSIVE_ROCK,
    SOFT_ROCK;

    @Id
    @Column
    public final int id =  ordinal();

    @Column(nullable = false, unique = true)
    public final String name = readable();

    public String readable() {
        return toString().charAt(0) + toString().toLowerCase().replace('_', ' ').substring(1);
    }
}
