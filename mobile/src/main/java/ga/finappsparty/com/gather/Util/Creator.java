package ga.finappsparty.com.gather.Util;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;

public class Creator extends Activity {

    public static String robotoRegular = "Roboto-Regular.ttf"; // 1
    public static String robotoThin = "Roboto-Thin.ttf"; // 2
    public static String robotoBlack = "Roboto-Black.ttf"; // 3
    public static String robotoCondensedBold = "RobotoCondensed-Bold.ttf"; // 4
    public static String robotoCondensedLight = "RobotoCondensed-Light.ttf"; // 5
    public static String robotoCondensedRegular = "RobotoCondensed-Regular.ttf"; // 6

    public static Typeface genFont(AssetManager asset, int number) {
        Typeface roboto = null;
        switch (number) {
            case 1:
                return roboto = Typeface.createFromAsset(asset, robotoRegular);
            case 2:
                return roboto = Typeface.createFromAsset(asset, robotoThin);
            case 3:
                return roboto = Typeface.createFromAsset(asset, robotoBlack);
            case 4:
                return roboto = Typeface.createFromAsset(asset, robotoCondensedBold);
            case 5:
                return roboto = Typeface.createFromAsset(asset, robotoCondensedLight);
            case 6:
                return roboto = Typeface.createFromAsset(asset, robotoCondensedRegular);
            default:
                return roboto = Typeface.createFromAsset(asset, robotoRegular);

        }
    }


}
