use scott;
# EMPNO    = Employee Number = 사원번호
# ENAME    = Employee Name   = 사원이름
# JOB      = 직무
# MGR      = Manager         = 관리자/상사 사원번호
# HIREDATE = Hire Date       = 입사일
# SAL      = Salary          = 급여
# COMM     = Commission      = 커미션/수당
# DEPTNO   = Department No   = 부서번호

# ORDER BY
# 실습) 사원번호 기준 각각 오름차순, 내림차순 하시오.
# 실습) EMP 테이블의 전체 열을 부서번호(오름차순)와 급여(내림차순)으로 정렬하시오.
SELECT * FROM EMP ORDER BY EMPNO ASC;
SELECT * FROM EMP ORDER BY EMPNO DESC;

# 실습) 사원번호 기준 각각 오름차순, 내림차순 하시오.
# 실습) EMP 테이블의 전체 열을 부서번호(오름차순)와 급여(내림차순)으로 정렬하시오.
SELECT * FROM EMP ORDER BY DEPTNO ASC, SAL DESC;

# 실습) 사원번호가 7782인 사원 정보만 나오도록.
SELECT * FROM EMP WHERE EMPNO = 7782;

# 실습) 부서번호가 20이거나 사원번호가 7782인 경우
# 실습) 부서번호가 20이면서 연봉이 1000보다 높은 직원을 구하시오.
SELECT * FROM EMP WHERE EMPNO = 7782 OR DEPTNO = 20;
SELECT * FROM EMP WHERE DEPTNO=20 AND SAL > 1000;

# 실습) 급여가 2500 이상, 직업이 ANALYST인 사원 정보만 출력.
SELECT * FROM EMP WHERE SAL>2500 AND JOB='ANALYST';

# 실습) 연봉이 3000이 아닌 경우 정보 출력 (동일 표현)
SELECT * FROM EMP WHERE SAL!=3000;
SELECT * FROM EMP WHERE SAL <> 3000;
SELECT * FROM EMP WHERE NOT SAL = 3000;

# 실습) 사원의 번호, 이름, 급여, 부서번호를 출력하시오.
# 실습) 10번 부서의 모든 사원에게 급여의 13%를 보너스로 지불하기로 하였다. 이름, 급여, 보너스 금액, 부서번호를 출력하시오.
SELECT EMPNO, ENAME, SAL, DEPTNO FROM EMP;
SELECT ENAME, SAL, (SAL*0.13) AS BONUS, DEPTNO  FROM EMP WHERE DEPTNO=10;

# 문제 1) JOB이 CLERK인 데이터 조회
SELECT * FROM EMP WHERE JOB = 'CLERK';

# 문제 2) SAL이 1000 이상인 데이터 조회
SELECT * FROM EMP WHERE SAL >= 1000;
# 문제 3) COMM이 NULL인 데이터만 조회
SELECT * FROM EMP WHERE COMM IS NULL;
# 문제 4) COMM이 NULL이 아닌 데이터만 조회
SELECT * FROM EMP WHERE COMM IS NOT NULL;
# 문제 5) 사원이름(ENAME)에서 'R'로 끝나는 데이터 조회
SELECT * FROM EMP WHERE ENAME LIKE '%R';
# 문제 6) 이름이 M으로 시작, 부서번호가 10인 사람 조회
SELECT * FROM EMP WHERE ENAME LIKE '%M' OR DEPTNO = 10;
# 문제 7) 연봉이 1000~2000인 데이터 (BETWEEN 사용)
SELECT * FROM EMP WHERE SAL BETWEEN 1000 AND 2000
# 문제 8) 연봉이 1000~2000인 데이터 (부등호 사용)
SELECT * FROM EMP WHERE SAL >= 1000 AND SAL <= 2000;
# 문제 9) 연봉이 1000~2000인 데이터 (IN 사용)
SELECT * FROM EMP WHERE SAL IN(1000,2000);
# 문제 10) 부서번호 중복 제거
SELECT DISTINCT DEPTNO FROM EMP;
# 문제 11) 연봉순 정렬 각각 내림차순, 오름차순
SELECT * FROM EMP ORDER BY SAL DESC;
SELECT * FROM EMP ORDER BY SAL ASC;

SELECT NOW()    AS NOW,                          -- 날짜 + 시간
       CURDATE() AS TODAY,                        -- 날짜만
       CURDATE() - INTERVAL 1 DAY AS YESTERDAY,
       CURDATE() + INTERVAL 1 DAY AS TOMORROW;

# 문제) 32년(384개월)이 되지 않은 사원 이름 출력
SELECT ENAME FROM EMP WHERE DATE_ADD(HIREDATE, INTERVAL 384 MONTH ) > NOW();

# 문제) 입사일(HIREDATE)이 '1980-12-17' 이상인 데이터 조회
SELECT * FROM EMP WHERE HIREDATE >= '1980-12-17';