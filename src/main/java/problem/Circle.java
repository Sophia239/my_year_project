package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.vecmath.Vector2d;
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
     * цвет окружности
     */
    int color;
    /**
     * Конструктор окружности
     */
    Circle(double x, double y, double x1, double y1) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.r = Math.sqrt(((x1 - x)*(x1 - x) + (y1 - y)*(y1 - y)));
        this.color = 0;
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

    void render(GL2 gl, int color) {
        if (color == 0) gl.glColor3f(0.0f,1.0f,0.0f);
        else gl.glColor3f(0.0f,0.0f,1.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        float a;
        for(int i = 0; i < 50; i++) {
            a = (float)i / 50.0f * 3.1415f * 2.0f;
            gl.glVertex2f((float)x + (float)Math.cos(a) * (float)r, (float)y + (float)Math.sin(a) * (float)r);
        }
        gl.glEnd();
    }

    /*oCircle1 - coords and radius first circle
     *oCircle2 - coords and radius second circle
     *nMargin - tolerance of distance, i.e. if distance between border of circles between -nMargin/2 .. +nMargin/2
     *it counts as one point
     */
    static Line moGetCrossPoints(Circle oCircle1, Circle oCircle2)
    {
        int count = 0;
        Vector2d pos1;
        pos1 = null;
        Vector2d pos2;
        pos2 = null;
        double d = Math.sqrt(((oCircle2.x - oCircle1.x)*(oCircle2.x - oCircle1.x) + (oCircle2.y - oCircle1.y)*(oCircle2.y - oCircle1.y)));
        if (Math.abs(d - Math.abs(oCircle1.r - oCircle2.r)) < 0.00)
        {
            int nSign;
            if (oCircle1.r - oCircle2.r > 0) nSign = 1;
            else if(oCircle1.r - oCircle2.r < 0) nSign = -1;
            else nSign = 0;
            double nDelta = (d - Math.abs(oCircle1.r - oCircle2.r)) / 2;
            count = 1;
            pos1 = new Vector2d(oCircle1.x + (oCircle1.r - nDelta) * (oCircle2.x - oCircle1.x) / d * nSign,
                    oCircle1.y + (oCircle1.r - nDelta) * (oCircle2.y - oCircle1.y) / d * nSign);
        }
        else if (Math.abs(d - (oCircle1.r + oCircle2.r)) < 0.00)
        {
            count = 1;
            double nDelta = (d - Math.abs(oCircle1.r + oCircle2.r)) / 2;
            pos1 = new Vector2d(oCircle1.x + (oCircle1.r + nDelta) * (oCircle2.x - oCircle1.x) / d,
                    oCircle1.y + (oCircle1.r + nDelta) * (oCircle2.y - oCircle1.y) / d);
        }
        else if (d < oCircle1.r + oCircle2.r && d > Math.abs(oCircle1.r - oCircle2.r))
        {
            count = 2;
            double b = (oCircle2.r * oCircle2.r - oCircle1.r * oCircle1.r + d * d) / (2 * d);
            double a = d - b;
            double h = Math.sqrt(oCircle1.r * oCircle1.r - a * a);
            Vector2d oPos0 = new Vector2d(oCircle1.x + a / d * (oCircle2.x - oCircle1.x),
                    oCircle1.y + a / d * (oCircle2.y - oCircle1.y));
            pos1 = new Vector2d(oPos0.x + h / d * (oCircle2.y - oCircle1.y),
                    oPos0.y - h / d * (oCircle2.x - oCircle1.x));
            pos2 = new Vector2d(oPos0.x - h / d * (oCircle2.y - oCircle1.y),
                    oPos0.y + h / d * (oCircle2.x - oCircle1.x));
        }
        if (count == 2) {
            return new Line(pos1.x, pos1.y, pos2.x, pos2.y);
        }
        else {
            return new Line(0, 0, 0, 0);
        }
    }
}
