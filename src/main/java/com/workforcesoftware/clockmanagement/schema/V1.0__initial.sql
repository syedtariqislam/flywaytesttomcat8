--  SQL SERVER SCRIPT

create table clkmgr_user (
  user_id bigint not null,
  username nvarchar(50) not null,
  password nvarchar(100) not null,
  email nvarchar(150) null,
  first_name nvarchar(255) not null,
  last_name nvarchar(255) not null,
  version bigint not null,
  constraint clkmgr_user_pk1 primary key (user_id)
);