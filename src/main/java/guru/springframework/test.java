package guru.springframework;

import javax.xml.bind.annotation.XmlElement;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */
public class test {

    public static List<Class> getClasssFromPackage(String pack) {
        List<Class> clazzs = new ArrayList<Class>();

        // 是否循环搜索子包
        boolean recursive = true;

        // 包名字
        String packageName = pack;
        // 包名对应的路径名称
        String packageDirName = packageName.replace('.', '/');

        Enumeration<URL> dirs;

        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();

                String protocol = url.getProtocol();

                if ("file".equals(protocol)) {
                    System.out.println("file类型的扫描");
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findClassInPackageByFile(packageName, filePath, recursive, clazzs);
                } else if ("jar".equals(protocol)) {
                    System.out.println("jar类型的扫描");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clazzs;
    }

    /**
     * 在package对应的路径下找到所有的class
     *
     * @param packageName
     *            package名称
     * @param filePath
     *            package对应的路径
     * @param recursive
     *            是否查找子package
     * @param clazzs
     *            找到class以后存放的集合
     */
    public static void findClassInPackageByFile(String packageName, String filePath, final boolean recursive, List<Class> clazzs) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 在给定的目录下找到所有的文件，并且进行条件过滤
        File[] dirFiles = dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                boolean acceptDir = recursive && file.isDirectory();// 接受dir目录
                boolean acceptClass = file.getName().endsWith("class");// 接受class文件
                return acceptDir || acceptClass;
            }
        });

        for (File file : dirFiles) {
            if (file.isDirectory()) {
                findClassInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, clazzs);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        List<Class> classsFromPackage = getClasssFromPackage("guru.springframework.domain");
        for (Class cls : classsFromPackage) {
            String name = cls.getName();
            Field[] declaredFields = cls.getDeclaredFields();
            name = name.replace("guru.springframework.domain.", "");
            System.out.println(name);
            String context = "<!DOCTYPE html>\n" +
                    "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                    "<head lang=\"en\">\n" +
                    "\n" +
                    "    <title>Spring Framework Guru</title>\n" +
                    "\n" +
                    "    <!--//* <th:block th:include=\"fragments/headerinc :: head\"></th:block> //*-->\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">";
            context += "<div th:if=\"${not #lists.isEmpty(" + name + ")}\">";
            context += "<h2>" + name + " List</h2>\n" +
                    "        <table class=\"table table-striped\">\n" +
                    "            <tr>";


            for (int i = -0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                String name1 = declaredField.getName();
                String name2 = name1.toUpperCase();
                System.out.println(name1);
                System.out.println(name2);
                context += "<th>" + name2 + "</th>";

            }
            String sname = name.toLowerCase();
            context += " </tr>\n" +
                    "            <tr th:each=\"" + sname + " : ${" + name + "}\">";


            for (int i = -0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                String name1 = declaredField.getName();
                System.out.println(name1);
                String name2 = name1.toUpperCase();

                context += "<td th:text=\"${" + sname + "." + name1 + "}\">" + name2 + "</td>";

            }

            context += "</tr>\n" +
                    "        </table>\n" +
                    "\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <!-- Pagination Bar -->\n" +
                    "    <div th:fragment='paginationbar'>\n" +
                    "        <div>\n" +
                    "            <ul class='pagination pagination-centered'>\n" +
                    "                <li th:class=\"${page.firstPage}?'disabled':''\">\n" +
                    "                <span th:if='${page.firstPage}'>← First</span>\n" +
                    "                <a th:if='${not page.firstPage}' th:href='@{${page.url}(page=0,size=${page.size})}'>← First</a>\n" +
                    "                </li>\n" +
                    "                <li th:class=\"${page.hasPreviousPage}? '' : 'disabled'\">\n" +
                    "                <span th:if='${not page.hasPreviousPage}'>«</span>\n" +
                    "                <a th:if='${page.hasPreviousPage}' th:href='@{${page.url}(page=${page.number-2},size=${page.size})}' title='Go to previous page'>«</a>\n" +
                    "                </li>\n" +
                    "                <li th:each='item : ${page.items}' th:class=\"${item.current}? 'active' : ''\">\n" +
                    "                <span th:if='${item.current}' th:text='${item.number}'>1</span>\n" +
                    "                <a th:if='${not item.current}' th:href='@{${page.url}(page=${item.number-1},size=${page.size})}'><span th:text='${item.number}'>1</span></a>\n" +
                    "                </li>\n" +
                    "                <li th:class=\"${page.hasNextPage}? '' : 'disabled'\">\n" +
                    "                <span th:if='${not page.hasNextPage}'>»</span>\n" +
                    "                <a th:if='${page.hasNextPage}' th:href='@{${page.url}(page=${page.number},size=${page.size})}' title='Go to next page'>»</a>\n" +
                    "                </li>\n" +
                    "                <li th:class=\"${page.lastPage}? 'disabled' : ''\">\n" +
                    "                <span th:if='${page.lastPage}'>Last →</span>\n" +
                    "                <a th:if='${not page.lastPage}' th:href='@{${page.url}(page=${page.totalPages - 1},size=${page.size})}'>Last →</a>\n" +
                    "                </li>\n" +
                    "            </ul>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "</div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>";
            writeInFileByfi(context, sname + ".html");
            System.out.println(sname);
        }
    }

    /*public static void main(String[] args) {
        List<Class> classsFromPackage = getClasssFromPackage("guru.springframework.domain");
        for(Class cls : classsFromPackage) {
            String name = cls.getName();
            Field[] declaredFields = cls.getDeclaredFields();
            name = name.replace("guru.springframework.domain.", "");
            System.out.println(name);
            for (int i= 0;i<declaredFields.length;i++) {
                Field declaredField = declaredFields[i];
                Annotation annotation = declaredField.getAnnotation(XmlElement.class);
                if (annotation!=null) {
                    XmlElement xmlElement = (XmlElement) annotation;
                    String name1 = xmlElement.name();
                    System.out.println(name1);
                }

                Annotation[] allAnnotations = declaredField.getAnnotations();

                if(allAnnotations.length>0) {
                    Annotation ano = allAnnotations[0];
                    Class<? extends Annotation> aClass = ano.annotationType();
                    System.out.println("属性【" + declaredField + "】的注解类型有: " + aClass);
                    System.out.println("属性【" + declaredField + "】的注解类型有: " + ano.toString());
                }
            }

           *//* Annotation[] annotations = cls.getAnnotations();
            for (int i= -0;i<annotations.length;i++){
                Annotation annotation = annotations[i];
                XmlElement xmlElement = (XmlElement)annotation;
                String name1 = xmlElement.name();
                String s = annotation.toString();
                System.out.println(s);
                System.out.println(name1);

            }*//*

        }


    }*/








    public static void writeInFileByfi(String context,String path){
        File f=new File(path);
        FileOutputStream fos=null;
        try {
            if(!f.exists()){
                f.createNewFile();
            }
            fos=new FileOutputStream(f);
//            String content="要写入的新内容！";
            fos.write(context.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
