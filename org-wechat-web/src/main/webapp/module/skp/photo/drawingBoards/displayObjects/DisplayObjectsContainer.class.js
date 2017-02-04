/**
 * Created by Administrator on 2015/3/13.
 * 显示对象容器
 *
 * 继承 DisplayObjects
 */
drawingBoards.displayObjects.DisplayObjectsContainer = function()
{
    drawingBoards.displayObjects.DisplayObjects.call(this);//继承

    var _my = this;
    var _displayList = [];//显示对象列表

    //显示对象列表
    _my.displayList = function()
    {
        return _displayList;
    };

    //添加显示对象
    _my.addChild = function(child)
    {
        var _t = _displayList.indexOf(child);
        if(_t>=0){

            _displayList.splice(_t, 1);
            _displayList.push(child);
        }
        else
        {
            child.stage = _my.stage;
            _displayList.push(child);
            child.sendMessage(new drawingBoards.AddedToStageEvent());
        }
    };

    //添加显示列表
    _my.addChildAt = function(child, index)
    {
        var _t = _displayList.indexOf(child);
        if(_t>=0){

            _displayList.splice(_t, 1);
            _displayList.splice(index, 0, child);
        }
        else
        {
            child.stage = _my.stage;
            _displayList.splice(index, 0, child);
            child.sendMessage(new drawingBoards.AddedToStageEvent());
        }
    };

    //删除显示对象
    _my.removeChild = function(child)
    {
        _my.removeChildAt(_displayList.indexOf(child));
    };

    //删除知道索引的显示对象
    _my.removeChildAt = function(index)
    {
        if(index>=0&& index<_displayList.length)
        {
            _displayList[index].sendMessage(new drawingBoards.RemovedFromStageEvent());
            _displayList[index].stage = null;
            _displayList.splice(index, 1);
        }
    };
};
drawingBoards.displayObjects.DisplayObjectsContainer.prototype = new drawingBoards.displayObjects.DisplayObjects();