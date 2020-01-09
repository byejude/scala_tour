package javainterop;

import scala.Tuple2;

/**
 * Author: Tulip
 * Date: 2020/1/9 22:25
 */
public class ScalaTuples {
    public static void main(String[] args) {
        Tuple2 stringInteger = new Tuple2<String, Integer>("one", 2);
        System.out.println(stringInteger);
    }
}
