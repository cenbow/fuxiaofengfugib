package com.cqliving.cloud.online.manuscript.dto;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cqliving.cloud.online.manuscript.domain.ManuscriptColumns;
import com.cqliving.cloud.online.manuscript.domain.ManuscriptInfoClassify;
import com.cqliving.cloud.online.manuscript.manager.ManuscriptInfoClassifyManager;
import com.cqliving.cloud.online.manuscript.utils.ManuscriptUtils;
import com.cqliving.redis.base.AbstractRedisClient;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;

/**
 * Title:
 * <p>Description:
 * 秀山抓稿工具
 * 需求：每个栏目只抓取最新的三页数据
 * </p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年12月30日
 */
public class XiuShanManuscript {
	private static final Logger logger = LoggerFactory.getLogger(XiuShanManuscript.class);
	
	/** 抓取秀山网站的域名 */
	private final static String host = "http://www.zgcqxs.net";
	/** 错误日志前缀 */
	private final static String loggerErrorPrefix = "秀山爬虫-->>";

    private AbstractRedisClient abstractRedisClient;
    private ManuscriptInfoClassifyManager manuscriptInfoClassifyManager;
    private ManuscriptColumns mc;
	
	/**
	 * 初始化
	 * @param abstractRedisClient
	 * @param mim
	 */
	public XiuShanManuscript(AbstractRedisClient abstractRedisClient, ManuscriptInfoClassifyManager mim){
	    this.abstractRedisClient = abstractRedisClient;
	    this.manuscriptInfoClassifyManager = mim;
	}
	
	/**
	 * Title:设置栏目数据
	 * <p>Description:</p>
	 * @author DeweiLi on 2017年1月3日
	 * @param mc
	 */
	public void setMc(ManuscriptColumns mc){
	    this.mc = mc;
	}
	
	/**
	 * Title:获取一页的内容
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月29日
	 * @param url 抓取的地址
	 * @param maxPage 控制抓取多少页数据
	 * @return
	 */
	public List<ManuscriptInfo> getNewsList(String url, int maxPage){
		logger.info(url);
		List<ManuscriptInfo> list = Lists.newArrayList();
		ManuscriptInfo mi;
		Document document = getDom(url);
		if(document == null){
			logger.error(loggerErrorPrefix + url + "获取失败");
			return null;
		}
		Elements elements;
		elements = document.getElementsByClass("xwlb_libjo");
		if(elements == null){
			logger.error(loggerErrorPrefix + "class=xwlb_libjo未获取到内容");
			return null;
		}
		for(Element element : elements){
			Elements elements1 = element.getElementsByTag("a");
			if(elements1 != null && elements1.size() > 0){
			    url = elements1.get(0).attr("href");
	            String id = getIdByUrl(url);
	            //根据id去判断，同一个栏目下是否已经抓取过这个数据，如果已经抓过了，那么后面的数据就不抓去，也不用在去抓取下一页的数据了。
	            if(isGet(id)){
	                maxPage = 0;
	                break ;
	            }
				mi = getNewsObj(url);//去获取新闻的相信内容
				if(mi != null){
				    if(StringUtils.isBlank(mi.getTitle())){//注意这里的位置
				        mi.setTitle(elements1.get(0).html().trim());
				    }
				    if(StringUtils.isBlank(mi.getPubDate())){//如果发布时间为空
				        //因为图集类型的新闻详情页没有发布时间，所以只有把列表的发布时间获取出来
				        elements1 = element.getElementsByTag("span");
				        mi.setPubDate(getListPubDate(elements1));
				    }
				    list.add(mi);
				}
			}
		}
		
		//处理分页
		if(list != null && list.size() > 0 && maxPage > 1){
			elements = document.getElementsByClass("digg");
			if(elements != null && elements.size() > 0){
				elements = elements.get(0).getElementsByTag("a");
				if(elements != null && elements.size() > 0){
					Element el = elements.get(elements.size() - 1);
					url = host + el.attr("href");
					maxPage --;
					List<ManuscriptInfo> listTmp = getNewsList(url, maxPage);
					if(listTmp != null && listTmp.size() > 0){
						list.addAll(listTmp);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * Title:获取列表的日期，并已当前的时分秒作为一个新的日期
	 * <p>Description:
	 * 如果最终获取的日期是错误格式，那么就返回当前时间
	 * </p>
	 * @author DeweiLi on 2017年1月5日
	 * @param els
	 * @return
	 */
	private String getListPubDate(Elements els){
	    String pubDate = "";
	    if(els != null && els.size() > 0){
            Element el = els.get(0);
            if(el != null){
                pubDate = el.html().trim();
                if(StringUtils.isNotBlank(pubDate) && pubDate.length() < 19){
                    pubDate = pubDate + " " + DateUtil.format(DateUtil.now(), "HH:mm:ss");
                }
            }
	    }
        try {
            DateUtil.parse(pubDate, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
        } catch (Exception e) {
            pubDate = DateUtil.format(DateUtil.now(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
        }
        return pubDate;
	}
	/**
	 * Title:获取新闻详情
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月29日
	 * @param url
	 */
	private ManuscriptInfo getNewsObj(String url){
		ManuscriptInfo mi = new ManuscriptInfo();
		String id = getIdByUrl(url);
		mi.setGuid(id);
		url = host + url;
		Document document = getDom(url);
		if(document == null){
			logger.error(loggerErrorPrefix + url + "获取失败");
			return null;
		}
		mi.setXMLCONTENT(document.html());
		
		Elements elements = document.getElementsByClass("tjsp");
		if(elements != null && elements.size() > 0){
		    elements = elements.get(0).getElementsByClass("left");
		}
		Element el;
		Element element = null;
		if(elements != null && elements.size() > 0){
		   element = elements.get(0);
		}
		if(element != null){
		  //标题
	        Elements els = element.getElementsByTag("H3");
	        if(els != null){
	            el = els.get(0);
	            mi.setTitle(getTitle(el));//设置标题
	            //编辑、来源、发布时间
	            els = el.getElementsByTag("div");
	            if(els != null){
	                el = els.get(0);
	                getSourceOrOther(el, mi);
	            }
	        }
	        //内容
	        el = element.getElementById("imgwidth");
	        mi.setContent(getContent(el).toString());
	        //列表图片
	        mi.setImg(getListViewImage(el));
	        //处理视频地址
	        mi.setMedia(getMedia(element));
		}
		//图片
		mi = getImages(mi, document.getElementById("smallImgScroll"));
		return mi;
	}
	
	/**
	 * Title:处理内容中图片地址
	 * <p>Description:
	 * 给内容中的图片地址加上秀山的域名，因为源码中用的相对地址
	 * </p>
	 * @author DeweiLi on 2017年1月5日
	 * @param el
	 * @return
	 */
	private StringBuilder getContent(Element el){
	    StringBuilder content = new StringBuilder();
	    if(el != null){
	        //给每个图片地址加上秀山的域名
	        Elements imgs = el.getElementsByTag("img");
	        if(imgs != null && imgs.size() > 0){
	            for(Element img : imgs){
	                img.attr("src", host + img.attr("src"));
	            }
	        }
	        content.append(el.html());
	    }
	    return content;
	}
//	public static void main(String[] args) {
//	    String url = "/news/show-24176.html";//图文
//	    url = "/news/show-24164.html";//视频
//	    url = "http://www.zgcqxs.net/news/75.html";//列表
//	    XiuShanManuscript xm = new XiuShanManuscript(null, null);
//        
//        List<ManuscriptInfo> list = xm.getNewsList(url, 3);
//        System.err.println(list.size());
//    }
	
	/**
	 * Title:获取图片
	 * <p>Description:
	 * 获取图片多图新闻和列表图
	 * 最后通过XmlUtils.getImgAttribute(mi.getContent(), "src,alt")方法解析图片地址和描述信息
	 * </p>
	 * @author DeweiLi on 2017年1月4日
	 * @param element
	 * @return
	 */
	private ManuscriptInfo getImages(ManuscriptInfo mi, Element element){
	    if(element == null){
	        return mi;
	    }
	    StringBuilder images = new StringBuilder();
	    Elements els = element.getElementsByTag("a");
	    if(els != null && els.size() > 0){
	        Element imgEl;
	        String src = "";
	        for(Element el : els){
	            imgEl = el.getElementsByTag("img").get(0);
	            src = imgEl.attr("src");
	            src = host + src.replace("thumb_", "");
	            imgEl.attr("alt", el.attr("info"));//设置描述信息
	            imgEl.attr("src", src);//拼接图片的全地址
	            if(images.length() == 0){
	                mi.setImg(src);//设置列表图
	            }
	            images.append(el.html());
	        }
	    }
	    mi.setImages(images.toString());
	    mi.setContent(images.toString());
	    return mi;
	}
	
	/**
	 * Title:获取title
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月29日
	 * @param element
	 * @return
	 */
	private String getTitle(Element element){
		String title = "";
		List<TextNode> list = element.textNodes();
		if(list != null && list.size() > 0)
			title = list.get(0).text();
		return title;
	}
	
	/**
	 * Title:解析编辑、来源、发布时间
	 * <p>Description:
	 * 如果编辑、来源为空，发布时间不为空则表示是视频新闻
	 * </p>
	 * @author DeweiLi on 2016年12月29日
	 * @param element
	 * @return
	 */
	private ManuscriptInfo getSourceOrOther(Element element, ManuscriptInfo mi){
		if(element == null){
			return null;
		}
		List<TextNode> list = element.textNodes();
		if(list != null && list.size() > 0){
			String str = list.get(0).outerHtml();
			if(StringUtils.isNotBlank(str)){
				str = str.trim();
				String strs[] = str.split("&nbsp;");
				String tmp;
				for(String s : strs){
					if(StringUtils.isNotBlank(s)){
						s = s.replace("&nbsp;", "").trim();
						if(s.startsWith("来源")){
							tmp = s.substring(s.indexOf("来源") + 3, s.length()).trim();
							mi.setSource(tmp);
						}else if(s.startsWith("编辑")){
							tmp = s.substring(s.indexOf("编辑") + 3, s.length()).trim();
							mi.setEditor(tmp);
						}else if(s.startsWith("发布时间")){
							tmp = s.substring(s.indexOf("发布时间") + 5, s.length()).trim();
							tmp = tmp.replace("/", "-");
							try {
                                tmp = DateUtil.format(DateUtil.parse(tmp, DateUtil.YYYY_MM_DD_HH_MM_SS), DateUtil.YYYY_MM_DD_HH_MM_SS);
                            } catch (Exception e) {
                                tmp = DateUtil.format(DateUtil.now(), DateUtil.YYYY_MM_DD_HH_MM_SS);
                            }
							mi.setPubDate(tmp);
						}
					}
				}
			}
		}
//		logger.info(JSON.toJSON(mi).toString());
		return mi;
	}
	
	/**
	 * Title:根据列表跳转到详情的url地址取得id值
	 * <p>Description:
	 * 例如url的格式为：/news/show-24095.html
	 * </p>
	 * @author DeweiLi on 2016年12月29日
	 * @param url
	 * @return
	 */
	private String getIdByUrl(String url){
		String id = "";
		if(StringUtils.isNotBlank(url)){
			id = url.substring(url.indexOf("show-") + 5, url.indexOf(".html"));
		}
		return id;
	}
	
	/**
	 * Title:获取内容中的第一张图片作为新闻的 列表图片
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月29日
	 * @param content
	 * @return
	 */
	private String getListViewImage(Element el){
		String image = "";
		if(el != null){
		    Elements imgs = el.getElementsByTag("img");
		    if(imgs != null && imgs.size() > 0){
		        image = imgs.get(0).attr("src").trim();
		    }
		}
		if(StringUtils.isNotBlank(image) && !image.startsWith("http://")){
		    image = host + image;
		}
		return image;
	}
	
	/**
	 * Title：获取视频地址
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年12月29日
	 * @param element
	 * @return
	 */
	private String getMedia(Element element){
		Element el = element.getElementById("video-box");
		if(el == null){
			return null;
		}
		Elements els = element.getElementsByTag("script");
		String media = "";
		if(els != null && els.size() > 0){
			for(Element e : els){
				if(e.childNodeSize() > 0 ){
					String content = e.childNode(0).outerHtml();
					if(content.indexOf("initCKPlayer") > 0){
						media = content.substring(content.indexOf("initCKPlayer('video-box',") + 27, content.indexOf("'/scripts/ckplayer/ckplayer.swf'") - 3);
						break;
					}
				}
			}
		}
		return media;
	}
	
	/**
	 * Title:根据id和栏目去判断是否已经抓取过
	 * <p>Description:
	 * 只验证同一栏目下是否存在重复数据
	 * </p>
	 * @author DeweiLi on 2016年12月30日
	 * @param id
	 * @return true:已经重复;false:未重复
	 */
	private boolean isGet(String id){
	    if(ManuscriptUtils.addCache(abstractRedisClient, null, mc, id)){
            return true;
        }
	    ManuscriptInfoClassify manuscriptInfoClassify = manuscriptInfoClassifyManager.queryIsImport(id, mc.getId());
	    if(manuscriptInfoClassify != null){
	        return true;
	    }
	    return false;
	}
    
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2017年1月3日
	 * @param url
	 * @return
	 */
	private Document getDom(String url){
	    try {
	        sleep(null);
	        logger.debug("----------url----------->>>>>" + url);
	        Document doc = Jsoup.connect(url).get();
	        return doc;
        } catch (IOException e) {
            logger.error(loggerErrorPrefix + "url=" + url + "访问失败" + e, e.getMessage());
        }
	    return null;
	}
    
    /**
     * Title:休眠一定的时间再继续执行。由于秀山网有访问限制
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月3日
     * @param millis
     */
	private void sleep(Long millis){
	    try {
	        if(millis == null || millis < 1){
	            millis = 2000L;
	        }
	        logger.debug("-------------休眠("+millis+")毫秒再继续------------------>>");
            Thread.sleep(millis);//由于秀山有访问限制，这里休眠两秒再执行下一页
        } catch (InterruptedException e) {
            logger.error(loggerErrorPrefix + "休眠("+millis+")毫秒出错了。");
        }
	}
}
