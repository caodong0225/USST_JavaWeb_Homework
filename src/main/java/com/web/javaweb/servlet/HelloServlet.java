package com.web.javaweb.servlet;

import java.io.*;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

/**
 * <p>
 *     USST课程《JavaWeb开发技术》的第一个Servlet程序
 * </p>
 * @author jyzxc
 * @since 2024-09-20
 */
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private List<String> naviString;
    private String[][] resourceString;
    private String[] resourceNameString;
    private String[] newsString;
    private String[] databaseString;

    public void init() {
        naviString = List.of(new String[]{
                "学校主页",
                "馆藏书目检索",
                "新书通报",
                "书刊荐购",
                "学术信息动态",
                "新生专栏",
                "联系我们",
                "馆长信箱"
        });
        resourceString = new String[][]{
                {"中文数据库", "外文数据库", "试用数据库", "电子图书", "网络免费资源", "特色资源", "自建资源", "随书光盘"},
                {"读者信息查询", "文献续借", "文献预约", "证件管理", "常见问题", "外借服务", "读者教育", "图书捐赠"},
                {"本馆概况", "入馆须知", "开放时间", "馆藏分布", "机构设置", "规章制度", "服务项目", "馆容馆貌"},
                {"学科馆员", "信息咨询", "馆际互借", "文献传递", "图书分类", "导读书目", "读书平台", "专家讲座"},
                {"学科导航", "网上图书馆", "推荐站点", "常用软件", "友情链接", "报刊资源", "馆员天地"}
        };
        resourceNameString = new String[]{
                "资源检索",
                "读者服务",
                "入馆指南",
                "信息服务",
                "网络导航"
        };
        newsString = new String[]{
                "图书馆2024年国庆节放假通知",
                "ProQuest教育学、心理学学术...",
                "《典海民国图书资源平台》试用...",
                "校友著作捐赠仪式在图书馆举行",
                "外文期刊学科服务平台试用通知",
                "图书馆被评为陕西省图书馆学会",
                "图书馆和校团委联合召开2024年",
                "ProQuest综合学术期刊数据库...",
                "图书馆2024年“书香校园”系列..."
        };
        databaseString = new String[]{
                "中国科技论文在线",
                "中国开放教育资源",
                "国家精品课程",
                "教育网公开课导航",
                "Emerald电子资源",
                "OA学术资源"
        };
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html data-darkreader-mode=\"dynamic\" data-darkreader-scheme=\"dark\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"./index.css\">\n" +
                "    <script src=\"./index.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"container\">\n" +
                "    <div id=\"logo\" onmouseover=\"mouseovermethod()\">\n" +
                "\n" +
                "    </div>\n" +
                "        <div id=\"navigator\">\n" +
                "            <ul>");
        for (String s : naviString) {
            out.println(
                    "                <li>\n" +
                    "                    <a href=\"#\">" + s + "</a>\n" +
                    "                </li>"
            );
        }
        out.println(
                "            </ul>\n" +
                "\n" +
                "        </div>\n" +
                "    <div id=\"content\">\n");
        for (int i = 0; i < resourceString.length; i++) {
            out.println(
                    "        <div id=\"c" + i + "\">\n" +
                    "            <ul>\n" +
                    "                <li class=\"first\">\n" +
                    "                    &gt;" + resourceNameString[i] + "\n" +
                    "                </li>\n"
            );
            for(int j = 0; j < resourceString[i].length; j++) {
                out.println(
                        "            <li>\n" +
                        "                <a href=\"#\">" + resourceString[i][j] + "</a>\n" +
                        "            </li>\n"
                );
            }
            out.println("        </ul>\n" +
                        "    </div>");
        }
        out.println(
                "        <div id=\"news\">\n" +
                "            <ul>\n");
        for (String s : newsString) {
            out.println(
                    "            <li>\n" +
                    "                <a href=\"#\">" + s + "</a>\n" +
                    "            </li>"
            );
        }
        out.println("        </ul>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"recommand\">\n" +
                "        <div id=\"box\">\n");
        for (int i = 1; i < 6; i++) {
            out.println(
                    "        <a href=\"#\"><img src=\"./img/bggundong_" + i + ".jpg\" id=\"img" + i +"\"></a>\n"
            );
        }
        out.println("    </div>\n" +
                "        <div id=\"scroll_end\">\n" +
                "\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\"resource\">\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                请输入资源检索关键词：\n" +
                "            </li>\n" +
                "            <li>\n" +
                "                <input type=\"text\" name=\"search\" id=\"search\">\n" +
                "                <input type=\"button\" value=\"查询\" id=\"submit\" onclick=\"show()\">\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div id=\"database\">\n" +
                "        <ul>\n");
        for (int i = 1; i < 7; i++) {
            out.println(
                    "        <li>\n" +
                    "            <a href=\"#\"><img src=\"./img/db" + i + ".jpg\"></a>\n" +
                    "        </li>"
            );
        }
        out.println("    </ul>\n" +
                "\n" +
                "    </div>\n" +
                "    <div id=\"hotlink\">\n" +
                "        <ul>\n");
        for (String s: databaseString) {
            out.println(
                    "        <li>\n" +
                    "            <a href=\"#\">" + s + "</a>\n" +
                    "        </li>"
            );
        }
        out.println("    </ul>\n" +
                "    </div>\n" +
                "    <div id=\"copyright\">\n" +
                "        版权所有：上海理工大学图书馆&nbsp;&nbsp;技术支持：网络管理中心&nbsp;&nbsp;建议使用IE7.0以上浏览器&nbsp;&nbsp;1024x768以上分辨率\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" +
                "<style>\n" +
                "    @import url(style.css);\n" +
                "</style>");
    }

    public void destroy() {
    }
}