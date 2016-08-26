package com.xsynergy.schemacomposer.model.xsd;

import com.xsynergy.schemacomposer.model.Generator;
import com.xsynergy.schemacomposer.model.Model;

import java.io.OutputStream;

import org.w3c.dom.Document;

public class XsdGenerator extends Generator {
    
    private static final String kPARAM_IGNORE_RESOLVERS = "ignore-resolvers";
    private static final String kXSDEXTENSION = ".xsd";
    
    Document doc = null;
    
    public XsdGenerator(Model model) {
        super(model);
        
        doc = getXMLDocument();
    }

    @Override
    public void write(OutputStream stream) {
        // TODO Implement this method
    }
}
