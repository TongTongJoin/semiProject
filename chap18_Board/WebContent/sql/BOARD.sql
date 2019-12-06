
drop table board purge;

CREATE TABLE BOARD(
	BOARD_NUM NUMBER, 
	BOARD_NAME VARCHAR2(20),
	BOARD_PASS VARCHAR2(15),
	BOARD_SUBJECT VARCHAR2(50),
	BOARD_CONTENT VARCHAR2(2000),
	BOARD_FILE VARCHAR2(50),
	BOARD_RE_REF NUMBER, 
	-- 댓글을 보여주기 위한 기능. 댓글처리가 어려운 것이 작성시간과 댓글시간의 혼합으로 이를 식별해야한다. 
	-- DB 상에서는 최신댓글이 가장 먼저이다. 이 default 순서를 select 문으로 최적화시켜야 한다.
	   -- topquery 의 rownum 을 활용해서 가장 최신의 댓글을 반영할 수 있는 기능을 구현한다.
	BOARD_RE_LEV NUMBER,
	BOARD_RE_SEQ NUMBER,
	BOARD_READCOUNT NUMBER, -- 조회수 관련이다.
	BOARD_DATE DATE, -- 작성일; 이건 DB 연동 필요없다
	PRIMARY KEY(BOARD_NUM)
);


-- 서버 필요: 5, 11, 15~18

select * from BOARD;

insert into BOARD
values(01, "SCOTT", "tiger", "test", "testing", 

delete from board;
