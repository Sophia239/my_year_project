package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.Random;

/**
 * Класс окружности
 */
public class Circle {
    /**
     * x - координата центра
     */
    double x;
    /**
     * y - координата центра
     */
    double y;
    /**
     * x1 - координата точки на окружности
     */
    double x1;
    /**
     * y1 - координата точки на окружности
     */
    double y1;
    /**
     * r - радиус окружности
     */
    double r;
    /**
     * Конструктор окружности
     */
    Circle(double x, double y, double x1, double y1) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.r = Math.sqrt(((x1 - x)*(x1 - x) + (y1 - y)*(y1 - y)));
    }

    /**
     * Получить случайную окружность
     *
     * @return случайная окружность
     */

    static Circle getRandomCircle() {
        Random r = new Random();
        double nx = (double) r.nextInt(50) / 25 - 1;
        double ny = (double) r.nextInt(50) / 25 - 1;
        double nx1 = (double) r.nextInt(50) / 25 - 1;
        double ny1 = (double) r.nextInt(50) / 25 - 1;
        return new Circle(nx, ny, nx1, ny1);
    }

    /**
     * Рисование окружности
     *
     * @param gl переменная OpenGl для рисования
     */

    void render(GL2 gl) {
        gl.glColor3f(0.0f,1.0f,0.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        float a;
        for(int i = 0; i < 50; i++) {
            a = (float)i / 50.0f * 3.1415f * 2.0f;
            gl.glVertex2f((float)x + (float)Math.cos(a) * (float)r, (float)y + (float)Math.sin(a) * (float)r);
        }
        gl.glEnd();
    }
}
