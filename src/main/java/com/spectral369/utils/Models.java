package com.spectral369.utils;

import java.util.LinkedList;
import java.util.List;


public enum Models {

    Cerere_de_restituire(1, "Cerere de Restituire","com.spectral369.CDR.CDRInfo"),
	Declaratie_de_conrespondenta(2, "Declaratie de Corespondenta", "com.spectral369.DDC.DDCInfo");
	
	private java.lang.String cls;
    private java.lang.String name;
    private java.lang.Integer id;
        
    Models(Integer id, java.lang.String name,java.lang.String cls) {
        this.name = name;
        this.id = id;
        this.cls = cls;
    }
    
	public static java.lang.String getClassName(Integer id){
    	  for (Models e : values()) {
              if (e.id.equals(id)) {
                  return e.cls;
              }
          }
		return null;
    }

    public java.lang.String getName() {
        return name;
    }

    public java.lang.Integer getId() {
        return id;
    }

    public static Models getById(Integer id) {
        for (Models e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return null;

    }

    public static List<String> getNames() {
        List<String> list =  new LinkedList<>();
        for (Models e : values()) {
           list.add(e.getName());
        }
        return list;

    }
    
    public static boolean isContained(String name){
       for(Models e:values()){
           if(e.name.equals(name))
               return true;
       }
       return false;
    }
    
    public static int getIDFromName(String name) {
    	 for(Models e:values()){
             if(e.name.equals(name))
                 return e.id;
         }
         return -1;
    }
    
    public static String getName(String name){
          for(Models e:values()){
           if(e.name.equals(name))
               return e.name;
       }
       return null;
    }
        
}

