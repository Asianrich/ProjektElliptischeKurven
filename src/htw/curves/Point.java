package htw.curves;

public interface Point {
    public Point add(Point p);
    public Point mul(Point p);
    public Point sub(Point p);
    public String toString();
    public boolean equals(Point p);
}
