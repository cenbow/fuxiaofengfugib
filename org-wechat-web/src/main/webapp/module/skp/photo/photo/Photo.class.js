/**
 * Created by Administrator on 2015/7/14.
 */
photo.Photo = function(db){

    photo.drawingBoards = db;

    var _my = this;
    var _mathReam = drawingBoards.components.MathReam;
    var _mc = new drawingBoards.displayObjects.MovieClip();//照片
    var _adorns = {};//装饰字典
    var _stage = photo.drawingBoards.getStage();
    var _canvas = photo.drawingBoards.getCanvas();
    var _touches = 0;
    var _focus = null;//焦点
    var _oldW, _oldH, _oldX, _oldY, _oldR;

    _stage.addChild(_mc);

    _canvas.addEventListener("touchstart", onTouchstartHandler);//触摸开始
    _canvas.addEventListener("touchmove", onTouchstartHandler);//屏幕上滑动
    _canvas.addEventListener("touchend", onTouchstartHandler);//触摸结束

    /**
     * 设置图片
     * image：图片image
     * */
    _my.setPhoto = function(image){

        _mc.setMovieData(image, []);
        _stage.addChildAt(_mc, 0);

        _mc.rotationBasePoint.x = 0;
        _mc.rotationBasePoint.y = 0;

        if(_mc.width>_mc.height)
        {
            _my.setPhotoAngle(90);
        }
        else
        {
            _my.setPhotoAngle(0);
        }
    };

    /**
     * 获取照片的角度
     * */
    _my.getPhotoAngle = function(){

        return _mc.getRotation();
    };

    /**
     * 设置照片的角度  0、90、180、270
     * */
    _my.setPhotoAngle = function(value){

        value = value%360;
        if(value % 90 != 0){

            throw new Error("参数错误，只支持90的倍数");
        }

        var ret;

        _mc.setRotation(value);
        if(value==0)
        {
            ret = _mathReam.getFillDimensionsAll
            (
                {width: _mc.width, height:_mc.height},
                {width: _stage.getStageWidth(), height:_stage.getStageHeight()}
            );

            _mc.width = ret.width;
            _mc.height = ret.height;
            _mc.x = (_stage.getStageWidth()-_mc.width)/2;
            _mc.y = (_stage.getStageHeight()-_mc.height)/2;
        }
        else if(value==180)
        {
            ret = _mathReam.getFillDimensionsAll
            (
                {width: _mc.width, height:_mc.height},
                {width: _stage.getStageWidth(), height:_stage.getStageHeight()}
            );

            _mc.width = ret.width;
            _mc.height = ret.height;
            _mc.x = (_stage.getStageWidth()-_mc.width)/2+_mc.width;
            _mc.y = (_stage.getStageHeight()-_mc.height)/2+_mc.height;
        }
        else if(value==90)
        {
            ret = _mathReam.getFillDimensionsAll
            (
                {width: _mc.width, height:_mc.height},
                {height: _stage.getStageWidth(), width:_stage.getStageHeight()}
            );

            _mc.width = ret.width;
            _mc.height = ret.height;

            _mc.x = (_stage.getStageWidth()-_mc.height)/2 + _mc.height;
            _mc.y = (_stage.getStageHeight()-_mc.width)/2;
        }
        else if(value==270)
        {
            ret = _mathReam.getFillDimensionsAll
            (
                {width: _mc.width, height:_mc.height},
                {height: _stage.getStageWidth(), width:_stage.getStageHeight()}
            );

            _mc.width = ret.width;
            _mc.height = ret.height;

            _mc.x = (_stage.getStageWidth()-_mc.height)/2;
            _mc.y = (_stage.getStageHeight()-_mc.width)/2 + _mc.width;
        }
    };

    /**
     * 添加饰品
     *
     * id 饰品ID
     * image 图片地址
     * x 初始化x坐标
     * y 初始化y坐标
     * isControl 是否可以编辑
     * isFit 是否满屏
     * */
    _my.addAdorn = function(id, image, x, y, isControl, isFit){

        if(_adorns[id])
        {
            _adorns[id].image.addEventListener("load", onAdornsLoadHandler);
            _adorns[id].image.src = image;
            _adorns[id].mc.x = x;
            _adorns[id].mc.y = y;
            _adorns[id].isControl = isControl;
            _adorns[id].isFit = isFit;
        }
        else
        {
            _adorns[id] = {};
            _adorns[id].mc = new drawingBoards.displayObjects.MovieClip();
            _adorns[id].image = new Image();
            _adorns[id].image.addEventListener("load", onAdornsLoadHandler);
            _adorns[id].image.src = image;
            _adorns[id].mc.x = x;
            _adorns[id].mc.y = y;
            _adorns[id].isControl = isControl;
            _adorns[id].isFit = isFit;
            _adorns[id].image.adorns = _adorns[id];
            if(_adorns[id].isControl)
                _adorns[id].mc.enrollMessage(drawingBoards.MouseOverEvent.type, onAdornsMouseMoveEventHandler);

            _stage.addChild(_adorns[id].mc);
        }
    };

    /**
     * 删除饰品
     *
     * id 饰品ID
     * */
    _my.remAdorn = function(id){

        if(_adorns[id])
        {
            _adorns[id].mc.removeChildThat();
            delete _adorns[id];
        }
    };

    /**
     * 饰品列表
     * */
    _my.getAdorns = function(){

        return _adorns
    };

    /**
     * 饰品是否存在
     *
     * id 饰品ID
     * */
    _my.isHave = function(id){

        var _r = false;
        if(_adorns[id])
        {
            _r =  true;
        }
        return _r;
    };

    /**
     * 获取位图
     * */
    _my.getBitmap = function(){

        return _stage.toDataURL("image/png");
    };

    var _startingPoint;//触摸点
    var _history = {x:0, y:0};//操作对象历史位置

    /**
     * 点击了元件
     * */
    function onAdornsMouseMoveEventHandler(event){

        onTouchstartHandler({touches:{length:0}});
        _focus = event.target;
        _stage.addChild(_focus);
    }

    /**
     * 配件加载完成
     * */
    function onAdornsLoadHandler(event){

        var mc;

        event.currentTarget.removeEventListener(event.type, onAdornsLoadHandler);
        event.currentTarget.adorns.mc.setMovieData(event.currentTarget, []);

        mc = event.currentTarget.adorns.mc;

        //初始化位置
        if(mc.x ==-1 && mc.y==-1){

            mc.x = _stage.getStageWidth()/2-mc.width/2;
            mc.y = _stage.getStageHeight()/2-mc.height/2;
        }

        if(event.currentTarget.adorns.isFit){

            var ret = _mathReam.getFillDimensionsAll
            (
                {width: mc.width, height:mc.height},
                {width: _stage.getStageWidth(), height:_stage.getStageHeight()}
            );

            mc.width = ret.width;
            mc.height = ret.height;
        }

        //是否切换焦点
        if(event.currentTarget.adorns.isControl){

            _focus = mc;
        }
    }

    //触摸事件
    function onTouchstartHandler(event){

        if(_focus == null)
            return;

        console.log(event.touches.length);

        if(event.touches.length == 1){

            if(_touches != 1)
            {
                _startingPoint = [
                    {x:event.touches[0].clientX, y:event.touches[0].clientY}
                ];
                _history.x = _focus.x;
                _history.y = _focus.y;
            }
            else if(_touches == 1)
            {
                var _nx, _ny;

                _nx = _history.x + (event.touches[0].clientX - _startingPoint[0].x);
                _ny = _history.y + (event.touches[0].clientY - _startingPoint[0].y);
                _focus.x = _nx;
                _focus.y = _ny;
            }
        }

        else if(event.touches.length == 2){

            if(_touches != 2)
            {
                _startingPoint = [
                    {x:event.touches[0].clientX, y:event.touches[0].clientY},
                    {x:event.touches[1].clientX, y:event.touches[1].clientY}
                ];

                _oldH = _focus.height;
                _oldW = _focus.width;
                _oldX = _focus.x;
                _oldY = _focus.y;
                _oldR = _focus.getRotation();
            }
            else if(_touches == 2)
            {
                var newL, oldL, newW, newH, newR, oldR, oldC, newC;

                oldL =  _mathReam.removing(
                    {x:_startingPoint[0].x, y:_startingPoint[0].y},
                    {x:_startingPoint[1].x, y:_startingPoint[1].y}
                );

                newL = _mathReam.removing(
                    {x:event.touches[0].clientX, y:event.touches[0].clientY},
                    {x:event.touches[1].clientX, y:event.touches[1].clientY}
                );

                newR = _mathReam.angle(
                    {x:event.touches[0].clientX, y:event.touches[0].clientY},
                    {x:event.touches[1].clientX, y:event.touches[1].clientY}
                );
                oldR = _mathReam.angle(
                    {x:_startingPoint[0].x, y:_startingPoint[0].y},
                    {x:_startingPoint[1].x, y:_startingPoint[1].y}
                );

                newC = _mathReam.centerPoint(
                    {x:event.touches[0].clientX, y:event.touches[0].clientY},
                    {x:event.touches[1].clientX, y:event.touches[1].clientY}
                );
                oldC = _mathReam.centerPoint(
                    {x:_startingPoint[0].x, y:_startingPoint[0].y},
                    {x:_startingPoint[1].x, y:_startingPoint[1].y}
                );

                newW  = _oldW+(newL-oldL)*2;
                newW = newW<10?10:newW;
                newH = _oldH*(newW/_oldW);

                _focus.x = (_oldX+(_oldW-newW)/2)+(newC.x-oldC.x);
                _focus.y = (_oldY+(_oldH-newH)/2)+(newC.y-oldC.y);
                _focus.width  = newW;
                _focus.height = newH;

                _focus.rotationBasePoint.x = _focus.width/2;
                _focus.rotationBasePoint.y = _focus.width/2;

                _focus.setRotation(_oldR+(newR-oldR));
            }
        }
        _touches = event.touches.length
    }
};