package exercise;

public class Segment {
    private Point begin;
    private Point end;

    public Segment(Point begin, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getBeginPoint() {
        return this.begin;
    }

    public Point getEndPoint() {
        return this.end;
    }

    public Point getMidPoint() {
        var midX = (start.getX + end.getX) / 2;
        var midY = (start.getY + end.getY) / 2;
        return new Point(midX, midY);
    }
}
