/*
 * Copyright (C) 2014 Evgeniy Egorov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.apertum.qsystem.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Evgeniy Egorov
 */
public class XsltTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Where are three parameters required: xml xsl txt");
            System.err.println("URL for file ex: file:///E:/temp/Xslt/data/official_exchange_rates.xsl");
            System.exit(1);
        }

        try {
            String url = args[0];

            final Pattern pattern = Pattern.compile("##.+?##");
            final Matcher matcher = pattern.matcher(url);
            // check all occurance
            while (matcher.find()) {
                System.out.print("Start index: " + matcher.start());
                System.out.print(" End index: " + matcher.end() + " ");
                System.out.println(matcher.group());
                SimpleDateFormat sdf = new SimpleDateFormat(matcher.group().substring(2, (matcher.group().length() - 2)));
                url = url.replace(matcher.group(), sdf.format(new Date()));
            }
            System.out.println(url);

            final URL xmlIn = new URL(url);

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            //document = builder.parse(datafile);
            final Document document = builder.parse(xmlIn.openStream());

            // Use a Transformer for output
            final URL xslIn = new URL(args[1]);
            //StreamSource stylesource = new StreamSource(stylesheet);
            final StreamSource stylesource = new StreamSource(xslIn.openStream());

            final TransformerFactory tFactory = TransformerFactory.newInstance();
            final Transformer transformer = tFactory.newTransformer(stylesource);

            final DOMSource source = new DOMSource(document);
            final FileOutputStream fos = new FileOutputStream(args[2]);
            final StreamResult resultF = new StreamResult(fos);
            final StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            transformer.transform(source, resultF);
        } catch (TransformerConfigurationException tce) {
            // Error generated by the parser
            System.out.println("\n** Transformer Factory error");
            System.out.println("   " + tce.getMessage());
        } catch (TransformerException te) {
            // Error generated by the parser
            System.out.println("\n** Transformation error");
            System.out.println("   " + te.getMessage());
        } catch (SAXException sxe) {
            System.out.println("\n** Parsing error");
            System.out.println("   " + sxe.getMessage());
        } catch (ParserConfigurationException pce) {
            System.out.println("\n** Parsing configuration error");
            System.out.println("   " + pce.getMessage());
        } catch (IOException ioe) {
            System.out.println("\n** Reading or writing error");
            System.out.println("   " + ioe.getMessage());
        }
    }

}
