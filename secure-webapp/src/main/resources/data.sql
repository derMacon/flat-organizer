insert into app_user (username, password, role)
values ('user1', '$2a$10$FBJBMqWqoPXc2aEKeOZoaechKfjahVlrNi6wFQwoq1KhLnqxCzqy2', 'ROLE_USER')
ON CONFLICT DO NOTHING;
