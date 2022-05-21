package spellboard.api.board;

import java.util.Collection;
import java.util.Optional;

public interface Board {

    Collection<Tile> tiles();

    Optional<Tile> tileAt(int x, int y);

    default Optional<Tile> tileAt(Point point) {
        return this.tileAt(point.x(), point.y());
    }

}
