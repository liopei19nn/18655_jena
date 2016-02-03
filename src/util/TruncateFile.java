/**
 *
 */
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */

public class TruncateFile {

    // input file and output truncate file
    public static final String INPUTFILENAME = "dblp_truncate.xml";
    public static final String OUTPUTFILE = "dblp.xml";
    public static final int READNODENUMBER = 10;

    public static void main(String[] args) {
        BufferedReader brReader = null; // buffer reader
        BufferedWriter brWriter = null; // buffer writer
        try {

            // IO-Redirection
            brReader = new BufferedReader(new FileReader(new File(INPUTFILENAME)));
            brWriter = new BufferedWriter(new FileWriter(new File(OUTPUTFILE)));
            int inputNodeCount = 0;

            String store;
            while (inputNodeCount < READNODENUMBER) {
                store = new String(brReader.readLine());
                // used to count first 1000 article
                if (store.startsWith("</article>")) {
                    inputNodeCount++;
                }
                brWriter.write(store + "\n");
            }

            brWriter.write("</dblp>");

        } catch (IOException e) {
            // catch IOException
            System.out.println("Error -- " + e.toString());
        } finally {
            try {
                brReader.close();
                brWriter.close();
            } catch (IOException brCloseException) {
                // catch IOExcetion for br.close()
                System.out.println("Error -- " + brCloseException.toString());
            }
        }
    }
}
