/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counter1.utils;

import counter1.listeners.IRealTimeCounter;
import counter1.models.RegisteredClicks;

/**
 *
 * @author Leya
 */
public class TimeCounter implements IRealTimeCounter {
    private static final int SECCOND = 1000;
    private static final int MINUTE = 60*1000;
    private static final int HOUR = 60*60*1000;
    private static final int DAY = 24*60*60*1000;
    
    
    private static TimeCounter instance;
    private RegisteredClicks registeredClicks;
    
    private TimeCounter(){
        registeredClicks = RegisteredClicks.getFromStorage();
    }
    
    public static TimeCounter getInstance(){
        if(instance == null){
            instance =  new TimeCounter();
        }
        return instance;
    }
    
    public IRealTimeCounter getIRealTimeCounter(){
        return this;
    }
    
    @Override
    public void increment() {
        registeredClicks.increment();
    }

    @Override
    public int getCountInLastSecond() {
        return registeredClicks.getIncrementCountInLast(SECCOND);
    }

    @Override
    public int getCountInLastMinute() {
        return registeredClicks.getIncrementCountInLast(MINUTE);
    }

    @Override
    public int getCountInLastHour() {
        return registeredClicks.getIncrementCountInLast(HOUR);
    }

    @Override
    public int getCountInLastDay() {
        return registeredClicks.getIncrementCountInLast(DAY);
    }
    
}
