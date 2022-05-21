package spellboard.api.board;

import spellboard.api.Owned;
import spellboard.api.Targetable;

import java.util.Optional;

public interface Tile extends Targetable, Owned {

    Optional<Tile> adjacent(Direction direction);

    Point pos();

}
