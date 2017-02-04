/**
 * Created by Administrator on 2015/3/13.
 * 系统
 */
drawingBoards.DrawingBoardsSystem = function(canvas)
{
    var _canvas = canvas;//绘图标签
    var _context = _canvas.getContext('2d');//渲染上下文
    var _messages = new drawingBoards.frame.Messages(this);//消息器
    var _stage = new drawingBoards.displayObjects.Stage(canvas);//舞台对象

    //系统消息
    this.enrollMessage = _messages.enrollMessage;
    this.logoutMessages = _messages.logoutMessages;
    this.sendMessage = _messages.sendMessage;

    _context.globalCompositeOperation = "destination-over";//顶层渲染

    //获取舞台
    this.getStage = function(){

        return _stage;
    };

    //获取绘图标签
    this.getCanvas = function(){

        return _canvas;
    };

    //获取渲染上下文对象
    this.getContext = function(){

        return _context;
    };
};