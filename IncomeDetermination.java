package javaapplication2;

import java.util.*;

public class IncomeDetermination
{
    private ArrayList<PlanLot> planlotlist ;
    private Money fixedCost;    //固定費
    private String id;
    
    public IncomeDetermination(){
    }
    
    public IncomeDetermination(String id){
        this.id = id;
    }
    
    public void setPlanLotList(ArrayList<PlanLot> planlotlist){
        this.planlotlist = planlotlist;
    }
    
    public void setFixedCost(Money fixedCost){
        this.fixedCost = fixedCost;
    }
    
    //売上高
    public Money calcTotalSales() throws UnitContradictionException,CurrencyContradictionException{
        Money total = new Money(0);
        for(PlanLot pl : planlotlist){
            total = total.add(pl.calcSales());
        }
        return total;
    }
    
    //変動費
    public Money calcVariableCost() throws UnitContradictionException,CurrencyContradictionException{
        Money variable = new Money(0);
        for(PlanLot pl : planlotlist){
            variable = variable.add(pl.calcCost());
        }
        return variable;
    }
    
    public Money getFixedCost(){
        return fixedCost;
    }
    
    //貢献利益
    public Money calcContributionMarginMoney() throws UnitContradictionException,CurrencyContradictionException{
        Money cm = calcTotalSales().deduct(calcVariableCost());
        return cm;
    }
    
    //営業利益
    public Money calcOperatingProfit() throws UnitContradictionException,CurrencyContradictionException{
        Money op = calcContributionMarginMoney().deduct(getFixedCost());
        return op;
    }
}
    
    
        