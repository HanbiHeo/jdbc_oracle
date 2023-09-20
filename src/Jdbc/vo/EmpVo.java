package Jdbc.vo;

import java.math.BigDecimal;
import java.sql.Date;

public class EmpVo {
    private  int empNo; //사원번호
    private String name;
    private String job;
    private int mgr;
    private Date date;
    private BigDecimal sal;  //오라클의 NUMBER와 같다고 생각하면 됨.
    private BigDecimal comm;
    private  int deptNo;

    public EmpVo(int empNo, String name, String job, int mgr, Date date, BigDecimal sal, BigDecimal comm, int deptNo) {
        this.empNo = empNo;
        this.name = name;
        this.job = job;
        this.mgr = mgr;
        this.date = date;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    public BigDecimal getComm() {
        return comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }
}
