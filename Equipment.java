package javaapplication2;

import java.util.*;

public class Equipment {
    String eqId;
    String eqName;
    ProcessTime pt;
    
    ArrayList<Operation> olist= new ArrayList<Operation>();
    private static Map<String,Equipment> equipMap = new HashMap<String,Equipment>();

    public Equipment(String eqId, String eqName) throws RegistrationException {
        this.eqId=eqId;
        this.eqName=eqName;
        if(!equipMap.containsKey(eqId)){
            equipMap.put(eqId,this);
        }else{
            throw new RegistrationException(
            "すでに同じOperationIDが登録済です\nID:" + eqId);
        }        
    }
    
    public void setCapacity(ProcessTime pt){
        this.pt = pt;
    }
    
    public ProcessTime totalProcessTime(){
        ProcessTime tatalProcessTime = new ProcessTime(0,"m");
        for(Operation op: olist){
            tatalProcessTime = tatalProcessTime.add(op.getProcessTime());
        }
        return tatalProcessTime;  
    }
    
    public ProcessTime getIdleTime(){
        ProcessTime idleTime = pt.deduct(totalProcessTime());
        return idleTime;  
    }
    
    public void addOperation(Operation op){
        olist.add(op);
    }
    
    public ArrayList<Operation> getOperationList(){
        return olist;
    }
    
    public static Equipment get(String id){
        return equipMap.get(id);
    }
    
    public static Map<String,Equipment> getEquipmentMap(){
        return equipMap;
    }
    
    public static List<String> keys(){ 
        List<String> list = new ArrayList<String>();
        for(String id:equipMap.keySet()){
            list.add(id);
        }
        Collections.sort(list);
        return list;
    }
       
    public String toString(){
        return "ID: " + eqId + " : 機械名: " + eqName + "  ";
    }
}
