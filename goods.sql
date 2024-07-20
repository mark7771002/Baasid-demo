CREATE TABLE goods (
    _id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(128)NOT NULL,
    cr_user UUID NOT NULL,
	cr_datetime TIMESTAMP(6) WITHOUT TIME ZONE,
    up_user UUID NOT NULL,
	up_datetime TIMESTAMP(6) WITHOUT TIME ZONE,
    CONSTRAINT fk_user1
    FOREIGN KEY(cr_user) 
    REFERENCES systemUser(_id),
	CONSTRAINT fk_user2
    FOREIGN KEY(up_user) 
    REFERENCES systemUser(_id)
	
);

COMMENT ON COLUMN goods._id IS 'UUID';
COMMENT ON COLUMN goods.name IS '商品名稱';
COMMENT ON COLUMN goods.cr_user IS '創建者<br />systemUser._id的外鍵';
COMMENT ON COLUMN goods.cr_datetime IS '預設現在時間';
COMMENT ON COLUMN goods.up_user IS '更新者<br />systemUser._id的外鍵';
COMMENT ON COLUMN goods.up_datetime IS '預設現在時間';



INSERT INTO goods (name, cr_user, up_user,cr_datetime,up_datetime)
   select 'adminInsert', _id,_id ,NOW(),NOW() from systemUser where account='admin@example.com'


select * from  goods
select * from  systemUser
drop table goods
drop table systemUser

alter table goods drop constraint fk_user1;
alter table goods drop constraint fk_user2;