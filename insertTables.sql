-- Вставка тестовых данных для таблицы coffeeshop.customers
INSERT INTO coffeeshop.customers (full_name, birth_date, contact_phone, contact_email, discount) VALUES
('John Doe', '1990-01-01', '123456789', 'john@example.com', 0.05),
('Alice Smith', '1985-05-15', '987654321', 'alice@example.com', 0.10),
('Bob Johnson', '1978-10-20', '456123789', 'bob@example.com', 0.00);

-- Вставка тестовых данных для таблицы coffeeshop.menu
INSERT INTO coffeeshop.menu (type, name, price) VALUES
('Coffee', 'Espresso', 2.50),
('Tea', 'Green Tea', 2.00),
('Pastry', 'Croissant', 1.75);

-- Вставка тестовых данных для таблицы coffeeshop.staff
INSERT INTO coffeeshop.staff (full_name, contact_phone, contact_email, position) VALUES
('Mary Brown', '741852963', 'mary@example.com', 'Barista'),
('Tom Wilson', '852369741', 'tom@example.com', 'Waiter'),
('Emily Jones', '369852147', 'emily@example.com', 'Chef');

-- Вставка тестовых данных для таблицы coffeeshop.schedule
INSERT INTO coffeeshop.schedule (staff_id, day_of_week, start_time, end_time) VALUES
(1, 'Monday', '08:00:00', '16:00:00'),
(2, 'Tuesday', '09:00:00', '17:00:00'),
(3, 'Wednesday', '10:00:00', '18:00:00');

-- Вставка тестовых данных для таблицы coffeeshop.orders
INSERT INTO coffeeshop.orders (customer_id, staff_id, menu_id, order_date) VALUES
(1, 1, 1, '2024-03-15 10:00:00'),
(2, 2, 2, '2024-03-16 11:30:00'),
(3, 3, 3, '2024-03-17 12:45:00');
