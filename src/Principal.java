import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ConversorApp conversor = new ConversorApp();

        int opcion = 0;

        while (opcion != 7) {

            String menu = """
            ============================================================
                  SEA BIENVENIDO/A AL CONVERSOR DE MONEDA
            ============================================================
            1) Dólar => Peso argentino
            2) Peso argentino => Dólar
            3) Dólar => Real brasileño
            4) Real brasileño => Dólar
            5) Dólar => Peso colombiano
            6) Peso colombiano => Dólar
            7) Salir
            Elija una opción válida:\s""";

            System.out.print(menu);
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 6) {

                System.out.print("Ingrese la cantidad: ");
                double cantidad = scanner.nextDouble();

                String base = "";
                String destino = "";

                switch (opcion) {
                    case 1 -> { base = "USD"; destino = "ARS"; }
                    case 2 -> { base = "ARS"; destino = "USD"; }
                    case 3 -> { base = "USD"; destino = "BRL"; }
                    case 4 -> { base = "BRL"; destino = "USD"; }
                    case 5 -> { base = "USD"; destino = "COP"; }
                    case 6 -> { base = "COP"; destino = "USD"; }
                }

                double tasa = conversor.obtenerTasa(base, destino);
                double resultado = conversor.convertir(base, destino, cantidad);

                System.out.println("\nConvirtiendo de " + base + " a " + destino + "...");
                System.out.printf("Tasa actual: %.4f\n", tasa);
                System.out.printf("Resultado: %.2f %s\n", resultado, destino + "\n");

            }
        }
        System.out.println("Programa finalizado.");
    }
}