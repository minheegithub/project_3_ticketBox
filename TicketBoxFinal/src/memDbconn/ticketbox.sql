drop table board;

create table board(
    aid number(10) primary key not null,
    rid number(10),
    pwd varchar2(50),
    dbsubject varchar2(50),
    dbname varchar2(50),
    dbdate date default sysdate,
    dbhits number(10),
    dbmemo varchar2(500),
    jumsoo varchar2(10)
);
commit;

CREATE TABLE member(
name varchar2(20), 
id varchar2(20), 
pw varchar2(30), 
phone varchar2(20), 
gender varchar2(10),
address varchar2(100),
email varchar2(50)
);
select * from member_seat;
select * from member;

delete from member_seat where id= 'suhyun' and p_id = 'MA08281400';
commit;


