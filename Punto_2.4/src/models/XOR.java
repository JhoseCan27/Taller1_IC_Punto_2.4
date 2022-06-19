package models;

/**
 * Clase encargada de generar aprendizaje para la función XOR.
 * @author Jose
 *
 */
public class XOR {

	public static double APRENDIZAJE = 0.25;
	public static int[][] dataSetNeurona1 = { { 0, 0, 0 }, { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
	public static int[][] dataSetNeurona2 = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 0, 0 }, { 1, 1, 1 } };
	public double pesoN1X1, pesoN1X2, pesoN2X1, pesoN2X2, baiasN1, baiasN2;

	/**
	 * Constructor de la clase
	 * Aquí se inicializan los datos de entrada para comanezar la iteración
	 */
	public XOR() {
		pesoN1X1 = -1;
		pesoN1X2 = 0.50;
		pesoN2X1 = -0.40;
		pesoN2X2 = 0.40;
		baiasN1 = 0.05;
		baiasN2 = 0.05;
	}

	/**
	 * Funcion de activacion HARDLIMIT
	 * @param Entrada para el HARDLIMIT
	 * @return 0 o 1 dependiendo de la función de activación
	 */
	private int hardLimit(double value) {
		if (value >= 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Genera el aprendizaje de la neurona
	 * llama la función de evaluar los pesos
	 * 
	 */
	public void aprendizaje() {
		System.out.println("Iniciando Aprendizaje....");
		for (int i = 0; i < 100; i++) {
			evaluarPesos(dataSetNeurona1, dataSetNeurona2);
		}
		System.out.println("Aprendizaje terminado");
		imprimirSalida();
	}

	/**
	 * Imprime los datos de salida de la neurona
	 */
	private void imprimirSalida() {
		for (int i = 0; i < dataSetNeurona1.length; i++) {
			int hardLimitN1 = hardLimit(dataSetNeurona1[i][0] * pesoN1X1 + dataSetNeurona1[i][1] * pesoN1X2 - baiasN1);
			int hardLimitN2 = hardLimit(dataSetNeurona2[i][0] * pesoN2X1 + dataSetNeurona2[i][1] * pesoN2X2 - baiasN2);
			
			if (i == 1) {
				System.out.println(hardLimitN2+" | "+hardLimitN1+" = "+resultado(hardLimitN2, hardLimitN1));
			} else {
				System.out.println(hardLimitN1+" | "+hardLimitN2+" = "+resultado(hardLimitN1, hardLimitN2));
			}
		}
	}

	/**
	 * Evalúa las entradas que entran de la neurona de salida
	 * los parametros son las entradas de la neurona
	 * @param x1
	 * @param x2
	 * @return el valor esperado respecto a las entradas
	 */
	private int resultado(int x1, int x2) {
		return x1==1&&x2==0||x1==0&&x2==1?1:0;
	}
	
	/**
	 * Se encarga de evaluar los datos ingresados de la neurona con los nuevos pesos y
	 * de calcular el valor del ias, el valor del error y las diferencias.
	 * @param matriz data set de la neurona 1
	 * @param matriz2  data set de la neurona 2
	 */
	private void evaluarPesos(int[][] matriz, int[][] matriz2) {
		
		imprimirSalida();
		
		for (int i = 0; i < matriz.length; i++) {
			
			double y1 = matriz[i][0] * pesoN1X1 + matriz[i][1] * pesoN1X2 - baiasN1;
			double y2 = matriz2[i][0] * pesoN2X1 + matriz2[i][1] * pesoN2X2 - baiasN2;

			int hardLimit1 = hardLimit(y1);
			int hardLimit2 = hardLimit(y2);

			int error1 = matriz[i][2] - hardLimit1;
			int error2 = matriz2[i][2] - hardLimit2;

			double delta1 = APRENDIZAJE * error1 * matriz[i][0];
			double delta2 = APRENDIZAJE * error1 * matriz[i][1];

			double delta3 = APRENDIZAJE * error2 * matriz2[i][0];
			double delta4 = APRENDIZAJE * error2 * matriz2[i][1];

			pesoN1X1 = pesoN1X1 + delta1;
			pesoN1X2 = pesoN1X2 + delta2;
			baiasN1 = baiasN1 - (APRENDIZAJE * error1);

			pesoN2X1 = pesoN2X1 + delta3;
			pesoN2X2 = pesoN2X2 + delta4;
			baiasN2 = baiasN2 - (APRENDIZAJE * error2);
			
			System.out.println("----------------------------------");
		}
	}

	/**
	 * Imprime las ecuaciones de las neuronas resultantes
	 */
	public void mostrarEcuaciones() {
		System.out.println("Ecuacion de la neurona 1: " + pesoN1X1 + " * X1 + " + pesoN1X2 + " * X2 - " + baiasN1);
		System.out.println("Ecuacion de la neurona 2: " + pesoN2X1 + " * X1 + " + pesoN2X2 + " * X2 - " + baiasN2);
	}

	public static void main(String[] args) {
		XOR xor = new XOR();
		xor.aprendizaje();
		xor.mostrarEcuaciones();
	}
}
