package br.com.poo.nethack.util;
import java.util.Calendar;

/** Classe que gera numeros aleatorios com semente padrao, gerada pela data ou adicionada pelo usuario
 * 
 * @author hermeto
 * @author eleazar
 *
 */
public class Random {
	private long p = 2147483648l;
	private long m = 843314861;
	private long a = 453816693;
	
	private long xi = 1023;
	
	public Random(int k) {
		xi = k;
	}
	
	public Random() {
		setSemente(Math.abs((int) Calendar.getInstance().getTimeInMillis()));
	}

	public double getRand() {
		xi = (a + m * xi) % p;
		double d = xi;
		return d / p;
	}
	
	public int getIntRand(int max) {
		double d = getRand() * max;
		return (int) d + 1;
	}

	public void setSemente(int semente) {
       xi = semente;		
	}
}
