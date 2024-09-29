import java.io.IOException;
import java.nio.file.Path;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXParseLetura {
    public static void main (String[] args) {
        Path path= Path.of("books.xml");
        leerXML(path);
    }

    private static void leerXML(Path path){
        //Crea una instancia de la fábrica de SAXParser, que se utilizará para construir un parser SAX.
        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser parser=null;
            try{
                //Se crea un objeto SAXParser. 
                parser= factory.newSAXParser();
            }catch(ParserConfigurationException|SAXException e){
                //Si hay algún problema en la configuración o creación del parser, 
                //se captura la excepción ParserConfigurationException o SAXException, 
                //y se imprimen los mensajes de error correspondientes antes de terminar el programa con System.exit(-1).
                System.err.println("No se ha podido crear el nuevo SAXParser");
                System.err.println(e.getMessage());
                System.exit(-1);
            }
            XMLReader reader=null;
            try{
                //Se obtiene un objeto XMLReader a partir del SAXParser. 
                //Este XMLReader es el encargado de procesar el archivo XML. 
                reader=parser.getXMLReader();
            }catch(SAXException e){
                //Si hay un problema al crear el XMLReader, se captura el SAXException y se imprime un mensaje de error, finalizando el programa con System.exit(-2).
                System.err.println("Error al crear el XMLReader");
                System.err.println(e.getMessage());
                System.exit(-2);
            }
            //Se asigna una instancia de MiControladoraXML como ContentHandler. 
            //Un ContentHandler es una clase que define cómo reaccionar ante los eventos que genera 
            //el parser SAX durante la lectura del XML (como cuando encuentra elementos, atributos, o texto).
            reader.setContentHandler((ContentHandler) new MiControladoraXML());
            try{
                //Inicia la lectura y el procesamiento del archivo XML. El método parse() recibe la ruta del archivo 
                //(convertida a cadena de texto) y procesa el documento línea por línea.
                reader.parse(path.toString());
            }catch(SAXException|IOException e){
                System.err.println("Error al parsear el documento:" + path.toString());
                System.err.println(e.getMessage());
                System.exit(-3);
            }
       
    }
}
