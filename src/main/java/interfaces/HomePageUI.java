package interfaces;

public class HomePageUI {
    public static final String PHONE_TXTBX       = "//input[@data-cy = 'register-person-phone']";
    public static final String LOGIN_BTN         = "//div[@class = 'aspire-cta']/button";
    public static final String PHONE_ERROR_TXT   = "//div[@data-cy= 'register-person-phone']//div[@class = " +
                                                   "'aspire-label__text text-negative']";
    public static final String NATION_DDL_PARENT = "//div[@class = 'q-img__content absolute-full']";
    public static final String NATION_DDL_ITEM  = "//div[@class = 'q-item__label' and text() = '%s']";
    public static final String OTP_TXTBX         = "//div[contains(@class, 'current')]";
}
