package spellboard.api.board.shape;

import spellboard.api.board.Point;

import java.util.Objects;

public class BoundingBox {
    private Point min;
    private Point max;
    private Point size;

    public BoundingBox(Point a, Point b) {
        this.min = new Point(Math.min(a.x(), b.x()), Math.min(a.y(), b.y()));
        this.max = new Point(Math.max(a.x(), b.x()), Math.max(a.y(), b.y()));
        this.size = new Point(max.x() - min.x(), max.y() - min.y());
    }

    public Point min() {
        return min;
    }

    public Point max() {
        return max;
    }

    public Point size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoundingBox that = (BoundingBox) o;
        return Objects.equals(min, that.min) && Objects.equals(max, that.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    @Override
    public String toString() {
        return "BoundingBox{" +
                "min=" + min +
                ", max=" + max +
                ", size=" + size +
                '}';
    }
}
