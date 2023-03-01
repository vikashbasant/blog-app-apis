package co.blog.util;

public enum BlogServiceType {

    /*---- For User ----*/
    CREATE_USER,
    UPDATE_USER,
    GET_USER,
    GET_ALL_USER,
    DELETE_USER,

    /*---- For Category ----*/
    CREATE_CATEGORY,
    UPDATE_CATEGORY,
    GET_CATEGORY,
    GET_ALL_CATEGORY,
    DELETE_CATEGORY,

    /*---- For Post ----*/
    CREATE_POST,
    GET_POST_BY_CATEGORY_ID,
    GET_POST_BY_USER_ID,
    GET_POST,
    GET_ALL_POST,
    UPDATE_POST,
    DELETE_POST,
    SEARCH_POST,

    /*----For File Service-----*/
    UPLOAD_IMAGE,
    DELETE_IMAGE,
    GET_RESOURCE,

    /*---- For Comment Service----*/
    CREATE_COMMENT,
    DELETE_COMMENT,
    UPDATE_COMMENT,
    GET_COMMENT
}
