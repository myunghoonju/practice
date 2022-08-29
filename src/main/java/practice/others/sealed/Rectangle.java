package practice.others.sealed;

public final class Rectangle extends Shape implements ShapeService {

    public Rectangle(int edge1, int edge2) {
        super(edge1, edge2);
    }

    @Override
    public int getPerimeter() {
        return 2;
    }
}
