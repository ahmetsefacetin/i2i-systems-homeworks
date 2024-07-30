SELECT 
    EMPLOYEE_ID, 
    FIRST_NAME, 
    LAST_NAME, 
    ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))
FROM 
    HR.EMPLOYEES;
    
SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, JOB_ID, 
    DECODE(
        JOB_ID, 
        'AD_PRES', 'A',
        'ST_MAN', 'B',
        'IT_PROG', 'C',
        'SA_REP', 'D',
        'ST_CLERK', 'E',
        '0'
    ) AS GRADE
FROM HR.EMPLOYEES;

SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, JOB_ID, 
    CASE 
        WHEN JOB_ID = 'AD_PRES' THEN 'A'
        WHEN JOB_ID = 'ST_MAN' THEN 'B'
        WHEN JOB_ID = 'IT_PROG' THEN 'C'
        WHEN JOB_ID = 'SA_REP' THEN 'D'
        WHEN JOB_ID = 'ST_CLERK' THEN 'E'
        ELSE '0'
    END AS GRADE
FROM HR.EMPLOYEES;

SELECT EMPLOYEE_ID, LAST_NAME
FROM HR.EMPLOYEES
WHERE 
    DEPARTMENT_ID IN (
        SELECT 
            DISTINCT DEPARTMENT_ID
        FROM 
            HR.EMPLOYEES
        WHERE 
            LAST_NAME LIKE '%i%'
    )
ORDER BY EMPLOYEE_ID;

CREATE TABLE MY_EMP_TABLE (
    ID NUMBER PRIMARY KEY,
    LAST_NAME VARCHAR2(50),
    FIRST_NAME VARCHAR2(50),
    SALARY NUMBER
);

INSERT INTO MY_EMP_TABLE (ID, LAST_NAME, FIRST_NAME, SALARY) VALUES (1, 'Black', 'John', 1100);
INSERT INTO MY_EMP_TABLE (ID, LAST_NAME, FIRST_NAME, SALARY) VALUES (2, 'White', 'Kent', 1300);
INSERT INTO MY_EMP_TABLE (ID, LAST_NAME, FIRST_NAME, SALARY) VALUES (3, 'Orange', 'David', 1700);
INSERT INTO MY_EMP_TABLE (ID, LAST_NAME, FIRST_NAME, SALARY) VALUES (4, 'Pink', 'Alissa', 1900);

UPDATE MY_EMP_TABLE SET SALARY = SALARY * 1.10;

DELETE FROM MY_EMP_TABLE WHERE FIRST_NAME = 'David';

TRUNCATE TABLE MY_EMP_TABLE;






