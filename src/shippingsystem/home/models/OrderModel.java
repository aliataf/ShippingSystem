/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippingsystem.home.models;

/**
 *
 * @author alilo
 */
public class OrderModel {

    protected int id;
    protected int number;
    protected String shippingAddress;
    protected int totalPrice;
    protected String approxTime;
    protected String status;
    private int userId;

    public OrderModel() {
    }

    public OrderModel(int number, String shippingAddress, int totalPrice, String approxTime, String status, int userId) {
        this.number = number;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.approxTime = approxTime;
        this.status = status;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getApproxTime() {
        return approxTime;
    }

    public void setApproxTime(String approxTime) {
        this.approxTime = approxTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
