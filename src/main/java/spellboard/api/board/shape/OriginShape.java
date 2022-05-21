package spellboard.api.board.shape;

import spellboard.api.board.Board;
import spellboard.api.board.Direction;
import spellboard.api.board.Point;
import spellboard.api.board.Tile;

import java.util.Collection;
import java.util.List;

public class OriginShape implements Shape {
    private static final BoundingBox box = new BoundingBox(Point.ZERO, Point.ONE);

    @Override
    public Shape facing(Direction direction) {
        return this;
    }

    @Override
    public Collection<Tile> at(Tile origin, Board board) {
        return List.of(origin);
    }

    @Override
    public BoundingBox bounding() {
        return OriginShape.box;
    }
}
