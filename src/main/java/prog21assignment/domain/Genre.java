package prog21assignment.domain;

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

    public String readable() {
        return toString().charAt(0) + toString().toLowerCase().replace('_', ' ').substring(1);
    }
}
