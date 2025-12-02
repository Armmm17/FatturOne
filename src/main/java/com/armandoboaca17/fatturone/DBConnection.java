package com.armandoboaca17.fatturone;

import javafx.application.Application;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class DBConnection {
    private static final String SQL_QUERY = "SELECT " +
            "customers.customerNumber, " +
            "customers.customerName, " +
            "customers.phone, " +
            "CONCAT_WS(', ', " +
            "customers.addressLine1, " +
            "customers.addressLine2, " +
            "customers.city, " +
            "COALESCE(customers.state, ''), " +
            "customers.postalCode, " +
            "customers.country " +
            ") AS indirizzo_spedizione, " +
            "customers.salesRepEmployeeNumber, " +
            "CONCAT(employees.lastName, ' ', employees.firstName) AS responsabile, " +
            "employees.email, " +
            "orders.orderNumber, " +
            "orders.orderDate, " +
            "orderdetails.productCode, " +
            "products.productName, " +
            "orderdetails.quantityOrdered, " +
            "orderdetails.priceEach, " +
            "(orderdetails.quantityOrdered * orderdetails.priceEach) AS totale_prodotto, " +
            "CASE " +
            "WHEN products.MSRP > 0 AND orderdetails.priceEach <= products.MSRP " +
            "THEN ROUND(((products.MSRP - orderdetails.priceEach) / products.MSRP) * 100, 2) " +
            "ELSE 0 " +
            "END AS sconto_percentuale, " +
            "SUM(orderdetails.quantityOrdered * orderdetails.priceEach) OVER(PARTITION BY orders.orderNumber) AS totale_ordine " +
            "FROM customers " +
            "JOIN employees ON customers.salesRepEmployeeNumber = employees.employeeNumber " +
            "JOIN orders ON customers.customerNumber = orders.customerNumber " +
            "JOIN orderdetails ON orders.orderNumber = orderdetails.orderNumber " +
            "JOIN products ON orderdetails.productCode = products.productCode " +
            "WHERE orders.orderNumber LIKE '"+ V.ORDER_NUMBER +"'";


    public static void eseguiQuery() {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL caricato con successo.");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL non trovato nel classpath.");
            e.printStackTrace();
            return; // Esci se il driver non è disponibile
        }

        // Lista per contenere tutti gli oggetti


        try (Connection conn = DriverManager.getConnection(V.DBURL, V.DBUSER, V.DBPASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_QUERY)) {

            // Processa ogni riga del risultato
            while (rs.next()) {
                // Crea un nuovo oggetto OrdineDettaglio
                OrderDetailResult ordine = new OrderDetailResult();

                // Popola l'oggetto con i dati della query
                ordine.setCustomerNumber(rs.getInt("customerNumber"));
                ordine.setCustomerName(rs.getString("customerName"));
                ordine.setPhone(rs.getString("phone"));
                ordine.setIndirizzoSpedizione(rs.getString("indirizzo_spedizione"));
                ordine.setSalesRepEmployeeNumber(rs.getInt("salesRepEmployeeNumber"));
                ordine.setResponsabile(rs.getString("responsabile"));
                ordine.setEmail(rs.getString("email"));
                ordine.setOrderNumber(rs.getString("orderNumber"));
                ordine.setOrderDate(rs.getDate("orderDate"));
                ordine.setProductCode(rs.getString("productCode"));
                ordine.setProductName(rs.getString("productName"));
                ordine.setQuantityOrdered(rs.getInt("quantityOrdered"));
                ordine.setPriceEach(rs.getBigDecimal("priceEach"));
                ordine.setTotaleProdotto(rs.getBigDecimal("totale_prodotto"));
                ordine.setScontoPercentuale(rs.getBigDecimal("sconto_percentuale"));
                ordine.setTotaleOrdine(rs.getBigDecimal("totale_ordine"));

                // Aggiungi l'oggetto alla lista
                V.LISTAORDINI.add(ordine);
            }

            // ANALISI DEI DATI OTTENUTI
            System.out.println("=== RISULTATI DELLA QUERY ===");
            System.out.println("Numero totale di righe trovate: " + V.LISTAORDINI.size());

            if (!V.LISTAORDINI.isEmpty()) {
                // Esempio 1: Stampa di tutti gli oggetti
                System.out.println("\n--- Tutti i dettagli dell'ordine ---");
                for (OrderDetailResult ordine : V.LISTAORDINI) {
                    System.out.println(ordine.toString());
                }

                // Esempio 2: Calcolo statistiche
                System.out.println("\n--- Statistiche dell'ordine ---");
                BigDecimal totaleOrdine = V.LISTAORDINI.get(0).getTotaleOrdine();
                System.out.println("Totale ordine: €" + totaleOrdine);

                // Calcola quantità totale prodotti
                int quantitaTotale = V.LISTAORDINI.stream()
                        .mapToInt(OrderDetailResult::getQuantityOrdered)
                        .sum();
                System.out.println("Quantità totale prodotti: " + quantitaTotale);

                // Calcola sconto medio
                double scontoMedio = V.LISTAORDINI.stream()
                        .mapToDouble(o -> o.getScontoPercentuale().doubleValue())
                        .average()
                        .orElse(0.0);
                System.out.println("Sconto medio applicato: " + String.format("%.2f", scontoMedio) + "%");

                // Esempio 3: Raggruppamento per prodotto (se ci fossero più ordini)
                System.out.println("\n--- Dettaglio per prodotto ---");
                V.LISTAORDINI.forEach(o -> {
                    System.out.printf("Prodotto: %-20s | Prezzo unitario: €%7.2f | Quantità: %3d | Totale: €%8.2f%n",
                            o.getProductName(),
                            o.getPriceEach(),
                            o.getQuantityOrdered(),
                            o.getTotaleProdotto());
                });

                // Esempio 4: Accesso a un oggetto specifico
                System.out.println("\n--- Informazioni complete del primo prodotto ---");
                OrderDetailResult primo = V.LISTAORDINI.get(0);
                System.out.println("Cliente: " + primo.getCustomerName());
                System.out.println("Telefono: " + primo.getPhone());
                System.out.println("Indirizzo: " + primo.getIndirizzoSpedizione());
                System.out.println("Responsabile vendite: " + primo.getResponsabile() + " (" + primo.getEmail() + ")");
                System.out.println("Data ordine: " + primo.getOrderDate());

                // Puoi ora usare la lista come preferisci:
                // - Salvare in JSON
                // - Scrivere su file
                // - Passare a un'altra parte dell'applicazione
                // - Mostrare in una tabella GUI
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'esecuzione della query:");
            e.printStackTrace();
        }
    }
}
