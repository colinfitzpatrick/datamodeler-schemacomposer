<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:x="my:x" exclude-result-prefixes="x">
                
  <xsl:output method="text" encoding="UTF-8"/>
  <xsl:param name="ignore-resolvers" select="'true'"/>
   
   <xsl:variable name="spaces" select="'                                                                 '"/>
   
  <!-- Main - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
  <xsl:template match="/">    
    <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
      <xsl:apply-templates/>
    </xs:schema>
  </xsl:template>

  <!-- Match the root entity, which can't have cardinality - - - - - - - - - - - - - -->
  <xsl:template match="/entity">
 		<xsl:param name="indentLevel" select="1"/>
  		<xsl:variable name="indent" select="substring($spaces, 1, $indentLevel)"/>
  		<xsl:variable name="indent2" select="substring($spaces, 1, $indentLevel+3)"/>

  		<xsl:text>{&#xA;</xsl:text>
		<xsl:value-of select="$indent"/> <xsl:text>"$schema": "http://json-schema.org/draft-04/schema#",&#xA;</xsl:text>
		<xsl:value-of select="$indent"/> <xsl:text>"type": "array",&#xA;</xsl:text>
  		<xsl:value-of select="$indent"/> <xsl:text>"items":&#xA;</xsl:text>
  		<xsl:value-of select="$indent"/> <xsl:text>{&#xA;</xsl:text>
  		<xsl:value-of select="$indent2"/> <xsl:text>"type": "object",&#xA;</xsl:text>
  		<xsl:value-of select="$indent2"/> <xsl:text>"properties":&#xA;</xsl:text>
  		<xsl:value-of select="$indent2"/> <xsl:text>{&#xA;</xsl:text>
  		
   		<xsl:apply-templates select="*">
   			<xsl:with-param name="indentLevel" select="$indentLevel + 6"></xsl:with-param>
   		</xsl:apply-templates>

  		<xsl:value-of select="$indent2"/> <xsl:text>},&#xA;</xsl:text>       	
       	<xsl:value-of select="$indent2"/> <xsl:text>"additionalProperties": false&#xA;</xsl:text>
  		<xsl:value-of select="$indent"/> <xsl:text>}&#xA;</xsl:text>
		<xsl:text>}&#xA;</xsl:text>
	</xsl:template>
  
  <!-- Match all entities, shouldn't match root - - - - - - - - - - - - - - - - - - -->
  <xsl:template match="entity|supertype">
	<xsl:param name="indentLevel" select="1"/>
	<xsl:variable name="indent" select="substring($spaces, 1, $indentLevel)"/>
	<xsl:variable name="indent2" select="substring($spaces, 1, $indentLevel+3)"/>
	<xsl:variable name="indent3" select="substring($spaces, 1, $indentLevel+6)"/>
	<xsl:variable name="indent4" select="substring($spaces, 1, $indentLevel+9)"/>

  
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
  
  	<xsl:value-of select="$indent"/> <xsl:text>"</xsl:text> <xsl:value-of select="$objectName"/> <xsl:text>":&#xA;</xsl:text>
  	<xsl:value-of select="$indent"/> <xsl:text>{&#xA;</xsl:text>
	<xsl:value-of select="$indent2"/> <xsl:text>"type": "array",&#xA;</xsl:text>
	<xsl:value-of select="$indent2"/> <xsl:text>"description": "</xsl:text> <xsl:value-of select="normalize-space(@comment)"/>  <xsl:text>",&#xA;</xsl:text>
	<xsl:value-of select="$indent2"/> <xsl:text>"items":&#xA;</xsl:text>
	<xsl:value-of select="$indent2"/>  <xsl:text>{&#xA;</xsl:text>
	<xsl:value-of select="$indent3"/>  <xsl:text>"type": "object",&#xA;</xsl:text>
	<xsl:value-of select="$indent3"/>  <xsl:text>"properties":&#xA;</xsl:text>
	<xsl:value-of select="$indent3"/>  <xsl:text>{&#xA;</xsl:text>

	<xsl:apply-templates select="*">
		<xsl:with-param name="indentLevel" select="$indentLevel + 12"></xsl:with-param>
	</xsl:apply-templates>
	
	<xsl:value-of select="$indent3"/>  <xsl:text>},&#xA;</xsl:text>
	
	<xsl:if test="count(attribute[@mandatory='true']/@name) &gt; 0 or count(entity[@mandatory='true']/@name) &gt; 0">
	
		<!--  The entity has mandatory fields or entities  -->
	
		<xsl:value-of select="$indent2"/> <xsl:text>"required":</xsl:text>
		<xsl:text>[</xsl:text>
			<xsl:for-each select="attribute[@mandatory='true']/@name | entity[@mandatory='true']/@name">
			   <xsl:text>"</xsl:text><xsl:value-of select="."/><xsl:text>"</xsl:text>
			   <xsl:if test="position() != last()">
			      <xsl:text>, </xsl:text>
			   </xsl:if>
			</xsl:for-each>
		<xsl:text>],&#xA;</xsl:text>
	</xsl:if>
	
	<xsl:if test="self::supertype and count(entity[@mandatory='false']/@name) &gt; 0">
	
		<xsl:value-of select="$indent2"/> <xsl:text>"oneOf":&#xA;</xsl:text>
		<xsl:value-of select="$indent3"/> <xsl:text>[&#xA;</xsl:text>
		
		<xsl:for-each select="entity[@mandatory='false']/@name">
			<xsl:value-of select="$indent4"/> <xsl:text>{"required" : ["</xsl:text> <xsl:value-of select="."/> <xsl:text>"]}</xsl:text>
			<xsl:if test="position() != last()">
			      <xsl:text>, </xsl:text>
			</xsl:if>
			<xsl:text>&#xA;</xsl:text>
		</xsl:for-each>
		
		<xsl:value-of select="$indent3"/> <xsl:text>],&#xA;</xsl:text>
	</xsl:if>


	<xsl:value-of select="$indent2"/> <xsl:text>"additionalProperties": false&#xA;</xsl:text>

	<xsl:value-of select="$indent2"/>  <xsl:text>}&#xA;</xsl:text>
	<xsl:value-of select="$indent"/>  <xsl:text>}</xsl:text><xsl:if test="position() != last()"><xsl:text>,</xsl:text></xsl:if><xsl:text>&#xA;</xsl:text>
		
  </xsl:template>


  
  <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
  <xsl:template match="attribute"> 
	<xsl:param name="indentLevel" select="1"/>
	<xsl:variable name="indent" select="substring($spaces, 1, $indentLevel)"/>
	<xsl:variable name="indent2" select="substring($spaces, 1, $indentLevel+3)"/>

	<xsl:value-of select="$indent"/>  <xsl:text>"</xsl:text><xsl:value-of select="@name"/> <xsl:text>":&#xA;</xsl:text>
	<xsl:value-of select="$indent"/>  <xsl:text>{&#xA;</xsl:text>
	<xsl:value-of select="$indent2"/>  <xsl:text>"type": "</xsl:text>
		<xsl:call-template name="datatype">
			<xsl:with-param name="datatype"><xsl:value-of select="@datatype"/></xsl:with-param>
		</xsl:call-template>
	<xsl:text>",&#xA;</xsl:text>
	<xsl:value-of select="$indent2"/>  <xsl:text>"description": "</xsl:text><xsl:value-of select="normalize-space(@comment)"/><xsl:text>"&#xA;</xsl:text>
	<xsl:value-of select="$indent"/>  <xsl:text>}</xsl:text><xsl:if test="position() != last()"><xsl:text>,</xsl:text></xsl:if><xsl:text>&#xA;</xsl:text>

 </xsl:template>

  <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
  
  <xsl:template name="datatype">
  	<xsl:param name="datatype"></xsl:param>
    
    <xsl:choose>
      <xsl:when test="$datatype = 'INTEGER'"><xsl:text>number</xsl:text></xsl:when>
      <xsl:when test="$datatype = 'NUMBER'"><xsl:text>number</xsl:text></xsl:when>
      <xsl:otherwise>string</xsl:otherwise>
    </xsl:choose>
  
  </xsl:template>
  
  
 <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
  
</xsl:stylesheet>