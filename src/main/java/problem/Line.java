package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

/**
 * Класс отрезка
 */
public class Line {
    /**
     * x - координата начала отрезка
     */
    double x;
    /**
     * y - координата начала отрезка
     */
    double y;
    /**
     * x1 - координата конца отрезка
     */
    double x1;
    /**
     * y1 - координата конца отрезка
     */
    double y1;
    /**
     * r - длина отрезка
     */
    double r;
    /**
     * Конструктор отрезка
     */
    Line(double x, double y, double x1, double y1) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.r = Math.sqrt(((x1 - x)*(x1 - x) + (y1 - y)*(y1 - y)));
    }

    /**
     * Рисование отрезка (прямой)
     *
     * @param gl переменная OpenGl для рисования
     */
    void render(GL2 gl) {
        gl.glLineWidth(5);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3d(1.0, 0.5, 0.0);
        gl.glVertex2d(x, y);
        gl.glColor3d(0.5, 0.0, 1.0);
        gl.glVertex2d(x1, y1);
        gl.glEnd();
    }
}
