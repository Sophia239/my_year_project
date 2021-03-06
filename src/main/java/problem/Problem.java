package problem;

import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс задачи
 */
public class Problem {
    /**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "На плоскости задано множество окружностей.\n" +
            "Найти такую пару пересекающихся окружностей, что длина отрезка,\n" +
            "проведенного от одной точки пересечения этих двух окружностей \n" +
            "до другой, максимальна.";

    /**
     * заголовок окна
     */
    public static final String PROBLEM_CAPTION = "Итоговый проект ученицы 10-2 Костюкевич Софии";

    /**
     * путь к файлу
     */
    private static final String FILE_NAME = "circles.txt";

    /**
     * путь к файлу ответа
     */
    private static final String FILE_NAME2 = "circles2.txt";

    /**
     * список окружностей
     */
    private final ArrayList<Circle> circles;
    private final ArrayList<Line> lines;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        circles = new ArrayList<>();
        lines = new ArrayList<>();
    }

    /**
     * Добавить окружность
     *
     * @param x      координата X точки
     * @param y      координата Y точки
     * @param x1      координата X1 точки
     * @param y1      координата  точки
     */
    public void addCircle(double x, double y, double x1, double y1) {
        Circle circle = new Circle(x, y, x1, y1);
        circles.add(circle);
    }

    /**
     * Решить задачу
     */
    public void solve() {
        // перебираем пары окружностей
        Circle max_c = null;
        Circle max_c2 = null;
        double max_length = 0;
        for (Circle c : circles) {
            for (Circle c2 : circles) {
                if (c != c2) {
                    if (Circle.GetCrossPoints(c, c2).r > max_length) {
                        max_length = Circle.GetCrossPoints(c, c2).r;
                        max_c = c;
                        max_c2 = c2;
                    }
                }
            }
        }
        assert max_c != null;
        max_c.color = 1;
        max_c2.color = 1;
        lines.clear();
        lines.add(Circle.GetCrossPoints(max_c, max_c2));
    }

    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {
        try {
            File file = new File(FILE_NAME);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                double x1 = sc.nextDouble();
                double y1 = sc.nextDouble();
                sc.nextLine();
                Circle circle = new Circle(x, y, x1, y1);
                circles.add(circle);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    /**
     * Сохранить задачу в файл
     */
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME2));
            for (Line line : lines) {
                out.printf("%.2f %.2f\n", line.x, line.y);
                out.printf("%.2f %.2f\n", line.x1, line.y1);
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    /**
     * Добавить заданное число случайных точек
     *
     * @param n кол-во точек
     */
    public void addRandomCircles(int n) {
        for (int i = 0; i < n; i++) {
            Circle p = Circle.getRandomCircle();
            circles.add(p);
        }
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        circles.clear();
        lines.clear();
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        for (Circle circle : circles) {
            circle.render(gl, circle.color);
        }
        for (Line line : lines) {
            line.render(gl);
        }
    }
}
