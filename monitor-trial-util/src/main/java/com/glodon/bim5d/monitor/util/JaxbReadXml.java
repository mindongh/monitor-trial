package com.glodon.bim5d.monitor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * jaxb工具类
 * @author hemd
 *
 */
public class JaxbReadXml {

   private static String getClasspathResource(String path){
      URL url=Thread.currentThread().getContextClassLoader().getResource(path);
      return url.getFile();
   }
   /**
    * 从配置文件读取
    * @param clazz
    * @param context
    * @return
    * @throws JAXBException
    */
    @SuppressWarnings("unchecked")
    public static <T> T readString(Class<T> clazz, String context) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            File file=new File(getClasspathResource(context));
            if(file==null||!file.isFile())
               file=new File(context);
            T t=(T) u.unmarshal(file);
            return t;
        } catch (JAXBException e) {
            throw e;
        }
    }

    /**
     * 从带参数的配置文件读取
     * @param clazz
     * @param config
     * @param arguments
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T readConfig(Class<T> clazz, String config, Object... arguments) throws IOException,
            JAXBException {
        InputStream is = null;
        try {
            if (arguments.length > 0) {
                config = MessageFormat.format(config, arguments);
            }
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            File file=new File(getClasspathResource(config));
            if(file==null||!file.isFile())
               file=new File(config);
            is = new FileInputStream(file);
            return (T) u.unmarshal(is);
        } catch (IOException e) {
            throw e;
        } catch (JAXBException e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * 从流读取
     * @param clazz
     * @param dataStream
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T readConfigFromStream(Class<T> clazz, InputStream dataStream) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(dataStream);
        } catch (JAXBException e) {
            throw e;
        }
    }
}