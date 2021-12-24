package prog21assignment.domain;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id = ordinal();

    @Column(name = "name", nullable = false, unique = true)
    public final String name = readable();

    public String readable() {
        return toString().charAt(0) + toString().toLowerCase().replace('_', ' ').substring(1);
    }
}
