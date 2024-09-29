import java.io.IOException;
import java.nio.file.Path;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParseLecturaSinControladora {
    public static void main (String[] args) {
        Path path= Path.of("books.xml");
        leerXML(path);
    }

    private static void leerXML(Path path){
        //**** 1. Crea una instancia de factory de SAXParser, que se utilizará para construir un parser SAX.
        SAXParserFactory factory=SAXParserFactory.newInstance();

        //**** 2. Se crea un objeto SAXParser. 
        SAXParser parser=null;
        try{
            parser= factory.newSAXParser();
        }catch(ParserConfigurationException|SAXException e){
            //Si hay algún problema en la configuración o creación del parser, 
            //se captura la excepción ParserConfigurationException o SAXException, 
            //y se imprimen los mensajes de error correspondientes antes de terminar el programa con System.exit(-1).
            System.err.println("No se ha podido crear el nuevo SAXParser");
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        //**** 3. Se obtiene un objeto XMLReader a partir del SAXParser. 
        XMLReader reader=null;
        try{
            //Este XMLReader llamado reader es el encargado de procesar el archivo XML. 
            reader=parser.getXMLReader();
        
            //**** 4. Se asigna una instancia de DefaultHandler. 
            //Un DefaultHandler es una clase que define cómo reaccionar ante los eventos que genera 
            //el parser SAX durante la lectura del XML (como cuando encuentra elementos, atributos, o texto).
            reader.setContentHandler( new DefaultHandler() {
            @Override
            public void startDocument() throws SAXException{
                super.startDocument();
                System.out.println("Iniciando documentos...");
            }  
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                //Este método es invocado al inicio del parsing del documento XML, es decir, cuando el parser empieza a procesar el archivo.
                //Este método es llamado cuando el parser encuentra el inicio de un elemento XML (por ejemplo, <Catalog>, <Book>, <Author>, etc.).
                //Dependiendo del nombre del elemento (qName), el switch maneja cada caso.
                //Para el elemento <Catalog>, imprime su nombre.
                //Para el elemento <Book>, imprime el nombre y el valor del atributo id.
                //Para otros elementos como <Author>, <Title>, <Genre>, etc., imprime el nombre del elemento, dejando espacio para luego mostrar el contenido textual de cada uno.

                super.startElement(uri,localName,qName,attributes);
                switch (qName) {
                    case "Catalog":
                        System.out.println(qName);
                        break;
                    case "Book":
                        System.out.print("\t" + qName + ":\t");
                        String id= attributes.getValue("id");
                        System.out.println(id);
                        break;
                    case "Author":
                        System.out.print("\t\tAutor:" + qName);
                        break;
                    case "Title":
                        System.out.print("\t\tTitle:" + qName);
                        break;
                    case "Genre":
                        System.out.print("\t\tGenre:" + qName);
                        break;
                    case "Price":
                        System.out.print("\t\tPrice:" + qName);
                        break;
                    case "PublishDate":
                        System.out.print("\t\tPublishDate:" + qName);
                        break;
                    case "Description":
                        System.out.print("\t\tDescription:" + qName);
                        break;
                    default:
                        break;
                }
                
            }
            @Override
            public void characters(char[] ch, int start, int length) throws SAXException{
                //Este método es invocado cuando el parser encuentra contenido de texto dentro de un elemento XML.
                //Convierte el texto leído desde el archivo XML en una cadena de texto (String) y lo imprime si no está vacío.
                //El método trim() elimina espacios en blanco al inicio y al final del texto.
                super.characters(ch,start,length);
                String texto= new String(ch, start, length).trim();
                if(texto.length()>0)
                    System.out.println( texto);
            }
            });
    
        }catch(SAXException e){
            System.err.println("Error al procesar el documento");
            System.err.println(e.getMessage());
            System.exit(-2);
        }

        //**** 5. Inicia la lectura y el procesamiento del archivo XML. El método parse() recibe la ruta del archivo 
        //(convertida a cadena de texto) y procesa el documento línea por línea.
        try{
            reader.parse(path.toString());
        }catch(SAXException|IOException e){
            System.err.println("Error al parsear el documento:" + path.toString());
            System.err.println(e.getMessage());
            System.exit(-3);
        }
       
    }
}

