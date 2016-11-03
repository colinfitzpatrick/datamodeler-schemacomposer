<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:x="my:x" exclude-result-prefixes="x">
                
  <xsl:output method="text" encoding="UTF-8"/>
  <xsl:param name="ignore-resolvers" select="'true'"/>
   
  <!-- Main - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
  <xsl:template match="/">    
    <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
      <xsl:apply-templates/>
    </xs:schema>
  </xsl:template>

  <!-- Match the root entity, which can't have cardinality - - - - - - - - - - - - - -->
  <xsl:template match="/entity">{
	"$schema": "http://json-schema.org/draft-04/schema#",
    "type": "array",
    "items":
    {
    	"type": "object",
        "properties":
        {
       		<xsl:apply-templates select="*"/>
    	}
    }
}</xsl:template>
  
  <!-- Match all entities, shouldn't match root - - - - - - - - - - - - - - - - - - -->
  <xsl:template match="entity">
	
  	<xsl:variable name="entityName" select="@name"/>
  	<xsl:variable name="objectName">
	    <xsl:choose>
	    	<!--  If we have more then one child entity with the same name,choose the parent keyname, or add a numnber -->
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
 	</xsl:variable>
  	
	"<xsl:value-of select="$objectName"/>":
	{
		"type": "array",
		"description": "<xsl:value-of select="normalize-space(@comment)"/>",
		"items":
		{
			"type": "object",
			"properties":
			{
	      		<xsl:apply-templates select="*"/>,
	      		<xsl:choose>
					<xsl:when test="count(attribute[@mandatory='true']/@name) &gt; 0">
					"additionalProperties": false,
					"required":
					[
						<xsl:for-each select="attribute[@mandatory='true']/@name">
						   <xsl:text>"</xsl:text><xsl:value-of select="."/><xsl:text>"</xsl:text>
						   <xsl:if test="position() != last()">
						      <xsl:text>, </xsl:text>
						   </xsl:if>
						</xsl:for-each>
					]		
					</xsl:when>
					<xsl:otherwise>
						"additionalProperties": false
					</xsl:otherwise>
				</xsl:choose>
			}
		}
	}<xsl:if test="position() != last()">,</xsl:if>

	
  </xsl:template>
  
  <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
  <xsl:template match="attribute"> 

	"<xsl:value-of select="@name"/>":
	{
		"type": "<xsl:apply-templates select="@datatype"/>",
		"description": "<xsl:value-of select="normalize-space(@comment)"/>"
	}<xsl:if test="position() != last()">,</xsl:if>
 </xsl:template>

  <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
  
  <xsl:template match="@datatype">

	<xsl:text>string</xsl:text>  
<!--   	<xsl:variable name="datatype"><xsl:value-of select="@datatype"/></xsl:variable>
  	<xsl:text><xsl:value-of select="$datatype"/></xsl:text>
  
  	<xsl:text>
	    <xsl:choose>
	      <xsl:when test="$datatype = 'INTEGER'">number</xsl:when>
	      <xsl:when test="$datatype = 'NUMBER'">number</xsl:when>
	      <xsl:otherwise>string</xsl:otherwise>
	    </xsl:choose>
	</xsl:text> -->
  
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