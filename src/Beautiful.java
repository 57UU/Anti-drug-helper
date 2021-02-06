import javax.swing.*;
import java.awt.*;

public class Beautiful {
    public static void setUIFont() {
        Font font = new Font("Dialog", Font.PLAIN, 14);
        UIManager.put("Label.font", new Font("Dialog", Font.PLAIN, 16));
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, font);
        }
        try {
/*            String look;
            //look="com.jtattoo.plaf.fast.FastLookAndFeel";
            //look="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
            //look = "javax.swing.plaf.metal.MetalLookAndFeel";
            //look="com.sun.java.swing.plaf.mac.MacLookAndFeel";
            //look = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            //look = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            //look = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            //look="com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"
            UIManager.setLookAndFeel(look);*/

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Substance Raven Graphite failed to initialize");
        }
    }

    public static void setMid(Window frame) {
        final int windowWidth = frame.getWidth();
        final int windowHeight = frame.getHeight();
        final Toolkit kit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = kit.getScreenSize();
        final int screenWidth = screenSize.width;
        final int screenHeight = screenSize.height;
        //System.out.println(screenHeight);
        //System.out.println(screenWidth);
        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }
}
