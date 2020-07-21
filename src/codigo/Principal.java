package codigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Charly Ponce
 */
public class Principal {
       
    public static void main(String[] args) throws Exception {
        String ruta1 = "C:/Users/carlo/OneDrive/Documents/NetBeansProjects/Compilador/src/codigo/Lexer.flex";
        String ruta2 = "C:/Users/carlo/OneDrive/Documents/NetBeansProjects/Compilador/src/codigo/LexerCup.flex";
        String[] rutaS = {"-parser", "Sintax", "C:/Users/carlo/OneDrive/Documents/NetBeansProjects/Compilador/src/codigo/Sintax.cup"};
        generar(ruta1, ruta2, rutaS);
        
    }
    
    public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception{
        File archivo;
        archivo = new File(ruta1);
        jflex.Main.generate(archivo);
        archivo = new File(ruta2);
        jflex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        
        Path rutaSym = Paths.get("C:/Users/carlo/OneDrive/Documents/NetBeansProjects/Compilador/src/codigo/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("C:/Users/carlo/OneDrive/Documents/NetBeansProjects/Compilador/sym.java"), 
                Paths.get("C:/Users/carlo/OneDrive/Documents/NetBeansProjects/Compilador/src/codigo/sym.java")
        );
        Path rutaSin = Paths.get("C:/Users/carlo/OneDrive/Documentos/NetBeansProjects/Compilador/src/codigo/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("C:/Users/carlo/OneDrive/Documents/NetBeansProjects/Compilador/Sintax.java"), 
                Paths.get("C:/Users/carlo/OneDrive/Documents/NetBeansProjects/Compilador/src/codigo/Sintax.java")
        );
    }
}
