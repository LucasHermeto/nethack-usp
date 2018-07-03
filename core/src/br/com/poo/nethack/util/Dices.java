package br.com.poo.nethack.util;

/**
 * class Dices: Representa os dados rolados durante o jogo
 * Varios aspectos e valores do jogo sao baseados em numero de dados
 * como se fosse um RPG de mesa. Essa classe simula um dado na vida real
 * @author braga
 *
 */
public class Dices {
	private int n;
	private int faces;
	private int resultado;
	private int modifier;
	
	public Dices(int n, int f, int m) {
		this.n = n;
		faces = f;
		modifier = m;
	}
	
	/**
	 * Roda o dado de acordo com o numero de dados e face
	 * e depois retorna o resultado
	 * @return
	 */
	public int Roll() {
		resultado = 0;
		for(int i = 0; i < n; i++)
			resultado += this.rolar();
		return resultado + modifier;
	}
	
	/**
	 * Funcao responsavel pela aletoriedade do dado
	 * @return
	 */
	private int rolar() {
		Random rand = new Random();
		this.resultado = rand.getIntRand(faces);
		return this.resultado;
	}
}
