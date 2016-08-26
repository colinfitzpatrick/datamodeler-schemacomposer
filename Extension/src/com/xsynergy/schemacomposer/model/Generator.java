package com.xsynergy.schemacomposer.model;

import com.xsynergy.jdeveloper.Util;
import com.xsynergy.schemacomposer.model.ModelNode.Cardinality;
import com.xsynergy.util.tree.TreeObject;

import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Generator implements ModelProcessor {

    Document doc = null;

    private static final String kID = "ID";

    private static final String kENTITY = "entity";
    private static final String kSUPERTYPE = "supertype";
    private static final String kNAME = "name";
    private static final String kCARDINALITY = "cardinality";

    private static final String kATTRIBUTE = "attribute";
    private static final String kDATATYPE = "datatype";
    private static final String kSIZE = "size";
    private static final String kCOMMENT = "comment";
    private static final String kMANDATORY = "mandatory";
    private static final String kPARENTFK = "parentkey";

    private static final String kSELECTED = "selected";

    private static final String kFK = "FK";



    public Generator(Model model) {
        assert (model != null) : "Model cannot be null";
        createXML(model);
    }

    private void createXML(Model model) {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder = null;
        
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            Util.log(Util.LogLevel.ERROR, "Unable to create XML document", e);
        }

        doc = docBuilder.newDocument();

        model.ProcessModel(this);
        
        
    }

    protected Document getXMLDocument() {
        return doc;
    }

    private Element rootElement = null;

    public void process(TreeObject<ModelNode> treeNode, boolean firstNode) {

        ModelNode node = treeNode.getValue();

        Util.log(Util.LogLevel.DEBUG, "Processing node " + node);

        Element currentElement = null;

        if (node instanceof TypeNode) {
            currentElement = doc.createElement(Generator.kSUPERTYPE);
        } else {
            currentElement = doc.createElement(Generator.kENTITY);
        }

        if (firstNode) {
            if (rootElement != null)
            {
                
                rootElement.appendChild(currentElement);
            }
 
            rootElement = currentElement;
            
            if(doc.hasChildNodes() == false)
                doc.appendChild(rootElement);
        }

        Attr attrEntityID= doc.createAttribute(Generator.kID);
        attrEntityID.setValue(node.getID());
        rootElement.setAttributeNode(attrEntityID);

        Attr attrEntityName = doc.createAttribute(Generator.kNAME);
        attrEntityName.setValue(node.getName());
        rootElement.setAttributeNode(attrEntityName);

        for (ModelAttribute attribute : node.getAttributes()) {

            Element attributeElement = doc.createElement(Generator.kATTRIBUTE);

            Attr attrID = doc.createAttribute(Generator.kID);
            attrID.setValue(attribute.getID());
            attributeElement.setAttributeNode(attrID);

            Attr attrName = doc.createAttribute(Generator.kNAME);
            attrName.setValue(attribute.getName());
            attributeElement.setAttributeNode(attrName);

            Attr attrDataType = doc.createAttribute(Generator.kDATATYPE);
            attrDataType.setValue(attribute.getDataType());
            attributeElement.setAttributeNode(attrDataType);

            Attr attrDataTypeSize = doc.createAttribute(Generator.kSIZE);
            attrDataTypeSize.setValue(attribute.getSize());
            attributeElement.setAttributeNode(attrDataTypeSize);

            Attr attrComment = doc.createAttribute(Generator.kCOMMENT);
            attrComment.setValue(attribute.getComment() );
            attributeElement.setAttributeNode(attrComment);
            
            Attr attrMandatory = doc.createAttribute(Generator.kMANDATORY);
            attrMandatory.setValue(attribute.isMandatory() ? "true" : "false");
            attributeElement.setAttributeNode(attrMandatory);

            Attr attrFK = doc.createAttribute(Generator.kFK);
            attrFK.setValue(attribute.isForeignKey() ? "true" : "false");
            attributeElement.setAttributeNode(attrFK);

            Attr attrSelected = doc.createAttribute(Generator.kSELECTED);
            attrSelected.setValue(attribute.isSelected()? "true" : "false");
            attributeElement.setAttributeNode(attrSelected);
            
            currentElement.appendChild(attributeElement);
            
        }
        
        Cardinality cardinality = node.getCardinality();
        if(cardinality != null)
        {
            Attr attrMandatory = doc.createAttribute(Generator.kMANDATORY);
            attrMandatory.setValue(cardinality.getSource()=="1"?"true":"false" );
            rootElement.setAttributeNode(attrMandatory);
    
            Attr attrCardinality = doc.createAttribute(Generator.kCARDINALITY);
            attrCardinality.setValue(cardinality.getTarget() );
            rootElement.setAttributeNode(attrCardinality);
            
            Attr attrParentFK = doc.createAttribute(Generator.kPARENTFK);
            attrParentFK.setValue(node.getParentForeignKeyNameAsString());
            rootElement.setAttributeNode(attrParentFK);
        }

        Attr attrSelected = doc.createAttribute(Generator.kSELECTED);
        attrSelected.setValue(node.isSelected()? "true" : "false");
        rootElement.setAttributeNode(attrSelected);


    }

    public abstract void write(OutputStream stream);

}
