package com.spectral369.utils;


import java.util.LinkedList;
import java.util.List;



public enum Models {

    Cerere_de_restituire(1, "Cerere de Restituire","com.spectral369.CDR.CDRInfo"),
	Declaratie_de_conrespondenta(2, "Declaratie de Corespondenta", "com.spectral369.DDC.DDCInfo"),
	Instiintare_plata_iarba(3,"Instiintare Plata Iarba","com.spectral369.instiintareIarba.InstiintareIarbaInfo"),
	Adeverinta_radiere_auto(4,"Adeverinta Radiere Auto","com.spectral369.ARD.AdeverintaRadiereAutoInfo"),
	Process_verbal_spatii_verzi(5,"PV Intretinere Spatii Verzi","com.spectral369.PVIarba.PVIarbaInfo"),
	Inchiriere_Utilaje(6,"Inchiriere Utilaje","com.spectral369.Utilaje.InchiriereUtilajeInfo"),
	Cerere_acord_functionare(7,"Cerere Acord Functionare","com.spectral369.CEAF.CerereEmitereAutorizatieFunctionareInfo"),
	Cerere_concediu_odihna(8,"Cerere Concediu","com.spectral369.CCO.CerereConcediuOdihnaInfo"),
	Cerere_Capela(9,"Cerere Capela","com.spectral369.capela.CerereCapelaInfo"),
	Declaratie_pe_propria_raspundere(10,"Declaratie pe Propria Raspundere","com.spectral369.DPR.DeclaratiePePropriaRaspundereInfo"),
    	Declaratie_examinare_sociala(11,"Declaratie examinare sociala","com.spectral369.CES.CESInfo"),
    	Cerere_scutire_persoane_cu_handicap(12,"Declaratie Scutire Persoane cu Handicap","com.spectral369.CSPH.CerereScutirePersoaneHandicapInfo"),
    	Proces_verbal_de_predare_primire(13,"PV Predare-Primire","com.spectral369.PVPP.PVPredarePrimireInfo"),
    	Adresa_Scoatere_Evidenta_Auto(14,"Adresa Scoatere Evidenta Auto","com.spectral369.ASEA.AdresaScoatereEvidentaAutoInfo"),
    	Cerere_Scutire(15,"Cerere Scutire","com.spectral369.CS.CerereScutireInfo"),
    	Cerere_Apa(16,"Cerere Apa","com.spectral369.CA.CerereApaInfo"),
    	Adeverinta_detinere_teren(17,"Adeverinta Teren","com.spectral369.ADP.AdeverintaDetinerePamantInfo");
    	
	
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

