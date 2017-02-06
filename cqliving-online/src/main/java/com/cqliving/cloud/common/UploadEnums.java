package com.cqliving.cloud.common;

/**
 * 上传枚举.
 */
public class UploadEnums {
    public enum Module {
        COMMON("common", "公共模块", "common");

        private String moduleName;
        private String moduleDescription;
        private String modulePath;

        Module(String moduleName, String moduleDescription, String modulePath) {
            this.moduleName = moduleName;
            this.moduleDescription = moduleDescription;
            this.modulePath = modulePath;
        }

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public String getModuleDescription() {
            return moduleDescription;
        }

        public void setModuleDescription(String moduleDescription) {
            this.moduleDescription = moduleDescription;
        }

        public String getModulePath() {
            return modulePath;
        }

        public void setModulePath(String modulePath) {
            this.modulePath = modulePath;
        }
    }

    public enum HandleType {
        NORMAL, SCALE;
    }
}
