package com.armandoboaca17.fatturone;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDetailResult {
    private int customerNumber;
    private String customerName;
    private String phone;
    private String indirizzoSpedizione;
    private int salesRepEmployeeNumber;
    private String responsabile;
    private String email;
    private String orderNumber;
    private Date orderDate;
    private String productCode;
    private String productName;
    private int quantityOrdered;
    private BigDecimal priceEach;
    private BigDecimal totaleProdotto;
    private BigDecimal scontoPercentuale;
    private BigDecimal totaleOrdine;

    public OrderDetailResult(int customerNumber, String customerName, String phone,
                             String indirizzoSpedizione, int salesRepEmployeeNumber,
                             String responsabile, String email, String orderNumber,
                             Date orderDate, String productCode, String productName,
                             int quantityOrdered, BigDecimal priceEach,
                             BigDecimal totaleProdotto, BigDecimal scontoPercentuale,
                             BigDecimal totaleOrdine) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.phone = phone;
        this.indirizzoSpedizione = indirizzoSpedizione;
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
        this.responsabile = responsabile;
        this.email = email;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.productCode = productCode;
        this.productName = productName;
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.totaleProdotto = totaleProdotto;
        this.scontoPercentuale = scontoPercentuale;
        this.totaleOrdine = totaleOrdine;
    }
    // GETTER e SETTER per ogni campo
    public int getCustomerNumber() { return customerNumber; }
    public void setCustomerNumber(int customerNumber) { this.customerNumber = customerNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getIndirizzoSpedizione() { return indirizzoSpedizione; }
    public void setIndirizzoSpedizione(String indirizzoSpedizione) { this.indirizzoSpedizione = indirizzoSpedizione; }

    public int getSalesRepEmployeeNumber() { return salesRepEmployeeNumber; }
    public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) { this.salesRepEmployeeNumber = salesRepEmployeeNumber; }

    public String getResponsabile() { return responsabile; }
    public void setResponsabile(String responsabile) { this.responsabile = responsabile; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantityOrdered() { return quantityOrdered; }
    public void setQuantityOrdered(int quantityOrdered) { this.quantityOrdered = quantityOrdered; }

    public BigDecimal getPriceEach() { return priceEach; }
    public void setPriceEach(BigDecimal priceEach) { this.priceEach = priceEach; }

    public BigDecimal getTotaleProdotto() { return totaleProdotto; }
    public void setTotaleProdotto(BigDecimal totaleProdotto) { this.totaleProdotto = totaleProdotto; }

    public BigDecimal getScontoPercentuale() { return scontoPercentuale; }
    public void setScontoPercentuale(BigDecimal scontoPercentuale) { this.scontoPercentuale = scontoPercentuale; }

    public BigDecimal getTotaleOrdine() { return totaleOrdine; }
    public void setTotaleOrdine(BigDecimal totaleOrdine) { this.totaleOrdine = totaleOrdine; }

    @Override
    public String toString() {
        return String.format(
                "Ordine %s | Cliente: %s | Prodotto: %s | Quantità: %d | Totale: €%.2f | Sconto: %.2f%%",
                orderNumber, customerName, productName, quantityOrdered, totaleProdotto, scontoPercentuale
        );
    }
}
