use question
go
--�û�������ɾ��������
if (object_id('tgr_USER0000_delete', 'TR') is not null)
    drop trigger tgr_USER0000_delete
go
create trigger tgr_USER0000_delete
on USER0000
    for delete --ɾ������
as 
	begin
		print '�������ݿ������ֶ��С���';    
		dbcc   checkident(USER0000,RESEED,0)
		print '�������ݿ������ֶγɹ���';
	end   
go
--ϵͳ�����˱�����ɾ��������
if (object_id('tgr_SYSUSER0_delete', 'TR') is not null)
    drop trigger tgr_SYSUSER0_delete
go
create trigger tgr_SYSUSER0_delete
on SYSUSER0
    for delete --ɾ������
as 
	begin
		print '�������ݿ������ֶ��С���';    
		dbcc   checkident(SYSUSER0,RESEED,0)
		print '�������ݿ������ֶγɹ���';
	end   
go
--ϵͳ������ɾ��������
if (object_id('tgr_SYSTEMS0_delete', 'TR') is not null)
    drop trigger tgr_SYSTEMS0_delete
go
create trigger tgr_SYSTEMS0_delete
on SYSTEMS0
    for delete --ɾ������
as 
	begin
		print '�������ݿ������ֶ��С���';    
		dbcc   checkident(SYSTEMS0,RESEED,0)
		print '�������ݿ������ֶγɹ���';
	end   
go
--���������ɾ��������
if (object_id('tgr_QUESTION_delete', 'TR') is not null)
    drop trigger tgr_QUESTION_delete
go
create trigger tgr_QUESTION_delete
on QUESTION
    for delete --ɾ������
as 
	begin
		print '�������ݿ������ֶ��С���';    
		dbcc   checkident(QUESTION,RESEED,0)
		print '�������ݿ������ֶγɹ���';
	end   
go
--�ظ�������ɾ��������
if (object_id('tgr_REPLY000_delete', 'TR') is not null)
    drop trigger tgr_REPLY000_delete
go
create trigger tgr_REPLY000_delete
on REPLY000
    for delete --ɾ������
as 
	begin
		print '�������ݿ������ֶ��С���';    
		dbcc   checkident(REPLY000,RESEED,0)
		print '�������ݿ������ֶγɹ���';
	end   
go