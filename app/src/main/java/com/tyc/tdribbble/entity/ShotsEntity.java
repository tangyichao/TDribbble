package com.tyc.tdribbble.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：tangyc on 2017/6/22
 * 邮箱：874500641@qq.com
 */
public class ShotsEntity {

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
    private UserBean user;
    private TeamBean team;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public TeamBean getTeam() {
        return team;
    }

    public void setTeam(TeamBean team) {
        this.team = team;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static class ImagesBean {
        /**
         * hidpi : null
         * normal : https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch.png
         * teaser : https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch_teaser.png
         */

        private String hidpi;
        private String normal;
        private String teaser;

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

    public static class UserBean {
        /**
         * id : 1
         * name : Dan Cederholm
         * username : simplebits
         * html_url : https://dribbble.com/simplebits
         * avatar_url : https://d13yacurqjgara.cloudfront.net/users/1/avatars/normal/dc.jpg?1371679243
         * bio : Co-founder &amp; designer of <a href="https://dribbble.com/dribbble">@Dribbble</a>. Principal of SimpleBits. Aspiring clawhammer banjoist.
         * location : Salem, MA
         * links : {"web":"http://simplebits.com","twitter":"https://twitter.com/simplebits"}
         * buckets_count : 10
         * comments_received_count : 3395
         * followers_count : 29262
         * followings_count : 1728
         * likes_count : 34954
         * likes_received_count : 27568
         * projects_count : 8
         * rebounds_received_count : 504
         * shots_count : 214
         * teams_count : 1
         * can_upload_shot : true
         * type : Player
         * pro : true
         * buckets_url : https://dribbble.com/v1/users/1/buckets
         * followers_url : https://dribbble.com/v1/users/1/followers
         * following_url : https://dribbble.com/v1/users/1/following
         * likes_url : https://dribbble.com/v1/users/1/likes
         * shots_url : https://dribbble.com/v1/users/1/shots
         * teams_url : https://dribbble.com/v1/users/1/teams
         * created_at : 2009-07-08T02:51:22Z
         * updated_at : 2014-02-22T17:10:33Z
         */

        private int id;
        private String name;
        private String username;
        private String html_url;
        private String avatar_url;
        private String bio;
        private String location;
        private LinksBean links;
        private int buckets_count;
        private int comments_received_count;
        private int followers_count;
        private int followings_count;
        private int likes_count;
        private int likes_received_count;
        private int projects_count;
        private int rebounds_received_count;
        private int shots_count;
        private int teams_count;
        private boolean can_upload_shot;
        private String type;
        private boolean pro;
        private String buckets_url;
        private String followers_url;
        private String following_url;
        private String likes_url;
        private String shots_url;
        private String teams_url;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public int getBuckets_count() {
            return buckets_count;
        }

        public void setBuckets_count(int buckets_count) {
            this.buckets_count = buckets_count;
        }

        public int getComments_received_count() {
            return comments_received_count;
        }

        public void setComments_received_count(int comments_received_count) {
            this.comments_received_count = comments_received_count;
        }

        public int getFollowers_count() {
            return followers_count;
        }

        public void setFollowers_count(int followers_count) {
            this.followers_count = followers_count;
        }

        public int getFollowings_count() {
            return followings_count;
        }

        public void setFollowings_count(int followings_count) {
            this.followings_count = followings_count;
        }

        public int getLikes_count() {
            return likes_count;
        }

        public void setLikes_count(int likes_count) {
            this.likes_count = likes_count;
        }

        public int getLikes_received_count() {
            return likes_received_count;
        }

        public void setLikes_received_count(int likes_received_count) {
            this.likes_received_count = likes_received_count;
        }

        public int getProjects_count() {
            return projects_count;
        }

        public void setProjects_count(int projects_count) {
            this.projects_count = projects_count;
        }

        public int getRebounds_received_count() {
            return rebounds_received_count;
        }

        public void setRebounds_received_count(int rebounds_received_count) {
            this.rebounds_received_count = rebounds_received_count;
        }

        public int getShots_count() {
            return shots_count;
        }

        public void setShots_count(int shots_count) {
            this.shots_count = shots_count;
        }

        public int getTeams_count() {
            return teams_count;
        }

        public void setTeams_count(int teams_count) {
            this.teams_count = teams_count;
        }

        public boolean isCan_upload_shot() {
            return can_upload_shot;
        }

        public void setCan_upload_shot(boolean can_upload_shot) {
            this.can_upload_shot = can_upload_shot;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isPro() {
            return pro;
        }

        public void setPro(boolean pro) {
            this.pro = pro;
        }

        public String getBuckets_url() {
            return buckets_url;
        }

        public void setBuckets_url(String buckets_url) {
            this.buckets_url = buckets_url;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getLikes_url() {
            return likes_url;
        }

        public void setLikes_url(String likes_url) {
            this.likes_url = likes_url;
        }

        public String getShots_url() {
            return shots_url;
        }

        public void setShots_url(String shots_url) {
            this.shots_url = shots_url;
        }

        public String getTeams_url() {
            return teams_url;
        }

        public void setTeams_url(String teams_url) {
            this.teams_url = teams_url;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public static class LinksBean {
            /**
             * web : http://simplebits.com
             * twitter : https://twitter.com/simplebits
             */

            private String web;
            private String twitter;

            public String getWeb() {
                return web;
            }

            public void setWeb(String web) {
                this.web = web;
            }

            public String getTwitter() {
                return twitter;
            }

            public void setTwitter(String twitter) {
                this.twitter = twitter;
            }
        }
    }

    public static class TeamBean {
        /**
         * id : 39
         * name : Dribbble
         * username : dribbble
         * html_url : https://dribbble.com/dribbble
         * avatar_url : https://d13yacurqjgara.cloudfront.net/users/39/avatars/normal/apple-flat-precomposed.png?1388527574
         * bio : Show and tell for designers. This is Dribbble on Dribbble.
         * location : Salem, MA
         * links : {"web":"http://dribbble.com","twitter":"https://twitter.com/dribbble"}
         * buckets_count : 1
         * comments_received_count : 2037
         * followers_count : 25011
         * followings_count : 6120
         * likes_count : 44
         * likes_received_count : 15811
         * members_count : 7
         * projects_count : 4
         * rebounds_received_count : 416
         * shots_count : 91
         * can_upload_shot : true
         * type : Team
         * pro : false
         * buckets_url : https://dribbble.com/v1/users/39/buckets
         * followers_url : https://dribbble.com/v1/users/39/followers
         * following_url : https://dribbble.com/v1/users/39/following
         * likes_url : https://dribbble.com/v1/users/39/likes
         * members_url : https://dribbble.com/v1/teams/39/members
         * shots_url : https://dribbble.com/v1/users/39/shots
         * team_shots_url : https://dribbble.com/v1/users/39/teams
         * created_at : 2009-08-18T18:34:31Z
         * updated_at : 2014-02-14T22:32:11Z
         */

        private int id;
        private String name;
        private String username;
        @SerializedName("html_url")
        private String htmlUrl;
        @SerializedName("avatar_url")
        private String avatarUrl;
        private String bio;
        private String location;
        private LinksBeanX links;
        @SerializedName("buckets_count")
        private int bucketsCount;
        @SerializedName("comments_received_count")
        private int commentsReceivedCount;
        @SerializedName("followers_count")
        private int followersCount;
        @SerializedName("followings_count")
        private int followingsCount;
        @SerializedName("likes_count")
        private int likesCount;
        @SerializedName("likes_received_count")
        private int likesReceivedCount;
        @SerializedName("members_count")
        private int membersCount;
        @SerializedName("projects_count")
        private int projectsCount;
        @SerializedName("rebounds_received_count")
        private int reboundsReceivedCount;
        @SerializedName("shots_count")
        private int shotsCount;
        @SerializedName("can_upload_shot")
        private boolean canUploadShot;
        private String type;
        private boolean pro;
        @SerializedName("buckets_url")
        private String bucketsUrl;
        @SerializedName("followers_url")
        private String followersUrl;
        @SerializedName("following_url")
        private String followingUrl;
        @SerializedName("likes_url")
        private String likesUrl;
        @SerializedName("members_url")
        private String membersUrl;
        @SerializedName("shots_url")
        private String shotsUrl;
        @SerializedName("team_shots_url")
        private String teamShotsUrl;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public LinksBeanX getLinks() {
            return links;
        }

        public void setLinks(LinksBeanX links) {
            this.links = links;
        }

        public int getBucketsCount() {
            return bucketsCount;
        }

        public void setBucketsCount(int bucketsCount) {
            this.bucketsCount = bucketsCount;
        }

        public int getCommentsReceivedCount() {
            return commentsReceivedCount;
        }

        public void setCommentsReceivedCount(int commentsReceivedCount) {
            this.commentsReceivedCount = commentsReceivedCount;
        }

        public int getFollowersCount() {
            return followersCount;
        }

        public void setFollowersCount(int followersCount) {
            this.followersCount = followersCount;
        }

        public int getFollowingsCount() {
            return followingsCount;
        }

        public void setFollowingsCount(int followingsCount) {
            this.followingsCount = followingsCount;
        }

        public int getLikesCount() {
            return likesCount;
        }

        public void setLikesCount(int likesCount) {
            this.likesCount = likesCount;
        }

        public int getLikesReceivedCount() {
            return likesReceivedCount;
        }

        public void setLikesReceivedCount(int likesReceivedCount) {
            this.likesReceivedCount = likesReceivedCount;
        }

        public int getMembersCount() {
            return membersCount;
        }

        public void setMembersCount(int membersCount) {
            this.membersCount = membersCount;
        }

        public int getProjectsCount() {
            return projectsCount;
        }

        public void setProjectsCount(int projectsCount) {
            this.projectsCount = projectsCount;
        }

        public int getReboundsReceivedCount() {
            return reboundsReceivedCount;
        }

        public void setReboundsReceivedCount(int reboundsReceivedCount) {
            this.reboundsReceivedCount = reboundsReceivedCount;
        }

        public int getShotsCount() {
            return shotsCount;
        }

        public void setShotsCount(int shotsCount) {
            this.shotsCount = shotsCount;
        }

        public boolean isCanUploadShot() {
            return canUploadShot;
        }

        public void setCanUploadShot(boolean canUploadShot) {
            this.canUploadShot = canUploadShot;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isPro() {
            return pro;
        }

        public void setPro(boolean pro) {
            this.pro = pro;
        }

        public String getBucketsUrl() {
            return bucketsUrl;
        }

        public void setBucketsUrl(String bucketsUrl) {
            this.bucketsUrl = bucketsUrl;
        }

        public String getFollowersUrl() {
            return followersUrl;
        }

        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }

        public String getFollowingUrl() {
            return followingUrl;
        }

        public void setFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
        }

        public String getLikesUrl() {
            return likesUrl;
        }

        public void setLikesUrl(String likesUrl) {
            this.likesUrl = likesUrl;
        }

        public String getMembersUrl() {
            return membersUrl;
        }

        public void setMembersUrl(String membersUrl) {
            this.membersUrl = membersUrl;
        }

        public String getShotsUrl() {
            return shotsUrl;
        }

        public void setShotsUrl(String shotsUrl) {
            this.shotsUrl = shotsUrl;
        }

        public String getTeamShotsUrl() {
            return teamShotsUrl;
        }

        public void setTeamShotsUrl(String teamShotsUrl) {
            this.teamShotsUrl = teamShotsUrl;
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

        public static class LinksBeanX {
            /**
             * web : http://dribbble.com
             * twitter : https://twitter.com/dribbble
             */

            private String web;
            private String twitter;

            public String getWeb() {
                return web;
            }

            public void setWeb(String web) {
                this.web = web;
            }

            public String getTwitter() {
                return twitter;
            }

            public void setTwitter(String twitter) {
                this.twitter = twitter;
            }
        }
    }
}
