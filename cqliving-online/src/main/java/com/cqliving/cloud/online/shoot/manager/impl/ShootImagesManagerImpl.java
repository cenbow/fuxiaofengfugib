package com.cqliving.cloud.online.shoot.manager.impl;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.shoot.dao.ShootImagesDao;
import com.cqliving.cloud.online.shoot.domain.ShootImages;
import com.cqliving.cloud.online.shoot.manager.ShootImagesManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("shootImagesManager")
public class ShootImagesManagerImpl extends EntityServiceImpl<ShootImages, ShootImagesDao> implements ShootImagesManager {
}
