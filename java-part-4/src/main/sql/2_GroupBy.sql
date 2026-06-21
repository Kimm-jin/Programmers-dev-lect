use scott;

-- 부서별 평균 급여
SELECT AVG(SAL), DEPTNO
FROM EMP
GROUP BY DEPTNO;


# 문제 1) 10번 부서에 대해 급여의 평균값, 최대/최소값, 인원수를 구하시오. (인원수: COUNT(*))
SELECT AVG(SAL), MAX(SAL),MIN(SAL), COUNT(EMPNO) FROM EMP WHERE DEPTNO = 10;
# 문제 2) 각 부서별 같은 직무를 갖는 사원의 인원수를 구하여 부서번호, 직무, 인원수를 출력하시오.
SELECT DEPTNO, JOB, COUNT(EMPNO) FROM EMP
GROUP BY DEPTNO, JOB
ORDER BY DEPTNO;
# 문제 3) 사원들의 직무별 평균 급여와 최고 급여, 최저 급여를 평균 급여에 대해 오름차순으로 정렬하시오.
SELECT JOB, AVG(SAL), MAX(SAL), MIN(SAL) FROM EMP GROUP BY JOB ORDER BY AVG(SAL);
# 문제 4) 각 직무별 사원 수와 최고/최저/평균 급여액을 구하되, 사원 수가 많은 직무부터 출력하시오. (평균 급여는 소수점 둘째 자리에서 반올림)
SELECT JOB, COUNT(EMPNO), MAX(SAL), MIN(SAL), ROUND(AVG(SAL),1) FROM EMP
GROUP BY JOB
ORDER BY COUNT(EMPNO) DESC;
# 문제 5) 부서별 월급 합계가 5000을 초과하는 각 업무에 대해, 업무와 월 급여 합계를 조회하시오. (GROUP BY ~ HAVING)
SELECT JOB, SUM(SAL) FROM EMP
GROUP BY DEPTNO, JOB
HAVING SUM(SAL)>5000;
# 문제 6) 급여가 1000 이상인 사원들의 부서별 평균 급여를 출력하시오. 단, 부서별 평균 급여가 2000 이상인 부서만 출력할 것.
SELECT DEPTNO, AVG(SAL) FROM EMP WHERE SAL > 1000
GROUP BY DEPTNO
HAVING AVG(SAL)>=2000;
# 문제 7) 각 부서별 같은 업무(JOB)를 하는 사람의 인원수를 구해서, 부서번호·업무·인원수를 부서번호에 대해 오름차순으로 정렬하여 출력하시오.
SELECT DEPTNO, JOB, COUNT(JOB) FROM EMP
GROUP BY DEPTNO, JOB
ORDER BY DEPTNO ASC;
# 문제 8) EMP 테이블에서 부서 인원이 4명보다 많은 부서의 부서번호, 인원수, 급여의 합을 출력하시오.
SELECT DEPTNO, COUNT(DEPTNO), SUM(SAL) FROM EMP
GROUP BY DEPTNO
HAVING COUNT(DEPTNO) > 4;