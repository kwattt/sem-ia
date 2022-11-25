package general;
// Archivo con todos los datsets contenidos
/*
    h3* = dataset de la practica 3
    https://www.kaggle.com/datasets/karthickveerakumar/startup-logistic-regression
*/

public class Dataset {
    String type;

    public Dataset(String type) {
        this.type = type;
    }

    public double[][] getData(){
        if(this.type == "h3"){
            // return h3 data

            double b0[] = {165349.2,162597.7,153441.51,144372.41,142107.34,131876.9	,134615.46,130298.13,120542.52,123334.88,101913.08,100671.96,93863.75,91992.39,119943.24,114523.61,78013.11,94657.16,91749.16,86419.7	,76253.86,78389.47,73994.56,67532.53,77044.01,64664.71,75328.87,72107.6	,66051.52,65605.48,61994.48,61136.38,63408.86,55493.95,46426.07,46014.02,28663.76,44069.95,20229.59,38558.51,28754.33,27892.92,23640.93,15505.73,22177.74,1000.23,1315.46,0,542.05,0};
            double b1[] = {136897.8,151377.59,101145.55,118671.85,91391.77,99814.71,147198.87,145530.06,148718.95,108679.17,110594.11,91790.61,127320.38,135495.07,156547.42,122616.84,121597.55,145077.58,114175.79,153514.11,113867.3,153773.43,122782.75,105751.03,99281.34,139553.16,144135.98,127864.55,182645.56,153032.06,115641.28,152701.92,129219.61,103057.49,157693.92,85047.44,127056.21,51283.14,65947.93,82982.09,118546.05,84710.77,96189.63,127382.3,154806.14,124153.04,115816.21,135426.92,51743.15,116983.8};
            double b2[] = {471784.1,443898.53,407934.54,383199.62,366168.42,362861.36,127716.82,323876.68,311613.29,304981.62,229160.95,249744.55,249839.44,252664.93,256512.92,261776.23,264346.06,282574.31,294919.57,0,298664.47,299737.29,303319.26,304768.73,140574.81,137962.62,134050.07,353183.81,118148.2	,107138.38,91131.24,88218.23,46085.25,214634.81,210797.67,205517.64,201126.82,197029.42,185265.1	,174999.3	,172795.67,164470.71,148001.11,35534.17,28334.72,1903.93,297114.46,0,0,45173.06};

            // valor resultante
            double b4[] = {192261.83,191792.06,191050.39,182901.99,166187.94,156991.12,156122.51,155752.6,152211.77,149759.96,146121.95,144259.4,141585.52,134307.35,132602.65,129917.04,126992.93,125370.37,124266.9,122776.86,118474.03,111313.02,110352.25,108733.99,108552.04,107404.34,105733.54,105008.31,103282.38,101004.64,99937.59,97483.56,97427.84,96778.92,96712.8,96479.51,90708.19,89949.14,81229.06,81005.76,78239.91,77798.83,71498.49,69758.98,65200.33,64926.08,49490.75,42559.73,35673.41,14681.4};
        
            // retornar los valores
            double[][] data = {b0, b1, b2, b4};
            return data;
        }
        return null;
    }
    
}

/* public class Dataset {
    int[][] matrizdatset = new int[9][9];
    int x[] = {23, 26, 30, 34, 43, 48, 52, 57, 58};
    int y[] = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};

    public void datos(int valor) {
        int n=9;

        for (int i =0;i<n;i++){
            for (int j=0;j<2;j++){
                if (j==0) {
                    matrizdatset[i][j]=x[i];
                }
                else{
                    matrizdatset[i][j]=y[i];
                }
            }
        }
        System.out.printf("x    y\n");

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 2; ++j) {
                System.out.printf("%d \t", matrizdatset[i][j]);
            }
            System.out.printf("\n");
        }
        Math p = new Math();

        System.out.printf("\nB0 = %.4f",p.b0(matrizdatset));
        System.out.printf("\nB1 = %.4f",p.b1(matrizdatset));

        System.out.printf("\nyhat es igual a %.4f",p.yhat(matrizdatset, valor));
    }
}

*/