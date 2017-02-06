package com.cqliving.cloud.online.wz.manager;

import java.util.List;

import com.cqliving.cloud.online.interfacc.dto.WzCollectInfoData;
import com.cqliving.cloud.online.wz.domain.WzCollectInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 信息收集表 Manager
 * Date: 2016-05-10 09:47:12
 * @author Code Generator
 */
public interface WzCollectInfoManager extends EntityService<WzCollectInfo> {
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月12日
     * @param appId
     * @param appAuthorityId
     * @return
     */
    public List<WzCollectInfo> getByAppAuthority(Long appId, Long appAuthorityId);
    
    /**
     * Title:获得用户收集的信息
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月8日
     * @param appId
     * @param questionId
     * @return
     */
    public List<WzCollectInfoData> getUserCollectInfo(Long appId, Long questionId);
}
