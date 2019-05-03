package WaveFront;

import java.util.Scanner;

public class Wave {
	int [][] mapa;
	int lado = 0;
	int jInicio;
	int iInicio;
	int jFin;
	int iFin;
	
	Wave () {
		Scanner in = new Scanner(System.in);
		System.out.print("lado: ");
		lado = in.nextInt();
		mapa = new int [this.lado][this.lado];
		System.out.print("jInicio: ");
		jInicio = in.nextInt();
		System.out.print("iInicio: ");
		iInicio = in.nextInt();
		System.out.print("jFin: ");
		jFin = in.nextInt();
		System.out.print("iFin: ");
		iFin = in.nextInt();
		IniciarArreglo();
		int opcion;
		int iObs;
		int jObs;
		do {
			System.out.print("iObs: ");
			iObs = in.nextInt();
			System.out.print("jObs: ");
			jObs = in.nextInt();
			mapa[iObs][jObs] = -1;
			System.out.print("Otro obstaculo(0 no 1 si): ");
			opcion = in.nextInt();
		}while(opcion != 0);
		 MostrarArreglo();
	}
	
	void IniciarArreglo() {
		for(int i = 0; i < lado; i++) {
			for(int j = 0 ; j < lado; j++) {
				mapa[i][j] = -2;
			}
		}
		mapa[iInicio][jInicio] = 0;
		mapa[iFin][jFin] = 100;		
	}
	
	void MostrarArreglo() {
		for(int i = 0; i < lado; i++) {
			for(int j = 0 ; j < lado ; j++) {
				System.out.print(mapa[i][j] + "  ");
			}
			System.out.println("");
		}
		System.out.println("---------------------------------------------------");
	}
	
	void MakeWave(int num) {
		int menosx;
		int menosy;
		int masx;
		int masy;
		boolean seguir = false;
		for(int i = 0; i < lado; i++) {
			for(int j = 0 ; j < lado ; j++) {
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
								seguir = true;
							}
						}
					}
					if(masy < lado) {
						if(mapa[masy][j] == 100) {
							return;
						}else {
							if(mapa[masy][j] == -2) {
								mapa[masy][j] = num+1;
								seguir = true;
							}
						}
					}
					
					if(menosx >= 0) {
						if(mapa[i][menosx] == 100) {
							return;
						}else {
							if(mapa[i][menosx] == -2) {
								mapa[i][menosx] = num+1;
								seguir = true;
							}
						}
					}
					if(masx < lado) {
						if(mapa[i][masx] == 100) {
							return;
						}else {
							if(mapa[i][masx] == -2) {
								mapa[i][masx] = num+1;
								seguir = true;
							}
						}
					}		
				}
			}
		}
		if(seguir) {
			MakeWave(num+1);
		}else {
			return;
		}
		
	}

	void ruta(boolean bandera, int x, int y) {
		if (bandera) {
			boolean band = false;
			int a = 0;
			int b = 0;
			for(int i = 0; i < lado; i++) {
				for(int j = 0 ; j < lado ; j++) {
					if(mapa[i][j] == 100) {
						a = i;
						b = j;
						band = true; 
						break;
					}
				}
			}
			if(band) {
				ruta(false, b, a);
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
			if(masx < lado) {
				if(mapa[y][masx] < menor && mapa[y][masx] >= 0) {
					menor = mapa[y][masx];
					menorx = masx;
					menory = y;
					encontrado = true;
				}
			}
			if(masy < lado) {
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
			if(menor == 0) {
				mapa[menory][menorx] = 100;
				return;
			}
			if(encontrado) {
				mapa[menory][menorx] = 100;
				ruta(false, menorx, menory);
			}else {
				return;
			}
		}
		return;
		
	}

	void LimpiarArreglo() {
		for(int i = 0; i < lado; i++) {
			for(int j = 0 ; j < lado ; j++) {
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
}
