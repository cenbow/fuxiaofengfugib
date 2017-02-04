/**
 * Created by Administrator on 2015/7/11.
 *
 * 资源管理类
 */
drawingBoards.components.ResourceManage = function(){

    //常量
    var _constants ={

        //加载器数据类型
        TYPE_JSON:"typeJson",//json 数据
        TYPE_IMAGE:"typeImage",//图片数据

        //事件类型
        COMPLETE_ALL_EVENT:"completeAllEvent",//所以资源加载完毕
        COMPLETE_ITEM_EVENT:"completeItemEvent"//一个资源加载完毕
    };

    var _my = this;//自己
    var _loaderList = [];//加载器列表
    var _resourceList = {};//资源列表
    var _isLoad = false;//是否在加载
    var _messages = new drawingBoards.frame.Messages(_my);//消息器

    //系统消息
    _my.enrollMessage = _messages.enrollMessage;
    _my.logoutMessages = _messages.logoutMessages;
    _my.sendMessage = _messages.sendMessage;

    /**
     * 添加资源
     * */
    _my.addItem = function(type, url, key){

        if(!key)
            key = url;

        var isAdd = true;
        if(_resourceList[key]){

            isAdd = false;
        }

        for(var t=0; t<_loaderList; t++){

            if(_loaderList[t].key == key){

                isAdd = false;
                break;
            }
        }

        if(isAdd)
            _loaderList.push(getLoader(key, type, url));//添加加载器
    };

    /**
     * 加载资源
     * */
    _my.load = function(){

        doStartLoad();
    };

    /**
     * 获取常量
     * */
    _my.getConstants = function(name){

        return _constants[name];
    };

    /**
     * 获取资源
     * */
    _my.getResource = function(key){

        return _resourceList[key];
    };

    /**
     *  开始加载
     * */
    function doStartLoad(){

        //判断是在正在加载
        if(_isLoad)
            return null;
        else
            _isLoad = true;

        //是否还有加载列表(是否加载完成)
        if(_loaderList.length<1){

            _isLoad = false;
            _my.sendMessage(new CompleteAllEvent());
            return null;
        }

        //开始加载
        _loaderList[0].load();
    }

    /**
     * 获取加载器
     * */
    function getLoader(key, type, url) {

        switch(type)
        {
            case _constants.TYPE_JSON:

                return new LoaderJson(url,  type, key);
                break;

            case _constants.TYPE_IMAGE:

                return new LoaderImage(url,  type, key);
                break;
        }
    }

    /**
     * 加载完成
     * */
    function onLoadCompleteHandler(loaderItem){

        //删除已经加载完成的加载器
        var _t = _loaderList.indexOf(loaderItem);
        if(_t>-1)
            _loaderList.splice(_t, 1);

        _resourceList[loaderItem.key] = loaderItem.data;//保存数据到数据池
        _my.sendMessage(new CompleteItemEvent(loaderItem));//发送消息
        _isLoad = false;//标记现在状态未加载
        doStartLoad();//加载下一个元素
    }

    //消息对象*******************************************************************
    //所有资源加载完成
    function CompleteAllEvent(){}
    CompleteAllEvent.type = _constants.COMPLETE_ALL_EVENT;

    //完成了单个加载项
    function CompleteItemEvent(item){

        var _my = this;
        _my.loaderItem = item;//加载项目
    }
    CompleteItemEvent.type = _constants.COMPLETE_ITEM_EVENT;

    //加载器**********************************************************************
    /**
     * 加载器基类
     * */
    function LoaderBase(url,  type, key){

        var _my = this;

        _my.url = url;
        _my.type = type;
        _my.key = key;
        _my.data = null;

        _my.load = function(){};//开始加载
    }

    /**
     * json 加载器
     * */
     function LoaderJson(url,  type, key){

        LoaderBase.call(this, url,  type, key);

        var _my = this;

        _my.load = function(){

            $.ajax
            ({
                url:_my.url,
                async:true,
                dataType:"json",
                success:function(data)
                {
                    _my.data = data;
                    onLoadCompleteHandler(_my);
                }
            });
        }
    }
    LoaderJson.prototype = new LoaderBase();

    /**
     * 图片加载器
     * */
    function LoaderImage(url,  type, key){

        LoaderBase.call(this, url,  type, key);

        var _my = this;
        var _image = new Image();

        _image.addEventListener("load", onImageLoadHeader);

        _my.load = function(){

            _image.src = _my.url;
        };

        function onImageLoadHeader(){

            _my.data = _image;
            onLoadCompleteHandler(_my);
        }
    }
    LoaderImage.prototype = new LoaderBase();
};