use question
go
--用户表数据删除触发器
if (object_id('tgr_USER0000_delete', 'TR') is not null)
    drop trigger tgr_USER0000_delete
go
create trigger tgr_USER0000_delete
on USER0000
    for delete --删除触发
as 
	begin
		print '重置数据库自增字段中……';    
		dbcc   checkident(USER0000,RESEED,0)
		print '重置数据库自增字段成功！';
	end   
go
--系统负责人表数据删除触发器
if (object_id('tgr_SYSUSER0_delete', 'TR') is not null)
    drop trigger tgr_SYSUSER0_delete
go
create trigger tgr_SYSUSER0_delete
on SYSUSER0
    for delete --删除触发
as 
	begin
		print '重置数据库自增字段中……';    
		dbcc   checkident(SYSUSER0,RESEED,0)
		print '重置数据库自增字段成功！';
	end   
go
--系统表数据删除触发器
if (object_id('tgr_SYSTEMS0_delete', 'TR') is not null)
    drop trigger tgr_SYSTEMS0_delete
go
create trigger tgr_SYSTEMS0_delete
on SYSTEMS0
    for delete --删除触发
as 
	begin
		print '重置数据库自增字段中……';    
		dbcc   checkident(SYSTEMS0,RESEED,0)
		print '重置数据库自增字段成功！';
	end   
go
--问题表数据删除触发器
if (object_id('tgr_QUESTION_delete', 'TR') is not null)
    drop trigger tgr_QUESTION_delete
go
create trigger tgr_QUESTION_delete
on QUESTION
    for delete --删除触发
as 
	begin
		print '重置数据库自增字段中……';    
		dbcc   checkident(QUESTION,RESEED,0)
		print '重置数据库自增字段成功！';
	end   
go
--回复表数据删除触发器
if (object_id('tgr_REPLY000_delete', 'TR') is not null)
    drop trigger tgr_REPLY000_delete
go
create trigger tgr_REPLY000_delete
on REPLY000
    for delete --删除触发
as 
	begin
		print '重置数据库自增字段中……';    
		dbcc   checkident(REPLY000,RESEED,0)
		print '重置数据库自增字段成功！';
	end   
go