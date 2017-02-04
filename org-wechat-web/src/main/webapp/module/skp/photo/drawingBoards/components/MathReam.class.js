/**
 * Created by Administrator on 2015/7/14.
 *
 * 数学公式
 */
drawingBoards.components.MathReam = function(){};

/**
 * 两点的距离
 *  a A点
 *  b B点
 *  距离
 */
drawingBoards.components.MathReam.removing = function (a, b){

    return Math.pow(Math.pow(a.x - b.x, 2)+Math.pow(a.y - b.y, 2), 0.5);
};

/**
 * 获取 目标矩形 放入 容器矩形的 合适的矩形尺寸
 * target 目标矩形
 * container 容器矩形
 * return 合适的矩形
 */
drawingBoards.components.MathReam.getFillDimensions = function (target, container){

    var ret = {};
    var wz = container.height*target.width/target.height;
    if(wz<container.width)
    {
        ret.width  = wz;
        ret.height = container.height;
    }
    else
    {
        ret.width  = container.width;
        ret.height = container.width*target.height/target.width;
    }
    return ret;
};

/**
 * 获取 目标矩形 放入 容器矩形的 盛满的矩形尺寸
 * @param target     目标矩形
 * @param container  容器矩形
 * @return           合适的矩形
 */
drawingBoards.components.MathReam.getFillDimensionsAll = function(target, container){

    var ret = {};
    var wz = container.height*target.width/target.height;
    if(wz>container.width)
    {
        ret.width  = wz;
        ret.height = container.height;
    }
    else
    {
        ret.height = container.width*target.height/target.width;
        ret.width  = container.width;
    }
    return ret;
};

/**
 * 两个点的直线方程的斜角
 * param A点
 * param B点
 * return 角度
 */
drawingBoards.components.MathReam.angle = function(A, B){

    return (Math.atan2(B.y-A.y, B.x-A.x)/Math.PI)*180;
};

/**
 * 两个点的之间的中心点
 * param A点
 * param B点
 * return 点
 */
drawingBoards.components.MathReam.centerPoint = function(A, B){

    var c = {};
    c.x = (A.x+ B.x)/2;
    c.y = (A.y+ B.y)/2;
    return c;
};

