package javaapplication2;

import java.util.*;

//p38
//Operationの末できる一定数量の製品
public class Lot {
    private String id;
    private Product product;
    private Quantity qt;
    private Operation op;
    private static Map<String, Lot> lotMap = new HashMap<String, Lot>();
    
    public Lot(String id){
        this.id = id;
        lotMap.put(id, this);
    }
    
    public void setProduct(Product product){
        this.product = product;
        product.setCurrentLot(this);
    }
    
    public void setQuantity(Quantity qt){
        this.qt = qt;
    }
    
    public void setOperation(Operation op){
        this.op = op;
    }
    
    public Operation getOperation(){
        return op;
    }
    
    public Quantity getQuantity(){
        return qt;
    }
    
    public Product getProduct(){
        return product;
    }
    
     public Money calcCost() throws UnitContradictionException,CurrencyContradictionException  {
        return op.calcCost();
    }
     
    public static Lot get(String id){
        return lotMap.get(id);
    }
    
    public String toString(){
        String message = "LotID:" + id +
        "\n生産品目: " + product +
        "\n生産数量: " + qt;
        return message;
    }
}
