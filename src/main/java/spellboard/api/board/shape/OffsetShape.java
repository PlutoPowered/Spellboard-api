package spellboard.api.board.shape;

import spellboard.api.board.Board;
import spellboard.api.board.Direction;
import spellboard.api.board.Point;
import spellboard.api.board.Tile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OffsetShape implements Shape {
    private Point[] offsets;
    private BoundingBox box;

    public OffsetShape(Collection<Point> offsets) {
        this.offsets = offsets.toArray(Point[]::new);
        int index = 0;

        int xmin = Integer.MAX_VALUE, ymin = Integer.MAX_VALUE, xmax = Integer.MIN_VALUE, ymax = Integer.MIN_VALUE;

        for (Point offset : offsets) {
            this.offsets[index++] = offset;

            xmin = Math.min(xmin, offset.x());
            ymin = Math.min(ymin, offset.y());

            xmax = Math.max(xmax, offset.x());
            ymax = Math.max(ymax, offset.y());
        }

        this.box = new BoundingBox(new Point(xmin, ymin), new Point(xmax, ymax));
    }

    public static OffsetShape from(String pattern, char origin, boolean includeOrigin) {
        Set<Point> offsets = new LinkedHashSet<>();
        String[] lines = pattern.split("\\r?\\n|\\r");
        int ox = 0, oy = 0;

        loop:
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                if (origin == lines[y].codePointAt(x)) {
                    ox = x;
                    oy = y;
                    break loop;
                }
            }
        }

        if (includeOrigin) {
            offsets.add(Point.ZERO);
        }

        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                if (!Character.isWhitespace(lines[y].codePointAt(x))) {
                    offsets.add(new Point(x - ox, y - oy));
                }
            }
        }

        return new OffsetShape(offsets);
    }

    public String format(char normal, char origin, boolean includeOrigin) {
        char[][] result = new char[this.box.size().y() + 1][this.box.size().x() + 1];
        for (char[] arr : result) {
            Arrays.fill(arr, ' ');
        }

        for (Point offset : this.offsets) {
            result[offset.y() - this.box.min().y()][offset.x() - this.box.min().x()] = offset.x() == 0 && offset.y() == 0 ? origin : normal;
        }

        if (includeOrigin) result[-this.box.min().y()][-this.box.min().x()] = origin;
        return Stream.of(result).map(String::new).reduce("", (a, b) -> a + System.lineSeparator() + b);
    }

    @Override
    public OffsetShape facing(Direction direction) {
        int rotations = Direction.NORTH.rotations(direction);
        List<Point> offsets = new ArrayList<>();
        for (Point offset : this.offsets) {
            int x = offset.x();
            int y = offset.y();
            for (int i = 0; i < rotations; i++) {
                int xt = y;
                int yt = -x;

                x = xt;
                y = yt;
            }
            offsets.add(new Point(x, y));
        }
        return new OffsetShape(offsets);
    }

    @Override
    public Collection<Tile> at(Tile origin, Board board) {
        Collection<Tile> tiles = new ArrayList<>();
        for (Point point : this.offsets) {
            board.tileAt(origin.pos().x() + point.x(), origin.pos().y() + point.y())
                    .ifPresent(tiles::add);
        }
        return tiles;
    }

    @Override
    public BoundingBox bounding() {
        return this.box;
    }
}
