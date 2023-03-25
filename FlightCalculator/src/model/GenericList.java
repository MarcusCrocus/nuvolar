package model;

import java.util.ArrayList;

/**
 * Generic Class for storage Arrays of objects
 * @param <T>
 */
public class GenericList<T> {


    protected ArrayList<T> data;

    public GenericList() {
        data = new ArrayList<>();
    }
    public ArrayList<T>getData() {
        return data;
    }
    public void addData (T d){
        data.add(d);
    }



}
