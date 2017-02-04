/**
 * Created by Administrator on 2015/3/13.
 * 舞台
 */
drawingBoards.displayObjects.Stage = function(canvas)
{
    drawingBoards.displayObjects.DisplayObjectsContainer.call(this);

    var _canvas = canvas;
    this.stage = this;

    /**
     * 当前舞台位图数据
     * */
    this.toDataURL = function(string){

        return _canvas.toDataURL(string);
    };

    //获取舞台的宽度
    this.getStageWidth = function ()
    {
        return _canvas.width;
    };

    //获取舞台的高度
    this.getStageHeight = function ()
    {
        return _canvas.height;
    }
};
drawingBoards.displayObjects.Stage.prototype = new drawingBoards.displayObjects.DisplayObjectsContainer();
