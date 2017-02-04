/**
 * Created by Administrator on 2015/3/13.
 * 系统消息类
 */

//系统-------------------------------------------------------------------------
/**
 * 心跳
 * @time 心跳的真实间隔时间
 * */
drawingBoards.HeartbeatEvent = function(time){
    this.time = time;
};
drawingBoards.HeartbeatEvent.type = "heartbeatEvent";

//渲染相关---------------------------------------------------------------------
/**
 * 渲染前事件
 * */
drawingBoards.EnterFrameEvent = function(){};
drawingBoards.EnterFrameEvent.type = "enterFrameEvent";

/**
 * 被添加到显示列表
 * */
drawingBoards.AddedToStageEvent = function(){};
drawingBoards.AddedToStageEvent.type = "addedToStageEvent";

/**
 * 被移除显示列表
 * */
drawingBoards.RemovedFromStageEvent = function(){};
drawingBoards.RemovedFromStageEvent.type = "removedFromStage";

//鼠标相关---------------------------------------------------------------------
/**
 * 鼠标离开元件
 * */
drawingBoards.ClickEvent = function(){};
drawingBoards.ClickEvent.type = "clickEvent";

/**
 * 鼠标在原件上移动
 * */
drawingBoards.MouseMoveEvent = function(){};
drawingBoards.MouseMoveEvent.type = "mouseMoveEvent";

/**
 * 鼠标离开元件
 * */
drawingBoards.MouseOutEvent = function(){};
drawingBoards.MouseOutEvent.type = "mouseOutEvent";

/**
 * 鼠标进入元件
 * */
drawingBoards.MouseOverEvent = function(){};
drawingBoards.MouseOverEvent.type = "mouseOverEvent";

/**
 * 鼠标按下
 * */
drawingBoards.MouseDownEvent = function(){};
drawingBoards.MouseDownEvent.type = "mouseDownEvent";

/**
 * 鼠标放开
 * */
drawingBoards.MouseUpEvent = function(){};
drawingBoards.MouseUpEvent.type = "mouseUpEvent";