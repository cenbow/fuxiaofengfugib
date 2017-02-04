/**
 * Created by Administrator on 2015/7/10.
 * 声音管理类
 */

drawingBoards.components.Sound = function(url, fileName) {

    var _my = this;
    var _audio;
    var _number = -1;

    _audio = document.createElement("audio");
    _audio.setAttribute("src", url+fileName+"."+supportedAudioFormat(_audio));
    _audio.setAttribute("preload", "auto");
    document.body.appendChild(_audio);

    //播放状态
    _my.status = drawingBoards.components.Sound.STATUS_STOP;
    _audio.addEventListener("ended", onAudioEndedHandler);

    /**
     * 播放音乐
     * */
    _my.play = function(number){

        if(number == null)
            number = -1;

        _number = number;

        if(_number != 0)
            _audio.play();
    };

    /**
     * 停止播放
     * */
    _my.stop = function(){

        _audio.pause();
        _my.status = drawingBoards.components.Sound.STATUS_STOP;
    };

    /**
     * 回复播放
     * */
    _my.reply = function(){

        _audio.play();
        _my.status = drawingBoards.components.Sound.STATUS_PLAY;
    };

    /**
     * 设置音量大小(0-1)
     * */
    _my.setVolume = function(value){

        value = value>1?1:value;
        value = value<0?0:value;
        _audio.volume = value;
    };

    /**
     * 获取声音大小(0-1)
     * */
    _my.getVolume = function(){

        return _audio.volume;
    };

    /**
     * 播放完毕
     * */
    function onAudioEndedHandler(){

        if(_number>0)
            _number--;

        if(_number<0 || _number>0)
        {
            _my.reply();
        }
        else
        {
            _my.status = drawingBoards.components.Sound.STATUS_STOP;
        }
    }

    //检测浏览器支持的类型
    function supportedAudioFormat(audio){

        if (audio.canPlayType("audio/ogg")=="probably" ||audio.canPlayType("audio/ogg")=="maybe") {

            return "ogg";
        }
        else if (audio.canPlayType("audio/mp3")=="probably" ||audio.canPlayType("audio/mp3")=="maybe") {

            return "mp3";
        }
        else if (audio.canPlayType("audio/wav")=="probably" ||audio.canPlayType("audio/wav")=="maybe") {

            return "wav";
        }

        return "";
    }
};

drawingBoards.components.Sound.STATUS_STOP = "stop";
drawingBoards.components.Sound.STATUS_PLAY = "play";