package com.tyc.tdribbble.api;

import android.text.TextUtils;
import android.util.Log;

import com.tyc.tdribbble.entity.ShotsEntity;
import com.tyc.tdribbble.entity.UserEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Dribbble API does not have a search endpoint so we have to do gross things :(
 */
public class DribbbleSearchConverter implements Converter<ResponseBody, List<ShotsEntity>> {

    /**
     * Factory for creating converter. We only care about decoding responses.
     **/
    public static final class Factory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                                Annotation[] annotations,
                                                                Retrofit retrofit) {
            return INSTANCE;
        }

    }

    private DribbbleSearchConverter() {
    }

    private static final DribbbleSearchConverter INSTANCE = new DribbbleSearchConverter();

    private static final String HOST = "https://dribbble.com";
    private static final Pattern PATTERN_PLAYER_ID =
            Pattern.compile("users/(\\d+?)/", Pattern.DOTALL);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMMM d, yyyy");

    @Override
    public List<ShotsEntity> convert(ResponseBody value) throws IOException {
        final Elements shotElements =
                Jsoup.parse(value.string(), HOST).select("li[id^=screenshot]");
        final List<ShotsEntity> shots = new ArrayList<>(shotElements.size());
        for (Element element : shotElements) {
            final ShotsEntity shot = parseShot(element, DATE_FORMAT);
            if (shot != null) {
                shots.add(shot);
            }
        }
        return shots;
    }

    private static ShotsEntity parseShot(Element element, SimpleDateFormat dateFormat) {
        final Element descriptionBlock = element.select("a.dribbble-over").first();
        // API responses wrap description in a <p> tag. Do the same for consistent display.
        String description = descriptionBlock.select("span.comment").text().trim();
        if (!TextUtils.isEmpty(description)) {
            description = "<p>" + description + "</p>";
        }
        String imgUrl = element.select("img").first().attr("src");
        if (imgUrl.contains("_teaser.")) {
            imgUrl = imgUrl.replace("_teaser.", ".");
        }
        String createdAt = descriptionBlock.select("em.timestamp").first().text();


        ShotsEntity shotsEntity = new ShotsEntity();
        shotsEntity.setId(Integer.parseInt(element.id().replace("screenshot-", "")));
        shotsEntity.setHtmlUrl(HOST + element.select("a.dribbble-link").first().attr("href"));
        shotsEntity.setTitle(descriptionBlock.select("strong").first().text());
        shotsEntity.setDescription(description);
        shotsEntity.setImages(new ShotsEntity.ImagesBean(null, imgUrl, null));
        shotsEntity.setAnimated(element.select("div.gif-indicator").first() != null);
        shotsEntity.setCreatedAt(createdAt);
        shotsEntity.setUpdatedAt(createdAt);
        shotsEntity.setLikesCount(Integer.parseInt(element.select("li.fav").first().child(0).text()
                .replaceAll(",", "")));
        shotsEntity.setCommentsCount(Integer.parseInt(element.select("li.cmnt").first().child(0).text
                ().replaceAll(",", "")));
        shotsEntity.setViewsCount(Integer.parseInt(element.select("li.views").first().child(0)
                .text().replaceAll(",", "")));
        shotsEntity.setUser(parsePlayer(element.select("h2").first()));

        return shotsEntity;
    }

    private static UserEntity parsePlayer(Element element) {
        final Element userBlock = element.select("a.url").first();
        String avatarUrl = userBlock.select("img.photo").first().attr("src");
        if (avatarUrl.contains("/mini/")) {
            avatarUrl = avatarUrl.replace("/mini/", "/normal/");
        }
        final Matcher matchId = PATTERN_PLAYER_ID.matcher(avatarUrl);
        Integer id = -1;
        if (matchId.find() && matchId.groupCount() == 1) {
            id = Integer.parseInt(matchId.group(1));
        }
        final String slashUsername = userBlock.attr("href");
        final String username =
                TextUtils.isEmpty(slashUsername) ? null : slashUsername.substring(1);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setName(userBlock.text());
        userEntity.setUsername(username);
        userEntity.setHtmlUrl(HOST + slashUsername);
        userEntity.setAvatarUrl(avatarUrl);
        userEntity.setPro(element.select("span.badge-pro").size() > 0);

        return userEntity;
    }

}
