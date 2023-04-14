package com.libyao.generics_started;

/**
 * <b>泛型的限制</b>
 *
 * <T>不能是基本类型，例如int，因为实际类型是Object，Object类型无法持有基本类型：
 * <p>
 * 无法取得带泛型的Class
 * <p>
 * 无法判断带泛型的类型
 * <p>
 * 不能实例化T类型
 *
 * @param <T>
 */
public class Pair<T> {
    private T first;
    private T last;

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }

    public T getLast() {
        return last;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setLast(T last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Pair{" + "first=" + first + ", last=" + last + '}';
    }

    // 静态泛型方法应该使用其他类型区分.
    public static <K> Pair<K> create(K first, K last) {
        return new Pair<K>(first, last);
    }

    /**
     * 现在 Pair的泛型是 Number
     *
     * @param p
     * @return
     */
    static int add(Pair<Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }

    /**
     * 上界通配符 Upper Bounds Wildcards
     *
     * @param p
     * @return
     */
    static int add1(Pair<? extends Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }

    /**
     * 方法参数签名setFirst(? extends Number)无法传递任何Number的子类型给setFirst(? extends Number)。
     * <p>
     * 方法内部可以调用获取Number引用的方法，例如：Number n = obj.getFirst();；
     * <p>
     * 方法内部无法调用传入Number引用的方法（null除外），例如：obj.setFirst(Number n);。
     *
     * @param p
     * @return
     */
    static int add2(Pair<? extends Number> p) {
        Number first1 = p.getFirst();
        Number last1 = p.getLast();

        // p.setFirst(new Integer(first1.intValue() + 100));
        // p.setLast(new Integer(last1.intValue() + 100));
        // return p.getFirst().intValue() + p.getFirst().intValue();

        return 0;
    }


    /**
     * 不恰当的覆写方法
     * <p>
     * 这是因为，定义的equals(T t)方法实际上会被擦拭成equals(Object t)，而这个方法是继承自Object的，编译器会阻止一个实际上会变成覆写的泛型方法定义。
     * <p>
     * // public boolean equals(T t) {
     * //     return this == t;
     * // }
     *
     * @return
     */
    public static void main(String[] args) {
        Pair<String> p1 = new Pair<>("Hello", "world");
        Pair<Integer> p2 = new Pair<>(123, 456);
        Class c1 = p1.getClass();
        Class c2 = p2.getClass();
        System.out.println(c1 == c2); // true
        System.out.println(c1 == Pair.class); // true

        testAdd();
    }


    public static void testAdd() {
        int sum = add(new Pair<Number>(4, 2));

        Pair<Integer> p = new Pair<>(123, 456);
        int n = add1(p);
        System.out.println(n);
    }

}
