package WaveFront;

public class Movimiento {
	static final  int LADO = 6;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int jInicio = 5;
		int iInicio = 3;
		int jFin = 1;
		int iFin = 2;
		//prueba
		//obstaculo (3,1) (2,3), (1,3) (2,2)
		
		int [][] mapa = new int[LADO][LADO];
		// -1 obstaculo, -2 vacio, 0 inicio, 100 fin
		IniciarArreglo(mapa, iInicio, jInicio, iFin, jFin);
		Wave(mapa, 0);
		MostrarArreglo(mapa);
		ruta(mapa, true, 0, 0, 0);
		MostrarArreglo(mapa);
		//100 ruta 444 obstaculo, 777 libre
		LimpiarArreglo(mapa);
		MostrarArreglo(mapa);
		
		
	}
	static void LimpiarArreglo(int [][] mapa) {
		for(int i = 0; i < LADO; i++) {
			for(int j = 0 ; j < LADO ; j++) {
				if(mapa[i][j] == 100) {
					mapa[i][j] = 100;
				}else {
					if(mapa[i][j] == -1) {
						mapa[i][j] = 444;
					}else {
						mapa[i][j] = 777;
					}
				}
			}
		}
		
	}
	static void ruta(int [][] mapa, boolean bandera, int x, int y, int contador) {
		if (bandera) {
			boolean band = false;
			int a = 0;
			int b = 0;
			for(int i = 0; i < LADO; i++) {
				for(int j = 0 ; j < LADO ; j++) {
					if(mapa[i][j] == 100) {
						a = i;
						b = j;
						band = true; 
						break;
						//ruta(mapa, false, j, i, contador++);
					}
				}
			}
			if(band) {
				ruta(mapa, false, b, a, contador++);
			}
		}else {
			int menosx = x-1;
			int menosy = y - 1;
			int masx = x + 1;
			int masy = y + 1;
			int menor = 1000;
			int menorx = 0;
			int menory = 0;
			boolean encontrado = false;
			if(menosy >= 0) {
				if(mapa[menosy][x] < menor && mapa[menosy][x] >= 0) {
					menor = mapa[menosy][x];
					menorx = x;
					menory = menosy;
					encontrado = true;
				}
			}
			if(masx < LADO) {
				if(mapa[y][masx] < menor && mapa[y][masx] >= 0) {
					menor = mapa[y][masx];
					menorx = masx;
					menory = y;
					encontrado = true;
				}
			}
			if(masy < LADO) {
				if(mapa[masy][x] < menor && mapa[masy][x] >= 0) {
					menor = mapa[masy][x];
					menorx = x;
					menory = masy;
					encontrado = true;
				}
			}
			if(menosx >= 0) {
				if(mapa[y][menosx] < menor && mapa[y][menosx] >= 0) {
					menor = mapa[y][menosx];
					menorx = menosx;
					menory = y;
					encontrado = true;
				}
			}
			if(menor == 0 || contador > 10) {
				mapa[menory][menorx] = 100;
				return;
			}
			if(encontrado) {
				mapa[menory][menorx] = 100;
				ruta(mapa, false, menorx, menory, contador++);
				
			}else {
				return;
			}
		}		
		
	}
	static void Wave(int [][] mapa, int num) {
		int menosx;
		int menosy;
		int masx;
		int masy;
		
		for(int i = 0; i < LADO; i++) {
			for(int j = 0 ; j < LADO ; j++) {
				if(mapa[i][j] == num) {
					menosy = i-1;
					masy = i+1;
					menosx = j -1;
					masx = j +1;
					if(menosy >= 0) {
						if(mapa[menosy][j] == 100) {
							return;
						}else {
							if(mapa[menosy][j] == -2) {
								mapa[menosy][j] = num+1;
							}
						}
					}
					if(masy < LADO) {
						if(mapa[masy][j] == 100) {
							return;
						}else {
							if(mapa[masy][j] == -2) {
								mapa[masy][j] = num+1;
							}
						}
					}
					
					if(menosx >= 0) {
						if(mapa[i][menosx] == 100) {
							return;
						}else {
							if(mapa[i][menosx] == -2) {
								mapa[i][menosx] = num+1;
							}
						}
					}
					if(masx < LADO) {
						if(mapa[i][masx] == 100) {
							return;
						}else {
							if(mapa[i][masx] == -2) {
								mapa[i][masx] = num+1;
							}
						}
					}		
				}
			}
		}
		if(num < LADO*LADO) {
			Wave(mapa, num+1);
		}else {
			return;
		}
		
	}
	
	static void IniciarArreglo(int [][] mapa, int xInicio, int yInicio, int xFin, int yFin ) {
		for(int i = 0; i < LADO; i++) {
			for(int j = 0 ; j < LADO ; j++) {
				mapa[i][j] = -2;
			}
		}
		mapa[3][1] = -1;
		mapa[1][3] = -1;
		mapa[2][3] = -1;
		mapa[3][3] = -1;
		mapa[xInicio][yInicio] = 0;
		mapa[xFin][yFin] = 100;
		MostrarArreglo(mapa);
		
	}
	
	static void MostrarArreglo(int [][] mapa) {
		for(int i = 0; i < LADO; i++) {
			for(int j = 0 ; j < LADO ; j++) {
				System.out.print(mapa[i][j] + "  ");
			}
			System.out.println("");
		}
		System.out.println("---------------------------------------------------");
	}
}
