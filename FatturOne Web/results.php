<?php
// Database configuration
$host = 'localhost';
$dbname = 'dipendenti';
$username = 'root';
$password = '';

// Check if orderNumber was submitted
if (!isset($_POST['orderNumber']) || empty(trim($_POST['orderNumber']))) {
    header('Location: index.php?error=Please enter an order number');
    exit();
}

$orderNumber = trim($_POST['orderNumber']);


// Create PDO connection
$dsn = "mysql:host=$host;dbname=$dbname;charset=utf8mb4";
$pdo = new PDO($dsn, $username, $password);

// Prepare the query with parameter binding
$query = "
        SELECT
            customers.customerNumber,
            customers.customerName,
            customers.phone,
            CONCAT_WS(', ',
                customers.addressLine1,
                customers.addressLine2,
                customers.city,
                COALESCE(customers.state, ''),
                customers.postalCode,
                customers.country
            ) AS indirizzo_spedizione,
            customers.salesRepEmployeeNumber,
            CONCAT(employees.lastName, ' ', employees.firstName) AS responsabile,
            employees.email,
            orders.orderNumber,
            orders.orderDate,
            orderdetails.productCode,
            products.productName,
            orderdetails.quantityOrdered,
            orderdetails.priceEach,
            (orderdetails.quantityOrdered * orderdetails.priceEach) AS totale_prodotto,
            CASE
                WHEN products.MSRP > 0 AND orderdetails.priceEach <= products.MSRP
                    THEN ROUND(((products.MSRP - orderdetails.priceEach) / products.MSRP) * 100, 2)
                ELSE 0
            END AS sconto_percentuale,
            SUM(orderdetails.quantityOrdered * orderdetails.priceEach) OVER(PARTITION BY orders.orderNumber) AS totale_ordine
        FROM customers
        JOIN employees ON customers.salesRepEmployeeNumber = employees.employeeNumber
        JOIN orders ON customers.customerNumber = orders.customerNumber
        JOIN orderdetails ON orders.orderNumber = orderdetails.orderNumber
        JOIN products ON orderdetails.productCode = products.productCode
        WHERE orders.orderNumber = :orderNumber
    ";

// Prepare and execute with named parameter
$stmt = $pdo->prepare($query);
$stmt->bindParam(':orderNumber', $orderNumber, PDO::PARAM_STR);
$stmt->execute();

// Fetch all results
$rows = $stmt->fetchAll();

// Check if any results were found
if (empty($rows)) {
    $noResults = true;
} else {
    $noResults = false;
    $firstRow = $rows[0];
}


?>

<!DOCTYPE html>
<html>
<head>
    <title>FatturOneWeb - Order #<?php echo htmlspecialchars($orderNumber); ?></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: #f5f5f5;
            color: #333;
        }
        .header {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }
        .back-btn {
            display: inline-block;
            background-color: black;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        .back-btn:hover {
            background-color: #5a6268;
        }
        h1 {
            color: black;
            margin-top: 0;
            padding-bottom: 10px;
        }
        h2 {
            color: black;
            margin-top: 30px;
            margin-bottom: 15px;
        }
        .order-info {
            background: white;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
            box-shadow: 0 1px 5px rgba(0,0,0,0.1);
        }
        .info-row {
            display: flex;
            margin-bottom: 10px;
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }
        .info-label {
            font-weight: bold;
            width: 200px;
            color: black;
        }
        .info-value {
            flex: 1;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 1px 5px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        th {
            background-color: white;
            color: black;
            text-align: left;
            padding: 12px 15px;
            border-bottom: 1px solid grey;
            font-weight: bold;
        }
        td {
            padding: 10px 15px;
            border-bottom: 1px solid grey;
        }
        tr:hover {
            background-color: #f8f9fa;
        }
        tr:last-child td {
            border-bottom: none;
        }
        .total-row {
            background-color: #e9ecef !important;
            font-weight: bold;
        }
        .discount {
            color: green;
            font-weight: bold;
        }
        .no-results {
            background: white;
            padding: 40px;
            text-align: center;
            border-radius: 8px;
            box-shadow: 0 1px 5px rgba(0,0,0,0.1);
        }

        .text-right {
            text-align: right;
        }
        .text-center {
            text-align: center;
        }
        .summary-box {
            background: #e9ecef;
            padding: 15px;
            border-radius: 4px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<a href="index.php" class="back-btn">← Back to Search</a>

<?php if ($noResults): ?>
    <div class="no-results">
        <h2>No Results Found</h2>
        <p>No order found with number: <strong><?php echo htmlspecialchars($orderNumber); ?></strong></p>
        <p><a href="index.php" style="color: #007bff;">Try another search</a></p>
    </div>
<?php else: ?>
    <div class="header">
        <h1>Order #<?php echo htmlspecialchars($firstRow['orderNumber']); ?></h1>
        <p><strong>Order Date:</strong> <?php echo date('F j, Y', strtotime($firstRow['orderDate'])); ?></p>
        <p><strong>Order Total:</strong> $<?php echo number_format($firstRow['totale_ordine'], 2); ?></p>
    </div>

    <div class="order-info">
        <h2>Customer Information</h2>
        <div class="info-row">
            <div class="info-label">Customer Name:</div>
            <div class="info-value"><?php echo htmlspecialchars($firstRow['customerName']); ?></div>
        </div>
        <div class="info-row">
            <div class="info-label">Customer Number:</div>
            <div class="info-value"><?php echo htmlspecialchars($firstRow['customerNumber']); ?></div>
        </div>
        <div class="info-row">
            <div class="info-label">Phone:</div>
            <div class="info-value"><?php echo htmlspecialchars($firstRow['phone']); ?></div>
        </div>
        <div class="info-row">
            <div class="info-label">Shipping Address:</div>
            <div class="info-value"><?php echo htmlspecialchars($firstRow['indirizzo_spedizione']); ?></div>
        </div>
        <div class="info-row">
            <div class="info-label">Sales Representative:</div>
            <div class="info-value"><?php echo htmlspecialchars($firstRow['responsabile']); ?></div>
        </div>
        <div class="info-row">
            <div class="info-label">Sales Rep Email:</div>
            <div class="info-value"><?php echo htmlspecialchars($firstRow['email']); ?></div>
        </div>
        <div class="info-row">
            <div class="info-label">Sales Rep Employee #:</div>
            <div class="info-value"><?php echo htmlspecialchars($firstRow['salesRepEmployeeNumber']); ?></div>
        </div>
    </div>

    <h2>Order Items (<?php echo count($rows); ?> items)</h2>
    <table>
        <thead>
        <tr>
            <th>Product Code</th>
            <th>Product Name</th>
            <th class="text-right">Quantity</th>
            <th class="text-right">Unit Price</th>
            <th class="text-right">Item Total</th>
            <th class="text-center">Discount %</th>
        </tr>
        </thead>
        <tbody>
        <?php
        $subtotal = 0;
        foreach ($rows as $row):
            $subtotal += $row['totale_prodotto'];
            ?>
            <tr>
                <td><?php echo htmlspecialchars($row['productCode']); ?></td>
                <td><?php echo htmlspecialchars($row['productName']); ?></td>
                <td class="text-right"><?php echo number_format($row['quantityOrdered']); ?></td>
                <td class="text-right">$<?php echo number_format($row['priceEach'], 2); ?></td>
                <td class="text-right">$<?php echo number_format($row['totale_prodotto'], 2); ?></td>
                <td class="text-center">
                    <?php if ($row['sconto_percentuale'] > 0): ?>
                        <span class="discount"><?php echo $row['sconto_percentuale']; ?>%</span>
                    <?php else: ?>
                        <span style="color: #999;">0%</span>
                    <?php endif; ?>
                </td>
            </tr>
        <?php endforeach; ?>
        <tr class="total-row">
            <td colspan="4" class="text-right"><strong>ORDER TOTAL:</strong></td>
            <td class="text-right"><strong>$<?php echo number_format($firstRow['totale_ordine'], 2); ?></strong></td>
            <td></td>
        </tr>
        </tbody>
    </table>

    <div class="summary-box">
        <p><strong>Order Summary:</strong></p>
        <p>• Total Items: <?php echo count($rows); ?></p>
        <p>• Order Total: $<?php echo number_format($firstRow['totale_ordine'], 2); ?></p>
        <p>• Order Date: <?php echo date('F j, Y', strtotime($firstRow['orderDate'])); ?></p>
        <p>• Customer: <?php echo htmlspecialchars($firstRow['customerName']); ?></p>
    </div>

    <p style="text-align: center; margin-top: 30px; color: #666;">
        <a href="index.php?order=<?php echo urlencode($orderNumber); ?>" style="color: #007bff; text-decoration: none;">
            Search another order
        </a>
    </p>
<?php endif; ?>

<div style="text-align: center; margin-top: 40px; padding-top: 20px; border-top: 1px solid #ddd; color: #999; font-size: 14px;">
    Order Search System | <?php echo date('Y'); ?>
</div>
</body>
</html>