package spellboard.api.board.shape;

import spellboard.api.board.Board;
import spellboard.api.board.Direction;
import spellboard.api.board.Tile;

import java.util.Collection;

public interface Shape {

    Shape facing(Direction direction);

    Collection<Tile> at(Tile origin, Board board);

    BoundingBox bounding();

}
