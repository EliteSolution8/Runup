package com.socard.Util;

/**
 * Created by puma on 4/5/2016.
 */
public class ReqConst {

    public static final String SERVER_ADDR = "http://52.208.71.172";
//    public static final String SERVER_ADDR = "http://192.168.0.20:8080/DataCollect";
    public static final String CHATTING_SERVER = "192.168.0.40";

    public static final String ROOM_SERVICE = "@conference.";

    public static final String SERVER_URL = SERVER_ADDR + "/index.php/api/";

    public static final String REQ_GETVERSIONINFO = "getVersionInfo";
    public static final String REQ_LOGIN = "login";
    public static final String REQ_UPDATEPOS = "updateLocation";
    public static final String REQ_LOGOUT = "logout";
    public static final String REQ_SINGUP = "signup";
    public static final String REQ_NEARBY = "getNearUser";
    public static final String REQ_GETNEARLIST = "user_infos";
    public static final String REQ_UPLOADIMAGE = "uploadPhoto";


    public static final String REQ_CREATEAUTHCODE = "createAuthCode";
    public static final String REQ_CONFIRMAUTHCODE = "confirmAuthCode";
    public static final String REQ_GETAUTHCODE = "getAuthCode";
    public static final String REQ_RESETNEWPASSWORD = "resetNewPassword";
    public static final String REQ_GETUSERINFO = "getUserInfo";
    public static final String REQ_GETALLUSERS = "getAllUsers";
    public static final String REQ_UPDATENAME = "updateName";
    public static final String REQ_UPDATELABEL = "updateLabel";

    public static final String REQ_UPLOADFILE = "uploadFile";
    public static final String REQ_ADDFRIENDALLOW = "addFriendAllow";
    public static final String REQ_SEARCHFRIEND = "searchFriend";
    public static final String REQ_ADDFRIENDBYID = "addFriendById";

    public static final String REQ_BLOCKFRIEND = "blockFriends";
    public static final String REQ_GETRECOMMENDFRIENDS = "getRecommendFriends";
    public static final String REQ_GETROOMINFO = "getRoomInfo";
    public static final String REQ_REMOVEPROFILE ="removeProfile";
    public static final String REQ_REMOVEBACKGROUND = "removeBackground";
    public static final String REQ_ADDFRIENDBYPHONENUMBER = "addFriendByPhoneNumber";
    public static final String REQ_SETALLOWFRIEND = "setAllowFriend";
    public static final String REQ_GETNOTELIST = "getNoteList";
    public static final String REQ_GETNOTECONTENT = "getNoteContent";
    public static final String REQ_REGISTERTOKEN = "registerToken";
    public static final String REQ_GETROOMINFOLIST = "getRoomInfoList";
    public static final String REQ_ADDFRIENDLIST = "addFriendList";
    public static final String REQ_BLOCKFRIENDLIST = "blockFriendList";


    public static final String REQ_GETARES = "getAreas";
    public static final String REQ_GETROOMS = "getRooms";
    public static final String REQ_SETPROFILE = "setProfile";
    public static final String REQ_UPDATEPROFILE = "updateProfile";
    public static final String REQ_GETPROFILE = "getProfile";
    public static final String REQ_ENTERROOM = "enterRoom";
    public static final String REQ_SETLOCATION = "setUserLocation";
    public static final String REQ_SETPOINT = "setPoint";
    public static final String REQ_GETPOINT = "getPoint";
    public static final String REQ_SETCACHE = "setCache";
    public static final String REQ_GETCACHE = "getCache";
    public static final String REQ_GETPARTCIPANTINFO = "getParticipantInfo";
    public static final String REQ_OUTROOM = "outRoom";
    public static final String REQ_GETTOPCIS = "getTopics";
    public static final String REQ_GETFRIENDPROFILE = "getFriendProfile";


    //request params
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_ID = "id";
    public static final String PARAM_TYPE = "type";
    public static final String PARAM_FILENAME = "filename";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_PHONE_NUMBER = "phone_number";
    public static final String PARAM_PHOTO = "photo";
    public static final String PARAM_AUTH_CODE = "auth_code";
    public static final String PARAM_NEW_PASSWORD = "new_password";
    public static final String PARAM_LABEL = "label";
    public static final String PARAM_IMAGE_TYPE = "image_type";
    public static final String PARAM_FILE = "file";
    public static final String PARAM_SEARCH_STRING = "search_string";
    public static final String PARAM_PHONE_NUMBERS = "phone_numbers";
    public static final String PARAM_ROOMS = "rooms";
    public static final String PARAM_FRIENDLIST = "friend_list";
    public static final String PARAM_BLCOKSTATUS = "block_status";

    //response value
    public static final String RES_CODE = "result_code";
    public static final String RES_IDX = "id";
    public static final String RES_NAME = "user_name";
    public static final String RES_EMAIL = "email";
    public static final String RES_PHOTO_URL = "photo_url";
    public static final String RES_ID = "id";
    public static final String RES_USERINFO = "user_info";
    public static final String RES_DISTANCE = "distance";

    public static final String RES_AUTH_CODE = "auth_code";
    public static final String RES_PHONE_NUMBER = "phone_number";
    public static final String RES_FILE_URL = "file_url";
    public static final String RES_SEARCH_RESULT = "search_result";
    public static final String RES_NEARLIST = "";
    public static final String RES_USERLIST = "user_list";
    public static final String RES_BLOCK_STATUS = "block_status";
    public static final String RES_FILENAME = "filename";
    public static final String RES_ALLOWFRIEND = "allow_friend";
    public static final String RES_NOTELIST = "note_list";
    public static final String RES_NOTEDATE = "note_date";
    public static final String RES_NOTETITLE = "note_title";
    public static final String RES_NOTECONTENT = "note_content";
    public static final String RES_VERSION = "version";



    public static final String RES_AREAINFO = "area_info";
    public static final String RES_AREAID = "area_id";
    public static final String RES_AREANAME = "area_name";
    public static final String RES_ROOMINFO = "room_info";
    public static final String RES_ROOMID = "room_id";
    public static final String RES_ROOMNAME = "room_name";
    public static final String RES_PATICIPANTS_INFO = "participants_info";
    public static final String RES_PATICIPANTS = "participants";
    public static final String RES_USERPROFILE = "user_profile";
    public static final String RES_AGE = "age";
    public static final String RES_TOPIC = "topic";
    public static final String RES_NICKNAME = "nick_name";
    public static final String RES_SEX = "sex";
    public static final String RES_VIDEOCOUNT = "video_count";
    public static final String RES_RECOMMENDER = "recommender";
    public static final String RES_POINT = "point";
    public static final String RES_CACHE = "cache";
    public static final String RES_USERID = "user_id";
    public static final String RES_FRIENDINFO = "friend_info";






    public static final int CODE_SUCCESS = 0;
    public static final int CODE_UNREGUSER = 101;
    public static final int CODE_INVALIDPWD = 102;
    public static final int CODE_INVALIDEMAIL = 104;
    public static final int CODE_UNAUTHEMAIL = 105;
    public static final int CODE_AUTHFAIL = 106;
    public static final int CODE_INVALIDAUTH = 107;
    public static final int CODE_RESET_UNAUTHEMAIL = 108;
    public static final int CODE_OVERFLOW = 111;


    public static final int CODE_PROFILE = 202;
    public static final int CODE_UNREGISTERDROOM = 201;

}
