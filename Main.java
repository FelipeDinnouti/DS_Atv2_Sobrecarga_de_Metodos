// Felipe Hiroshi Carvalho Dinnouti - 2° DS Mtec

import java.text.DecimalFormat;
import java.util.Scanner;

// To acostumado a comentar código em ingles entao vai tar tudo em ingles
public class Main {
    static String main_greeting = "\nBem vindo ao sistema de conversões (de temperatura) do japonês da sua vizinhança. \n1 - Fahrenheit para Celsius\n2 - Celsius para Fahrenheit\n3 - Configurar arredondamento\n4 - Configurar ajuste\n0 - Sair\nSelecione uma opção: ";
    static String count_option = "\n\nQuantidade de números pra converter\n\n1 - Converter um só\n2 - Converter muitos\nSelecione uma opção: ";
    static DecimalFormat df = new DecimalFormat();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int first_option, second_option;

        // Controller for decimal precision
        df.setMaximumFractionDigits(2);

        do {
            // Options
            // 1: F° para C°
            // 2: C° para F° 
            System.out.print(main_greeting);
            first_option = scanner.nextInt(); // Valid values: 1 or 2

            
            if (first_option == 0) break;
            
            // Configurign rounding precision
            if (first_option == 3) { 
                System.out.print("Insira a nova precisão de arredondamento (em n° de casas decimais): ");
                df.setMaximumFractionDigits(scanner.nextInt());
                System.out.println("\nConfigurado!\n");

                continue; // Context configuration will skip conversion logic
            } else if (first_option == 4) { // Configurar o ajuste (offset)
                System.out.println(String.format("Ajuste atual: %d", Converter.getOffset()));
                System.out.print("Insira o novo ajuste: ");
                Converter.setOffset(scanner.nextInt());
                System.out.println("\nConfigurado!\n");
                continue;
            }

            // 1: um só
            // 2: muitos
            System.out.print(count_option);
            second_option = scanner.nextInt()*4; // Valid values: 4 or 8

            int length;
           
            // There are four possible combinations of inputs, and four possible sum results.
            switch (first_option+second_option) {
                // 1+4: F to C, one
                case 5:
                    System.out.println("Digite o valor em Fahrenheit: ");
                    System.out.println(
                        String.format("Resultado:\n - Celsius: %sC°", df.format(Converter.Temperature(scanner.nextInt())+Converter.getOffset()))
                        );
                    break;  

                // 1+8: F to C, many
                case 9:
                    System.out.println("Quantos deseja converter?");
                    length = scanner.nextInt();    

                    System.out.print("\nInsira os números: ");

                    // Gets input and stores them in an aux array
                    int[] values = new int[length];
                    for (int i = 0; i<length; i++) {
                        System.out.print(String.format("\nInsira o número [%d]: ", i+1));
                        values[i] = scanner.nextInt();
                    }

                    System.out.println("Valores convertidos (mesma ordem) de Fahrenheit para Celsius:");
                    System.out.println(pprintArr(Converter.Temperature(values)));
                    System.out.println("\n\n");

                    break;

                // 2+4: C to F, one
                case 6:
                    System.out.println("Digite o valor em Celsius: ");
                    System.out.println(
                        String.format("Resultado:\n - Fahrenheit: %sF°", df.format(Converter.Temperature(scanner.nextFloat())))
                        );
                    break;

                // 2+8: C to F, many
                case 10:
                    System.out.println("Quantos deseja converter?");
                    length = scanner.nextInt();    

                    
                    float[] fvalues = new float[length];
                    for (int i = 0; i<length; i++) {
                        System.out.print(String.format("\nInsira o número [%d]: ", i+1));
                        fvalues[i] = scanner.nextFloat();
                    }

                    System.out.println("Valores convertidos (mesma ordem) de Celsius para Fahrenheit: ");
                    System.out.println(pprintArr(Converter.Temperature(fvalues)));
                    System.out.println("\n\n");
                    
                    break;

                default:
                    System.out.println("Escolha inválida");
                    break;
            }

            // Cleanup
            second_option = 0; 
            first_option = 0;
        } while(true);

        scanner.close();
    }   

    // Helper function to print the array in a pretty manner
    static String pprintArr(int[] values) {
        String res = "";

        for (int i = 0; i<values.length; i++) {
            res = res + Integer.toString(values[i])+ "F°, ";
        }

        return res;
    }
    static String pprintArr(float[] values) { // Float is fahrenheit
        String res = "";

        for (int i = 0; i<values.length; i++) {
            res = res + df.format(values[i]) + "C°, ";
        }
        
        return res;
    }
}
