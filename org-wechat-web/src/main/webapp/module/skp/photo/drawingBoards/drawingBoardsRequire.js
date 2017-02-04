/**
 * Created by Administrator on 2015/3/20.
 */

//包名
var drawingBoards ={

    frame:{},//框架
    displayObjects:{},//显示对象
    components:{}//组件
};

//配置加载器
drawingBoards.require = function(config, seat){

    if(seat==null) {

        seat = "";
    }

    if(!config.paths){

        config.paths = {};
    }

    //框架配置
    config.paths.messages = seat+"drawingBoards/frame/Messages.class";
    config.paths.heartbeat = seat+"drawingBoards/frame/Heartbeat.class";
    config.paths.rendering = seat+"drawingBoards/frame/Rendering.class";
    config.paths.mouseManage = seat+"drawingBoards/frame/MouseManage.class";

    //显示对象配置
    config.paths.displayObjects = seat+"drawingBoards/displayObjects/DisplayObjects.class";
    config.paths.displayObjectsContainer = seat+"drawingBoards/displayObjects/DisplayObjectsContainer.class";
    config.paths.stage = seat+"drawingBoards/displayObjects/Stage.class";
    config.paths.movieClip = seat+"drawingBoards/displayObjects/MovieClip.class";

    //组件配置
    config.paths.sound = seat+"drawingBoards/components/Sound.class";
    config.paths.resourceManage = seat+"drawingBoards/components/ResourceManage.class";
    config.paths.platformJudge = seat+"drawingBoards/components/PlatformJudge.class";
    config.paths.mathReam = seat+"drawingBoards/components/MathReam.class";

    //核心配置
    config.paths.drawingBoardsSystem = seat+"drawingBoards/DrawingBoardsSystem.class";
    config.paths.drawingBoardsSystemMessages = seat+"drawingBoards/DrawingBoardsSystemMessages.class";
    config.paths.drawingBoards = seat+"drawingBoards/DrawingBoards.class";

    //依赖关系
    if(!config.shim){
        config.shim = {};
    }

    config.shim.drawingBoards = {
        deps:
        [
            "stage",
            "movieClip",
            "messages",
            "heartbeat",
            "drawingBoardsSystemMessages",
            "drawingBoardsSystem",
            "rendering",
            "mouseManage"
        ]
    };

    config.shim.displayObjects = {
        deps:
        [
            "messages"
        ]
    };

    config.shim.displayObjectsContainer = {
        deps:
        [
            "displayObjects"
        ]
    };

    config.shim.movieClip = {
        deps:
        [
            "displayObjectsContainer"
        ]
    };

    config.shim.stage = {
        deps:
        [
            "displayObjectsContainer"
        ]
    };

    config.shim.heartbeat = {
        deps:
        [
            "drawingBoardsSystemMessages",
            "drawingBoardsSystem"
        ]
    };

    return config;
};