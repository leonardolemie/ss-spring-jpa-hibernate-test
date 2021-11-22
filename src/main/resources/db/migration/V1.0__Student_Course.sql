CREATE TABLE COURSE (
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
    NAME VARCHAR(255),
    PRIMARY KEY (ID)
);

CREATE TABLE STUDENT (
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
    NAME VARCHAR(255),
    PRIMARY KEY (ID)
);
CREATE TABLE STUDENT_COURSE_REGISTRATION (
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
    COURSE_ID INTEGER,
    STUDENT_ID INTEGER,
    PRIMARY KEY (ID)
);

CREATE TABLE STUDENT_COURSE_SCORE (
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY,
    SCORE DOUBLE,
    REGISTRATION_ID INTEGER,
    PRIMARY KEY (ID)
);

CREATE INDEX COURSE_NAME_IDX ON COURSE (NAME);

ALTER TABLE STUDENT_COURSE_REGISTRATION
    ADD CONSTRAINT STUDENT_ID_COURSE_ID_UK UNIQUE (STUDENT_ID, COURSE_ID);
ALTER TABLE STUDENT_COURSE_REGISTRATION
    ADD CONSTRAINT STUDENT_COURSE_REGISTRATION_COURSE_ID_FK FOREIGN KEY (COURSE_ID) REFERENCES COURSE;
ALTER TABLE STUDENT_COURSE_REGISTRATION
    ADD CONSTRAINT STUDENT_COURSE_REGISTRATION_STUDENT_ID_FK FOREIGN KEY (STUDENT_ID) REFERENCES STUDENT;
ALTER TABLE STUDENT_COURSE_SCORE
    ADD CONSTRAINT STUDENT_COURSE_SCORE_REGISTRATION_ID_FK FOREIGN KEY (REGISTRATION_ID) REFERENCES STUDENT_COURSE_REGISTRATION


