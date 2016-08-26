package com.xsynergy.jdeveloper;
/*
 *  Copyright (c) 2015, xsynergy and/or its affiliates. All rights reserved.
 */


/*
   DESCRIPTION
    <short description of component this file declares/defines>

   PRIVATE CLASSES
    <list of private classes defined - with one-line descriptions>

   NOTES
    <other useful comments, qualifications, etc.>

   MODIFIED    (MM/DD/YY)
    colin     08-Feb-2016 - Creation
 */

/**
 *  @version $Header: Util.java 08-Feb-2016.10:56:37 colin  Exp $
 *  @author  colin
 *  @since   release specific (what release of product did this appear in)
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

import java.util.Base64;

import oracle.ide.log.LogManager;

/**
 *
 * @author colin
 */
public final class Util {

  private Util(){
    super();
  }
  
  public enum LogLevel
  {
    OFF(0,""),
    DEBUG(10,"DEBUG"),
    INFO(20, "INFO"),
    ERROR(30, "ERROR"),
    WARN(40, "WARN");
    
    private int level;
    private String description;
    
    
    private LogLevel(int level, String description) {
            this.level = level;
            this.description = description;
        }
    
    public int getLevel()
    {
      return level;
    }
    
    public String getDescription()
    {
      return description;
    }
    
  }
  
  public static final String NL = System.getProperty("line.separator");
  public static final String FS = System.getProperty("file.separator");
  
  
  public static final void log(String message)
  {
    log(LogLevel.INFO, message);
  }
  
  public static final void log(LogLevel level, String message)
  {
      log(level, message, null)  ;
  }
  
  public static final void log(LogLevel level, String message, Exception e)
  {
      if(Util.curentLevel.getLevel() >= level.getLevel())
      {
        LogManager.getIdeLogWindow().log("[Schema Generator][" +level.getDescription() + "] " + 
          message + Util.NL + (e!=null?e.getMessage()+Util.NL:""));   
      }
  }

  private static LogLevel curentLevel = LogLevel.WARN;
  
  public static final void setLogLevel(LogLevel level)
  {
    Util.curentLevel = level;
  }
  
  @SuppressWarnings("oracle.jdeveloper.java.nested-assignment")
  public static String getText(URL url) throws Exception {
      URLConnection connection = url.openConnection();
      BufferedReader in = new BufferedReader(
                              new InputStreamReader(
                                  connection.getInputStream()));

      StringBuilder response = new StringBuilder();
      String inputLine;

      while ((inputLine = in.readLine()) != null) 
          response.append(inputLine);

      in.close();

      return response.toString();
  }
  
  public static String compress(String str) {
    
    assert(str != null);
    
//    ByteArrayOutputStream out = new ByteArrayOutputStream();
//    GZIPOutputStream gzip;
//        
//    try
//    {
//      gzip = new GZIPOutputStream(out);
//      gzip.write(str.getBytes());
//      gzip.close();
//    }
//    catch (IOException e)
//    {
//      return "";
//    }

    return Base64.getEncoder().encodeToString(str.getBytes());
   }

  public static String decompress(String str) {
    
    assert(str != null);
    
    return new String(Base64.getDecoder().decode(str));
    
//    StringBuffer result = new StringBuffer();
//    String decodedString = new String(Base64.getDecoder().decode(str));
//    
//    ByteArrayInputStream in = new ByteArrayInputStream(decodedString.getBytes());
//    
//    GZIPInputStream gzip;
//    
//    try
//    { 
//      gzip = new GZIPInputStream(in);
//      
//      byte[] buffer = new byte[1000];
//      
//      while(gzip.read(buffer) != -1 )
//      {
//        result.append(new String(buffer));
//      }
//    }
//    catch (IOException e)
//    {
//      Util.log(LogLevel.ERROR, e.toString());
//    }
//
//    return result.toString();

   }
  
}