package WaveFront;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Wave map = new Wave();
		map.MakeWave(0);
		map.MostrarArreglo();
		map.ruta(true, 0, 0);
		map.MostrarArreglo();
		map.LimpiarArreglo();
		map.MostrarArreglo();

	}

}
