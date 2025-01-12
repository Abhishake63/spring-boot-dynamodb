Here are the `curl` commands for the application running on `localhost:8080`:

### 1. **Get All Products**
```bash
curl -X GET http://localhost:8080/api/products
```

### 2. **Get Product by ID**
```bash
curl -X GET http://localhost:8080/api/products/id1
```

### 3. **Create a New Product**
```bash
curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d '{
  "id": "id7",
  "name": "Product Name",
  "price": 100.0,
  "stockCount": 5
}'
```

### 4. **Update a Product**
```bash
curl -X PUT http://localhost:8080/api/products/id7 \
-H "Content-Type: application/json" \
-d '{
  "id": "id7",
  "name": "Updated Product Name",
  "price": 150.0,
  "stockCount": 5
}'
```

### 5. **Delete a Product**
```bash
curl -X DELETE http://localhost:8080/api/products/id7
```
