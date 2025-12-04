<!DOCTYPE html>
<html>
<head>
    <title>Order Search</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: black;
            text-align: center;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: black;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid grey;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box;
        }
        input[type="text"]:focus {
            outline: none;
            border-color: grey;
        }
        .submit-btn {
            background-color: black;
            color: white;
            padding: 12px 20px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }



    </style>
</head>
<body>
<div class="container">
    <h1>FatturOne Web</h1>

    <?php if (isset($_GET['error'])): ?>
        <div >
            <?php echo htmlspecialchars($_GET['error']); ?>
        </div>
    <?php endif; ?>



    <form action="results.php" method="POST">
        <div class="form-group">
            <label for="orderNumber">Order Number:</label>
            <input type="text" id="orderNumber" name="orderNumber" required
                   placeholder="Enter order number" value="<?php echo isset($_GET['order']) ? htmlspecialchars($_GET['order']) : ''; ?>">
        </div>
        <button type="submit" class="submit-btn">Mostra Fattura</button>
    </form>

</div>
</body>
</html>