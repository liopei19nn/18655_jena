/**
 *
 */
package util;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public class QueryDemo {
    private static String NS = "http://dblp.uni-trier.de/article/";
    public static void main(String[] args) {
        // log for Jena
        LogCtl.setLog4j("jena-log4j.properties");

        // get model
        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open("10Authors.rdf");
        if (in == null) {
            throw new IllegalArgumentException("File: " + "10Authors.rdf" + " not found");
        }

        // read the RDF/XML file
        model.read(new InputStreamReader(in), "");
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

        // Query 1
        String queryTitle = "On the Power of Chain Rules in Context Free Grammars.";
        System.out.println(
                "-------------------Query 1 : On the Power of Chain Rules in Context Free Grammars.-----------------------------------");
        try {
            Resource r1 = model.getResource("http://dblp.uni-trier.de/article/" + queryTitle);
            System.out.println("Title : On the Power of Chain Rules in Context Free Grammars.");
            System.out.println("key : " + r1.getProperty(key).getObject().toString());
            System.out.println("mdate : " + r1.getProperty(mdate).getObject().toString());
            System.out.println("author : " + r1.getProperty(author).getObject().toString());
            System.out.println("pages : " + r1.getProperty(pages).getObject().toString());
            System.out.println("year : " + r1.getProperty(year).getObject().toString());
            System.out.println("volume : " + r1.getProperty(volume).getObject().toString());
            System.out.println("journal : " + r1.getProperty(journal).getObject().toString());
            System.out.println("number : " + r1.getProperty(number).getObject().toString());
            System.out.println("url : " + r1.getProperty(url).getObject().toString());
            System.out.println("ee : " + r1.getProperty(ee).getObject().toString());
        } catch (Exception e) {
            System.out.println(queryTitle + " Not Exist");
        }
        // Query 2
        queryTitle = "Equational weighted tree transformations.";
        System.out.println(
                "-------------------Query 2 : Equational weighted tree transformations.-----------------------------------");
        try {
            Resource r2 = model.getResource("http://dblp.uni-trier.de/article/" + queryTitle);
            System.out.println("Title : Equational weighted tree transformations.");
            System.out.println("key : " + r2.getProperty(key).getObject().toString());
            System.out.println("mdate : " + r2.getProperty(mdate).getObject().toString());
            System.out.println("author : " + r2.getProperty(author).getObject().toString());
            System.out.println("pages : " + r2.getProperty(pages).getObject().toString());
            System.out.println("year : " + r2.getProperty(year).getObject().toString());
            System.out.println("volume : " + r2.getProperty(volume).getObject().toString());
            System.out.println("journal : " + r2.getProperty(journal).getObject().toString());
            System.out.println("number : " + r2.getProperty(number).getObject().toString());
            System.out.println("url : " + r2.getProperty(url).getObject().toString());
            System.out.println("ee : " + r2.getProperty(ee).getObject().toString());
        } catch (Exception e) {
            System.out.println(queryTitle + " Not Exist");
        }

        // Query 3
        queryTitle = "Test Not Exist.";
        System.out.println("-------------------Query 3 : Test Not Exist.-----------------------------------");
        try {
            Resource r2 = model.getResource("http://dblp.uni-trier.de/article/" + queryTitle);
            System.out.println("Title : Test Not Exist.");
            System.out.println("key : " + r2.getProperty(key).getObject().toString());
            System.out.println("mdate : " + r2.getProperty(mdate).getObject().toString());
            System.out.println("author : " + r2.getProperty(author).getObject().toString());
            System.out.println("pages : " + r2.getProperty(pages).getObject().toString());
            System.out.println("year : " + r2.getProperty(year).getObject().toString());
            System.out.println("volume : " + r2.getProperty(volume).getObject().toString());
            System.out.println("journal : " + r2.getProperty(journal).getObject().toString());
            System.out.println("number : " + r2.getProperty(number).getObject().toString());
            System.out.println("url : " + r2.getProperty(url).getObject().toString());
            System.out.println("ee : " + r2.getProperty(ee).getObject().toString());
        } catch (Exception e) {
            System.out.println(queryTitle + " Not Exist");
        }
    }
}
