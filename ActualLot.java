package javaapplication2;

import java.util.*;

public class ActualLot extends Lot {
    private Lot mlot;
    
    public ActualLot(String id){
        super(id);
    }
    
    public ActualLot(String id, Lot mlot, Quantity qt){
        super(id);
        setQuantity(qt);
        setProduct(mlot.getProduct());
        this.mlot = mlot;
    }
    
    public ActualLot(String id,Lot mlot,double q){
        super(id);
        setQuantity(new Quantity(q));
        setProduct(mlot.getProduct());
        this.mlot = mlot;
    }
    
    public void setProduct(Product product){
        super.setProduct(product);
        product.setCurrentActualLot(this);
    }
    
    public StandardOperation createStandardOperation() throws UnitContradictionException,RegistrationException {
        Operation mOp = mlot.getOperation();
        StandardOperation sOp = new StandardOperation(mOp.getId()+"-STANDARD");
        sOp.setLot(this);
        double yield = mOp.getYield();
        double mult = getQuantity().dividedBy(mlot.getQuantity());
        sOp.setUsageContainer(mOp.getUsageContainer().multipliedBy(mult/yield));
        ProcessTime pt = mOp.getProcessTime().multipliedBy(getQuantity().getNumber() / (mlot.getQuantity().getNumber()));
        ProcessTime pt_yield = pt.multipliedBy(1/yield);
        sOp.setProcessTime(pt);
        sOp.setYield(yield);
        sOp.setEquipment(mOp.getEquipment());
        mOp.getEquipment().addOperation(sOp);
        return sOp; 
    }
    
    public ArrayList<StandardOperation> collectStandardOperation(ArrayList<StandardOperation> list)
    throws UnitContradictionException,RegistrationException {
       StandardOperation so = createStandardOperation();
       so.traceOperation(list);
       return list;
    }
    
    public ArrayList<StandardOperation> collectStandardOperation()
    throws UnitContradictionException,RegistrationException{
        ArrayList<StandardOperation> list = new ArrayList<StandardOperation>();
        StandardOperation so = createStandardOperation();        
        so.traceOperation(list);
        return list;
    }

    public Money calcSales() throws UnitContradictionException {
        return getProduct().getSalesPrice().multipliedBy(getQuantity());
    }
    
    public Money calcCostOfSales() throws UnitContradictionException, CurrencyContradictionException{
        return calcCost();
    }
}