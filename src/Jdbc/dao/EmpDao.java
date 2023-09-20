package Jdbc.dao;

import Jdbc.common.Common;
import Jdbc.vo.EmpVo;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//DAO ;Data Access Object, 데이터베이스에 접근해서 데이터를 조회하거나 수정하기위해 사용 (DML과 유사한 기능. DDL;데이터생성, 은 못함)
public class EmpDao {
    Connection conn = null; //db연결
    Statement stmt = null;
    PreparedStatement pstmt = null; //쿼리문을 날리기 위한 부분. 크리에이트 스테이트먼트는 다소 복잡해서 프리페어스테이트먼트 사용
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<EmpVo> emSelect() {
        List<EmpVo> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM EMP";
            rs = stmt.executeQuery(sql); // executeQuery SELECT 문에서 사용

            while (rs.next()) { //결과에서 읽을 내용이 있으면 true 반환 / print문에 넣는순간 읽어버리기때문에 결과가 바뀜. 읽는순간 내용 바뀜 // 변수에 다 담기(테이블의 행 개수만큼 순회)
                int empNo = rs.getInt("EMPNO");  //열이름을 띄워주고 읽을게 있는지 확인
                String name = rs.getString("ENAME");
                String job = rs.getString("JOB"); //오타나면 죽음
                int mgr = rs.getInt("MGR");
                Date date = rs.getDate("HIREDATE");
                BigDecimal sal = rs.getBigDecimal("SAL");
                BigDecimal comm = rs.getBigDecimal("COMM");
                int deptNo = rs.getInt("DEPTNO");
                list.add( new EmpVo(empNo, name, job, mgr, date,sal, comm, deptNo));
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace(); //못받아오면 죽음. 호출 순서가 스텍의로. 부하가 많이 걸림.
        }
        return list; // 필요한 경우 예외처리도 해줄 수 있음.
    }
    public void empInsert() {
        System.out.println("사원정보를 입력 하세요.");
        System.out.print("사원번호 : ");
        int no = sc.nextInt();
        System.out.print("이름:");
        String name = sc.next();
        System.out.print("직책:");
        String job = sc.next();
        System.out.print("상관:");
        int mgr = sc.nextInt();
        System.out.print("입사일:");
        String date = sc.next();
        System.out.print("급여:");
        BigDecimal sal = sc.nextBigDecimal();
        System.out.print("성과급:");
        BigDecimal comm = sc.nextBigDecimal();
        System.out.print("부서번호:");
        int deptNo = sc.nextInt();

        //쿼리문 날리기
        String sql = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES(?,?,?,?,?,?,?,?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            pstmt.setString(2, name);
            pstmt.setString(3, job);
            pstmt.setInt(4, mgr);
            pstmt.setString(5, date);
            pstmt.setBigDecimal(6, sal);
            pstmt.setBigDecimal(7, comm);
            pstmt.setInt(8, deptNo);
            int rst = pstmt.executeUpdate(); //**익스큐트 업데이트는 반환값이 정수로 넘어옴 = 실행결과가 정수 값으로 반환됨. (영향을 받은 행의 갯수가 반환 됨)
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }


    public void empSelectPrint(List<EmpVo> list) {
        for (EmpVo e : list) {
            System.out.print(e.getEmpNo() + " ");
            System.out.print(e.getName() + " ");
            System.out.print(e.getJob() + " ");
            System.out.print(e.getMgr() + " ");
            System.out.print(e.getDate() + " ");
            System.out.print(e.getSal() + " ");
            System.out.print(e.getComm() + " ");
            System.out.print(e.getDeptNo() + " ");
            System.out.println();
        }
    }
}
