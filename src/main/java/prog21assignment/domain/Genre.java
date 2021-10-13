package prog21assignment.domain;

public enum Genre {
    HARD_ROCK,
    HEAVY_METAL,
    PROGRESSIVE_ROCK,
    ART_ROCK,
    GLAM_ROCK,
    AVANT_POP,
    POWER_POP,
    SOFT_ROCK,
    OPERA;

    public String readable() {
        return toString().charAt(0) + toString().toLowerCase().replace('_', ' ').substring(1);
    }
}
