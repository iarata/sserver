package jp.uce.sserver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.ui.Model;

import jp.uce.sserver.blog.Blog;
import jp.uce.sserver.blog.BlogIO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringTokenizer;

@Controller
public class Routes {
    

    @GetMapping({ "/", "/index.html" })
    public String index(@RequestHeader (value = "User-Agent") String userAgent, Model model) {
        // model.addAttribute("employees", employees);
        // Get all blogs and add them to the model
        List<Blog> blogs = new BlogIO().findAll();
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        
        model.addAttribute("newTime", now);
        model.addAttribute("blogs", blogs);
        model.addAttribute("requestAgent", userAgent);

        model.addAttribute("isMobile", isMobileDevice(userAgent));

        StringTokenizer st = new StringTokenizer(userAgent, " ");
        
        String browser = "";
        String os = "";
        String version = "";
        String platform = "";
        String device = "";

        if (st.hasMoreTokens()) {
            browser = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            os = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            version = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            platform = st.nextToken();
        }
        if (st.hasMoreTokens()) {
            device = st.nextToken();
        }
        
        // add to model 
        model.addAttribute("browser", browser);
        model.addAttribute("os", os);
        model.addAttribute("version", version);
        model.addAttribute("platform", platform);
        model.addAttribute("device", device);

        

        return "index";
    }

    public static boolean isMobileDevice(String userAgent) {
        String ua = userAgent.toLowerCase();
        // iphone, ipad, ipod
        if (ua.indexOf("iphone") > -1 || ua.indexOf("ipad") > -1 || ua.indexOf("ipod") > -1) {
            return true;
        }
        // android
        else if (ua.indexOf("android") > -1) {
            return true;
        }
        // windows phone
        else if (ua.indexOf("windows phone") > -1) {
            return true;
        }
        // blackberry
        else if (ua.indexOf("blackberry") > -1) {
            return true;
        }
        // windows
        else if (ua.indexOf("windows") > -1) {
            return false;
        }
        // mac
        else if (ua.indexOf("mac") > -1) {
            return false;
        }
        // linux
        else if (ua.indexOf("linux") > -1) {
            return false;
        }
        // other
        else {
            return false;
        }

    }
}
