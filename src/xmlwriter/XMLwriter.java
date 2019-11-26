
package xmlwriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import serializacion2.Products;
/**
 *
 * @author Alba
 */
public class XMLwriter {

    public static void main(String[] args) throws FileNotFoundException, IOException, XMLStreamException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serial.dat"));
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xsw = xof.createXMLStreamWriter(new FileWriter("products.xml"));
        
        serializacion2.Products p2 = (Products) ois.readObject(); //si se le da un valor nulo inicial no añade los valores nuevos
        
        xsw.writeStartDocument("1.0");
        xsw.writeStartElement("products");
        
        while(p2 != null) {
//            System.out.println(p2.toString());
            xsw.writeStartElement("product");
            xsw.writeAttribute("codigo", p2.getCodigo());
            xsw.writeStartElement("descricion");
            xsw.writeCharacters(p2.getDescricion());
            xsw.writeEndElement();
            xsw.writeStartElement("prezo");
            xsw.writeCharacters(String.valueOf(p2.getPrezo()));
            xsw.writeEndElement();
            xsw.writeEndElement();
            
            p2 = (Products) ois.readObject();
        }
        
        xsw.writeEndElement();
//        xsw.writeEndDocument(); //valen igual ambos
        
        ois.close();
//        xsw.flush();
        xsw.close();
    }
    
}
