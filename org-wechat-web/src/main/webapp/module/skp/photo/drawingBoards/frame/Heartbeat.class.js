/**
 * Created by Administrator on 2015/3/13.
 * 心跳
 */
drawingBoards.frame.Heartbeat = function(system)
{
    var oldTime = new Date().getTime();
    setInterval(function()
    {
        var newTime = new Date().getTime();
        system.sendMessage(new drawingBoards.HeartbeatEvent(newTime - oldTime));
        oldTime = newTime;
    }, 30)
};
