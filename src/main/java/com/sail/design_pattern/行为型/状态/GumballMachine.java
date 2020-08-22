package com.sail.design_pattern.行为型.状态;

/**
 * @program: sail-java-demo
 * @description: TODO
 * @author: sail
 * @create: 2020/8/5 13:19
 */

/**
 * 口香糖机 定义它的状态 不同状态定义 状态自己的实现方法
 * 状态机里面定义了不同状态 的实现，以及从状态机里面取出下一个状态来，变更状态
 *
 */
public class GumballMachine {
    private State hasQuarterState;
    private State noQuarterState;
    private State soldState;
    private State soldOutState;

    private State state;
    private int count=0;
    public GumballMachine(int numberGumballs){
        count = numberGumballs;
        hasQuarterState = new HasQuarterState(this);
        noQuarterState = new NoQuarterState(this);
        soldOutState = new SoldOutState(this);
        soldState = new SoldState(this);
        if(numberGumballs>0){
            state = noQuarterState;
        }else {
            state = soldOutState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count -= 1;
        }
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public int getCount() {
        return count;
    }
}
