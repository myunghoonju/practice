package practice.others.sealed;

public sealed interface ShapeService permits Square, Rectangle {

    int getPerimeter();
}
