package common.lib.dialog;

public enum MoreSelectEnum {
    NONE,
    REMARK_UNREAD("标为未读"), //
    REMARK_READ("标为已读"),  //
    TOP_CHAT("置顶聊天"),     //
    CANCEL_TOP_CHAT("取消置顶"), //
    DELETE_CHAT("删除该聊天"),   //
    DELETE_MESSAGE("删除"), //（一条历史记录）
    COPY("复制"), //
    FORWARD("转发"), //
    COLLECTION("收藏"), //
    REVOKE("撤回"), //
    MORE("更多"),
    ALBUM("相册"),
    CAMERA("拍照"),
    SAVE("保存图片"),
    SEND_CONTACTS("发送给联系人"),
    EARPIECE("听筒播放"),
    SPEAKER("扬声器播放"),
    DELETE_GROUP_MEMBER("删除群成员"),
    ADD_GROUP_MEMBER("添加群成员");


    private String desc;

    MoreSelectEnum(String desc) {
        this.desc = desc;
    }

    MoreSelectEnum() {}

    public String getDesc() {
        return desc;
    }

    public static MoreSelectEnum valueString(String desc) {
        switch (desc.trim()) {
            case "标为未读" :
                return REMARK_UNREAD;
            case "标为已读" :
                return REMARK_READ;
            case "置顶聊天" :
                return TOP_CHAT;
            case "取消置顶" :
                return CANCEL_TOP_CHAT;
            case "删除该聊天" :
                return DELETE_CHAT;
            case "删除" :
                return DELETE_MESSAGE;
            case "复制" :
                return COPY;
            case "收藏" :
                return COLLECTION;
            case "撤回" :
                return REVOKE;
            case "转发" :
                return FORWARD;
            case "更多" :
                return MORE;
            case "拍照" :
                return CAMERA;
            case "相册" :
                return ALBUM;
            case "保存图片" :
                return SAVE;
            case "发送给联系人" :
                return SEND_CONTACTS;
            case "删除群成员" :
                return DELETE_GROUP_MEMBER;
            case "添加群成员" :
                return ADD_GROUP_MEMBER;
            case "听筒播放" :
                return EARPIECE;
            case "扬声器播放" :
                return SPEAKER;
            default: return NONE;

        }
    }
}