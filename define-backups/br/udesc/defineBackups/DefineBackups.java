package br.udesc.defineBackups;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.math3.special.Beta;
import br.udesc.defineBackups.modelo.Provider;
import br.udesc.defineBackups.util.XlsGenerator;

public class DefineBackups {

	
    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Provider> providers = new ArrayList<Provider>();
        String fullFilePath = "/home/anderson-raugust/Documentos/"; //Start and end with a bar (/)
        String fileName = "Backups"; //Without extension
        providers.add(new Provider("Amazon S3 (us-east-1)", 1, 2.94));
        providers.add(new Provider("DigitalOcean", 36, 2.81 ));
        providers.add(new Provider("SoftLayer (SEO)", 50, 1.68 ));
        providers.add(new Provider("VPS.NET (atlanta)", 1, 1.32));
        providers.add(new Provider("Google Compute Engine (us-central1)", 6, 0.13));
        providers.add(new Provider("Alibaba Elastic Compute Service	(cn-shanghai)", 26, 0.44));
        
//      UNCOMMENT THE LINES BELOW TO READ THE VALUES FROM USER
        System.out.println("Enter the desired reliability value (ex: 0,995): ");
        double targetReliability = scan.nextDouble();
        System.out.println("Enter the maximum number of critical nodes: ");
        int critical = scan.nextInt();
//      UNCOMMENT THE LINES BELOW TO DEFINE THE VALUES BEFORE EXECUTION
        //double x = 0.995;
        //int n = 300;
        
        defineBackups(providers, critical, targetReliability, fullFilePath, fileName);

    }
	
	
    /**
     * *
     * Calculate the reliability for the given values, with backups number
     * going from 2n to 1.
     * @param p error
     * @param n criticals
     * @return double reliability
     */
    private static double[] reliability(int n, double p) {
        double result[] = new double[2*n + 1];
        for (int k = 2*n; k > 0; k--) {
            double beta = Beta.regularizedBeta(1 - p, n, k);
            result[k] = beta;
        }
        return result;
    }
    
    /**
     * *
     * Create/subscribe a .xls file with backups needed for the given values. 
     * @param providers ArrayList containing all providers
     * @param criticals Max number of critical nodes, will generate 1 to
     * critical
     * @param targetReliability desired value provided
     * @param fullFilePath inform the full path starting and ending with a bar (/)
     * @param fileName inform the file name without extension
     */
    private static void defineBackups(ArrayList<Provider> providers, int criticals, double targetReliability, String fullFilePath, String fileName) throws IOException {
        XlsGenerator xls = new XlsGenerator(fullFilePath, fileName);  
        
        xls.openXLSFile();
        xls.addRow();
        xls.addColumn();
        xls.cell.setCellValue("N");
        
        for (Provider p : providers) {
        	xls.addColumn();
        	xls.cell.setCellValue(p.getName());
        }
        
        for (int n = 1; n <= criticals; n++) {
        	xls.addRow();
        	xls.addColumn();
            xls.cell.setCellValue(n);
            for (Provider p : providers) {
            	xls.addColumn();
                double result[] = reliability(n, p.getFailureProbability());
                for (int r = 0; r < result.length; r++) {
                    if (result[r] >= targetReliability) {
                        //System.out.println("Provider = " + x + " p = " + p + " Critical nodes = " + n + " Backup nodes: " + (r + 1) + " - Result: " + result[r]);
                    	xls.cell.setCellValue(r + 1);
                        break;
                    }
                }
            }
        }
        
        xls.closeXLSFile();
        System.out.println("Done!");
    }
}
