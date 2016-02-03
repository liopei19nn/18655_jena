package util;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Publication;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public class DOMParser {


    private static String NS = "http://dblp.uni-trier.de/article/";

    public void buildModel(Model model) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = builder.parse("dblp.xml");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        document.getDocumentElement().normalize();
        System.out.println("Root Element : " + document.getDocumentElement().getNodeName());
        NodeList nodeList = document.getElementsByTagName("article");
        System.out.println(nodeList.getLength());



        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            // Identifying the child tag of employee encountered
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Publication pub = new Publication();

                pub.idkey = node.getAttributes().getNamedItem("key").getNodeValue();
                pub.mdate = node.getAttributes().getNamedItem("mdate").getNodeValue();

                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);
                    if (cNode.getNodeType() == Node.ELEMENT_NODE) {
                        String content = cNode.getLastChild().getTextContent().trim();

                        // replace all ' with space
                        if (content.indexOf('\'') != -1) {
                            content = content.replace("'", "''");
                        }

                        switch (cNode.getNodeName()) {
                            case "author":
                                pub.author.add(content);
                                break;
                            case "title":
                                pub.title = content;
                                break;
                            case "pages":
                                pub.pages = content;
                                break;
                            case "year":
                                pub.year = content;
                                break;
                            case "volume":
                                pub.volume = content;
                                break;
                            case "journal":
                                pub.journal = content;
                                break;
                            case "number":
                                pub.numbers = content;
                                break;
                            case "url":
                                pub.url = content;
                            case "ee":
                                pub.ee = content;
                                break;
                        }
                    }

                } // end of for and build a complete publication

                Resource resource = model.createResource(NS + pub.title);
                Property key = model.createProperty(NS + "key");
                Property mdate = model.createProperty(NS + "mdate");
                Property author = model.createProperty(NS + "author");
                Property pages = model.createProperty(NS + "pages");
                Property year = model.createProperty(NS + "year");
                Property volume = model.createProperty(NS + "volume");
                Property journal = model.createProperty(NS + "journal");
                Property number = model.createProperty(NS + "number");
                Property url = model.createProperty(NS + "url");
                Property ee = model.createProperty(NS + "ee");

                resource.addProperty(key, pub.idkey, XSDDatatype.XSDstring)
                        .addProperty(mdate, pub.mdate, XSDDatatype.XSDstring)
                        .addProperty(author, pub.authorToString(), XSDDatatype.XSDstring)
                        .addProperty(pages, pub.pages, XSDDatatype.XSDstring)
                        .addProperty(year, pub.year, XSDDatatype.XSDstring)
                        .addProperty(volume, pub.volume, XSDDatatype.XSDstring)
                        .addProperty(journal, pub.journal, XSDDatatype.XSDstring)
                        .addProperty(number, pub.numbers, XSDDatatype.XSDstring)
                        .addProperty(url, pub.url, XSDDatatype.XSDstring)
                        .addProperty(ee, pub.ee, XSDDatatype.XSDstring);

            }
        }
    }


    public static void main(String[] args) {
        DOMParser domparser = new DOMParser();

        LogCtl.setLog4j("jena-log4j.properties");

        Model model = ModelFactory.createDefaultModel();

        domparser.buildModel(model);

        String fileName = "10Authors.rdf";

        FileWriter out;
        try {
            out = new FileWriter(fileName);
            model.write(out, "RDF/XML-ABBREV");
            out.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
