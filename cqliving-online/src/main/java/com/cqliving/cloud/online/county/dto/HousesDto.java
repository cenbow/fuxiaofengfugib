package com.cqliving.cloud.online.county.dto;

public class HousesDto {
    /** 贷款项目 */
    private String daikuanxiangmu;
    /** 项目地址 */
    private String xiangmudizhi;
    /** 建设单位 */
    private String jianshedanwei;
    /** 承包银行 */
    private String chengbanyinhang;
    
    public String getDaikuanxiangmu() {
        return daikuanxiangmu;
    }
    public void setDaikuanxiangmu(String daikuanxiangmu) {
        this.daikuanxiangmu = daikuanxiangmu;
    }
    public String getXiangmudizhi() {
        return xiangmudizhi;
    }
    public void setXiangmudizhi(String xiangmudizhi) {
        this.xiangmudizhi = xiangmudizhi;
    }
    public String getJianshedanwei() {
        return jianshedanwei;
    }
    public void setJianshedanwei(String jianshedanwei) {
        this.jianshedanwei = jianshedanwei;
    }
    public String getChengbanyinhang() {
        return chengbanyinhang;
    }
    public void setChengbanyinhang(String chengbanyinhang) {
        this.chengbanyinhang = chengbanyinhang;
    }
}