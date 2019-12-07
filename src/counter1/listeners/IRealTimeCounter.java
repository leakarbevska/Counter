/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counter1.listeners;

/**
 *
 * @author Leya
 */
public interface IRealTimeCounter {
   void increment();
   int getCountInLastSecond();
   int getCountInLastMinute();
   int getCountInLastHour();
   int getCountInLastDay();
}
