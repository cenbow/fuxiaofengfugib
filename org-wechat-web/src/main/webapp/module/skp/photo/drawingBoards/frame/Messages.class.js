/**
 * Created by Administrator on 2015/3/12.
 * 消息管理器
 */
drawingBoards.frame.Messages = function(target){

    var _my = this;
    var _target = target;
    var _meals = {};//注册对象列表

    /**
     * 判断是否注册了消息
     * */
    function isHoveEnroll(type, mes){

        var sun = _meals[type];
        if(sun && sun.constructor == Array)
        {
            return sun.indexOf(mes)>=0;
        }
        else
        {
            _meals[type] = [];
            return false;
        }
    }

    /**
     * 注册消息
     * */
    _my.enrollMessage = function(type, mes){

        if(isHoveEnroll(type, mes) == false)
        {
            _meals[type].push(mes);
        }
    };

    /**
     * 注销消息
     * */
    _my.logoutMessages = function(type, mes){

        var sun = _meals[type];
        if(sun && sun.constructor == Array){

            var index = sun.indexOf(mes);
            if(index>=0){

                sun.splice(index, 1);
            }
        }
    };

    /**
     * 发送消息
     * */
    _my.sendMessage = function(event)
    {
        var sun;

        event.type = event.constructor.type;
        sun = _meals[event.type];
        if(sun && sun.constructor == Array)
        {
            for(var i=0; i<sun.length; i++)
            {
                var messageMethod = sun[i];
                if(typeof messageMethod == "function")
                {
                    event.target = _target;
                    event.messageMethod = messageMethod;
                    messageMethod(event);
                }
            }
        }
    };
};
