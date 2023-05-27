package com.libyao.common;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * java char 使用utf16编码
 * java char的长度为2字节，也可以扩展到4字节? (应该是不可以的)  对于常见的ascii编码的字符 也可以是1字节.
 * char类型在Java中只能表示一个16位的Unicode字符
 * <p>
 * 四字节的字符可能需要使用代理对（surrogate pair）来进行表示和处理
 * <p>
 * 11011000 00111110 11011101 10000100
 * 00111110 11011000 10000100 11011101
 * <p>
 * unicode -> utf16 -> utf8 之间的转换
 * <p>
 * java 怎么判断下一个字符的字节长度: 检查当前字节是否为高位代理项（high surrogate）的值范围。高位代理项的范围是0xD800至0xDBFF。
 */
public class TestString {
    public static void main(String[] args) {
        char c1 = '你';
        char c2 = 'a';

        printUTF16Code(c1);
        printUTF16Code(c2);
        printUnicodePoint(c1);

        printStringBytes("你好", StandardCharsets.UTF_8);
        printStringBytes("你好", Charset.forName("gb2312"));

        int[] unicodeArray = {0x0048, 0x0065, 0x006C, 0x006C, 0x006F, 0x002C, 0x4E16, 0x754C};

        System.out.println(unicodeArrayToString1(0x1D546));

    }

    public static void printUTF16Code(char c) {
        String utf16Hex = Integer.toHexString(c);
        System.out.println("UTF-16: " + utf16Hex);
    }

    public static void printUnicodePoint(char c) {
        int codePoint = Character.codePointAt(Character.toString(c), 0);
        String hex = Integer.toHexString(codePoint);
        System.out.println("Unicode: " + hex);
    }

    public static void demoSurrogatePair() {
        int codePoint = 0x1F600; // 笑脸符号的Unicode码点
        char highSurrogate = (char) (((codePoint - 0x10000) >>> 10) + 0xD800);
        char lowSurrogate = (char) ((codePoint & 0x3FF) + 0xDC00);
        String surrogatePair = new String(new char[]{highSurrogate, lowSurrogate});
        System.out.println(surrogatePair); // 输出：😀
    }

    public static void printStringBytes(String value, Charset charset) {
        if (value == null) {
            return;
        }
        if (charset == null) {
            return;
        }
        byte[] bytes = value.getBytes(charset);
        for (byte b : bytes) {
            System.out.printf("%02X ", b);
        }
        System.out.println();
    }

    public static String unicodeArrayToString(int[] unicodeArray) {
        if (unicodeArray == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int codePoint : unicodeArray) {
            sb.appendCodePoint(codePoint);
        }
        return sb.toString();
    }

    public static String unicodeArrayToString1(int... unicodeArray) {
        StringBuilder sb = new StringBuilder();
        for (int codePoint : unicodeArray) {
            sb.appendCodePoint(codePoint);
        }
        return sb.toString();
    }
}
