create table if not exists app_user (
    user_id  SERIAL PRIMARY KEY,
    username VARCHAR(500)  UNIQUE NOT NULL,
    password VARCHAR(5000) NOT NULL,
    role     VARCHAR(5000) NOT NULL
);
