package com.xsynergy.schemacomposer.model.string;

import com.xsynergy.jdeveloper.Util;
import com.xsynergy.schemacomposer.model.Generator;
import com.xsynergy.schemacomposer.model.Model;

import java.io.OutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class StringGenerator extends Generator {
    public StringGenerator(Model model) {
        super(model);
    }

    @Override
    public void write(OutputStream outputStream) {
 
        TransformerFactory tf = TransformerFactory.newInstance();
        
        Transformer transformer = null;
        try {
            transformer = tf.newTransformer();
        } catch (TransformerConfigurationException e)
        {
            Util.log(Util.LogLevel.ERROR, "Error coverting XML to String", e);
        }
                
        try {
            transformer.transform(new DOMSource(getXMLDocument()), new StreamResult(outputStream));
        }
        catch (TransformerException e) {
            Util.log(Util.LogLevel.ERROR, "Error coverting XML to String", e);
        }
                
    }
}
