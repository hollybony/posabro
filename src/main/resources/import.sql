insert into WEB_ROLE(name,description) values('ROLE_GUEST','You are welcome to play');
insert into WEB_ROLE(name,description) values('ROLE_ADMIN','You can do whatever you want');
insert into WEB_USER(name,password,creationDate) values('guest','084e0343a0486ff05530df6c705c8bb4', CURDATE());
insert into WEB_USER(name,password,creationDate) values('admin','21232f297a57a5a743894a0e4a801fc3', CURDATE());
insert into WEB_USER_WEB_ROLE(WEB_USER_name,roles_name) values('guest','ROLE_GUEST');
insert into WEB_USER_WEB_ROLE(WEB_USER_name,roles_name) values('admin','ROLE_ADMIN');