CREATE INDEX IX_PAP_KEYVAL on paper(`keyval`);
CREATE INDEX IX_PAPAUTH_KEYVAL on paperauthor(`keyval`);
ALTER TABLE paperauthor 
 ADD COLUMN paperid INT NOT NULL DEFAULT 0;
update paperauthor pa
 join paper p on pa.keyval = p.keyval
 set pa.paperid = p.id
 where pa.keyval = p.keyval;

ALTER TABLE paper 
ADD COLUMN conference_name VARCHAR(200);
DELIMITER $$
DROP FUNCTION IF EXISTS SPLIT_STR; $$
CREATE FUNCTION SPLIT_STR(
x VARCHAR(255),
delim VARCHAR(12),
pos INT
)
RETURNS VARCHAR(255) DETERMINISTIC
BEGIN 
	RETURN REPLACE(SUBSTRING(SUBSTRING_INDEX(x, delim, pos),
        LENGTH(SUBSTRING_INDEX(x, delim, pos -1)) + 1), delim, '');
 END$$
 
DELIMITER ;
update paper 
 set conference_name = SPLIT_STR(keyval, '/', 2);
CREATE INDEX IX_PAP_ID on paper(`id`);
CREATE INDEX IX_PAPAUTH_ID on paperauthor(`paperid`);
alter table paper drop keyval;
alter table paperauthor drop keyval;




########Journals
CREATE INDEX IX_JOU_KEYVAL on journalpaper(`keyval`);
CREATE INDEX IX_JOUAUTH_KEYVAL on journalauthor(`keyval`);
ALTER TABLE journalauthor 
 ADD COLUMN paperid INT NOT NULL DEFAULT 0;
update journalauthor pa
 join journalpaper p on pa.keyval = p.keyval
 set pa.paperid = p.id
 where pa.keyval = p.keyval;

ALTER TABLE journalpaper
ADD COLUMN journal_name VARCHAR(200);

update journalpaper 
 set journal_name = SPLIT_STR(keyval, '/', 2);

CREATE INDEX IX_JOUPAP_ID on journalpaper(`id`);
CREATE INDEX IX_JOUPAPAUTH_ID on journalauthor(`paperid`);
alter table journalpaper drop keyval;
alter table journalauthor drop keyval;





##Thesis
CREATE INDEX IX_THES_KEYVAL on thesis(`keyval`);
CREATE INDEX IX_THESAUTH_KEYVAL on thesisauthor(`keyval`);
ALTER TABLE thesisauthor 
 ADD COLUMN paperid INT NOT NULL DEFAULT 0;
update thesisauthor pa
 join thesis p on pa.keyval = p.keyval
 set pa.paperid = p.id
 where pa.keyval = p.keyval;

CREATE INDEX IX_THES_ID on thesis(`id`);
CREATE INDEX IX_THESAUTH_ID on thesisauthor(`paperid`);




DROP TABLE IF EXISTS `author`;
 
 -- Create a new table structure
 CREATE TABLE author(
 
 id int primary key auto_increment,
 name Varchar(100) NOT NULL UNIQUE
 );

insert into author(name)
select distinct(RTRIM(LTRIM(authorname))) from  paperauthor;
insert ignore into author(name)
select distinct(RTRIM(LTRIM(authorname))) from  journalauthor;
insert ignore into author(name)
select distinct(RTRIM(LTRIM(authorname))) from  thesisauthor;

CREATE INDEX IX_PAPAUTH_NAME
 ON paperauthor (authorname);

CREATE INDEX IX_AUTH_NAME
ON author (name);

UPDATE paperauthor pa join author au
 on pa.authorname = au.name
 SET pa.authorid = au.id
where pa.authorname = au.name;

CREATE INDEX IX_JOUPAPAUTH_NAME
 ON journalauthor (authorname);

UPDATE journalauthor pa join author au
 on pa.authorname = au.name
 SET pa.authorid = au.id
where pa.authorname = au.name;

CREATE INDEX IX_THEPAPAUTH_NAME
 ON thesisauthor (authorname);

UPDATE thesisauthor pa join author au
 on pa.authorname = au.name
 SET pa.authorid = au.id
where pa.authorname = au.name;

# remove unused tables
drop table book;
drop table incollection;
drop table proceedings;

alter table shortlistcommittee drop column year;
alter table shortlistcommittee drop column keyval;
alter table shortlistcommittee drop column papertype;
alter table thesisauthor drop column keyval,drop column type,drop column authorname;
alter table paperauthor drop column authorname,drop column type;
alter table journalauthor drop column authorname,drop column type;
alter table paper drop column volume,drop column journal,drop column number;
alter table paper drop column authorname;
alter table journalpaper drop column authorname;
alter table thesis drop column keyval,drop column authorname,drop column url,drop column ee;

alter table affiliation add column authorid varchar(500);
create index IX_AFF_AUTHNAME on affiliation(authorname);
update affiliation af join author au 
on af.authorname = au.name
set af.authorid=au.id
where af.authorname=au.name;

create index IX_AFF_AUTHID on affiliation(authorid);
create index IX_CONFPAP_AUTHID on conferenceauthor(authorid);
create index IX_JOURNPAP_AUTHID on journalauthor(authorid);

alter table affiliation add column region varchar(200);

update affiliation af 
join universitydetails ud
 on af.university = ud.university
 SET af.region = ud.region
where af.university = ud.university;

alter table researchinfo add column authorid int(11);

create index IX_RESIN_AUNAME on researchinfo(authorname);
update researchinfo ri 
join author au
 on ri.authorname = au.name
 SET ri.authorid = au.id
where ri.authorname = au.name;



