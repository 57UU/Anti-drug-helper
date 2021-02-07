import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.*;
import java.util.ArrayList;

public class mainProgram {
    ArrayList<String> all;
    public static void main(String[] args) {
        try {
            new mainProgram().go();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    boolean ver(char a){
        int num=(int)a;
        //System.out.println(num);
        if (num>9000){
                return false;
        }
        return num!=9;
    }
    void go() throws InterruptedException {
        BufferedReader br;
        all=new ArrayList<>();
        Beautiful.setUIFont();

        try {
            br=new BufferedReader(new FileReader("ti"));
            String s;
            StringBuilder sb=new StringBuilder();
            while ((s=br.readLine()) != null){
                if(s.equals("")){
                    continue;
                }
                if(ver(s.charAt(0))){
                    all.add(sb.toString());
                    sb.replace(0,sb.length(),"");
                }
                //System.out.println(s);
                sb.append(s);
                sb.append("\n");
            }
            all.add(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("read complete");
        buildGUI();
        String key="";
        while (true){
            String newKey="";
            try{
                var theKey=getClipboardString();
                if(!theKey.isSupport){
                    txt.setText("Don't copy other thing except text");
                    continue;
                }
                newKey = theKey.content;
            }catch (IllegalStateException e){
                e.printStackTrace();
            }

            if(!key.equals(newKey)){
                find(newKey,true);
                System.out.println(newKey);
                key=newKey;
            }
            Thread.sleep(20);
        }
/*        for(String i:all){
            System.out.println(i);
            System.out.println("???????????????");
        }*/

    }
    JTextArea txt=new JTextArea();
    //JLabel jl=new JLabel();
    JFrame jf;
    void buildGUI(){
        jf=new JFrame("学习助手");
        jf.setAlwaysOnTop(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JScrollPane js=new JScrollPane(txt);
        txt.setEditable(false);
        txt.setFont(new Font("Dialog", Font.PLAIN, 16));
        js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jf.getContentPane().add(js,BorderLayout.CENTER);
        //jf.getContentPane().add(jl,BorderLayout.NORTH);
        jf.getContentPane().add(new JLabel("使用方法：用鼠标选中，按Ctrl+c"),BorderLayout.SOUTH);
        jf.setSize(400,300);
        jf.setVisible(true);
    }
    void find(String key,boolean isReCall){
        txt.setText("");
        boolean isResultNotEmpty =false;
        for(String i:all){
            if(i==null){
                continue;
            }
            if(key==null){
                continue;
            }
            if(i.contains(key)){
                txt.append(i);
                isResultNotEmpty =true;
            }
        }
        if(!isResultNotEmpty){
            txt.append("No such result");
        }
        if(isReCall){
            try {
                if (!isResultNotEmpty) {
                    find(key.substring(0, 6), false);
                }
            }catch (StringIndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
        //jf.setVisible(true);
    }

    static CilpBoardReader getClipboardString() throws IllegalStateException{
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);

        if (trans != null) {
            // 判断剪贴板中的内容是否支持文本
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // 获取剪贴板中的文本内容
                    return new CilpBoardReader((String) trans.getTransferData(DataFlavor.stringFlavor));
                } catch (Exception e) {
                    System.out.println("what you copied:can not parse what you copied");
                    e.printStackTrace();
                }
            }
        }

        return new CilpBoardReader(false);
    }
}
class CilpBoardReader{
    String content;
    boolean isSupport=true;
    public CilpBoardReader(String s){
        this.content=s;
    }
    public CilpBoardReader(boolean isSupport){
        this.isSupport=isSupport;
    }
}
