
package codigo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class FrmPrincipal extends javax.swing.JFrame {
   // private String sCadena;
      
    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
       private void analizarLexico() throws IOException{
        int cont = 1;
        
        String expr = (String) txtResultado.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "token " + "\t\t Expresiones Regulares\n\n";
              
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtAnalizarLex.setText(resultado);
                return;
            }
            switch (token) {
                /*case Linea:
                    cont++;
                break;*/
				
                case tipo_documento:
                    resultado += "Tipo Documento\t" +lexer.lexeme + "\n";
                    break;
                    
                case elementos_raiz:
                    resultado += "Elemento Raiz\t\t" +lexer.lexeme + "\n";
                    break;
                    
                case metadatos_cabecera:
                    resultado += "Metadatos Cabecera\t" +lexer.lexeme + "\n";
                    break;  
                    
                case metadatos_titulo:
                    resultado += "Metadatos Titulo\t" +lexer.lexeme + "\n";
                    break; 
                    
                case secciones_cuerpo:
                    resultado += "Secciones Cuerpo\t" +lexer.lexeme + "\n";
                    break;
                    
                case secciones_encabezado:
                    resultado += "Secciones Encabezados\t" +lexer.lexeme + "\n";
                    break;
                    
                case agrupacion_contenido:
                    resultado += "Agrupacion Contenidos\t" +lexer.lexeme + "\n";
                    break;
                    
                case informacion_tabulada:
                    resultado += "Tabla\t\t" +lexer.lexeme + "\n";
                    break;
                
                case atributos_tabla:
                    resultado += "Atributos Tabla\t\t" +lexer.lexeme + "\n";
                    break;
                 			
		case texto:
                    resultado += "texto\t\t" + lexer.lexeme +  "\n";
                    break;
                 
                case numero:
                    resultado += "numero\t\t" + lexer.lexeme +  "\n";
                    break;
					
		case operacion:
                    resultado += "operacion\t\t" + lexer.lexeme +  "\n";
                    break;
                    
                case salto_linea:
                    resultado += "salto de Linea\t" + lexer.lexeme +  "\n";
                    break;
                    
                case correo:
                    resultado += "correo\t\t" + lexer.lexeme +  "\n";
                    break;
                                      
                case listas:
                    resultado += "lista\t" + lexer.lexeme +  "\n";
                    break;
                    
                case listas_item: 
                    resultado += "item\t" + lexer.lexeme +  "\n";
                    break;
                    
                 case negrita: 
                    resultado += "negrita\t" + lexer.lexeme +  "\n";
                    break;
                    
                case vinculo: 
                    resultado += "vinculo\t\t" + lexer.lexeme +  "\n";
                    break;
                
                case direccion: 
                    resultado += "url\t\t" + lexer.lexeme +  "\n";
                    break;                    
                    
                case url_local:
                     resultado += "url_local\t" + lexer.lexeme +  "\n";
                    break;
                    
                case imagen:
                    resultado += "imagen\t" + lexer.lexeme +  "\n";
                    break;
                            
                case ERROR:
                    resultado += "<Simbolo no definido>\n";
                    break;
                default:
                    resultado += "  < " + lexer.lexeme + " >\n";
                    break;
            }
        }
    }
       
       
       private void htmlComver() throws IOException{
        int cont = 1;
        
        String expr = (String) txtResultado.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = " ";
              
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txthtml.setText(resultado);
                return;
            }
            
            //lo que trae el token
             String dato = lexer.lexeme;
            
             switch (token) {
                /*case Linea:
                    cont++;
                break;*/
				
                case tipo_documento:
                    resultado += "<!DOCTYPE html>\n";
                    break;
                    
                case elementos_raiz:
                   switch (dato){
                        case "INICIO_HTML":
                            resultado += "<html>\n";
                        break;
                        
                        case "FINAL_HTML":
                            resultado += "</html>\n";
                        break;
                     
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }  
                break;
					
                case metadatos_cabecera:
                    switch (dato){
                        case "INICIO_CABECERA":
                            resultado += "<head>\n";
                        break;
                        
                        case "FINAL_CABECERA":
                            resultado += "</head>\n";
                        break;
                     
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }  
                break;
                
                case metadatos_titulo:
                    switch (dato){
                        case "INICIO_TITULO":
                            resultado += "<title>\n";
                        break;
                        
                        case "FINAL_TITULO":
                            resultado += "</title>\n";
                        break;
                     
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }  
                break;
                
                case secciones_cuerpo:
                    switch (dato){
                        case "INICIO_CUERPO":
                            resultado += "<body>\n";
                        break;
                        
                        case "FINAL_CUERPO":
                            resultado += "</body>\n";
                        break;
                     
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }  
                break;
                
                case secciones_encabezado:
                    switch (dato){
                        case "INICIO_ENCABEZADO 1":
                            resultado += "<h1>\n";
                        break;
                        
                        case "FINAL_ENCABEZADO 1":
                            resultado += "</h1>\n";
                        break;
                        
                        case "INICIO_ENCABEZADO 2":
                            resultado += "<h2>\n";
                        break;
                        
                        case "FINAL_ENCABEZADO 2":
                            resultado += "</h2>\n";
                        break;
                        
                        case "INICIO_ENCABEZADO 3":
                            resultado += "<h3>\n";
                        break;
                        
                        case "FINAL_ENCABEZADO 3":
                            resultado += "</h3>\n";
                        break;
                        
                        case "INICIO_ENCABEZADO 4":
                            resultado += "<h4>\n";
                        break;
                        
                        case "FINAL_ENCABEZADO 4":
                            resultado += "</h4>\n";
                        break;
                        
                        case "INICIO_ENCABEZADO 5":
                            resultado += "<h5>\n";
                        break;
                        
                        case "FINAL_ENCABEZADO 5":
                            resultado += "</h5>\n";
                        break;
                        
                        case "INICIO_ENCABEZADO 6":
                            resultado += "<h6>\n";
                        break;
                        
                        case "FINAL_ENCABEZADO 6":
                            resultado += "</h6>\n";
                        break;
                     
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }  
                break;
                
                case agrupacion_contenido:
                    switch (dato){
                        case "INICIO_PARRAFO":
                            resultado += "<p>\n";
                        break;
                        
                        case "FINAL_PARRAFO":
                            resultado += "</p>\n";
                        break;
                     
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }  
                break;
                
                case informacion_tabulada:
                    switch (dato){
                        case "INICIO_TABLA":
                            resultado += "<table>\n";
                        break;
                        
                        case "INICIO_TABLA BORDE":
                            resultado += "<table border>\n";
                        break;
                        
                        case "INICIO_TABLA BORDE 1":
                            resultado += "<table border=1>\n";
                        break;
                        
                        case "INICIO_TABLA BORDE 2":
                            resultado += "<table border=2>\n";
                        break;
                        
                        case "INICIO_TABLA BORDE 3":
                            resultado += "<table border=3>\n";
                        break;
                        
                        case "INICIO_TABLA BORDE 4":
                            resultado += "<table border=4>\n";
                        break;
                        
                        case "INICIO_TABLA BORDE 5":
                            resultado += "<table border=5>\n";
                        break;
                        
                        case "INICIO_TABLA BORDE 6":
                            resultado += "<table border=6>\n";
                        break;
                        
                        case "INICIO_TABLA BORDE 7":
                            resultado += "<table border=7>\n";
                        break;
                        
                        case "INICIO_TABLA BORDE 8":
                            resultado += "<table border=8>\n";
                        break;
                        
                        case "INICIO_TABLA BORDE 9":
                            resultado += "<table border=9>\n";
                        break;
                        
                        case "INICIO_TABLA BORDE 10":
                            resultado += "<table border=10>\n";
                        break;
                        
                        case "FINAL_TABLA":
                            resultado += "</table>\n";
                        break;
                     
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }  
                break;
                
                case atributos_tabla:
                    switch (dato){
                        case "INICIO_ENCABEZADO_TABLA":
                            resultado += "<thead>\n";
                        break;
                        
                        case "FINAL_ENCABEZADO_TABLA":
                            resultado += "</thead>\n";
                        break;
                        
                        case "INICIO_CUERPO_TABLA":
                            resultado += "<tbody>\n";
                        break;
                        
                        case "FINAL_CUERPO_TABLA":
                            resultado += "</tbody>\n";
                        break;
                        
                        case "INICIO_FILA":
                            resultado += "<tr>\n";
                        break;
                        
                        case "FINAL_FILA":
                            resultado += "</tr>\n";
                        break;
                        
                        case "INICIO_CELDA_ENCABEZADO":
                            resultado += "<th>\n";
                        break;
                        
                        case "FINAL_CELDA_ENCABEZADO":
                            resultado += "</th>\n";
                        break;
                        
                        case "INICIO_CELDA_DATOS":
                            resultado += "<td>\n";
                        break;
                        
                        case "FINAL_CELDA_DATOS":
                            resultado += "</td>\n";
                        break;
                     
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }  
                break;
					
		case texto:
                    resultado +=  lexer.lexeme +  "\n";
                    break;
					
		case operacion:

                    String operacion=lexer.lexeme;      
                    int tam = operacion.length();
                     String A[] = new String[100];
                     //Array para almacenar operadores.
                     String operaciones[] = new String[100];
                     int pos = 0;
                     int operador = 0;
                     String aux = "";

                     //inicializa primer operador.
                     operaciones[0] = "+";
                     int index_operacion = 1;
                     for(int i = 0; i<tam ; i++)
                     {
                         if(operacion.charAt(i) == '+' || operacion.charAt(i) == '-' || operacion.charAt(i) == '*' || operacion.charAt(i) == '/')
                         {
                             A[pos] = aux;
                             operaciones[index_operacion] = String.valueOf(operacion.charAt(i));
                             pos++;   
                             index_operacion++;
                             aux = "";            
                         }
                         else
                         {
                             aux = aux + operacion.charAt(i);
                         }        
                     }
                     A[pos] = aux;
                     pos++;

                     for(int i = 0; i<pos ; i++)
                     {           
                         //Determina la operación a realizar.         
                         if(operaciones[i].equals("+")){
                          operador=operador+Integer.parseInt(A[i]);    
                         }else if (operaciones[i].equals("-")){
                             operador=operador-Integer.parseInt(A[i]);            
                         }else if (operaciones[i].equals("*")){
                             operador=operador*Integer.parseInt(A[i]);            
                         }else if (operaciones[i].equals("/")){
                             operador=operador/Integer.parseInt(A[i]);            
                         }          

                     }
                     //System.out.println("=  "+sum);
                     resultado +=  operador + "\n" ;
                break;  
                
                case numero:
                    resultado +=  lexer.lexeme +  "\n";
                    break;
                
                case salto_linea:
                    resultado +=  "<br>\n";
                    break;
                
                case correo:
                     resultado +=  lexer.lexeme +  "\n";
                    break;
                                 
                case listas:
                    switch (dato){
                        case "INICIO_LISTA_ORDENADA":
                            resultado += "<ol>\n";
                        break;
                        
                        case "FINAL_LISTA_ORDENADA":
                            resultado += "</ol>\n";
                        break;
                        
                        case "INICIO_LISTA_DESORDENADA":
                            resultado += "<ul>\n";
                        break;
                        
                        case "FINAL_LISTA_DESORDENADA":
                            resultado += "</ul>\n";
                        break;
                                           
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }
                break;
                    
                case listas_item:
                    switch (dato){
                        case "INICIO_LISTA_ITEM":
                            resultado += "<li>\n";
                        break;
                        
                        case "FINAL_LISTA_ITEM":
                            resultado += "</li>\n";
                        break;
                                           
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }
                break;  
                
                case negrita: 
                  switch (dato){
                        case "INICIO_NEGRITA":
                            resultado += "<strong>\n";
                        break;
                        
                        case "FINAL_NEGRITA":
                            resultado += "</strong>\n";
                        break;
                                           
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }
                break;
                    
                case direccion:
                    resultado +=  lexer.lexeme + ">"+ "\n";
                    break;
                    
                case vinculo: 
                    switch (dato){
                        case "INICIO_VINCULO":
                        resultado += "<a href= >\n";
                        break;
                        
                        case "INICIO_VINCULO ATRIBUTO":
                        resultado += "<a href=  ";
                        break;
                            
                        case "FINAL_VINCULO":
                            resultado += "</a>\n";
                        break;
                                           
                        default:
                            resultado += " Ninguna Considencia \n";
                        break;
                    }
                break;
              
                    
                case url_local:
                    resultado +=  lexer.lexeme + ">"+ "\n";
                    break;   
                        
                case imagen:
                     resultado += "<img src= ";
                    break;
                        
                case ERROR:
                    resultado += "  <Simbolo no definido>\n";
                    break;
                    
                default:
                    resultado += "  < " + lexer.lexeme + " >\n";
                    break;
            }
        }
    }
             
       
  
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAnalizarLex = new javax.swing.JButton();
        btnArchivo = new javax.swing.JButton();
        btnLimpiarLex = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnalizarLex = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAnalizarSin = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txthtml = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAnalizarLex.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAnalizarLex.setText("Analizar");
        btnAnalizarLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarLexActionPerformed(evt);
            }
        });

        btnArchivo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnArchivo.setText("Abrir archivo");
        btnArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivoActionPerformed(evt);
            }
        });

        btnLimpiarLex.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLimpiarLex.setText("Limpiar");
        btnLimpiarLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarLexActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Texto de Entrada", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analisis Lexico", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        txtAnalizarLex.setEditable(false);
        txtAnalizarLex.setColumns(20);
        txtAnalizarLex.setRows(5);
        jScrollPane2.setViewportView(txtAnalizarLex);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analisis Sintactico", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        txtAnalizarSin.setEditable(false);
        txtAnalizarSin.setColumns(20);
        txtAnalizarSin.setRows(5);
        jScrollPane3.setViewportView(txtAnalizarSin);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HTML", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        txthtml.setEditable(false);
        txthtml.setColumns(20);
        txthtml.setRows(5);
        jScrollPane4.setViewportView(txthtml);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAnalizarLex)
                                .addGap(33, 33, 33)
                                .addComponent(btnLimpiarLex)
                                .addGap(27, 27, 27)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiarLex)
                    .addComponent(btnAnalizarLex)
                    .addComponent(btnArchivo)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivoActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File archivo = new File(chooser.getSelectedFile().getAbsolutePath());
        
        try {
            String ST = new String(Files.readAllBytes(archivo.toPath()));
            txtResultado.setText(ST);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try {
            String ST = new String(Files.readAllBytes(archivo.toPath()));
            txtResultado.setText(ST);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnArchivoActionPerformed

    private void btnLimpiarLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarLexActionPerformed
        // TODO add your handling code here:
        txtAnalizarLex.setText(null);
        txtAnalizarSin.setText(null);
    }//GEN-LAST:event_btnLimpiarLexActionPerformed

    private void btnAnalizarLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarLexActionPerformed
        //////////////analisis lexico//////////
        try {
            analizarLexico();
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            htmlComver();
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /////////////////////analisis sintactico//////////
        String ST = txtResultado.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(ST)));
        
        try {
            s.parse();
            txtAnalizarSin.setText("Analisis realizado correctamente");
            txtAnalizarSin.setForeground(new Color(25, 111, 61));
        } catch (Exception ex) {
            Symbol sym = s.getS();
            txtAnalizarSin.setText("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
            txtAnalizarSin.setForeground(Color.red);
        }
       
                
    }//GEN-LAST:event_btnAnalizarLexActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try {
        JFileChooser archivo = new JFileChooser(System.getProperty("user.dir"));
        archivo.showSaveDialog(this);
        if (archivo.getSelectedFile() != null) {
        try (FileWriter guardado = new FileWriter(archivo.getSelectedFile())) {
        guardado.write(txthtml.getText());
        JOptionPane.showMessageDialog(rootPane, "El archivo fue guardado con éxito en la ruta establecida");
        }
        }
        } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizarLex;
    private javax.swing.JButton btnArchivo;
    private javax.swing.JButton btnLimpiarLex;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea txtAnalizarLex;
    private javax.swing.JTextArea txtAnalizarSin;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JTextArea txthtml;
    // End of variables declaration//GEN-END:variables

   
}
