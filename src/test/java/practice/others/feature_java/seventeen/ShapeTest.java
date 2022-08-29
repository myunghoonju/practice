package practice.others.feature_java.seventeen;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import practice.others.sealed.Rectangle;
import practice.others.sealed.Shape;
import practice.others.sealed.Square;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ShapeTest {

    @Test
    void sealedClassTest() {
        Square square = new Square(2, 3);
        Rectangle rectangle = new Rectangle(1, 2);

        assertThat(square.getPerimeter()).isEqualTo(1);
        assertThat(rectangle.getPerimeter()).isEqualTo(2);
    }

    @Test
    void sealedClassTypeTest() {
        Shape square2 = new Square(4, 5);
        assertThat(square2.getEdge1()).isEqualTo(4);
    }
}