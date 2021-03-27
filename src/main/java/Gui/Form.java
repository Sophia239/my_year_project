package Gui;

import problem.Problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Класс формы приложения
 */
public class Form extends JFrame {
    /**
     * панель для отображения OpenGL
     */
    private JPanel GLPlaceholder;
    private JPanel root;
    private JTextField xCircleField;
    private JTextField yCircleField;
    private JButton randomBtn;
    private JTextField circleCntField;
    private JButton loadFromFileBtn;
    private JButton saveToFileBtn;
    private JButton clearBtn;
    private JButton solveBtn;
    private JLabel problemText;
    private JButton addCircle;
    private JTextField x1CircleField;
    private JTextField y1CircleField;
    /**
     * таймер
     */
    private final Timer timer;
    /**
     * рисовалка OpenGL
     */
    private final RendererGL renderer;

    /**
     * Конструктор формы
     */
    private Form() {
        super(Problem.PROBLEM_CAPTION);
        // инициализируем OpenGL
        renderer = new RendererGL();
        // связываем элемент на форме с рисовалкой OpenGL
        GLPlaceholder.setLayout(new BorderLayout());
        GLPlaceholder.add(renderer.getCanvas());
        // указываем главный элемент формы
        getContentPane().add(root);
        // задаём размер формы
        setSize(getPreferredSize());
        // показываем форму
        setVisible(true);
        // обработчик зарытия формы
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(() -> {
                    renderer.close();
                    timer.stop();
                    System.exit(0);
                }).start();
            }
        });
        // тинициализация таймера, срабатывающего раз в 100 мсек
        timer = new Timer(100, e -> onTime());
        timer.start();
        initWidgets();
    }

    /**
     * Инициализация виджетов
     */
    private void initWidgets() {
        // задаём текст полю описания задачи
        problemText.setText("<html>" + Problem.PROBLEM_TEXT.replaceAll("\n", "<br>"));
        // делаем первое радио выбранным
        /*radioButton1.setSelected(true);
        radioButton2.setSelected(false);*/

        addCircle.addActionListener(e -> {
            double x = Double.parseDouble(xCircleField.getText());
            double y = Double.parseDouble(yCircleField.getText());
            double x1 = Double.parseDouble(x1CircleField.getText());
            double y1 = Double.parseDouble(y1CircleField.getText());
            renderer.problem.addCircle(x, y, x1, y1);
        });
        randomBtn.addActionListener(e -> renderer.problem.addRandomCircles(Integer.parseInt(circleCntField.getText())));
        loadFromFileBtn.addActionListener(e -> renderer.problem.loadFromFile());
        saveToFileBtn.addActionListener(e -> renderer.problem.saveToFile());
        clearBtn.addActionListener(e -> renderer.problem.clear());
        solveBtn.addActionListener(e -> renderer.problem.solve());
    }

    /**
     * Событие таймера
     */
    private void onTime() {
        // события по таймеру
    }

    /**
     * Главный метод
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        new Form();
    }
}
