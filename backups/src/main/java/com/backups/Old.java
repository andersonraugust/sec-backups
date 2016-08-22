/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backups;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author AndersonR
 */
public class Old {

    public static double failureProbabilities[] = {0.01, 0.02, 0.03, 0.04, 0.05};

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the availability/reliability/... value for a given provider: ");
        double x = scan.nextDouble();
        System.out.println("Enter the number of critical nodes: ");
        int n = scan.nextInt();
        for (int j = 0; j < failureProbabilities.length; j++) {
            double p = failureProbabilities[j];
            double result[] = reliability(n, p);
            for (int r = 0; r < result.length; r++) {
                if (result[r] >= x) {
                    System.out.println("p = " + p + " Backup nodes: " + r + " - Result: " + result[r]);
                }
            }
        }
    }

    public static BigInteger factorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = number; i > 0; i--) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    /**
     * *
     *
     * @param n criticals
     * @return double reliability
     */
    public static double[] reliability(int n, double p) {
        double result[] = new double[n];
        for (int k = n / 2; k > 0; k--) {
            double totalCriticalNode = 0;
            for (int x = 0; x < k; x++) {
                //totalCriticalNode = totalCriticalNode + ( (x != 0 ? ((n + k) / x) : 0) * (Math.pow(p, x) * (Math.pow(1 - p, n + k - x))));
                BigInteger bi = BigInteger.valueOf(0);
                if (x > 0) {
                    bi = factorial(n + k).divide(factorial(x).multiply(factorial(n + k - x)));
                }
                long fatorial = bi.longValue();
                totalCriticalNode += fatorial * (Math.pow(p, x) * (Math.pow(1 - p, n + k - x)));
            }
            result[k - 1] = totalCriticalNode;
        }
        return result;
    }
}
