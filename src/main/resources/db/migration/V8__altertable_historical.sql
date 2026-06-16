ALTER TABLE tb_user
ADD historical_id BIGINT;

ALTER TABLE tb_user
ADD CONSTRAINT fk_user_historical
FOREIGN KEY (historical_id) REFERENCES tb_loans(id);