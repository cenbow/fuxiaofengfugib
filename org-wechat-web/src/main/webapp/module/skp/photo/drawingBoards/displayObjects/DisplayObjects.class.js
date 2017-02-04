/**
 * Created by Administrator on 2015/3/13.
 * 所有显示对象基类
 */
drawingBoards.displayObjects.DisplayObjects = function(){

    var _my = this;
    var _alpha = 1.0;//显示对象透明度
    var _rotation = 0;//旋转角度
    var _messages = new drawingBoards.frame.Messages(this);//消息器

    //系统消息
    _my.enrollMessage = _messages.enrollMessage;
    _my.logoutMessages = _messages.logoutMessages;
    _my.sendMessage = _messages.sendMessage;

    _my.graphics = new Graphics();//矢量图

    //基础属性
    _my.x = 0;
    _my.y = 0;
    _my.width  = 0;
    _my.height = 0;
    _my.mouseX = 0;
    _my.mouseY = 0;
    _my.mouseEnabled = false;//是否接受鼠标操作
    _my.rotationBasePoint = {x:0, y:0};//旋转基点

    /**
     * 开始渲染
     * */
    _my.rendering = function(context, parent){

        context.beginPath();//清楚状态
        context.setTransform(1, 0, 0, 1, 0, 0);
        context.globalAlpha = _my.getAlpha();
        context.translate(_my.x+_my.rotationBasePoint.x, _my.y+_my.rotationBasePoint.y);
        context.rotate(_my.getRotation() * Math.PI/180);
    };

    /**
     * 删除自己
     * */
    _my.removeChildThat = function(){

        if(_my.stage)
            _my.stage.removeChild(_my);
    };

    /**
     * 获取透明度
     * */
    _my.getAlpha = function(){

        return _my;
    };
    /**
     * 设置透明度
     * */
    _my.setAlpha = function(value){

        value = value>1?1:value;
        value = value<0?0:value;
        _alpha = value;
    };

    /**
     * 获取角度
     * */
    _my.getRotation = function(){

        return _rotation;
    };
    /**
     * 设置角度
     * */
    _my.setRotation = function(value){

        _rotation = value%360;
    };

    /**
     * 获取舞台
     * */
    _my.getStage = function(){

        return _my.stage
    };

    function Graphics(){

        var _my = this;

        _my.graphicsType = Graphics.RECT;//矢量图类型
        _my.fillStyle = null;//填充颜色
        _my.strokeStyle = null;//边线颜色
        _my.lineWidth = 2;//边线宽度
        _my.points = [];//点数组
        _my.rotation = 0;

        /**
         * 创建矩形
         */
        _my.rect = function()
        {
            _my.graphicsType = Graphics.RECT;
        };

        //创建圆形
        _my.round = function()
        {
            _my.graphicsType = Graphics.ROUND;
        };

        //创建圆形
        _my.round = function()
        {
            _my.graphicsType = Graphics.CURVE;
        };
    }
    Graphics.RECT = "rect";//适量矩形
    Graphics.ROUND = "round";//适量圆形
    Graphics.CURVE = "curve";//路径
};


























