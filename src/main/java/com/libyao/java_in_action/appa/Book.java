package com.libyao.java_in_action.appa;


import java.util.Arrays;

@Author(name = "Tom")
@Author(name = "Bob")
@Author(name = "Peter")
public class Book {
    public static void main(String[] args) {
        Author[] authors = Book.class.getAnnotationsByType(Author.class);
        Arrays.stream(authors).forEach(val -> {
            System.out.println(val.name());
        });
    }
}
