--ϵͳ�����˱�
create table SYSUSER0
(
ID int identity(1,1) primary key,
YHMC varchar(50) not null,--�û�����
SYSID int not null,--��ϵͳ�����id�����
SYSNAME varchar(16) not null,--ϵͳ����
foreign key (SYSID) references SYSTEMS0(ID) on delete cascade
--sysidΪϵͳ���������������ɾ��ʱ�ֱ���Ӧ����ɾ��
)
--delete from SYSTEMS0 where ID=1


--���ⴴ���ظ�ʱ��[QANDRTIME]��,ɾ���ñ�����,��ʵ�ּ���ɾ�������&�ظ���
create table QANDRTIME
(
QID int primary key not null,--�������к�
CREATETIME date not null,--���ⴴ��ʱ��
REPLYTIME date null,--�������ظ�ʱ��
)

--�����
create table QUESTION
(
ID int identity(1,1) primary key,
QID int not null,--�������к�
TITLE varchar(40) null,--����
SYSNAME varchar(16) null,--����ϵͳ����
QTYPE varchar(16) null,--��������
DEGREE int null,--�����̶�
QCONTENT text not null,--��������
SUBMITSTATE int null,--�ύ״̬1δ�ύ  0�ύ
REPLYSTATE int null,--�ظ�״̬1δ�ظ�   0�ѻظ�
GRADE int null,--��������
ACCESSORY int null,--�Ƿ��и���
CREATETIME date null,--��������ʱ��
LCNUM int not null,--�����ע������¥�㣩
YHMC varchar(50) null,--�ύ�����û�����
foreign key (QID) references QANDRTIME(QID) on delete cascade
)

--�ظ���
create table REPLY000
(
ID int identity(1,1) primary key,
QID int not null,--�������к�
RCONTENT text not null,--�ظ�����
REPLYTIME date not null,--����ظ�ʱ��
LCNUM int not null,--�����ע������¥�㣩
foreign key (QID) references QANDRTIME(QID) on delete cascade
)