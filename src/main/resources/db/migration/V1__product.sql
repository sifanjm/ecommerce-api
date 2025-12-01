CREATE TABLE product_metadata (
    product_id VARCHAR(50) PRIMARY KEY,
    category VARCHAR(100),
    brand VARCHAR(100)
);


CREATE INDEX idx_category ON product_metadata(category);
CREATE INDEX idx_brand ON product_metadata(brand);
CREATE INDEX idx_category_brand ON product_metadata(category, brand);


CREATE TABLE shopper_product_shelf (
    shopper_id VARCHAR(50) NOT NULL,
    product_id VARCHAR(50) NOT NULL,
    relevancy_score NUMERIC(10, 2),
    PRIMARY KEY (shopper_id, product_id),
    FOREIGN KEY (product_id) REFERENCES product_metadata(product_id)
);


CREATE INDEX idx_shopper_id ON shopper_product_shelf(shopper_id);
CREATE INDEX idx_shopper_relevancy ON shopper_product_shelf(shopper_id, relevancy_score DESC);