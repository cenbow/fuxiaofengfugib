/**
 * Created by Administrator on 2015/3/16.
 * 渲染器
 *
 * system                系统对象
 * mouseManage  鼠标管理器
 */
drawingBoards.frame.Rendering = function(system, mouseManage){

    var _canvas = system.getCanvas();
    var _context = system.getContext();
    var _stage = system.getStage();
    var _displayList = _stage.displayList();

    //监听心跳
    system.enrollMessage(drawingBoards.HeartbeatEvent.type, onHeartbeatEventHandler);

    //心跳处理
    function onHeartbeatEventHandler(){

        rendering();
    }

    //渲染
    function rendering(){

        var _displayIn = null;//鼠标所在的显示对象

        _context.setTransform(1, 0, 0, 1, 0, 0);
        _context.clearRect(0, 0 , _canvas.width, _canvas.height);

        for(var _i=_displayList.length-1; _i>=0; _i--){

            _displayList[_i].rendering(_context, _stage);

            //鼠标判断
            if(_displayIn == null) {

                var _box  = _canvas.getBoundingClientRect();
                var _imgData = _context.getImageData(_stage.mouseX+_box.left, _stage.mouseY+_box.top, 1, 1);

                if (_imgData.data[0] != 0 || _imgData.data[1] != 0 || _imgData.data[2] != 0 || _imgData.data[3] != 0) {

                    _displayIn = _displayList[_i];
                }
            }
        }

        mouseManage.setDisplay(_displayIn);
    }
};

































