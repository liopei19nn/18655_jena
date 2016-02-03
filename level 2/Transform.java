import java.io.File;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public class Transform {
    public static void main(String[] args) throws TransformerException {
        String stylesheetPathname = args[0];
        String inputPathname = args[1];
        String outputPathname = args[2];

        TransformerFactory factory = TransformerFactory.newInstance();
        Source stylesheetSource = new StreamSource(new File(stylesheetPathname).getAbsoluteFile());
        Transformer transformer = factory.newTransformer(stylesheetSource);
        Source inputSource = new StreamSource(new File(inputPathname).getAbsoluteFile());
        Result outputResult = new StreamResult(new File(outputPathname).getAbsoluteFile());
        transformer.transform(inputSource, outputResult);
    }
}
