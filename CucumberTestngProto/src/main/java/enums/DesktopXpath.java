package enums;

public enum DesktopXpath {
	Desktop_my_account("//div//a[contains(@href, 'homepage')]"),
	Desktop_accept_cookies("//input[@id='sp-cc-accept']"),
	Desktop_reject_cookies("//div[contains(@id,'rejectall-container')]//a"),
	Desktop_reg_start("//a[@id='nav-link-accountList']//span[contains(@class,'nav-icon nav-arrow')]"),
	Desktop_identify("//span[@class='nav-action-inner']"),
	Desktop_new_custom("//div[@id='nav-flyout-ya-newCust']//a"),
	Desktop_name("//input[@id='ap_customer_name']"), 
	Desktop_email("//input[@id='ap_email']"),
	Desktop_password("//input[@id='ap_password']"),
	Desktop_password_confirm("//input[@id='ap_password_check']"),
	Desktop_continue("//input[@id='continue']"),
	Desktop_sign_in("//input[@id='signInSubmit']"),
	Desktop_search_bar("//div[@class='nav-search-field ']//input"),
	Desktop_search_results("//div[@class='sg-col-inner']//span[@data-component-type='s-search-results']"),
	Desktop_search_item("//div[contains(@class, 'card-container') and (contains(@class, 'card-border'))]"),
	Desktop_search_item_label("//div[contains(@class, 'a-section a-spacing-base')]//span[contains(@class, 'text-normal')]"),
	Desktop_add_to_cart("//input[@id='add-to-cart-button']"),
	Desktop_add_basket("//input[contains(@id,'add-to-cart')]"),
	Desktop_clean_basket("//img[@class='ewc-delete-icon']/ancestor::span[@class='a-button-inner']//input[(@type='submit') and (@class='a-button-input')]"),
	Desktop_item_description("//span[@id='productTitle']"),
	
	Desktop_google_es_cookies("//"),
	Desktop_home_google("//input[@role='combobox']"),
	Desktop_wikipedia_link("//div/a[contains(@ping, 'es.wikipedia.org') and not(contains(@ping, 'webcache'))]/h3"),
	Desktop_historia_temprana("//span[@id='Historia_temprana']"),
	Desktop_historia_temprana_link("//a[contains(@href,'Historia_temprana')]//span[@class='toctext']"),
	Desktop_historia_Egipto("//a[contains(@title,'Egipto')]/..");
	
	public final String label;

    private DesktopXpath(String label) {
        this.label = label;
    }
}
