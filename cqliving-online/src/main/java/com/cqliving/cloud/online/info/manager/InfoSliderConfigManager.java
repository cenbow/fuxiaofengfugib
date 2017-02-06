package com.cqliving.cloud.online.info.manager;

import java.util.List;

import com.cqliving.cloud.online.info.domain.InfoSliderConfig;
import com.cqliving.cloud.online.info.dto.InfoSliderConfigDto;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯轮播图配置 Manager
 * Date: 2016-08-30 10:19:57
 * @author Code Generator
 */
public interface InfoSliderConfigManager extends EntityService<InfoSliderConfig> {
    /**
     * 通过appId查询轮播图配置列表信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日下午2:08:49
     */
    public List<InfoSliderConfigDto> getListByAppId(Long appId);
    
    /**
     * 保存轮播图配置
     * @Description 
     * @Company 
     * @parameter 
     * <p>appId：配置所属app</p>
     * <p>columnsId：配置所属栏目</p>
     * <p>value：配置值</p>
     * <p>userId：操作人Id</p>
     * <p>userNmae：操作人名称</p>
     * @return
     * @author huxiaoping 2016年8月30日下午4:00:54
     */
    public void save(Long appId,Long[] id,Long columnsId[],Integer[] value,Long userId,String userNmae);
    
    /**
     * 保存缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日下午5:31:52
     */
    public void saveCache(List<InfoSliderConfig> sliderConfigList,Long appId);
    
    /**
     * 获取缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日下午5:34:53
     */
    public Integer getCache(Long appId,Long columnsId);
}
