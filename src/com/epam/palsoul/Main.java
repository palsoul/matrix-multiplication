package com.epam.palsoul;

public class Main {
    public static void main(String[] args) {
        Matrix A = new Matrix(2, 3);
        Matrix B = new Matrix(3, 2);
        Matrix C = new Matrix(A, B);
        C.print();
    }
}