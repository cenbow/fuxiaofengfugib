/**
 * Created by Administrator on 2015/4/20.
 */
var photo = {};

photo.require = function(config, seat){

    if(seat==null){

        seat = "";
    }

    config.paths.photo = seat+"photo/Photo.class";

    //依赖关系
    if(!config.shim){

        config.shim = {};
    }

    config.shim.photo = {

        deps:
        [
            "drawingBoards",
            "mathReam",
            "movieClip"
        ]
    };

    return config;
};