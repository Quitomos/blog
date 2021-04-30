package cn.quitomos.blog.util;

import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.emoji.EmojiImageType;
import com.vladsch.flexmark.ext.emoji.EmojiShortcutType;
import com.vladsch.flexmark.ext.escaped.character.EscapedCharacterExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.gitlab.GitLabExtension;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.ext.media.tags.MediaTagsExtension;
import com.vladsch.flexmark.ext.superscript.SuperscriptExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.ext.yaml.front.matter.AbstractYamlFrontMatterVisitor;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.misc.Extension;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Halo.ryanwang
 */
public class MarkdownUtil {
    private static final DataHolder OPTIONS =
            new MutableDataSet().set(Parser.EXTENSIONS, Arrays.<Extension>asList(AttributesExtension.create(),
                    AutolinkExtension.create(),
                    EmojiExtension.create(),
                    EscapedCharacterExtension.create(),
                    StrikethroughExtension.create(),
                    TaskListExtension.create(),
                    InsExtension.create(),
                    MediaTagsExtension.create(),
                    TablesExtension.create(),
                    TocExtension.create(),
                    SuperscriptExtension.create(),
                    YamlFrontMatterExtension.create(),
                    GitLabExtension.create()))
                    .set(TocExtension.LEVELS, 255)
                    .set(TablesExtension.WITH_CAPTION, false)
                    .set(TablesExtension.COLUMN_SPANS, false)
                    .set(TablesExtension.MIN_SEPARATOR_DASHES, 1)
                    .set(TablesExtension.MIN_HEADER_ROWS, 1)
                    .set(TablesExtension.MAX_HEADER_ROWS, 1)
                    .set(TablesExtension.APPEND_MISSING_COLUMNS, true)
                    .set(TablesExtension.DISCARD_EXTRA_COLUMNS, true)
                    .set(TablesExtension.HEADER_SEPARATOR_COLUMN_MATCH, true)
                    .set(EmojiExtension.USE_SHORTCUT_TYPE, EmojiShortcutType.EMOJI_CHEAT_SHEET)
                    .set(EmojiExtension.USE_IMAGE_TYPE, EmojiImageType.UNICODE_ONLY)
                    .set(HtmlRenderer.SOFT_BREAK, "<br />\n");

    private static final Parser PARSER = Parser.builder(OPTIONS).build();

    private static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();
    private static final Pattern FRONT_MATTER = Pattern.compile("^---[\\s\\S]*?---");

    public static String renderHtml(String markdown) {
        if (StringUtils.isBlank(markdown)) {
            return StringUtils.EMPTY;
        }

        // Render netease music short url.
        if (markdown.contains("[music:")) {
            markdown = markdown
                    .replaceAll("\\[music:(\\d+)\\]",
                            "<iframe frameborder=\"no\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" width=330 "
                            + "height=86 src=\"//music.163.com/outchain/player?type=2&id=$1&auto=1&height=66"
                            + "\"></iframe>");
        }

        // Render bilibili video short url.
        if (markdown.contains("[bilibili:")) {
            markdown = markdown
                    .replaceAll("\\[bilibili:(\\d+)\\,(\\d+)\\,(\\d+)\\]",
                            "<iframe height=$3 width=$2 src=\"//player.bilibili.com/player.html?aid=$1\" "
                            + "scrolling=\"no\" border=\"0\" frameborder=\"no\" framespacing=\"0\" "
                            + "allowfullscreen=\"true\"> </iframe>");
        }

        // Render youtube video short url.
        if (markdown.contains("[youtube:")) {
            markdown = markdown
                    .replaceAll("\\[youtube:(\\w+)\\,(\\d+)\\,(\\d+)\\]",
                            "<iframe width=$2 height=$3 src=\"https://www.youtube.com/embed/$1\" frameborder=\"0\" "
                                    + "allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" "
                                    + "allowfullscreen></iframe>");
        }

        Node document = PARSER.parse(markdown);

        return RENDERER.render(document);
    }

    /**
     * Get front-matter
     *
     * @param markdown markdown
     * @return Map
     */
    public static Map<String, List<String>> getFrontMatter(String markdown) {
        AbstractYamlFrontMatterVisitor visitor = new AbstractYamlFrontMatterVisitor();
        Node document = PARSER.parse(markdown);
        visitor.visit(document);
        return visitor.getData();
    }

    /**
     * remove front matter
     *
     * @param markdown markdown
     * @return markdown
     */
    public static String removeFrontMatter(String markdown) {
        markdown = markdown.trim();
        Matcher matcher = FRONT_MATTER.matcher(markdown);
        if (matcher.find()) {
            return markdown.replace(matcher.group(), "");
        }
        return markdown;
    }
}
