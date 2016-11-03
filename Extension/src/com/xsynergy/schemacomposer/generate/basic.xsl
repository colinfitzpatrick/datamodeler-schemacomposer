<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:x="my:x" exclude-result-prefixes="x">
                
  <xsl:output method="xml" encoding="UTF-8" indent="yes"/>
  <xsl:param name="ignore-resolvers" select="'true'"/>
  
  <!-- Main - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
  <xsl:template match="/">    
    <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
      <xsl:apply-templates/>
    </xs:schema>
  </xsl:template>
  
  <!-- Match the root entity, which can't have cardinality - - - - - - - - - - - - - -->
  <xsl:template match="/entity">
      <xs:element name="name">
      <xsl:attribute name="name">
        <xsl:value-of select="@name"/>
      </xsl:attribute>
      <xs:complexType>
        <xs:sequence>
          <xsl:apply-templates select="*"/>
        </xs:sequence>
      </xs:complexType>
    </xs:element>
  </xsl:template>
  
  <!-- Match all entities, shouldn't match root - - - - - - - - - - - - - - - - - - -->
  <xsl:template match="entity">

    <xsl:choose>
      <xsl:when test="$ignore-resolvers = 'true' and count(attribute[@FK='true']) = count(attribute)">
    
      <xsl:comment>Removed resolver <xsl:value-of select="@name"/></xsl:comment>
      <xsl:apply-templates select="*"/>
    
    </xsl:when>
      <xsl:otherwise>

      <xsl:variable name="entityName" select="@name"/>
      <xsl:comment>
        <xsl:value-of select="@name"/>-<xsl:value-of select="count(../entity[@name=$entityName])"/>
      </xsl:comment>
    
     <xs:element>
          <xsl:attribute name="name">
          
          <xsl:choose>
              <xsl:when test="count(../entity[@name=$entityName]) > 1">
              <xsl:choose>
                  <xsl:when test="@parentkey != ''">
                  <xsl:value-of select="translate(@parentkey, ' ','')"/>
                </xsl:when>
                  <xsl:otherwise>
                  <xsl:value-of select="translate(@name,' ','')"/><xsl:value-of select="position()"/>
                </xsl:otherwise>
                </xsl:choose>
            </xsl:when>
              <xsl:otherwise>
              <xsl:value-of select="translate(@name,' ','')"/>
            </xsl:otherwise>
            </xsl:choose>
            
        </xsl:attribute>
        
        <xsl:if test="parent::entity">
        
          <xsl:attribute name="minOccurs">
            <xsl:choose>
                <xsl:when test="@mandatory = 'true'">1</xsl:when>
                <xsl:otherwise>0</xsl:otherwise>
              </xsl:choose>
          </xsl:attribute>
        
          <xsl:attribute name="maxOccurs">
            <xsl:choose>
                <xsl:when test="@cardinality ='*'">unbounded</xsl:when>
                <xsl:otherwise>
                <xsl:value-of select="@cardinality"/>
              </xsl:otherwise>
              </xsl:choose>
          </xsl:attribute>
          
        </xsl:if>
        
        <xs:complexType>
          <xs:sequence>
            <xsl:if test="parent::supertype">
              <xsl:apply-templates select="parent::node()" mode="parent_attributes"/>
            </xsl:if>
            <xsl:apply-templates select="*"/>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
      
    </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  
  <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
  <xsl:template match="attribute">
  
    <xs:element name="name">
      
      <xsl:attribute name="name">
          <xsl:value-of select="translate(@name,' ','')"/>
      </xsl:attribute>
     
	  <xsl:apply-templates select="@datatype"/>
      
      <xsl:attribute name="minOccurs">
        <xsl:choose>
          <xsl:when test="@mandatory = 'true'">1</xsl:when>
          <xsl:otherwise>0</xsl:otherwise>
        </xsl:choose>
      </xsl:attribute>
      
      <xsl:attribute name="maxOccurs">1</xsl:attribute>
      
      <xs:annotation>
        <xs:documentation>
            <xsl:value-of select="@comment"/>
          </xs:documentation>
      </xs:annotation>
      
    </xs:element>
  </xsl:template>

  <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
  
  <xsl:template match="@datatype">
  
  	<xsl:variable name="datatype"><xsl:value-of select="datatype"/></xsl:variable>
  
  	<xsl:attribute name="type">
	    <xsl:choose>
	      <xsl:when test="$datatype = 'INTEGER'">xs:integer</xsl:when>
	      <xsl:when test="$datatype = 'NUMBER'">xs:integer</xsl:when>
	      <xsl:when test="$datatype = 'TIMESTAMP'">xs:dateTime</xsl:when>
	      <xsl:when test="$datatype = 'VARCHAR2'">xs:string</xsl:when>
	      <xsl:when test="$datatype = 'CHAR'">xs:integer</xsl:when>
	      <xsl:when test="$datatype = 'DATE'">xs:date</xsl:when>
	      <xsl:when test="$datatype = 'BLOB'">xs:hexBinary</xsl:when>
	      <xsl:when test="$datatype = 'CLOB'">xs:string</xsl:when>
	      <xsl:otherwise>xs:string</xsl:otherwise>
	    </xsl:choose>
    </xsl:attribute>
  
  </xsl:template>
  
  
  <xsl:template match="supertype" mode="parent_attributes">

    <xsl:comment>here</xsl:comment>
    <xsl:comment><xsl:value-of select="@name"/></xsl:comment>

    <xsl:if test="parent::supertype">
      <xsl:apply-templates select="parent::node()" mode="parent_attributes"/>
    </xsl:if>

    <xsl:apply-templates select="attribute"/>

  </xsl:template>
  
 <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

  <xsl:template match="supertype">

    <xsl:choose>
    
      <xsl:when test="parent::supertype">
        <xsl:apply-templates select="entity"/>
        <xsl:apply-templates select="supertype"/>
      </xsl:when>
      
      
      <!--  TODO, when a supertype has no subchoices ... add it's attributes -->
      
      <xsl:otherwise>
        <xs:choice>
        
          <xsl:attribute name="minOccurs">
            <xsl:choose>
              <xsl:when test="@mandatory = 'true'">1</xsl:when>
              <xsl:otherwise>0</xsl:otherwise>
            </xsl:choose>
          </xsl:attribute>
          
          <xsl:attribute name="maxOccurs">
            <xsl:choose>
              <xsl:when test="@cardinality ='*'">unbounded</xsl:when>
              <xsl:when test="@cardinality =''">unbounded</xsl:when>
              <xsl:otherwise>
                <xsl:value-of select="@cardinality"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:attribute>
          
          <xsl:apply-templates select="entity"/>
          <xsl:apply-templates select="supertype"/>
          
        </xs:choice>
      </xsl:otherwise>
    </xsl:choose>
    
  </xsl:template>
  
</xsl:stylesheet>