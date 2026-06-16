ALTER TABLE tb_user
ADD loans_id BIGINT;

ALTER TABLE tb_user
ADD CONSTRAINT fk_user_loans
FOREIGN KEY (loans_id) REFERENCES tb_loans(id);