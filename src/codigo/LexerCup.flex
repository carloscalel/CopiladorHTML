package codigo;
import java_cup.runtime.Symbol;

%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char

//declaracin de variables
L=[a-zA-Z]
D=[0-9]+
ignorados=[ ,\t,\r,\n,\#,\&,\,]+
permitidos=[\,\&,\.,\/,\-]
espacio=[ ]
operador=[\+, \-, \*, \/]
palabra= {L}{L}*

ht="https://" | "http://"

url= {ht}([A-Za-z0-9\+_\-(\W)*]+)*(\.[A-Za-z0-9\+_\-]+)*\.{palabra}"/"([A-Za-z0-9\+_\-(\W)*("/")?]+)*






%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

//comienso de expresiones regulares
{ignorados} {/*Ignore*/}

//doctype
TIPO_DOC {return new Symbol(sym.tipo_documento, yychar, yyline, yytext());}

//html
INICIO_HTML | FINAL_HTML {return new Symbol(sym.elementos_raiz, yychar, yyline, yytext());}                         

//header
INICIO_CABECERA | FINAL_CABECERA {return new Symbol(sym.metadatos_cabecera, yychar, yyline, yytext());}             

//title
INICIO_TITULO | FINAL_TITULO {return new Symbol(sym.metadatos_titulo, yychar, yyline, yytext());}

//body
INICIO_CUERPO | FINAL_CUERPO {return new Symbol(sym.secciones_cuerpo, yychar, yyline, yytext());}

//h1, h2, h3, h4, h5, h6
INICIO_ENCABEZADO {espacio} {D} | FINAL_ENCABEZADO {espacio} {D} {return new Symbol(sym.secciones_encabezado, yychar, yyline, yytext());}



INICIO_PARRAFO | FINAL_PARRAFO                 {return new Symbol(sym.agrupacion_contenido, yychar, yyline, yytext());} 

//tabla, tabla borde, tabla borde = 2 , tabla borde=2
INICIO_TABLA |              
INICIO_TABLA {espacio} BORDE|
INICIO_TABLA {espacio} BORDE {espacio} {D} |
FINAL_TABLA                                     {return new Symbol(sym.informacion_tabulada, yychar, yyline, yytext());}

// theader, tbody, tr, th hd
INICIO_ENCABEZADO_TABLA | FINAL_ENCABEZADO_TABLA |
INICIO_CUERPO_TABLA | FINAL_CUERPO_TABLA |
INICIO_FILA | FINAL_FILA |
INICIO_CELDA_ENCABEZADO | FINAL_CELDA_ENCABEZADO |
INICIO_CELDA_DATOS | FINAL_CELDA_DATOS          {return new Symbol(sym.atributos_tabla, yychar, yyline, yytext());} 

//br
SALTO_LINEA                                     {return new Symbol(sym.salto_linea, yychar, yyline, yytext());} 

//strong
INICIO_NEGRITA | FINAL_NEGRITA                 {return new Symbol(sym.negrita, yychar, yyline, yytext());} 

digito o numero ("/-"{D})|{D}                   {return new Symbol(sym.numero, yychar, yyline, yytext());} 

//texto, puede ser titulo, parrafo
({palabra} (["\ "]* {palabra}*)+) |
({palabra} (["\ "]* {palabra}*)+ ["\ "]* {D}) |
({D} ["\ "]* {palabra} (["\ "]* {palabra}*)+) |
({palabra} (["\ "]* {D})* (["\ "]* {palabra}*)+)  {return new Symbol(sym.texto, yychar, yyline, yytext());} 

//operadores aritmeticos
{D} {operador} {D}({operador}{D})*         {return new Symbol(sym.operacion, yychar, yyline, yytext());} 

//CORREO ELECTRONICOS      
[\w]+ ["\@"](gmail | hotmail | outlook | yahoo | umg | upana | miumg)["\."](com | es | net | org | edu.gt) {return new Symbol(sym.correo, yychar, yyline, yytext());}

//direccion url
["\""] {url} ["\""] {return new Symbol(sym.direccion, yychar, yyline, yytext());}
   






    
 //ENLACE
INICIO_VINCULO | 
INICIO_VINCULO {espacio} ATRIBUTO | 
FINAL_VINCULO {return new Symbol(sym.vinculo, yychar, yyline, yytext());} 
 
//LISTAS ORDENADAS Y DESORDENADAS
INICIO_LISTA_ORDENADA | FINAL_LISTA_ORDENADA |
INICIO_LISTA_DESORDENADA | FINAL_LISTA_DESORDENADA {return new Symbol(sym.listas, yychar, yyline, yytext());}   

//LISTAS ITEM
INICIO_LISTA_ITEM | FINAL_LISTA_ITEM {return new Symbol(sym.listas_item, yychar, yyline, yytext());} 

// URL LOCAL<img src="imagen/imagen.png"> 
["\""]([a-zA-Z0-9]*{espacio}*)+["\/"]([a-zA-Z0-9]*{espacio}*)+["\."](png | jpg | jpeg | gif)["\""] {return new Symbol(sym.url_local, yychar, yyline, yytext());}

//IMAGEN LOCAL <img src="imagen/imagen.png">
INICIO_IMG {return new Symbol(sym.imagen, yychar, yyline, yytext());}  

. {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
