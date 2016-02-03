18655 homework10 README
Name : Li Pei
ID : lip


Remember to configure all required .jar file in folder "lib".

Level 1 :

Use top 10 article in DBLP to test Apache Jena implementation.

	1. Use TruncateFile.java. Input any DBLP file named "dblp_truncate.xml" and truncate it into "dblp.xml". It will have top 10 articles.

	2. Run DOMParser.java to turn the xml into 10Authors.rdf.

	3. Run QueryDemo.java for demo query process.


Level 2 : 

Use XSLT form from "http://www.gac-grid.org/project-products/Software/XML2RDF.html" to parse xml into rdf.

	1. In level 2 folder, translate input.xml into output.rdf using Transform.java class and styel.xsl form.

	2. You will see the output.rdf.

	3. The query output will be similar as Level 1. But the output translation style is strange, so I do not impelement the query.

	
