/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backups;

import java.math.BigInteger;
import java.util.Scanner;
import org.apache.commons.math3.special.Beta;

/**
 *
 * @author AndersonR
 */
public class FindBackups {

    public static double failureProbabilities[] = {0.01, 0.02, 0.03, 0.04, 0.05};

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

//      DESCOMENTAR CÓDIGO ABAIXO PARA RODAR INFORMANDO OS DADOS   
        System.out.println("Enter the availability/reliability/... value for a given provider: ");
        double x = scan.nextDouble();
        System.out.println("Enter the number of critical nodes: ");
	int n = scan.nextInt();
//      DESCOMENTAR TRECHO ABAIXO PARA RODAR COM DADOS DA TABELA DO ARTIGO.
//        double x = 99.999;
//        int n = 50;

        for (int j = 0; j < failureProbabilities.length; j++) {
            double p = failureProbabilities[j];
            double result[] = reliability(n, p);
            for (int r = 0; r < result.length; r++) {
                if (result[r] >= x) {
                    System.out.println("p = " + p + " Backup nodes: " + r + " - Result: " + result[r]);
                    break;
                }
            }
        }
    }

    /**
     * *
     * @param p error
     * @param n criticals
     * @return double reliability
     */
    public static double[] reliability(int n, double p) {
        double result[] = new double[n];
        for (int k = n / 2; k > 0; k--) {
            double beta = Beta.regularizedBeta(1 - p, n, k);
            result[k] = beta;
        }
        return result;
    }

}
