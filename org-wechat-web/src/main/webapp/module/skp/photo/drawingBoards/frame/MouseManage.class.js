/**
 * Created by Administrator on 2015/3/19.
 * 鼠标管理器
 */
drawingBoards.frame.MouseManage = function(system){

    var _my = this;
    var _canvas = system.getCanvas();
    var _stage = system.getStage();
    var _display = null;

    _canvas.addEventListener("mousemove", onCanvasMouseMoveHandler);
    _canvas.addEventListener("mousedown", onCanvasMouseDownHandler);
    _canvas.addEventListener("mouseup", onCanvasMouseUpHandler);
    _canvas.addEventListener("click", onCanvasClickHandler);

    _canvas.addEventListener("touchstart", onTouchstartHandler);//触摸开始
    _canvas.addEventListener("touchmove", onTouchmoveHandler);//屏幕上滑动
    _canvas.addEventListener("touchend", onTouchendHandler);//触摸结束

    //设置当前鼠标停留的元件
    _my.setDisplay = function(display){

        if(_display === display){
            return null;
        }

        if(_display != null){
            _display.sendMessage(new drawingBoards.MouseOutEvent());
        }

        _display = display;

        if(_display != null){
            _display.sendMessage(new drawingBoards.MouseOverEvent());
        }
    };

    //鼠标在绘图标签上移动
    function onCanvasMouseMoveHandler(event){

        //console.log("坐标: x轴"+event.clientX+" y轴"+event.clientY);
        var _box  = _canvas.getBoundingClientRect();
        _stage.mouseX = parseInt(event.clientX-_box.left);
        _stage.mouseY = parseInt(event.clientY-_box.top);
    }

    //鼠标点击
    function onCanvasClickHandler(){

        if(_display != null){
            _display.sendMessage(new drawingBoards.ClickEvent());
        }
        _stage.sendMessage(new drawingBoards.ClickEvent());
    }

    //鼠标按下鼠标
    function onCanvasMouseDownHandler(){

        if(_display != null){
            _display.sendMessage(new drawingBoards.MouseDownEvent());
        }
        _stage.sendMessage(new drawingBoards.MouseDownEvent());
    }

    //鼠标放开左键
    function onCanvasMouseUpHandler(){

        if(_display != null){
            _display.sendMessage(new drawingBoards.MouseUpEvent());
        }
        _stage.sendMessage(new drawingBoards.MouseUpEvent());
    }

    //开始触摸
    function onTouchstartHandler(event){

        event.preventDefault();

        var _box  = _canvas.getBoundingClientRect();
        _stage.mouseX = parseInt(event.touches[0].clientX-_box.left);
        _stage.mouseY = parseInt(event.touches[0].clientY-_box.top);

        onCanvasMouseDownHandler();
    }

    //屏幕上滑动
    function onTouchmoveHandler(event){

        event.preventDefault();
        onCanvasMouseMoveHandler({

            clientX:event.touches[0].clientX,
            clientY:event.touches[0].clientY
        })
    }

    //触摸结束
    function onTouchendHandler(event){

        event.preventDefault();
        onCanvasMouseUpHandler();
        onCanvasClickHandler();
    }
};