package edu.purdue.bartleyt.complex_counter;

import android.os.Parcel;
import android.os.Parcelable;

public class Counters{
    int counterValue;
    String counterName;

    public Counters (String counterName, int counterValue){
        this.counterName = counterName;
        this.counterValue = counterValue;
    }

    public String toString(){
        return counterName+": "+counterValue;
    }


    public int getCounterValue() {
        return counterValue;
    }

    public void setCounterValue(int counterValue) {
        this.counterValue = counterValue;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }
}
