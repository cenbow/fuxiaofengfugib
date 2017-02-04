/**
 * Created by Administrator on 2015/7/16.
 */
drawingBoards.displayObjects = function(){

    drawingBoards.displayObjects.DisplayObjectsContainer.call(this);

    var _my = this;
    var _super = {};

    _my.fied = new Field();//文本属性

    _super.rendering = _my.rendering;
    _my.rendering = function(context, parent){

        var _x = 0;
        var _y = 0;

        //调用父类方法
        _super.rendering(context, parent);

        if(!_my.fied.text || !_my.fied.size)
            return null;

        //设置字体样式
        context.font = _my.fied.size+"px "+_my.fied.font;

        //水平对齐方式
        switch (_my.fied.alignH){

            case "left":{

                _x = _my.x;
                context.textAlign = "start";
                break;
            }

            case "right":{

                _x = _my.x+_my.width;
                context.textAlign = "right";
                break;
            }

            case "center":{

                _x = _my.x+_my.width/2;
                context.textAlign = "center";
                break;
            }
        }

        //垂直方向对齐
        switch (_my.fied.alignV){

            case "top":{

                _y = _my.y;
                context.textBaseline = "top";
                break;
            }

            case "middle":{

                _y = _my.y+_my.height/2;
                context.textBaseline = "middle";
                break;
            }

            case "beneath":{

                _y = _my.y+_my.height;
                context.textBaseline = "alphabetic";
                break;
            }
        }

        context.fillStyle = _my.fied.textColor;
        context.fillText(_my.fied.text, _x, _y);
    };

    //文本属性
    function Field(){

        var my = this;

        my.text = "";//文本内容
        my.alignH = "center";//水平对齐方式  left | right | center
        my.alignV = "middle";//垂直对齐方式  top | middle | beneath
        my.font = "微软雅黑";//字体
        my.size = 12;//字号
        my.textColor = "#000000";//文本颜色
    }
};
drawingBoards.displayObjects.Stage.prototype = new drawingBoards.displayObjects.DisplayObjectsContainer();