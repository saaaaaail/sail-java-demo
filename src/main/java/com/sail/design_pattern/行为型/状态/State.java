package com.sail.design_pattern.行为型.状态;

public interface State {
    //投入分钱
    void insertQuarter();
    //退回分钱
    void ejectQuarter();
    //转动曲柄
    void turnCrank();
    //发放糖果
    void dispense();
}
