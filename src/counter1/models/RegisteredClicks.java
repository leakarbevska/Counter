/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counter1.models;

import counter1.utils.StorageHelper;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Leya
 */
public class RegisteredClicks implements Serializable {

    public ArrayList<Long> increments;

    public RegisteredClicks() {
        increments = new ArrayList<>();
    }

    public void increment() {
        increments.add(System.currentTimeMillis());
        saveToStorage();
    }
    
    public int getIncrementCountInLast(int millisecconds) {
        long searchDate = System.currentTimeMillis() - millisecconds;
        return increments.stream().filter(increment -> increment > searchDate).collect(Collectors.toList()).size();
    }

    public static RegisteredClicks getFromStorage() {
        RegisteredClicks registeredClicks = (RegisteredClicks) StorageHelper.loadFromStorage(RegisteredClicks.class);
        if (registeredClicks != null) {
            return (RegisteredClicks) StorageHelper.loadFromStorage(RegisteredClicks.class);
        }
        return new RegisteredClicks();
    }

    public void saveToStorage() {
        StorageHelper.asyncSaveToStorage(this);
    }
}
