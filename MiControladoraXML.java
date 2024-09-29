import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MiControladoraXML extends DefaultHandler{
    //Esta clase actúa como un manejador de eventos para el parser SAX, que procesa un archivo XML. 
    //Implementa los métodos clave para manejar los eventos cuando el parser comienza a 
    //leer un documento XML, encuentra un elemento, o procesa el texto dentro de los elementos 

    //La clase MiControladoraXML extiende de DefaultHandler, 
    //que es una clase por defecto que implementa la interfaz ContentHandler. 
    //Esto permite sobrescribir solo los métodos que necesitamos.
    //En este caso, los métodos sobrescritos son startDocument(), startElement(), y characters().
    //Pero podriamos sobreescribir también los métodos endDocument() y endElement()
    @Override
    public void startDocument() throws SAXException{
        super.startDocument();
        System.out.println("Iniciando documentos...");
    }
    // @Override
    // public void endDocument() throws SAXException{
    // Se invocaría cuando el parser ha terminado de procesar todo el documento XML.
    //     super.endDocument();
    //     System.out.println("Finalizando documentos...");
    // }
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
    // @Override
    // public void endElement(String uri, String localName, String qName) throws SAXException{
    // Este método sería llamado cada vez que se encuentra el cierre de un elemento XML, permitiendo realizar acciones finales o limpiar el estado.    
    //     super.endElement(uri,localName,qName);
    //     System.out.println("Finalizando elemento " );
    // }


}