package spellboard.api.board;

public enum Direction {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    private Point direction;

    Direction(int x, int y) {
        this.direction = new Point(x, y);
    }

    public static Direction of(int x, int y) {
        x = signum(x);
        y = signum(y);

        for (Direction direction : values()) {
            if (x == direction.direction.x() && y == direction.direction.y()) {
                return direction;
            }
        }
        throw new IllegalStateException();
    }

    private static int signum(int a) {
        return (a < 0) ? -1 : ((a == 0) ? 0 : 1);
    }

    public int angle(Direction other) {
        return Math.abs(other.ordinal() - this.ordinal()) * 90;
    }

    public int rotations(Direction target) {
        int difference = target.ordinal() - this.ordinal();
        if (difference < 0) {
            return Direction.values().length + difference;
        } else {
            return difference;
        }
    }

    public Point vector() {
        return this.direction;
    }

    public Direction next() {
        return Direction.values()[rotated(this.ordinal() + 1)];
    }

    public Direction previous() {
        return Direction.values()[rotated(this.ordinal() - 1)];
    }

    public Direction relative(Direction from, Direction to) {
        int difference = to.ordinal() - from.ordinal();
        if (difference < 0) {
            Direction res = this;
            difference = -difference;
            for (int i = 0; i < difference; i++) {
                res = res.previous();
            }
            return res;
        } else if (difference > 0) {
            Direction res = this;
            for (int i = 0; i < difference; i++) {
                res = res.next();
            }
            return res;
        } else {
            return this;
        }
    }

    public Direction relativeNorth(Direction north) {
        return relative(Direction.NORTH, north);
    }

    private static int rotated(int ord) {
        if (ord < 0) {
            return rotated(Direction.values().length - ord);
        } else if (ord >= Direction.values().length) {
            return rotated(ord - Direction.values().length);
        } else {
            return ord;
        }
    }
}
