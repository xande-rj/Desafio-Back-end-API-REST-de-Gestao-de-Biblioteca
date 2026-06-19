INSERT INTO tb_user (
    id,
    name,
    password,
    email,
    removed,
    role,
    date_of_birth,
    created_at,
    updated_at
)
VALUES (
    1,
    'Alexandre',
    'senha123',
    'alex@email.com',
    false,
    2,
    '2000-01-01',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);