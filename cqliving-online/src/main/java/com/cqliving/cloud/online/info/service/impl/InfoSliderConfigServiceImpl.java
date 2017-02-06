package com.cqliving.cloud.online.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.info.domain.InfoSliderConfig;
import com.cqliving.cloud.online.info.dto.InfoSliderConfigDto;
import com.cqliving.cloud.online.info.manager.InfoSliderConfigManager;
import com.cqliving.cloud.online.info.service.InfoSliderConfigService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("infoSliderConfigService")
@ServiceHandleMapping(managerClass = InfoSliderConfigManager.class)
public class InfoSliderConfigServiceImpl implements InfoSliderConfigService {

//	private static final Logger logger = LoggerFactory.getLogger(InfoSliderConfigServiceImpl.class);
//	@Autowired
//	private InfoSliderConfigManager infoSliderConfigManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询资讯轮播图配置失败")})
	public Response<PageInfo<InfoSliderConfig>> queryForPage(PageInfo<InfoSliderConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询资讯轮播图配置失败")})
	public Response<InfoSliderConfig> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除资讯轮播图配置失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存资讯轮播图配置失败")})
	public Response<Void> save(InfoSliderConfig infoSliderConfig) {
		return null;
	}
	
	/**
     * 通过appId查询轮播图配置列表信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日下午2:17:14
     */
    @Override
    @ServiceMethodHandle(managerMethodName="getListByAppId",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="通过appId查询轮播图配置列表失败")})
    public Response<List<InfoSliderConfigDto>> getListByAppId(Long appId) {
        return null;
    }

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
    @Override
    @ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存轮播图配置失败")})
    public Response<Void> save(Long appId,Long[] id,Long columnsId[],Integer[] value,Long userId,String userNmae) {
        return null;
    }
}
