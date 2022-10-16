package enums;

public enum AndroidXpath {
	Android_SignUp("//android.widget.TextView[@text='Sign Up']");
	
	
	public final String label;

    private AndroidXpath(String label) {
        this.label = label;
    }
}
