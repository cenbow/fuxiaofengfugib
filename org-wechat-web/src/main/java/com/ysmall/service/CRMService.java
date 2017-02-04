package com.ysmall.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.5.9
 * 2016-03-29T10:29:55.333+08:00
 * Generated source version: 2.5.9
 * 
 */
@WebService(targetNamespace = "http://service.ysmall.com/", name = "CRMService")
@XmlSeeAlso({ObjectFactory.class})
public interface CRMService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "CheckVerificationCode", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.CheckVerificationCode")
    @WebMethod(operationName = "CheckVerificationCode")
    @ResponseWrapper(localName = "CheckVerificationCodeResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.CheckVerificationCodeResponse")
    public java.lang.String checkVerificationCode(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getNoticeFlag", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetNoticeFlag")
    @WebMethod
    @ResponseWrapper(localName = "getNoticeFlagResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetNoticeFlagResponse")
    public java.lang.String getNoticeFlag(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "LoginAuthentication", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.LoginAuthentication")
    @WebMethod(operationName = "LoginAuthentication")
    @ResponseWrapper(localName = "LoginAuthenticationResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.LoginAuthenticationResponse")
    public java.lang.String loginAuthentication(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "ExchangeCoupon", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ExchangeCoupon")
    @WebMethod(operationName = "ExchangeCoupon")
    @ResponseWrapper(localName = "ExchangeCouponResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ExchangeCouponResponse")
    public java.lang.String exchangeCoupon(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.Integer arg3
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "Couponverification", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.Couponverification")
    @WebMethod(operationName = "Couponverification")
    @ResponseWrapper(localName = "CouponverificationResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.CouponverificationResponse")
    public java.lang.String couponverification(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4
    ) throws Exception_Exception, SQLException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "LoginOrOut", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.LoginOrOut")
    @WebMethod(operationName = "LoginOrOut")
    @ResponseWrapper(localName = "LoginOrOutResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.LoginOrOutResponse")
    public java.lang.String loginOrOut(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "ReadFlagLog", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ReadFlagLog")
    @WebMethod(operationName = "ReadFlagLog")
    @ResponseWrapper(localName = "ReadFlagLogResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ReadFlagLogResponse")
    public java.lang.String readFlagLog(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.Integer arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "GetReleaseCouponStatus", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetReleaseCouponStatus")
    @WebMethod(operationName = "GetReleaseCouponStatus")
    @ResponseWrapper(localName = "GetReleaseCouponStatusResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetReleaseCouponStatusResponse")
    public java.lang.String getReleaseCouponStatus(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "ChangePassword", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ChangePassword")
    @WebMethod(operationName = "ChangePassword")
    @ResponseWrapper(localName = "ChangePasswordResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ChangePasswordResponse")
    public java.lang.String changePassword(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.Integer arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        java.lang.String arg6
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "GetNotice", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetNotice")
    @WebMethod(operationName = "GetNotice")
    @ResponseWrapper(localName = "GetNoticeResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetNoticeResponse")
    public java.lang.String getNotice(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.Integer arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.Integer arg3
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "CheckUserORCode", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.CheckUserORCode")
    @WebMethod(operationName = "CheckUserORCode")
    @ResponseWrapper(localName = "CheckUserORCodeResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.CheckUserORCodeResponse")
    public java.lang.String checkUserORCode(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "ExchangePresent", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ExchangePresent")
    @WebMethod(operationName = "ExchangePresent")
    @ResponseWrapper(localName = "ExchangePresentResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ExchangePresentResponse")
    public java.lang.String exchangePresent(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "UpdateBonus", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.UpdateBonus")
    @WebMethod(operationName = "UpdateBonus")
    @ResponseWrapper(localName = "UpdateBonusResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.UpdateBonusResponse")
    public java.lang.String updateBonus(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.Integer arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.Integer arg3
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "ChangePasswordByCode", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ChangePasswordByCode")
    @WebMethod(operationName = "ChangePasswordByCode")
    @ResponseWrapper(localName = "ChangePasswordByCodeResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ChangePasswordByCodeResponse")
    public java.lang.String changePasswordByCode(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "GetMemberInfo", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetMemberInfo")
    @WebMethod(operationName = "GetMemberInfo")
    @ResponseWrapper(localName = "GetMemberInfoResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetMemberInfoResponse")
    public java.lang.String getMemberInfo(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "GetCouponDetail", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetCouponDetail")
    @WebMethod(operationName = "GetCouponDetail")
    @ResponseWrapper(localName = "GetCouponDetailResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetCouponDetailResponse")
    public java.lang.String getCouponDetail(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.Integer arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "NEWCheckMobilPone", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.NEWCheckMobilPone")
    @WebMethod(operationName = "NEWCheckMobilPone")
    @ResponseWrapper(localName = "NEWCheckMobilPoneResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.NEWCheckMobilPoneResponse")
    public java.lang.String newCheckMobilPone(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "UpdateScore", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.UpdateScore")
    @WebMethod(operationName = "UpdateScore")
    @ResponseWrapper(localName = "UpdateScoreResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.UpdateScoreResponse")
    public java.lang.String updateScore(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "CheckMobilPone", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.CheckMobilPone")
    @WebMethod(operationName = "CheckMobilPone")
    @ResponseWrapper(localName = "CheckMobilPoneResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.CheckMobilPoneResponse")
    public java.lang.String checkMobilPone(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "ModifyMember", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ModifyMember")
    @WebMethod(operationName = "ModifyMember")
    @ResponseWrapper(localName = "ModifyMemberResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ModifyMemberResponse")
    public java.lang.String modifyMember(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        java.lang.String arg6,
        @WebParam(name = "arg7", targetNamespace = "")
        java.lang.String arg7,
        @WebParam(name = "arg8", targetNamespace = "")
        java.lang.String arg8,
        @WebParam(name = "arg9", targetNamespace = "")
        java.lang.String arg9,
        @WebParam(name = "arg10", targetNamespace = "")
        java.lang.String arg10,
        @WebParam(name = "arg11", targetNamespace = "")
        java.lang.String arg11,
        @WebParam(name = "arg12", targetNamespace = "")
        java.lang.String arg12,
        @WebParam(name = "arg13", targetNamespace = "")
        java.lang.Integer arg13
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "GetCouponByMembID", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetCouponByMembID")
    @WebMethod(operationName = "GetCouponByMembID")
    @ResponseWrapper(localName = "GetCouponByMembIDResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetCouponByMembIDResponse")
    public java.lang.String getCouponByMembID(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.Integer arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.Integer arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.Integer arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.Integer arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        java.lang.Integer arg6,
        @WebParam(name = "arg7", targetNamespace = "")
        java.lang.Integer arg7,
        @WebParam(name = "arg8", targetNamespace = "")
        java.lang.String arg8,
        @WebParam(name = "arg9", targetNamespace = "")
        java.lang.Integer arg9,
        @WebParam(name = "arg10", targetNamespace = "")
        java.lang.Integer arg10,
        @WebParam(name = "arg11", targetNamespace = "")
        java.lang.Integer arg11
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "RegisterMember1", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.RegisterMember1")
    @WebMethod(operationName = "RegisterMember1")
    @ResponseWrapper(localName = "RegisterMember1Response", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.RegisterMember1Response")
    public java.lang.String registerMember1(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        java.lang.String arg6,
        @WebParam(name = "arg7", targetNamespace = "")
        java.lang.String arg7,
        @WebParam(name = "arg8", targetNamespace = "")
        java.lang.String arg8,
        @WebParam(name = "arg9", targetNamespace = "")
        java.lang.String arg9,
        @WebParam(name = "arg10", targetNamespace = "")
        java.lang.Integer arg10,
        @WebParam(name = "arg11", targetNamespace = "")
        java.lang.String arg11,
        @WebParam(name = "arg12", targetNamespace = "")
        java.lang.String arg12,
        @WebParam(name = "arg13", targetNamespace = "")
        java.lang.String arg13,
        @WebParam(name = "arg14", targetNamespace = "")
        java.lang.String arg14,
        @WebParam(name = "arg15", targetNamespace = "")
        java.lang.String arg15,
        @WebParam(name = "arg16", targetNamespace = "")
        java.lang.String arg16,
        @WebParam(name = "arg17", targetNamespace = "")
        java.lang.String arg17
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "UpdateScoreus", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.UpdateScoreus")
    @WebMethod(operationName = "UpdateScoreus")
    @ResponseWrapper(localName = "UpdateScoreusResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.UpdateScoreusResponse")
    public java.lang.String updateScoreus(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "RegisterMember", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.RegisterMember")
    @WebMethod(operationName = "RegisterMember")
    @ResponseWrapper(localName = "RegisterMemberResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.RegisterMemberResponse")
    public java.lang.String registerMember(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.Integer arg4
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "UpdateBonus1", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.UpdateBonus1")
    @WebMethod(operationName = "UpdateBonus1")
    @ResponseWrapper(localName = "UpdateBonus1Response", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.UpdateBonus1Response")
    public java.lang.String updateBonus1(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.Integer arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        java.lang.Double arg6,
        @WebParam(name = "arg7", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg7,
        @WebParam(name = "arg8", targetNamespace = "")
        java.lang.Integer arg8,
        @WebParam(name = "arg9", targetNamespace = "")
        java.lang.String arg9,
        @WebParam(name = "arg10", targetNamespace = "")
        java.lang.String arg10,
        @WebParam(name = "arg11", targetNamespace = "")
        java.lang.String arg11,
        @WebParam(name = "arg12", targetNamespace = "")
        java.lang.String arg12,
        @WebParam(name = "arg13", targetNamespace = "")
        java.lang.Integer arg13,
        @WebParam(name = "arg14", targetNamespace = "")
        java.lang.String arg14,
        @WebParam(name = "arg15", targetNamespace = "")
        java.lang.Double arg15,
        @WebParam(name = "arg16", targetNamespace = "")
        java.lang.String arg16,
        @WebParam(name = "arg17", targetNamespace = "")
        java.lang.Double arg17,
        @WebParam(name = "arg18", targetNamespace = "")
        java.lang.Double arg18,
        @WebParam(name = "arg19", targetNamespace = "")
        java.lang.Double arg19,
        @WebParam(name = "arg20", targetNamespace = "")
        java.lang.String arg20
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "ChangeMobilPhone", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ChangeMobilPhone")
    @WebMethod(operationName = "ChangeMobilPhone")
    @ResponseWrapper(localName = "ChangeMobilPhoneResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ChangeMobilPhoneResponse")
    public java.lang.String changeMobilPhone(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "GetCoupon", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetCoupon")
    @WebMethod(operationName = "GetCoupon")
    @ResponseWrapper(localName = "GetCouponResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetCouponResponse")
    public java.lang.String getCoupon(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.Integer arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.Integer arg4
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "GetActivity", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetActivity")
    @WebMethod(operationName = "GetActivity")
    @ResponseWrapper(localName = "GetActivityResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetActivityResponse")
    public java.lang.String getActivity();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getRefereeMobilPhone", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetRefereeMobilPhone")
    @WebMethod
    @ResponseWrapper(localName = "getRefereeMobilPhoneResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetRefereeMobilPhoneResponse")
    public java.lang.String getRefereeMobilPhone(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "GenerationIntegral", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GenerationIntegral")
    @WebMethod(operationName = "GenerationIntegral")
    @ResponseWrapper(localName = "GenerationIntegralResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GenerationIntegralResponse")
    public java.lang.String generationIntegral(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "ChangePasswordByQuestions", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ChangePasswordByQuestions")
    @WebMethod(operationName = "ChangePasswordByQuestions")
    @ResponseWrapper(localName = "ChangePasswordByQuestionsResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.ChangePasswordByQuestionsResponse")
    public java.lang.String changePasswordByQuestions(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.Integer arg5
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "GetQuestionsList", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetQuestionsList")
    @WebMethod(operationName = "GetQuestionsList")
    @ResponseWrapper(localName = "GetQuestionsListResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GetQuestionsListResponse")
    public java.lang.String getQuestionsList(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.Integer arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "GenerateQRCode", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GenerateQRCode")
    @WebMethod(operationName = "GenerateQRCode")
    @ResponseWrapper(localName = "GenerateQRCodeResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.GenerateQRCodeResponse")
    public java.lang.String generateQRCode(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "SelectLogin", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.SelectLogin")
    @WebMethod(operationName = "SelectLogin")
    @ResponseWrapper(localName = "SelectLoginResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.SelectLoginResponse")
    public java.lang.String selectLogin(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "LoginForAuth", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.LoginForAuth")
    @WebMethod(operationName = "LoginForAuth")
    @ResponseWrapper(localName = "LoginForAuthResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.LoginForAuthResponse")
    public java.lang.String loginForAuth(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "QueryBonusDetail", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.QueryBonusDetail")
    @WebMethod(operationName = "QueryBonusDetail")
    @ResponseWrapper(localName = "QueryBonusDetailResponse", targetNamespace = "http://service.ysmall.com/", className = "com.ysmall.service.QueryBonusDetailResponse")
    public java.lang.String queryBonusDetail(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.Integer arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.Integer arg5
    );
}
