package com.epam.palsoul;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

public class Matrix {

    private final int HEIGHT;
    private final int WIDTH;
    private final double MATR[][];

    public Matrix() {
        HEIGHT = 0;
        WIDTH = 0;
        MATR = new double[HEIGHT][WIDTH];
    }

    public Matrix(final int H, final int W) {

        HEIGHT = H;
        WIDTH = W;
        MATR = new double[HEIGHT][WIDTH];

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Random random = new Random();
                MATR[i][j] = random.nextDouble();
            }
        }

        print();
    }

    public Matrix(String filename) {

        int h = 0, w = 0;
        double matr[][] = new double[h][w];

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) {
            StringTokenizer tokenizer;

            // Получаем размер матрицы
            String size = reader.readLine();
            tokenizer = new StringTokenizer(size, " \n", false);

            h = Integer.parseInt(tokenizer.nextToken());
            w = Integer.parseInt(tokenizer.nextToken());

            // Получаем саму матрицу
            StringBuilder builder = new StringBuilder();
            matr = new double[h][w];

            String sMatr;
            while((sMatr = reader.readLine()) != null)
                builder.append(sMatr);
            tokenizer = new StringTokenizer(builder.toString(), " \n", false);

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    matr[i][j] = Double.parseDouble(tokenizer.nextToken());
                }
            }

        } catch (IOException ex) {

            System.err.println("file not found");
            h = 0;
            w = 0;

        } finally {

            HEIGHT = h;
            WIDTH = w;
            MATR = matr;

        }
    }

    public Matrix(Matrix A, Matrix B) {
        if (A.getWidth()  == B.getHeight()) {

            // Инициализируем размер
            HEIGHT = A.getHeight();
            WIDTH = B.getWidth();
            MATR = new double[HEIGHT][WIDTH];

            // Присваеваем значения матрице
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    MATR[i][j] = 0;

                    for (int k = 0; k < A.getWidth(); k++) {
                        MATR[i][j] += A.getElement(i, k) * B.getElement(k, j);
                    }
                }
            }

        } else {
            HEIGHT = 0;
            WIDTH = 0;
            MATR = new double[HEIGHT][WIDTH];
        }
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public double getElement(int i, int j) {
        return MATR[i][j];
    }

    public void print() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(" " + MATR[i][j]);
            }
            System.out.println();
        }
    }
}