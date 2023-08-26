package com.libyao.algs4.chp3;

/*
散列表

如果所有的键都是小整数，我们可以用一个数组来实现无序的符号表，
将键作为数组的索引而数组中键i处储存的就是它对应的值。这样我们就可以快速访问任意键的值。

第一步是用散列函数将被查找的键转化为数组的一个索引。
散列查找的第二步就是一个处理碰撞冲突的过程：拉链法和线性探测法。


散列函数的计算：
如果我们有一个能够保存M个键值对的数组，
那么我们就需要一个能够将任意键转化为该数组范围内的索引（[0, M-1]范围内的整数）的散列函数。
对于任意键，0到M-1之间的每个整数都有相等的可能性与之对应（与键无关）

对于不同类型的键的处理:

整数
除留余数法 k % M (散列数组的长度) 素数

浮点数
将键表示为二进制数然后再使用除留余数法

字符串

hashCode
但如果两个对象的hashCode()方法的返回值相同，这两个对象也有可能不同，我们还需要用equals()方法进行判断。
默认散列函数会返回对象的内存地址


------------------------------------------------
一致性 等价的键必然产生相等的散列值；
高效性 计算简便；
均匀性 均匀地散列所有的键。

------------------------------------------------
HashMap
TreeMap
LinkedHashMap
ConcurrentHashMap

 */
public class HashST {


    public static void main(String[] args) {
        int start = 100;
        int end = 200; // 设置搜索范围，可以根据需要调整

        System.out.println("在范围 " + start + " 到 " + end + " 内的素数:");
        findPrimesInRange(start, end);
        printDoubleToBinary();
    }

    private int hash(Integer x) {
        return (x.hashCode() & 0x7fffffff) % 101;
    }

    public static void printDoubleToBinary() {
        double number = 3.14159265359;
        long bits = Double.doubleToLongBits(number); // 转换为二进制表示

        // 将long类型的二进制表示转换为字符串
        String binaryString = Long.toBinaryString(bits);

        System.out.println("原始双精度浮点数: " + number);
        System.out.println("二进制表示: " + binaryString);
    }

    public static void findPrimesInRange(int start, int end) {
        for (int number = start; number <= end; number++) {
            if (isPrime(number)) {
                System.out.print(number + " ");
            }
        }
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }
}
