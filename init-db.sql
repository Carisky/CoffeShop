-- Создание таблицы для информации о клиентах
CREATE TABLE coffeeshop.customers (
id SERIAL PRIMARY KEY,
full_name VARCHAR(100) NOT NULL,
birth_date DATE NOT NULL,
contact_phone VARCHAR(15) NOT NULL,
contact_email VARCHAR(100) NOT NULL,
discount DECIMAL(4, 3) DEFAULT 0.00
);

-- Создание таблицы для ассортимента кафе
CREATE TABLE coffeeshop.menu (
id SERIAL PRIMARY KEY,
type VARCHAR(50) NOT NULL,
name VARCHAR(50) NOT NULL,
price DECIMAL(8, 2) NOT NULL
);

-- Создание таблицы для информации о персонале
CREATE TABLE coffeeshop.staff (
id SERIAL PRIMARY KEY,
full_name VARCHAR(100) NOT NULL,
contact_phone VARCHAR(15) NOT NULL,
contact_email VARCHAR(100) NOT NULL,
position VARCHAR(20) NOT NULL
);

-- Создание таблицы для расписания работы персонала
CREATE TABLE coffeeshop.schedule (
id SERIAL PRIMARY KEY,
staff_id INT REFERENCES coffeeshop.staff(id) ON DELETE CASCADE,
day_of_week VARCHAR(15) NOT NULL,
start_time TIME NOT NULL,
end_time TIME NOT NULL
);

-- Создание таблицы для информации о заказах
CREATE TABLE coffeeshop.orders (
id SERIAL PRIMARY KEY,
customer_id INT REFERENCES coffeeshop.customers(id) ON DELETE CASCADE,
staff_id INT REFERENCES coffeeshop.staff(id) ON DELETE CASCADE,
menu_id INT REFERENCES coffeeshop.menu(id),
order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
