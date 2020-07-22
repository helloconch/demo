package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.utils.FileIOUtils;
import sample.utils.WindowAlert;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class Main extends Application {
    private static final String LINE_SEP = "\n";

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 创建布局控件
        VBox vBox = new VBox();
        //请输入需要转换倍数
        Label multipleNumLabel = new Label("请输入需要转换倍数:");
        TextField multipleNum = new TextField("1.33");
        multipleNum.setMaxSize(200, 50);
        //请输入新的文件夹名称
        Label folderNameLabel = new Label("请输入新的文件夹名称:");
        TextField folderName = new TextField("values-sw540dp");
        folderName.setMaxSize(200, 50);

        //开始
        Button button = new Button("开始");
        button.setOnMouseClicked((e) -> {
            String resolution = multipleNum.getText().toString();
            String fileName = folderName.getText().toString();
            selectFile(resolution, fileName);
        });

        vBox.getChildren().addAll(multipleNumLabel, multipleNum, folderNameLabel, folderName, button);
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setPadding(new Insets(10));

        // 创建场景 宽=400 高=400
        Scene scene = new Scene(vBox, 200, 400);

        // 将场景添加到窗口
        primaryStage.setScene(scene);

        // 显示窗口
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void selectFile(String resolution, String fileName) {
        System.out.println("用户输入：resolution:" + resolution + "      fileName" + fileName);
        JFileChooser jfc = new JFileChooser();
        //只支持文件
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showDialog(new JLabel(), "选择");
        File file = jfc.getSelectedFile();
        if (file.isFile()) {
            //获取文件名
            String getFileName = jfc.getSelectedFile().getName();
            String fileType = getFileName.substring(getFileName.lastIndexOf("."));
            if (fileType.equals(".xml")) {
                //file.getAbsolutePath() 获取文件路径
                convert(file.getAbsolutePath(), resolution, fileName);
            } else {
                WindowAlert.display("选择的文件类型有误", "失败");
            }
        }
        System.out.println(jfc.getSelectedFile().getName());
    }

    /**
     * 转换成输入对应比率的dimens文件
     *
     * @param absolutePath
     * @param resolution
     * @param fileName
     */
    private void convert(String absolutePath, String resolution, String fileName) {
        float scale = 1;
        try {
            scale = Float.parseFloat(resolution);
        } catch (Exception e) {
            WindowAlert.display("输入的转换倍数有误，保持原有数值", "失败");
        }
        //输入
        File xml = new File(absolutePath);
        //输出 原name+分辨率.xml
        String resName = File.separator + "res" + File.separator;
        int index = absolutePath.indexOf(resName);
        if (index < 0) {
            WindowAlert.display("请选择Android项目res中values中的dimens文件", "失败");
            return;
        }

        String newFilePath = absolutePath.substring(0, index);
        String newFileName = absolutePath.substring(absolutePath.lastIndexOf(File.separator));
        newFilePath = newFilePath + resName + fileName + File.separator + newFileName;
        System.out.println("新生成路径:" + newFilePath);
        File xmlOutput = new File(newFilePath);
        List<String> list = FileIOUtils.readFile2List(xml, "UTF-8");
        StringBuilder sb = new StringBuilder();
        for (int i = 0, len = list.size(); i < len; ++i) {
            String line = list.get(i);
            if (!line.contains("<dimen name=")) {
                sb.append(line);
            } else {
                int indexOfStart = line.indexOf('>');
                sb.append(line.substring(0, indexOfStart + 1));
                int indexOfEnd = line.lastIndexOf('<');
                String substring = line.substring(indexOfStart + 1, indexOfEnd - 2);
                int num = Integer.parseInt(substring);
                System.out.println("origin num:" + num);
                String unit = line.substring(indexOfEnd - 2, indexOfEnd);
                String scaleString = String.valueOf((int) ((scale * num) + 0.5));
                System.out.println("convert num:" + num);
                sb.append(scaleString)
                        .append(line.substring(indexOfEnd - 2));
            }
            sb.append(LINE_SEP);
        }
        FileIOUtils.writeFileFromString(xmlOutput, sb.toString(), false);
        WindowAlert.display("成功生成对应分辨率dimens文件", "成功");
    }


}
