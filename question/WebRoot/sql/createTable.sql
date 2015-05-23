--系统负责人表
create table SYSUSER0
(
ID int identity(1,1) primary key,
YHMC varchar(50) not null,--用户名称
SYSID int not null,--在系统表里的id，外键
SYSNAME varchar(16) not null,--系统名称
foreign key (SYSID) references SYSTEMS0(ID) on delete cascade
--sysid为系统表外键。主表数据删除时字表相应数据删除
)
--delete from SYSTEMS0 where ID=1


--问题创建回复时间[QANDRTIME]表,删除该表数据,可实现级联删除问题表&回复表
create table QANDRTIME
(
QID int primary key not null,--问题序列号
CREATETIME date not null,--问题创建时间
REPLYTIME date null,--问题最后回复时间
)

--问题表
create table QUESTION
(
ID int identity(1,1) primary key,
QID int not null,--问题序列号
TITLE varchar(40) null,--主题
SYSNAME varchar(16) null,--问题系统分类
QTYPE varchar(16) null,--问题类型
DEGREE int null,--紧急程度
QCONTENT text not null,--问题内容
SUBMITSTATE int null,--提交状态1未提交  0提交
REPLYSTATE int null,--回复状态1未回复   0已回复
GRADE int null,--问题评分
ACCESSORY int null,--是否有附件
CREATETIME date null,--问题提问时间
LCNUM int not null,--问题标注（帖子楼层）
YHMC varchar(50) null,--提交问题用户名称
foreign key (QID) references QANDRTIME(QID) on delete cascade
)

--回复表
create table REPLY000
(
ID int identity(1,1) primary key,
QID int not null,--问题序列号
RCONTENT text not null,--回复内容
REPLYTIME date not null,--问题回复时间
LCNUM int not null,--问题标注（帖子楼层）
foreign key (QID) references QANDRTIME(QID) on delete cascade
)