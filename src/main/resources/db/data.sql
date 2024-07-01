
-- Insert data in table USERS
INSERT INTO test.users (id,creation_date,email,is_active,last_login_date,modification_date,name,password,user_token) VALUES
	 (0x0858EF3CAC8049CD8F89AA3DD673D4EC,'2024-06-30 21:16:24','juan2@rodriguez.org',1,'2024-07-01 02:16:08','2024-07-01 02:16:08','Juan Rodriguez 2','hunter2','service-dummy-token'),
	 (0x9F46B9546AA54B87ACE8F8008E3FD748,'2024-07-01 03:25:40','carlos@lozano.org',1,'2024-07-01 03:25:40','2024-07-01 03:25:40','Carlos Lozano','hunter3','service-dummy-token'),
	 (0xD837F0E4B4CB4E7E9E5A238C9A4D8EEC,'2024-07-01 03:26:58','erika@martinez.org',1,'2024-07-01 03:26:58','2024-07-01 03:26:58','Erika Martinez','hunter4','service-dummy-token');


-- Insert data in table PHONES
INSERT INTO test.phones (`number`,city_code,country_code,user_id) VALUES
	 ('1234567','1','57',0x0858EF3CAC8049CD8F89AA3DD673D4EC),
	 ('345677','4','57',0x9F46B9546AA54B87ACE8F8008E3FD748),
	 ('74528','2','58',0xD837F0E4B4CB4E7E9E5A238C9A4D8EEC);
