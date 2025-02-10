package exercise;

public class Segment {
    private Point begin;
    private Point end;

    public Segment(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Point getBeginPoint() {
        return this.begin;
    }

    public Point getEndPoint() {
        return this.end;
    }

    public Point getMidPoint() {
        var midX = (begin.getX() + end.getX()) / 2;
        var midY = (begin.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }
}
