package com.dreaming.base;

/**
 * Message:
 * <p>
 * Content:
 *
 * @author lucky
 * create on 24/08/2018
 */
public class Page {
    private int offset = 0;
    private int size = 10;
    private Object data;
    private int total = 0;
    private int index = 1;
    private int total_index = 0;

    public Object getData() {
        return data;
    }
    public int getTotal_index() {
        return total_index;
    }
    public void setTotal_index(int total_index) {
        this.total_index = total_index;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        if(total%size!=0){
            total_index = total%size + 1;
        }else{
            total_index = total%size;
        }
        this.setTotal_index(total_index);
        this.total = total;
    }
}
