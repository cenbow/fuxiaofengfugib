require.config({
    //urlArgs: "bust=v2",//+ (new Date()).getTime()
	baseUrl: '/resource/assets/js',
	paths: {
		'jquery': 'jquery-1.10.2.min',
		'angular': '../../components/angular/angular.min',
		'angular.sanitize': '../../components/angular/angular-sanitize.min',
        'angular.ui.sortable' : '../../components/angular/angular-ui-sortable',
        'angular.ui.tree' : '../../components/angularuitree/angular-ui-tree',
        'underscore': '../lib/underscore-min',
        'util':'../lib/util',//菜单设置图标需要使用
        'css': '../lib/css.min',
        'bootstrap': 'bootstrap.min',
        'jquery.validate' : '../lib/jquery-validate-1.12.min',
        'validator.bootstrap' : '../lib/jquery-validate-expand',
        'cqliving_dialog':'../../js/common/cqliving_dialog',
        'cqliving_ajax':'../../js/common/cqliving_ajax',
        'cqliving_input':'../../js/common/cqliving_input',
        'moment': '../lib/moment',
        'datetimepicker': '../../components/datetimepicker/bootstrap-datetimepicker.min',
		'daterangepicker': '../../components/daterangepicker/daterangepicker',
		'webuploader':'../../components/webuploader-0.1.5/webuploader.min.js?v=1.0',
		'myUploader':'../../components/webuploader-0.1.5/my_webuploader.js?v=1.0',
		//'bootbox':'bootbox.min',//操作弹层，现在自己封装bootstarp的model，不需要了
		'chosen':'chosen.jquery.min',
		'ace_ex':'ace-extra.min',
		'ace_ele':'ace-elements.min',
		'ace':'uncompressed/ace',
        'treeview':'../../components/bootstrap-treeview/min/bootstrap-treeview.min',
		'ueditor':'../../components/ueditor/ueditor.all.js?v=1.0',
		'ueditorConfig':'../../components/ueditor/ueditor.config',
		'ZeroClipboard':'../../components/ueditor/third-party/zeroclipboard/ZeroClipboard.min',
		'cloud.time.input':'../../js/common/cloud.time.input.js?v=1.0',
        'cloud.table.curd':'../../js/common/cloud.table.curd',
		'jquery.cookie': '../../js/common/jquery.cookie',
		'fuelux.sampledata':'fuelux/data/fuelux.tree-sampledata',
		'fuelux.tree':'fuelux/fuelux.tree.min',
		'toastr':'../../components/toastr/toastr',
		'inputlimiter':'jquery.inputlimiter.1.3.1.min',
		'chunkedUpload':'../../business/webupload/chunkedUpload',
		'Jcrop':'../../components/Jcrop-0.9.12/js/jquery.Jcrop.min',
		'bootstrap-suggest':'../../components/bootstrap-suggest-plugin/src/bootstrap-suggest',
		'bigautocomplete':'../../components/input_auto_search/js/jquery.bigautocomplete',
		'wenzi':'../../components/words_limit/js/wenzi',
		'bootstrap-colorpicker':'../../components/colorpicker/bootstrap/js/bootstrap-colorpicker.min',
		'colorbox': 'jquery.colorbox-min',
		'qiniu':'../../components/qiniu-1.0.15-beta/dist/qiniu.min',
		'moxie':'../../components/plupload-2.1.9/js/moxie.min',
		'plupload':'../../components/plupload-2.1.9/js/plupload.full.min',
		'uiJs':'../../components/qiniu-1.0.15-beta/demo/scripts/ui',
		'myQiNiu':'../../components/qiniu-1.0.15-beta/my_qiniu',
		'jquery.nestable': '../js/jquery.nestable.min',
		'common_colorbox': '../../js/common/common-colorbox',
		'common_treeview':'../../js/common/cloud.treeview',
		'echarts':'../../components/echarts/echarts.common.min'
	},
	shim:{
		'angular': {
			exports: 'angular',
			deps: ['jquery']
		},
		'angular.sanitize': {
			exports: 'angular',
			deps: ['angular']
		},
        'angular.ui.sortable' : {
            deps :['jquery.ui', 'angular']
        },
        'angular.ui.tree' : {
            deps : ['angular', 'css!../../components/angularuitree/angular-ui-tree.min.css']
        },
        'bootstrap':{
        	exports: "$",
			deps: ['jquery']
        },
        'jquery.validate' : {
            exports : '$',
            deps : ['jquery','cqliving_dialog']
        },
        'validator.bootstrap' : {
            exports : '$',
            deps : ['jquery', 'jquery.validate']
        },
        'datetimepicker': {
			exports: '$',
			deps: ['css!../../components/datetimepicker/bootstrap-datetimepicker.min.css']
		},
		'daterangepicker': {
			exports: '$',
			deps: ['jquery', 'moment', 'css!../../components/daterangepicker/daterangepicker.css']
		},
		'webuploader':{
			deps: ['jquery','css!../../components/webuploader-0.1.5/webuploader.css']
		},
		/*'bootbox':{
			exports: 'bootbox',
			deps: ['jquery']
		},*/
		'chosen':{
			exports:'chosen',
			deps:['jquery']
		},
		'ace_ex':{
			exports:'ace',
			deps:['jquery','bootstrap']
		},
		'ace_ele':{
			exports:'ace_ele',
			deps:['jquery', 'jquery.cookie']
		},
		'ace':{
			exports:'ace',
			deps:['ace_ele', 'ace_ex']
		},
		'treeview':{
			exports:'$',
			deps:['bootstrap','css!../../components/bootstrap-treeview/min/bootstrap-treeview.min.css']
		},
		'jquery.cookie':{
			exports:'$',
			deps:['jquery']
		},
		'fuelux.tree':{
			exports:'Tree',
			deps:['bootstrap']
		},
		'fuelux.sampledata':{
			exports:'treeDataSource',
			deps:['fuelux.tree']
		},
		'toastr':{
			exports:'$',
			deps:['jquery','css!../../components/toastr/toastr.css']
		},
		'inputlimiter':{
			exports:'$',
			deps:['jquery']
		},
		'chunkedUpload':{
			exports:"chunkedUpload",
			deps:['jquery','webuploader']
		},
		'Jcrop':{
			exports:'Jcrop',
			deps:['jquery','css!../../components/Jcrop-0.9.12/css/jquery.Jcrop.min.css']
		},
		'ueditor':{
			exports:"UE",
			deps:['ueditorConfig']
		},
		'bootstrap-suggest':{
			exports:"$",
			deps:['bootstrap']
		},
		'bigautocomplete':{
			exports:"$",
			deps:['jquery','css!../../components/input_auto_search/css/jquery.bigautocomplete.css']
		},
		'wenzi':{
			exports:"$",
			deps:['jquery']
		},
		'bootstrap-colorpicker':{
			exports:"$",
			deps:['bootstrap','css!../../components/colorpicker/bootstrap/css/bootstrap-colorpicker.min.css']
		},
		'colorbox': {//因为colorbox.css和ace.css样式冲突，colorbox必须放在ace前面，所以单独提出来放在include里面了
			exports: 'colorbox'
		},
		'qiniu':{
			exports:'Qiniu',
			deps:['plupload']
		},
		'moxie':{
			exports:'mOxie'
		},
		'plupload':{
			exports:'plupload',
			deps:['moxie']
		},
		'myQiNiu':{
			deps:['uiJs']
		},
		'jquery.nestable': {
			exports:"$",
			deps:['jquery']
		},
		'common_colorbox': {
			exports:"$",
			deps:['jquery']
		},
		'common_treeview':{
			deps:['treeview']
		}
	}
});

/** AJAX请求未登录返回值*/
QZ_NOT_LOGIN = "999992";
/** AJAX请求重复登陆返回值*/
QZ_LOGIN_REPEAT = "999991";

/** 手机号码验证表达式*/
PHONE_PATTERN = /^(((1[3|4|5|6|7|8|9][0-9]{1})|(15[0-9]{1}))+\d{8})$/;
/** 座机号码验证表达式*/
TEL_PATTERN = /^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$/;
/** 邮箱验证表达式*/
EMAIL_PATTERN=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
/** 汉字验证表达式*/
CHINESE_PATTERN =/^[\u4e00-\u9fa5]+$/;
/** ??? */
ILLEGAL_PATTERN=/^(?:[\u4e00-\u9fa5]*\w*\s*)+$/;
/** 数字验证表达式*/
NUMBER_PATTERN=/^[0-9]+$/;
/** 数字字母表达式*/
NUM_EN_PATTERN = /^[A-Za-z0-9]+$/;
/** 融云表情表达式 */
EMOJI_PATTERN = /\[[^\[\]\s]+?\]/g;