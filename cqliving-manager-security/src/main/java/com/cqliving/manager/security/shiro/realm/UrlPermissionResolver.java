package com.cqliving.manager.security.shiro.realm;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.util.AntPathMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/5/20.
 */
public class UrlPermissionResolver implements PermissionResolver {

    private Logger logger = LoggerFactory.getLogger(UrlPermissionResolver.class);

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Permission resolvePermission(String permissionString) {
        return new UrlPermission(permissionString);
    }

    public class UrlPermission implements Permission {

        private String permissionString;

        public UrlPermission(String permissionString) {
            logger.trace("new UrlPermission permissionString:{}", permissionString);
            this.permissionString = permissionString;
        }

        @Override
        public boolean implies(Permission p) {
            logger.trace("Permission Class Name:{}", p.getClass().getName());
            if(p instanceof UrlPermission){
                boolean b = pathMatcher.match(permissionString, ((UrlPermission) p).getPermissionString());
                logger.trace("Is UrlPermission {} . this permissionString:{}, param permissionString:{}",
                        b, this.permissionString, ((UrlPermission) p).getPermissionString());
                return b;
            }
            return false;
        }

        public String getPermissionString() {
            return permissionString;
        }
    }
}
