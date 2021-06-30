package com.test;

import com.utils.KnnClassification;

/**
 * @Description:
 */

public class Test extends KnnClassification<Integer> {

    @Override
    public double similarScore(Integer o1, Integer o2) {
        return Math.abs(o1 - o2);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Test test = new Test();
        for (int i = 1; i < 10; i++) {
            test.addRecord(i, i > 5 ? "0" : "1");
        }
        System.out.println(test.getTypeId(-11));
        System.out.println(test.getTypeId(0));
        System.out.println(test.getTypeId(1));
        System.out.println(test.getTypeId(2));
        System.out.println(test.getTypeId(3));
        System.out.println(test.getTypeId(4));
        System.out.println(test.getTypeId(5));
        System.out.println();
        System.out.println(test.getTypeId(6));
        System.out.println(test.getTypeId(7));
        System.out.println(test.getTypeId(8));
        System.out.println(test.getTypeId(9));
        System.out.println(test.getTypeId(22));
    }
}

