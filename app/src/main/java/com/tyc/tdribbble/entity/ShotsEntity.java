package com.tyc.tdribbble.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：tangyc on 2017/6/22
 * 邮箱：874500641@qq.com
 */
public class ShotsEntity implements Serializable {

    /**
     * id : 471756
     * title : Sasquatch
     * description : <p>Quick, messy, five minute sketch of something that might become a fictional something.</p>
     * width : 400
     * height : 300
     * images : {"hidpi":null,"normal":"https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch.png","teaser":"https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch_teaser.png"}
     * views_count : 4372
     * likes_count : 149
     * comments_count : 27
     * attachments_count : 0
     * rebounds_count : 2
     * buckets_count : 8
     * created_at : 2012-03-15T01:52:33Z
     * updated_at : 2012-03-15T02:12:57Z
     * html_url : https://dribbble.com/shots/471756-Sasquatch
     * attachments_url : https://api.dribbble.com/v1/shots/471756/attachments
     * buckets_url : https://api.dribbble.com/v1/shots/471756/buckets
     * comments_url : https://api.dribbble.com/v1/shots/471756/comments
     * likes_url : https://api.dribbble.com/v1/shots/471756/likes
     * projects_url : https://api.dribbble.com/v1/shots/471756/projects
     * rebounds_url : https://api.dribbble.com/v1/shots/471756/rebounds
     * animated : false
     * tags : ["fiction","sasquatch","sketch","wip"]
     * user : {"id":1,"name":"Dan Cederholm","username":"simplebits","html_url":"https://dribbble.com/simplebits","avatar_url":"https://d13yacurqjgara.cloudfront.net/users/1/avatars/normal/dc.jpg?1371679243","bio":"Co-founder &amp; designer of <a href=\"https://dribbble.com/dribbble\">@Dribbble<\/a>. Principal of SimpleBits. Aspiring clawhammer banjoist.","location":"Salem, MA","links":{"web":"http://simplebits.com","twitter":"https://twitter.com/simplebits"},"buckets_count":10,"comments_received_count":3395,"followers_count":29262,"followings_count":1728,"likes_count":34954,"likes_received_count":27568,"projects_count":8,"rebounds_received_count":504,"shots_count":214,"teams_count":1,"can_upload_shot":true,"type":"Player","pro":true,"buckets_url":"https://dribbble.com/v1/users/1/buckets","followers_url":"https://dribbble.com/v1/users/1/followers","following_url":"https://dribbble.com/v1/users/1/following","likes_url":"https://dribbble.com/v1/users/1/likes","shots_url":"https://dribbble.com/v1/users/1/shots","teams_url":"https://dribbble.com/v1/users/1/teams","created_at":"2009-07-08T02:51:22Z","updated_at":"2014-02-22T17:10:33Z"}
     * team : {"id":39,"name":"Dribbble","username":"dribbble","html_url":"https://dribbble.com/dribbble","avatar_url":"https://d13yacurqjgara.cloudfront.net/users/39/avatars/normal/apple-flat-precomposed.png?1388527574","bio":"Show and tell for designers. This is Dribbble on Dribbble.","location":"Salem, MA","links":{"web":"http://dribbble.com","twitter":"https://twitter.com/dribbble"},"buckets_count":1,"comments_received_count":2037,"followers_count":25011,"followings_count":6120,"likes_count":44,"likes_received_count":15811,"members_count":7,"projects_count":4,"rebounds_received_count":416,"shots_count":91,"can_upload_shot":true,"type":"Team","pro":false,"buckets_url":"https://dribbble.com/v1/users/39/buckets","followers_url":"https://dribbble.com/v1/users/39/followers","following_url":"https://dribbble.com/v1/users/39/following","likes_url":"https://dribbble.com/v1/users/39/likes","members_url":"https://dribbble.com/v1/teams/39/members","shots_url":"https://dribbble.com/v1/users/39/shots","team_shots_url":"https://dribbble.com/v1/users/39/teams","created_at":"2009-08-18T18:34:31Z","updated_at":"2014-02-14T22:32:11Z"}
     */

    private int id;
    private String title;
    private String description;
    private int width;
    private int height;
    private ImagesBean images;
    @SerializedName("views_count")
    private int viewsCount;
    @SerializedName("likes_count")
    private int likesCount;
    @SerializedName("comments_count")
    private int commentsCount;
    @SerializedName("attachments_count")
    private int attachmentsCount;
    @SerializedName("rebounds_count")
    private int reboundsCount;
    @SerializedName("buckets_count")
    private int bucketsCount;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("attachments_url")
    private String attachmentsUrl;
    @SerializedName("buckets_url")
    private String bucketsUrl;
    @SerializedName("comments_url")
    private String commentsUrl;
    @SerializedName("likes_url")
    private String likesUrl;
    @SerializedName("projects_url")
    private String projectsUrl;
    @SerializedName("rebounds_url")
    private String reboundsUrl;
    private boolean animated;
    private UserEntity user;
    private TeamEntity team;
    private List<String> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getAttachmentsCount() {
        return attachmentsCount;
    }

    public void setAttachmentsCount(int attachmentsCount) {
        this.attachmentsCount = attachmentsCount;
    }

    public int getReboundsCount() {
        return reboundsCount;
    }

    public void setReboundsCount(int reboundsCount) {
        this.reboundsCount = reboundsCount;
    }

    public int getBucketsCount() {
        return bucketsCount;
    }

    public void setBucketsCount(int bucketsCount) {
        this.bucketsCount = bucketsCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getAttachmentsUrl() {
        return attachmentsUrl;
    }

    public void setAttachmentsUrl(String attachmentsUrl) {
        this.attachmentsUrl = attachmentsUrl;
    }

    public String getBucketsUrl() {
        return bucketsUrl;
    }

    public void setBucketsUrl(String bucketsUrl) {
        this.bucketsUrl = bucketsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getLikesUrl() {
        return likesUrl;
    }

    public void setLikesUrl(String likesUrl) {
        this.likesUrl = likesUrl;
    }

    public String getProjectsUrl() {
        return projectsUrl;
    }

    public void setProjectsUrl(String projectsUrl) {
        this.projectsUrl = projectsUrl;
    }

    public String getReboundsUrl() {
        return reboundsUrl;
    }

    public void setReboundsUrl(String reboundsUrl) {
        this.reboundsUrl = reboundsUrl;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static class ImagesBean implements Serializable {
        /**
         * hidpi : null
         * normal : https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch.png
         * teaser : https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch_teaser.png
         */

        private String hidpi;
        private String normal;
        private String teaser;

        public ImagesBean(String hidpi, String normal, String teaser) {
            this.hidpi = hidpi;
            this.normal = normal;
            this.teaser = teaser;
        }
        public String getHidpi() {
            return hidpi;
        }

        public void setHidpi(String hidpi) {
            this.hidpi = hidpi;
        }

        public String getNormal() {
            return normal;
        }

        public void setNormal(String normal) {
            this.normal = normal;
        }

        public String getTeaser() {
            return teaser;
        }

        public void setTeaser(String teaser) {
            this.teaser = teaser;
        }
    }


}
