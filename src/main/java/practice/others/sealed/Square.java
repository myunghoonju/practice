package practice.others.sealed;

public final class Square extends Shape implements ShapeService {

    public Square(int edge1, int edge2) {
        super(edge1, edge2);
    }

    @Override
    public int getPerimeter() {
        return 1;
    }
}
