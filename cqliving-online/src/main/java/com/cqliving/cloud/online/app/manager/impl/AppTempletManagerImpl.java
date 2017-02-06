package com.cqliving.cloud.online.app.manager.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.app.dao.AppColumnsDao;
import com.cqliving.cloud.online.app.dao.AppTempletDao;
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.domain.AppTemplet;
import com.cqliving.cloud.online.app.manager.AppTempletManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("appTempletManager")
public class AppTempletManagerImpl extends EntityServiceImpl<AppTemplet, AppTempletDao> implements AppTempletManager {
    
    @Autowired
    private AppColumnsDao appColumnsDao;
    
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    @Override
    public void deleteTemplet(Long[] ids) throws Exception{
        //校验是否被栏目使用
        AppTemplet templet = null;
        for(Long id :ids){
            if(null!=id){
                templet = this.get(id);
                if(null!=templet){
                    List<AppColumns> coiumnsList = appColumnsDao.findByTempletCodeAndAppId(templet.getAppId(), templet.getTempletCode());
                    if(null!=coiumnsList&&coiumnsList.size()>0){
                        throw new BusinessException("模板已经被使用，不能删除!");
                    }
                }
            }
        }
        super.removes((Serializable[])ids);
    }
    
    /**
     * 增加或保存
     */
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    @Override
    public void saveTemplet(AppTemplet templet) throws Exception{
        if(StringUtils.isNotBlank(templet.getTempletCode())){
            //校验模板code是否存在
            List<AppTemplet> templetList = null;
            if(null!=templet.getId()){
                templetList = this.getEntityDao().queryByAppAndCode(templet.getAppId(), templet.getTempletCode(),templet.getId());
            }else{
                templetList = this.getEntityDao().queryByAppAndCode(templet.getAppId(), templet.getTempletCode());
            }
            if(null!=templetList&&templetList.size()>0){
                throw new BusinessException("模板CODE已经存在");
            }
            this.save(templet);
        }
    }
    
    /**
     * 复制公共模板到某个app
     */
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    @Override
    public void copyConTemplet(Long appId){
      //复制模板
        List<AppTemplet> templetList = this.getEntityDao().queryByCommonType(AppTemplet.COMMONTYPE1);
        if(null!=templetList){
            List<AppTemplet> templetListNew = new ArrayList<AppTemplet>();
            AppTemplet templetnew ;
            for (AppTemplet templet : templetList) {
                templetnew = new AppTemplet();
                templetnew.setAppId(appId);
                templetnew.setCommonType(AppTemplet.COMMONTYPE2);
                templetnew.setImageUrl(templet.getImageUrl());
                templetnew.setName(templet.getName());
                templetnew.setTempletCode(templet.getTempletCode());
                templetnew.setTempletDesc(templet.getTempletDesc());
                templetnew.setType(templet.getType());
                templetListNew.add(templetnew);
            }
            this.getEntityDao().saves(templetListNew);
        }
    }
}
