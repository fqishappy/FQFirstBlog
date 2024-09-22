package com.fqishappy.constants;

/**
 * @author fqishappy
 * @date 2024/9/11 15:52
 */
public class SystemConstants {
    //文章正常
    public static final int ARTICLE_STATUS_NORMAL = 0;
    //草稿
    public static final int ARTICLE_STATUS_DRAFT = 1;

    //状态
    public static final String STATUS_NORMAL = "0";
    public static final String STATUS_ERROR = "1";

    //评论类型，0为文章评论，1为友链评论
    public static final String ARTICLE_COMMENT = "0";
    public static final String LINK_COMMENT = "1";


    public static final Integer UPLOAD_FAILED = 500;
    public static final Integer FILE_NAME_NULL = 500;
    public static final Integer FILETYPE_ERROR = 500;


    public static final String ARTICLE_VIEW_COUNT = "article:ViewCount";
    /**
     * 权限类型，菜单
     */
    public static final String TYPE_MENU = "C";

    /**
     * 权限类型，按钮
     */
    public static final String TYPE_BUTTON = "F";

    public static final String NORMAL = "0";

    public static final String IS_ADMIN = "1";
    public static final Integer LOGIC_DELETE = 1;
}
