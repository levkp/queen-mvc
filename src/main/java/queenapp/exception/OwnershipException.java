package queenapp.exception;

import queenapp.domain.Song;

@SuppressWarnings("rawtypes")
public class OwnershipException extends RuntimeException {
    public OwnershipException(Class c, int id) {
        super(String.format("Permission denied to update %s with id %d, because you're not the owner of this object %s",
                c.getSimpleName(), id, c.equals(Song.class) ? "or the album it belongs to" : ""));
    }
}
