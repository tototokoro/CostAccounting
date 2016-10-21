package javaapplication2;

import java.util.*;

//p37
public class Item {
    private String id;
    private String name;
    private Price price;
    private static HashMap<String, Item> itemmap = new HashMap<String, Item>(); //生成済インスタンスの材料IDのリストを保持
    
    public Item(String id, String name) throws RegistrationException{
        if(!itemmap.containsKey(id)){
            this.id = id;
            this.name = name;
            itemmap.put(id, this);
        }else{
            throw new RegistrationException("すでに同じItemIDが登録済みです。\nID:" + id);
        }
    }
    
    public Item(String id, String name,Price price) throws RegistrationException{
        if(!itemmap.containsKey(id)){
            this.id = id;
            this.name = name;
            this.price = price;
            itemmap.put(id, this);
        }else{
            throw new RegistrationException("すでに同じItemIDが登録済みです。\nID:" + id);
        }
    }
    
    public Item(String id, String name, double moneynumber, String currency, double quantitynumber, String quantityunit) 
    throws RegistrationException{
        if(!itemmap.containsKey(id)){
            this.id = id;
            this.name = name;
            this.price = new Price(new Money(moneynumber, currency), new Quantity(quantitynumber, quantityunit));
            itemmap.put(id, this);
        }else{
            throw new RegistrationException("すでに同じItemIDが登録済みです。\nID:" + id);
        }
    }
    
    Item(String id, String name, double moneynumber) throws RegistrationException{
        if(!itemmap.containsKey(id)){
            this.id = id;
            this.name = name;
            this.price = new Price(moneynumber);
            itemmap.put(id, this);
        } else{
            throw new RegistrationException("すでに同じIDが登録済みです\nID:" + id);
        }
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPrice(Price price){
        this.price = price;
    }
    
    public String getId(){ 
            return id; 
        }
        
    public String getName(){ 
            return name; 
    }
    
    public Price getPrice(){
        return price;
    }
    
    public static Item get(String id){
        return itemmap.get(id);
    }
    
    public static Map<String, Item> getItemMap(){
        return itemmap;
    }

    public static List<String> keys(){
        List<String> list = new ArrayList<>();
        
        for(String id: itemmap.keySet()){
            list.add(id);
        }
        Collections.sort(list);
        return list;
    }
    
    public String toString(){
        return id + ":" + name + ":" + getPrice();
    }
    
    public boolean equals(Item i){
        return id.equals(i.getId());
    }
    
    public boolean isProduct(){
        return false;
    }
    
}
