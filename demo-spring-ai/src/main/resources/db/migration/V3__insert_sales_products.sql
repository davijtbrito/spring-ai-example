-- V3__Insert_sample_data.sql

-- ========================================
-- Insert Products (20 sample products)
-- ========================================
INSERT INTO products (name, description, price, stock_quantity, sku, active) VALUES
('Wireless Headphones', 'Noise cancelling Bluetooth headphones', 89.99, 150, 'WH-001', true),
('Smart Watch Pro', 'Fitness tracking smartwatch', 199.99, 80, 'SW-002', true),
('USB-C Laptop Charger', '65W fast charging adapter', 29.99, 300, 'UC-003', true),
('Mechanical Keyboard', 'RGB backlit mechanical keyboard', 79.99, 120, 'KB-004', true),
('4K Webcam', 'Professional streaming webcam', 69.99, 95, 'WC-005', true),
('Gaming Mouse', 'Wireless ergonomic gaming mouse', 49.99, 200, 'GM-006', true),
('Portable SSD 1TB', 'External solid state drive', 119.99, 60, 'SSD-007', true),
('Bluetooth Speaker', 'Waterproof portable speaker', 45.99, 180, 'BS-008', true),
('Office Chair', 'Ergonomic mesh office chair', 249.99, 40, 'OC-009', true),
('Monitor 27 inch', '4K IPS monitor', 349.99, 55, 'MN-010', true),
('Laptop Backpack', 'Water resistant laptop bag', 39.99, 250, 'BP-011', true),
('Power Bank 20000mAh', 'Fast charging power bank', 34.99, 320, 'PB-012', true),
('Wireless Earbuds', 'True wireless earbuds with ANC', 59.99, 140, 'WE-013', true),
('Tablet 10 inch', 'Android tablet with stylus', 229.99, 70, 'TB-014', true),
('Printer Ink Set', 'Compatible ink cartridges', 24.99, 400, 'INK-015', true);

-- ========================================
-- Insert 200 Sales Records
-- ========================================

INSERT INTO sales (product_id, quantity, total_amount, customer_name, notes, sale_date)
SELECT 
    product_id,
    quantity,
    ROUND(price * quantity, 2) as total_amount,
    customer_name,
    notes,
    sale_date
FROM (
    SELECT 
        p.id as product_id,
        p.price,
        (FLOOR(RANDOM() * 5) + 1)::int as quantity,
        ('Customer ' || FLOOR(RANDOM()*900 + 100)) as customer_name,
        CASE 
            WHEN RANDOM() > 0.7 THEN 'Online Order'
            WHEN RANDOM() > 0.5 THEN 'In-store purchase'
            ELSE NULL 
        END as notes,
        CURRENT_DATE - (FLOOR(RANDOM() * 180))::int as sale_date
    FROM products p
    CROSS JOIN generate_series(1, 14) as series  -- This will generate ~210 rows
) as sales_data
LIMIT 200;