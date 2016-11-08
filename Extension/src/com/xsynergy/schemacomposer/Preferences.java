package com.xsynergy.schemacomposer;

import oracle.javatools.data.HashStructure;
import oracle.javatools.data.HashStructureAdapter;
import oracle.javatools.data.PropertyStorage;

public final class Preferences
  extends HashStructureAdapter
{
  private static final String DATA_KEY = "com.xsynergy.schemacomposer.Preferences";
  
  private Preferences(HashStructure hashStructure)
  {
    super(hashStructure);
  }
  
  public static Preferences getInstance(PropertyStorage prefs)
  {
   // findOrCreate makes sure the HashStructure is not null.  If it is null, a
   // new empty HashStructure is created and the default property values will
   // be determined by the getters below.
    return new Preferences(findOrCreate(prefs, DATA_KEY));
  }
  
  private static final String NAMESPACE_NAME = "NAMESPACE";   
  
  							// 2016107 Fix #22
  private static final String DEFAULT_NAMESPACE_NAME = "http://schemacomposer.xsynergy.com/v1"; 

  public String getNamespace()
  {
    return _hash.getString(NAMESPACE_NAME, DEFAULT_NAMESPACE_NAME);
  }
  
  public void setNamespace(String namespace)
  {
    _hash.putString(NAMESPACE_NAME, namespace);
  }  
  
}
