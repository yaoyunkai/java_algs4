package com.libyao.algs4.chp2;

public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        checkAge(age);
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void checkAge(int age) {
        if (age > 149 || age < 0) {
            throw new IllegalArgumentException();
        }
    }

    public void setAge(int age) {
        checkAge(age);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        if (o == null) {
            throw new IllegalArgumentException("null of Person");
        }

        if (age == o.age) return 0;
        return age > o.age ? 1 : -1;
    }

    public static void main(String[] args) {
        Person tom = new Person("tom", 18);
        Person tom1 = new Person("tom", 19);
        int i = tom.compareTo(tom1);
        System.out.println(i);
    }
}
