package com.xsynergy.schemacomposer.generate;

import com.xsynergy.datamodeler.Util;
import com.xsynergy.datamodeler.Util.LogLevel;
import com.xsynergy.schemacomposer.model.EntityNode;
import com.xsynergy.schemacomposer.model.SchemaAttribute;


import com.xsynergy.schemacomposer.model.SchemaNode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;


import javax.swing.JFileChooser;

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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import oracle.ide.controls.tree.JMutableTreeNode;
import oracle.ide.controls.tree.JTreeCellData;
import oracle.ide.util.TriStateBoolean;

public class Generate
{
  oracle.ide.controls.tree.CustomJTree tree;
  
  Document doc = null;
  
  private String subViewName = null;
  
  private static final String kENTITY ="entity";
  private static final String kSUPERTYPE ="supertype";
  private static final String kNAME ="name";
  private static final String kCARDINALITY="cardinality";
  
  private static final String kATTRIBUTE ="attribute";
  private static final String kDATATYPE ="datatype";
  private static final String kSIZE ="size";
  private static final String kCOMMENT ="comment";
  private static final String kMANDATORY ="mandatory";
  private static final String kPARENTFK ="parentkey";

  private static final String kFK ="FK";

  private static final String kPARAM_IGNORE_RESOLVERS = "ignore-resolvers";

  public Generate(oracle.ide.controls.tree.CustomJTree tree)
  {
    this.tree = tree;
  }
  
  public void createModel(String subViewName, URL xsd, String extension, boolean ignoreResolvers)
  {
    this.subViewName = subViewName;
    
    JMutableTreeNode rootNode = (JMutableTreeNode)tree.getModel().getRoot();
        
    JTreeCellData cell = (JTreeCellData)rootNode.getUserObject();
    
    EntityNode rootEntity = (EntityNode)cell.getUserObject();   
    
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = null;
    try
    {
      docBuilder = docFactory.newDocumentBuilder();
    }
    catch (ParserConfigurationException e)
    {
    }

    doc = docBuilder.newDocument();

    Element rootElement = null;
    
    if(rootEntity.getType() == SchemaNode.Type.RELATIONAL)
      rootElement = doc.createElement(Generate.kENTITY);
    else
      rootElement = doc.createElement(Generate.kSUPERTYPE);
    
    Attr attrName = doc.createAttribute(Generate.kNAME);
    attrName.setValue(rootEntity.getName());
    rootElement.setAttributeNode(attrName);    
    
    doc.appendChild(rootElement);
    
    createModel(rootNode, rootElement);

    Util.log(LogLevel.DEBUG, getModelAsXMLString());
    
    try
    {

      String outputFile = "";

      if(outputFile.length() == 0)
      {
        final JFileChooser fc = new JFileChooser();
      
        fc.setDialogTitle("Choose Directory");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      
        int returnVal = fc.showOpenDialog(null);
        
        Util.log(LogLevel.TRACE, "File chooser returned:" + returnVal);
        
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            
          File file = fc.getSelectedFile();
          outputFile = file.getAbsolutePath();
          Util.log(LogLevel.DEBUG, "User selected directory[" + outputFile + "]");
        }
        else
        {
            Util.log(LogLevel.TRACE, "User cancelled file selection");

            return;
        }
      }      
      
      String filename = outputFile + Util.FS + subViewName + extension;
      Util.log(LogLevel.TRACE, "Filename will be:["+ filename + "]");
      
      Util.log(LogLevel.TRACE, "About to create TransformerFactory ...");
      
      TransformerFactory transformerFactory = TransformerFactory.newInstance();

      Util.log(LogLevel.TRACE, "About to create DOMSource ...");

      DOMSource xmlsource = new DOMSource(doc);

      Util.log(LogLevel.TRACE, "About to create Transformer ...");
      
      Transformer xsltransformer = transformerFactory.newTransformer(new StreamSource(xsd.openStream()) );

      Util.log(LogLevel.TRACE, "Setting XSLT Params ...");
      
      xsltransformer.setParameter(Generate.kPARAM_IGNORE_RESOLVERS, ignoreResolvers==true?"true":"false");
      
      Util.log(LogLevel.TRACE, "Running XSLT ...");
      
      StreamResult resultxslt = new StreamResult(new FileOutputStream(filename));
      xsltransformer.transform(xmlsource, resultxslt);     
      
      Util.log(Util.LogLevel.INFO, "File written to " + filename);
      
    }
    catch (Exception e)
    {
      Util.log(Util.LogLevel.ERROR, "Error creating file", e);
    }
    
  }
 
  private void createModel(JMutableTreeNode rootNode,  Element root)
  {    
    for(int i=0; i< tree.getModel().getChildCount(rootNode);i++)
    {
      JMutableTreeNode node = (JMutableTreeNode)tree.getModel().getChild(rootNode, i);
      
      JTreeCellData cell = (JTreeCellData)node.getUserObject();
            
      Object obj = cell.getUserObject();
    
      if(cell.getCheckBoxState() == TriStateBoolean.TRUE || cell.getCheckBoxState() == TriStateBoolean.TRI_STATE )
      {
        if(obj instanceof SchemaAttribute)
        {
          SchemaAttribute attribute = (SchemaAttribute)obj;
          
          Element attributeElement = doc.createElement(Generate.kATTRIBUTE);
 
          Attr attrName = doc.createAttribute(Generate.kNAME);
          attrName.setValue(attribute.getName());
          attributeElement.setAttributeNode(attrName);    

         
          Attr attrDataType = doc.createAttribute(Generate.kDATATYPE);
          attrDataType.setValue(attribute.getDataType().getDatatypeAsString());
          attributeElement.setAttributeNode(attrDataType);
  
          Attr attrDataTypeSize = doc.createAttribute(Generate.kSIZE);
          attrDataTypeSize.setValue(attribute.getDataType().getSize() );
          attributeElement.setAttributeNode(attrDataTypeSize);
  
          Attr attrComment = doc.createAttribute(Generate.kCOMMENT);
          attrComment.setValue(attribute.getComments() );
          attributeElement.setAttributeNode(attrComment);  

          Attr attrMandatory = doc.createAttribute(Generate.kMANDATORY);
          attrMandatory.setValue(attribute.isMandatory()?"true":"false" );
          attributeElement.setAttributeNode(attrMandatory);   

          Attr attrFK = doc.createAttribute(Generate.kFK);
          attrFK.setValue(attribute.isForeignKey()?"true":"false" );
          attributeElement.setAttributeNode(attrFK);
          
          root.appendChild(attributeElement);
        }
        else if(obj instanceof EntityNode)
        {
          EntityNode entity = (EntityNode)obj;

          Element entityElement = null;
          
          if(entity.getType() == SchemaNode.Type.RELATIONAL)
            entityElement = doc.createElement(Generate.kENTITY);
          else
            entityElement = doc.createElement(Generate.kSUPERTYPE);
       
          Attr attrName = doc.createAttribute(Generate.kNAME);
          attrName.setValue(entity.getName());
          entityElement.setAttributeNode(attrName);    
 
          Attr attrMandatory = doc.createAttribute(Generate.kMANDATORY);
          attrMandatory.setValue(entity.isMandatory()?"true":"false" );
          entityElement.setAttributeNode(attrMandatory);   
   
          Attr attrCardinality = doc.createAttribute(Generate.kCARDINALITY);
          attrCardinality.setValue(entity.getRelationshipWithParent());
          entityElement.setAttributeNode(attrCardinality); 

          Attr attrParentFK = doc.createAttribute(Generate.kPARENTFK);
          attrParentFK.setValue(entity.getParentForeignKeyNameAsString());
          entityElement.setAttributeNode(attrParentFK); 

          Attr attrComment = doc.createAttribute(Generate.kCOMMENT);
          attrComment.setValue(entity.getComment());
          entityElement.setAttributeNode(attrComment);           
          
          root.appendChild(entityElement);
          
          createModel(node, entityElement);
        }
      }
    }
  } 
  
  public String getModelAsXMLString()
  {
    assert(doc != null) : "Model hasn't be initialised";
    
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = null;
    try
    {
      transformer = transformerFactory.newTransformer();
    }
    catch (TransformerConfigurationException e)
    {
      Util.log(Util.LogLevel.ERROR, e.toString());
    }

    DOMSource source = new DOMSource(doc);
    
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    
    StreamResult result = new StreamResult(stream);

    try
    {
      transformer.transform(source, result);
    }
    catch (TransformerException e)
    {
      Util.log(Util.LogLevel.ERROR, e.toString());
    }

    Util.log(Util.LogLevel.DEBUG, stream.toString());
    
    return stream.toString();
  }
  
}
