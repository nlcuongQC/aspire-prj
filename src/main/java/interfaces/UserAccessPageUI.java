package interfaces;

public class UserAccessPageUI {
    public static final String INVITE_NEW_USER_BTN     = "//img[contains(@src, 'debit')]/parent::div";
    public static final String FULLNAME_TXTBX          = "//div[@label = 'Full name']//input";
    public static final String EMAIL_TXTBX             = "//div[@label = 'Email address']//input";
    public static final String DYNAMIC_ROLE_RADIO_BTN  = "//div[text() = '%s']/ancestor::div[@role = 'radio']";
    public static final String CHOOSE_THEIR_ACCESS_BTN = "//button[@type = 'submit']";
    public static final String DYNAMIC_ACCESS          = "//div[contains(text(), '%s')]/ancestor::div[@class = " +
                                                         "'q-item__label']";
    public static final String INVITE_CLOSE_BTN        = "//img[contains(@src, 'close')]";
    public static final String INVITED_NEW_USER_INFO   = "//div[contains(text(), 'Pending')]/../..//div[contains" +
                                                         "(@class, 'invitations-list')]//div[contains(text(), \"%s\")" +
                                                         "]/" + "../following-sibling::div[contains(text(), '%s')]";
    public static final String INVITE_FORM_TITLE       = "//div[@class = 'q-card']//div[@class = " +
                                                         "'aspire-header__titles']/div[@class]";
}
