package com.cqliving.cloud.online.question.manager.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.JaxbUtil;
import com.cqliving.cloud.common.JaxbUtil.CollectionWrapper;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.question.dto.Coord;
import com.cqliving.cloud.online.question.dto.Medias;
import com.cqliving.cloud.online.question.dto.WisdomPlatform;
import com.cqliving.cloud.online.question.manager.QuestionManager;
import com.cqliving.cloud.online.shoot.dao.ShootInfoDao;
import com.cqliving.cloud.online.shoot.domain.ShootInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.encrypt.Base64Util;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service("questionManager")
public class QuestionManagerImpl extends EntityServiceImpl<ShootInfo, ShootInfoDao> implements QuestionManager {
    private static final Logger logger = LoggerFactory.getLogger(QuestionManagerImpl.class);
    
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserSessionManager userSessionManager;
    
    //每天的案件编号开始数字
    private static int nunber = 0;
    //当前日期
    private static String day = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
    //同步锁
    private byte[] lock1 = new byte[0];

    /**
     * 问题上报
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月15日
     * @throws Exception 
     */
    @Override
    public Boolean saveQuestion(String eventId, String appId, String imgs, String recDesc, String address, String coordX, String coordY,
            String reporterName, String reporterMobile,String token,String sessionId) throws Exception {
        //登录校验
        UserInfo userInfo = loginCheck(token);
        //生成上报对象
        WisdomPlatform wisdomPlatform = caeateObj(eventId, appId, imgs, recDesc, address, coordX, coordY, null!=userInfo?userInfo.getName():"", reporterMobile);
        //对象转换成xml字符串
        JaxbUtil requestBinder = new JaxbUtil(WisdomPlatform.class,CollectionWrapper.class); 
        String reqXml = requestBinder.toXml(wisdomPlatform, "utf-8");
        logger.info("请求的xml是：\n"+reqXml);
        //发送请求
        send(reqXml);
        return null;
    }
    
    /**
     * 登录校验，返回登录的用户对象
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月17日上午11:48:33
     */
    private UserInfo loginCheck(String token) throws BusinessException{
      //查询用户
        UserSession userSession = userSessionManager.getByToken(token);
        if (userSession == null) { 
            //需要登录
            throw new BusinessException(
                    ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
                    ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
        }
        Long userId = userSession.getUserId();
        return userInfoDao.get(userId);
    }
    
    /**
     * 发送请求
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月14日下午5:42:39
     * @throws Exception 
     */
    private void send(String reqXml) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost(PropertiesConfig.getString(PropertyKey.AMQP_HOST));
        factory.setUsername(PropertiesConfig.getString(PropertyKey.AMQP_USERNAME));
        factory.setPassword(PropertiesConfig.getString(PropertyKey.AMQP_PASSWORD));
        factory.setPort(PropertiesConfig.getInteger(PropertyKey.AMQP_PORT));
        factory.setVirtualHost("/");
        //创建一个连接
        Connection connection = factory.newConnection();
        //创建一个频道
        Channel channel = connection.createChannel();
        //申明交换区
        channel.exchangeDeclare(PropertiesConfig.getString(PropertyKey.AMQP_EXCHANGENAME), "topic",true);
        //发送
        channel.basicPublish(PropertiesConfig.getString(PropertyKey.AMQP_EXCHANGENAME), PropertiesConfig.getString(PropertyKey.AMQP_ROUTINGKEY), null, reqXml.getBytes());
        logger.info(" 发送的路由键是 '" + PropertiesConfig.getString(PropertyKey.AMQP_ROUTINGKEY) + "'发送的消息是:'" + reqXml.getBytes() + "'");
        channel.close();
        connection.close();        
    }
    
    /**
     * 生成上报对象
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月14日下午5:39:10
     */
    private WisdomPlatform caeateObj(String eventId, String appId, String imgs, String recDesc, String address, String coordX, String coordY,
            String reporterName, String reporterMobile){
        String reportnum = caeateReportnum();
        WisdomPlatform wisdomPlatform = new WisdomPlatform();
        //报案编号
        wisdomPlatform.setReportnum(reportnum);
        //案件来源ID  暂定，需要确认
        wisdomPlatform.setRecSource(PropertiesConfig.getString(PropertyKey.AMQP_SOURCE));
        String[] eventIds = eventId.split(",");
        //事件 ID
        wisdomPlatform.setEventtypeid(eventIds[0]+"");
        //大类 ID
        wisdomPlatform.setMaintypeid(eventIds[1]+"");
        if(eventIds.length>2){
            //子类 ID
            wisdomPlatform.setSubtypeid(eventIds[2]+"");
        }
        //案件描述
        wisdomPlatform.setRecDesc(StringUtils.isBlank(recDesc)?"":recDesc);
        //案件地址
        wisdomPlatform.setAddress(StringUtils.isBlank(address)?"":address);
        Coord coord = new Coord();
        //X坐标
        coord.setCoordX(StringUtils.isBlank(coordX)?"":coordX);
        //Y坐标
        coord.setCoordY(StringUtils.isBlank(coordY)?"":coordY);
        wisdomPlatform.setCoord(coord);
        //上报者姓名
        wisdomPlatform.setReporterName(StringUtils.isBlank(reporterName)?"":reporterName);
        //上报者手机号
        wisdomPlatform.setReporterMobile(StringUtils.isBlank(reporterMobile)?"":reporterMobile);
        Medias medias = imgUpLoad(imgs, appId);
        wisdomPlatform.setMedias(medias);
        return wisdomPlatform;
    }
    
    /**
     * 图片上传
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月14日下午2:31:45
     */
    private Medias imgUpLoad(String imgs,String appId){
        Medias medias = new Medias();
        medias.setMediaName("");
        medias.setMediaType("");
        medias.setMediaPath("");
        if(StringUtils.isNotBlank(imgs)){
            String fileUrlPath = PropertiesConfig.getString(PropertyKey.FILE_URL_PATH);
            //保存图片，返回图片路径
            String filePath = PropertiesConfig.getString(PropertyKey.FILE_LOCAL_PATH);
            //图片保存路径
            String modulePath = handleModulePath(appId);
            if (!filePath.endsWith(File.separator)) {
                filePath += File.separator;
            }
            String[] imgArray = imgs.split(",");
            for (String img : imgArray) {
                String mediaName = StringUtil.getUUID();
                String fileName = StringUtil.getUUID() + ".jpg";
                Base64Util.decodeBase64(img.replaceAll("\\s*|\t|\r|\n", ""), modulePath + fileName, filePath);
                medias.setMediaName(StringUtils.isBlank(medias.getMediaName())?(mediaName):(medias.getMediaName()+","+mediaName));
                medias.setMediaType(StringUtils.isBlank(medias.getMediaType())?("jpg"):(medias.getMediaType()+","+"jpg"));
                medias.setMediaPath(StringUtils.isBlank(medias.getMediaPath())?(fileUrlPath+modulePath+fileName):(medias.getMediaPath()+","+fileUrlPath+modulePath+fileName));
            }
        }
        return medias;
    }
    
    /**
     * <p>Description: 处理图片保存路径</p>
     * @author Tangtao on 2016年7月4日
     * @param modulePath
     * @param appId
     * @return
     */
    private String handleModulePath(String appId) {
        StringBuilder modulePathBuilder = new StringBuilder();//app_27/web/question/
        modulePathBuilder.append(File.separator).append("app_").append(StringUtils.isBlank(appId) ? 0 : appId.trim());
        modulePathBuilder.append(File.separator).append("web").append(File.separator).append("question").append(File.separator);
        return modulePathBuilder.toString();
    }
    
    
    /**
     * 生成报案编号
     * @Description yyyymmdd+4位数字，每天清零重新开始，开始数字0001
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月14日上午11:27:09
     */
    private String caeateReportnum(){
        synchronized (lock1) {
            String nowDay = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
            if(day.equals(nowDay)){
                nunber++;
                String ret = ""+nunber;
                for (int i = ret.length(); i <4; i++) {
                    ret = "0"+ret;
                }
                return nowDay+ret;
            }else{
                day = nowDay;
                nunber = 1;
                return nowDay+"0001";
            }
        }
    }

}
