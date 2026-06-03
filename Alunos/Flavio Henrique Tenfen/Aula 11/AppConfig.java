import java.awt.*;

public class AppConfig {


    public static final String API_KEY = "JNVQT52NHTGAW28D6JPW48JUR";

    public static final String API_BASE_URL =
        "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public static final int CONNECT_TIMEOUT_MS = 12_000;
    public static final int READ_TIMEOUT_MS    = 12_000;

    public static final Color GRAD_TOP    = new Color(0x0F1B3C);
    public static final Color GRAD_BOT    = new Color(0x1A0A3C);

    public static final Color GLASS_BG    = new Color(255, 255, 255, 22);   
    public static final Color GLASS_BDR   = new Color(255, 255, 255, 45);   
    public static final Color GLASS_HOVER = new Color(255, 255, 255, 35);

    public static final Color TEXT_PRI    = new Color(0xFFFFFF);
    public static final Color TEXT_SEC    = new Color(0xB8C8E8);
    public static final Color TEXT_MUTED  = new Color(0x7090B8);

    public static final Color ACCENT      = new Color(0x4DA6FF);
    public static final Color ACCENT_GLOW = new Color(0x4DA6FF, true);

    public static final Color DANGER      = new Color(0xFF6B6B);
    public static final Color SUCCESS     = new Color(0x6BFFB8);
    public static final Color RAIN_COLOR  = new Color(0x80C8FF);

    public static final Color INPUT_BG    = new Color(255, 255, 255, 18);
    public static final Color INPUT_BDR   = new Color(255, 255, 255, 60);
    public static final Color INPUT_BDR_FOCUS = new Color(0x4DA6FF);

    public static final Font F_APPNAME = new Font("Segoe UI", Font.PLAIN,  13);
    public static final Font F_CITY    = new Font("Segoe UI", Font.BOLD,   26);
    public static final Font F_TEMP    = new Font("Segoe UI", Font.BOLD,   80);
    public static final Font F_DEG     = new Font("Segoe UI", Font.PLAIN,  32);
    public static final Font F_TSUB    = new Font("Segoe UI", Font.PLAIN,  15);
    public static final Font F_LABEL   = new Font("Segoe UI", Font.PLAIN,  11);
    public static final Font F_VALUE   = new Font("Segoe UI", Font.BOLD,   20);
    public static final Font F_UNIT    = new Font("Segoe UI", Font.PLAIN,  12);
    public static final Font F_COND    = new Font("Segoe UI", Font.BOLD,   15);
    public static final Font F_BODY    = new Font("Segoe UI", Font.PLAIN,  14);
    public static final Font F_SMALL   = new Font("Segoe UI", Font.PLAIN,  11);
    public static final Font F_BTN     = new Font("Segoe UI", Font.BOLD,   13);
    public static final Font F_MONO    = new Font("Consolas",  Font.PLAIN,  13);
    public static final Font F_EMOJI   = new Font("Segoe UI Emoji", Font.PLAIN, 28);
    public static final Font F_EMOJI_SM= new Font("Segoe UI Emoji", Font.PLAIN, 18);
}
