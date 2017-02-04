/**
 * Created by Administrator on 2015/4/15.
 */
drawingBoards.displayObjects.MovieClip = function(){

    drawingBoards.displayObjects.DisplayObjectsContainer.call(this);

    var _my = this;
    var _super = {};
    var _frames = [];//帧数据
    var _isPlay = this;
    var _currentFrame = 1;
    var _image = null;//图片数据

    /**
     * 开始渲染
     * */
    _super.rendering = _my.rendering;
    _my.rendering = function(context, parent){

        //调用父类方法
        _super.rendering(context, parent);

        if(_image==null)
            return null;

        //开始渲染
        context.drawImage(_image,  -_my.rotationBasePoint.x, -_my.rotationBasePoint.y, _my.width, _my.height);

        //是否播放
        if(_isPlay)
            _my.nextFrame();
    };

    /**
     * 总帧数
     * */
    _my.getTotalFrames = function(){

        return _my.frames.length;
    };

    /**
     * 获取当前帧
     * */
    _my.getCurrentFrame = function(){

        return _my.currentFrame;
    };

    /**
     * 设置影片剪辑 位图序列 和 帧数据
     * @ image  位图序列
     * @ frames  帧数据
     * */
    _my.setMovieData = function(image, frames){

        if(frames == null)
            frames = [];

        if(image && frames){

            _image = image;
            _frames = frames;
            _my.width = image.width;
            _my.height = image.height;
        }
    };

    /**
     * 跳到下一帧
     * */
    _my.nextFrame = function(){

        _currentFrame++;
        if(_currentFrame>_frames.length)
            _currentFrame=1;
    };

    /**
     * 跳到上一帧
     * */
    _my.prevFrame = function(){

        _currentFrame--;
        if(_currentFrame<1)
            _currentFrame = 1;
    };

    /**
     * 跳到制定帧并播放
     * */
    _my.gotoAndPlay = function(frame){

        setFrame(frame);
        _my.play();
    };

    /**
     * 跳到制定帧并停止
     * */
    _my.gotoAndStop = function(frame){

        setFrame(frame);
        _my.stop();
    };

    /**
     * 停止播放
     * */
    _my.stop = function(){

        _isPlay = false;
    };

    /**
     * 开发播放
     * */
    _my.play = function(){

        _isPlay = true;
    };

    /**
     * 获取当前帧数据
     * *//*
     function getCurrentFrameData(){

        return _my.frames[_currentFrame-1];
    }*/

    /**
     * 设置帧
     * */
    function setFrame(frame){

        _my.currentFrame = frame;

        if(_my.currentFrame>_my.frames.length)
            _my.currentFrame=1;

        if(_my.currentFrame<1)
            _my.currentFrame = 1;
    }
};
drawingBoards.displayObjects.MovieClip.prototype = new drawingBoards.displayObjects.DisplayObjectsContainer();