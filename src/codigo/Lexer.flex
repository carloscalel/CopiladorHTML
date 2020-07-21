package codigo;    //importando paquetes
import static codigo.Tokens.*;   //importando clases

%%   //comienso directivas jflex
%class Lexer
%type Tokens

//declaracin de variables
L=[a-zA-Z]
D=[0-9]+
ignorados=[ ,\t,\r,\n,\,\#,\&,\,]+
permitidos=[\,\&,\.,\/,\-]
espacio=[ ]
operador=[\+, \-, \*, \/]
palabra= {L}{L}*

ht="https://" | "http://"

url= {ht}([A-Za-z0-9\+_\-(\W)*]+)*(\.[A-Za-z0-9\+_\-]+)*\.{palabra}"/"([A-Za-z0-9\+_\-(\W)*("/")?]+)*

%{
    public String lexeme;
%}

%%    //fin directivas jflex

//comienso de expresiones regulares

{ignorados} {/*Ignore*/}

//doctype
TIPO_DOC                                        {lexeme=yytext(); return tipo_documento;}

//html
INICIO_HTML|FINAL_HTML                          {lexeme=yytext(); return elementos_raiz;} 

//header
INICIO_CABECERA | FINAL_CABECERA                {lexeme=yytext(); return metadatos_cabecera;}

//title
INICIO_TITULO | FINAL_TITULO                    {lexeme=yytext(); return metadatos_titulo;}

//body
INICIO_CUERPO | FINAL_CUERPO                    {lexeme=yytext(); return secciones_cuerpo;}

//h1, h2, h3, h4, h5, h6
INICIO_ENCABEZADO {espacio} {D} | FINAL_ENCABEZADO {espacio} {D}    {lexeme=yytext(); return secciones_encabezado;}

//p
INICIO_PARRAFO | FINAL_PARRAFO                  {lexeme=yytext(); return  agrupacion_contenido;}

//tabla, tabla borde, tabla borde = 2 , tabla borde=2
INICIO_TABLA |              
INICIO_TABLA {espacio} BORDE|
INICIO_TABLA {espacio} BORDE {espacio} {D} |
FINAL_TABLA                                     {lexeme=yytext(); return informacion_tabulada;}

// theader, tbody, tr, th hd
INICIO_ENCABEZADO_TABLA | FINAL_ENCABEZADO_TABLA |
INICIO_CUERPO_TABLA | FINAL_CUERPO_TABLA |
INICIO_FILA | FINAL_FILA |
INICIO_CELDA_ENCABEZADO | FINAL_CELDA_ENCABEZADO |
INICIO_CELDA_DATOS | FINAL_CELDA_DATOS          {lexeme=yytext(); return atributos_tabla;}

//br
SALTO_LINEA                                     {lexeme=yytext(); return salto_linea;}

//strong
INICIO_NEGRITA | FINAL_NEGRITA                  {lexeme=yytext(); return negrita;}

//digito o numero 
("/-"{D})|{D}                   {lexeme=yytext(); return numero;}

//texto, puede ser titulo, parrafo
({palabra} (["\ "]* {palabra}*)+) |
({palabra} (["\ "]* {palabra}*)+ ["\ "]* {D}) |
({D} ["\ "]* {palabra} (["\ "]* {palabra}*)+) |
({palabra} (["\ "]* {D})* (["\ "]* {palabra}*)+)  {lexeme=yytext(); return texto;}

//operadores aritmeticos
{D} {operador} {D}({operador}{D})*         {lexeme=yytext(); return operacion;}

//CORREO ELECTRONICOS      
[\w]+ ["\@"](gmail | hotmail | outlook | yahoo | umg | upana | miumg)["\."](com | es | net | org | edu.gt) {lexeme=yytext(); return correo;}

//direccion url
["\""] {url} ["\""] {lexeme=yytext(); return direccion;}
                  
 //ENLACE
INICIO_VINCULO  | INICIO_VINCULO {espacio} ATRIBUTO |  FINAL_VINCULO     {lexeme=yytext(); return vinculo;}


//LISTAS ORDENADAS Y DESORDENADAS
INICIO_LISTA_ORDENADA | FINAL_LISTA_ORDENADA |
INICIO_LISTA_DESORDENADA | FINAL_LISTA_DESORDENADA {lexeme=yytext(); return listas;}   

//LISTAS ITEM
INICIO_LISTA_ITEM | FINAL_LISTA_ITEM {lexeme=yytext(); return listas_item;}  

// URL LOCAL 
["\""]([a-zA-Z0-9]*{espacio}*)+["\/"]([a-zA-Z0-9]*{espacio}*)+["\."](png | jpg | jpeg | gif)["\""] {lexeme=yytext(); return url_local;}

//IMAGEN LOCAL <img src="imagen/imagen.png">
INICIO_IMG {lexeme=yytext(); return imagen;}  


// <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTNelK28LOAMfhtWMrmJaxfNZpu3hAH8XpWIQFs5G2MZwef1--B&usqp=CAU">





 . {return ERROR;}