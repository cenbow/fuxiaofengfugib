/*
绘图板类	
*/
drawingBoards.DrawingBoards = function(canvas)
{
    var _my = this;
    var system = new drawingBoards.DrawingBoardsSystem(canvas);
    var mouseManage = new drawingBoards.frame.MouseManage(system);
    new drawingBoards.frame.Heartbeat(system);//初始化心跳;
    new drawingBoards.frame.Rendering(system, mouseManage);//渲染器;

    //绘图对象
    _my.getCanvas = function(){

      return canvas;
    };

    //舞台
    _my.getStage = function(){

        return system.getStage();
    };

    /**
     * 设置舞台大小
     * */
    _my.setSize = function(width, height){

        canvas.width = width;
        canvas.height = height;
        system.getContext().globalCompositeOperation = drawingBoards.globalCompositeOperation;
    }
};
drawingBoards.globalCompositeOperation = "destination-over";//渲染叠加方式
