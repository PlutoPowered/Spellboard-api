package spellboard.api.board.shape;

import spellboard.api.board.Board;
import spellboard.api.board.Direction;
import spellboard.api.board.Point;
import spellboard.api.board.Tile;

import java.util.*;

public class CircleShape implements Shape {
    private int radius;
    private boolean filled;
    private BoundingBox box;

    public CircleShape(int radius, boolean filled) {
        this.radius = radius;
        this.filled = filled;
        this.box = new BoundingBox(new Point(-radius, -radius), new Point(radius, radius));
    }

    @Override
    public Shape facing(Direction direction) {
        return this;
    }

    @Override
    public Collection<Tile> at(Tile origin, Board board) {
        Set<Tile> tiles = new HashSet<>();

        int d = 3 - 2 * this.radius;
        int x = 0;
        int y = this.radius;
        int ox = origin.pos().x();
        int oy = origin.pos().y();


        do {
            if (this.filled) {
                lineDraw(ox + x, ox - x, oy + y, board, tiles);
                lineDraw(ox + x, ox - x, oy - y, board, tiles);

                lineDraw(ox + y, ox - y, oy + x, board, tiles);
                lineDraw(ox + y, ox - y, oy - x, board, tiles);

            } else {
                board.tileAt(ox + x, oy + y).ifPresent(tiles::add);
                board.tileAt(ox - x, oy + y).ifPresent(tiles::add);

                board.tileAt(ox + x, oy - y).ifPresent(tiles::add);
                board.tileAt(ox - x, oy - y).ifPresent(tiles::add);

                board.tileAt(ox + y, oy + x).ifPresent(tiles::add);
                board.tileAt(ox - y, oy + x).ifPresent(tiles::add);

                board.tileAt(ox - y, oy - x).ifPresent(tiles::add);
                board.tileAt(ox + y, oy - x).ifPresent(tiles::add);
            }

            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
            }
            x++;
        } while (x <= y);

        return tiles;
    }

    @Override
    public BoundingBox bounding() {
        return this.box;
    }

    private static void lineDraw(int x1, int x2, int y, Board board, Collection<Tile> tiles) {
        for (int x = Math.min(x1, x2); x < Math.max(x1, x2); x++) {
            board.tileAt(x, y).ifPresent(tiles::add);
        }
    }

}
