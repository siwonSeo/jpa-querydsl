CREATE DATABASE reco
;
USE reco
;

CREATE TABLE store(
store_id int PRIMARY KEY auto_increment,
store_nm varchar(100),
region_nm varchar(20),
contract_date date,
buisness_nm varchar(100)
);

CREATE TABLE collection_history(
collection_id bigint PRIMARY KEY auto_increment,
store_id int,
collection_amount int,
collection_barrel_cnt int,
attach_file_cnt int,
collection_comment varchar(500),
real_collection_date datetime,
foreign key (store_id) references store(store_id)
);

CREATE TABLE collection_picture(
picture_id bigint PRIMARY KEY auto_increment,
file_nm varchar(500),
extension enum('jpg','png','jpeg'),
collection_id bigint,
create_date datetime,
update_date datetime,
foreign key (collection_id) references collection_history(collection_id)
);