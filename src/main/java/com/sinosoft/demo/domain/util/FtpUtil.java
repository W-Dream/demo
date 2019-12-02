package com.sinosoft.demo.domain.util;

import com.jcraft.jsch.*;
import com.sinosoft.demo.domain.entity.FtpEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;

@Slf4j
@Component
public class FtpUtil {

    public static final String NO_FILE = "No such file";

    private ChannelSftp sftp = null;

    private Session sshSession = null;

    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.remoteFilePath}")
    private String remoteFilePath;
    @Value("${ftp.localFilePath}")
    private String localFilePath;

    public FtpUtil() {
    }

    public FtpUtil(String username, String password, String host, int port, String remoteFilePath, String localFilePath) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.remoteFilePath = remoteFilePath;
        this.localFilePath = localFilePath;
    }

    /**
     * 连接sftp服务器
     *
     * @return ChannelSftp sftp类型
     * @throws Exception
     */
    public ChannelSftp connect() throws Exception {
        log.info("FtpUtil-->connect--ftp连接开始>>>>>>host=" + host + ">>>port" + port + ">>>username=" + username);
        JSch jsch = new JSch();
        try {
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            log.info("ftp---Session created.");
            sshSession.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(properties);
            sshSession.connect();
            log.info("ftp---Session connected.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            log.info("Opening Channel.");
            sftp = (ChannelSftp) channel;
            log.info("ftp---Connected to " + host);
        } catch (JSchException e) {
            log.info("FtpUtil-->connect异常" + e.getMessage());
            throw new Exception("FtpUtil-->connect异常" + e.getMessage());
        }
        return sftp;
    }

    /**
     * 载单个文件
     *
     * @param remoteFileName FTP服务器文件名称 如：xxx.txt ||xxx.txt.zip
     * @param localFile      本地文件路径 如 D:\\xxx.txt
     * @return
     * @throws Exception
     */
    public File downloadFile(String remoteFileName, String localFile) throws Exception {
        log.info(">>>>>>>>FtpUtil-->downloadFile--ftp下载文件" + remoteFileName + "开始>>>>>>>>>>>>>");
        connect();
        File file = null;
        OutputStream output = null;
        try {
            file = new File(localFilePath + localFile);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            sftp.cd(remoteFilePath);
            output = new FileOutputStream(file);
            System.out.println("-----------------------------------------------------");
            System.err.println("准备下载");
            System.out.println("-----------------------------------------------------");
            sftp.get(remoteFileName, output);
            System.out.println("-----------------------------------------------------");
            System.err.println("下载完成");
            System.out.println("-----------------------------------------------------");
            log.info("===DownloadFile:" + remoteFileName + " success from sftp.");
        } catch (SftpException e) {
            if (e.toString().equals(NO_FILE)) {
                log.error(">>>>>>>>FtpUtil-->downloadFile--ftp下载文件失败" + remoteFilePath + remoteFileName + "不存在>>>>>>>>>>>>>");
                throw new Exception("FtpUtil-->downloadFile--ftp下载文件失败" + remoteFilePath + remoteFileName + "不存在");
            }
            log.error("ftp目录或者文件异常，检查ftp目录和文件" + e.toString());
            throw new Exception("ftp目录或者文件异常，检查ftp目录和文件" + e.toString());
        } catch (FileNotFoundException e) {
            log.error("本地目录异常，请检查" + file.getPath() + e.getMessage());
            throw new Exception("本地目录异常，请检查" + file.getPath() + e.getMessage());
        } catch (IOException e) {
            log.error("创建本地文件失败" + file.getPath() + e.getMessage());
            throw new Exception("创建本地文件失败" + file.getPath() + e.getMessage());
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    log.error("Close stream error." + e.getMessage());
                    throw new Exception("Close stream error." + e.getMessage());
                }
            }
            disconnect();
        }

        log.info(">>>>>>>>FtpUtil-->downloadFile--ftp下载文件结束>>>>>>>>>>>>>");
        return file;
    }

    /**
     * 上传单个文件
     *
     * @param in       要上传的文件流
     * @param fileName FTP服务器文件名称 如：xxx.txt ||xxx.txt.zip
     * @throws Exception
     */
    public void uploadFile(InputStream in, String fileName)
            throws Exception {
        log.info(">>>>>>>>FtpUtil-->uploadFile--ftp上传文件开始>>>>>>>>>>>>>");
//        FileInputStream in = null;
        connect();
        try {
            sftp.cd(remoteFilePath);
        } catch (SftpException e) {
            try {
                sftp.mkdir(remoteFilePath);
                sftp.cd(remoteFilePath);
            } catch (SftpException e1) {
                log.error("ftp创建文件路径失败，路径为" + remoteFilePath);
                throw new Exception("ftp创建文件路径失败，路径为" + remoteFilePath);
            }

        }
//        File file = new File(uploadFilePath);
        try {
//            in = new FileInputStream(file);
            sftp.put(in, fileName);
        } catch (SftpException e) {
            log.error("sftp异常-->" + e.getMessage());
            throw new Exception("sftp异常-->" + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("Close stream error." + e.getMessage());
                    throw new Exception("Close stream error." + e.getMessage());
                }
            }
            disconnect();
        }
        log.info(">>>>>>>>FtpUtil-->uploadFile--ftp上传文件结束>>>>>>>>>>>>>");
    }

//    private synchronized static File certTempEmptyile() {
//        String dirPath = SystemConfig.getProperty("down_settle_file.temp_path");
//        FileUtil.mkDir(dirPath);
//        String newFileName = System.currentTimeMillis() + ".txt";
//        String filePath = dirPath + File.separator + newFileName;
//        File file = new File(filePath);
//        return file;
//    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                sftp = null;
                log.info("sftp is closed already");
            }
        }
        if (sshSession != null) {
            if (sshSession.isConnected()) {
                sshSession.disconnect();
                sshSession = null;
                log.info("sshSession is closed already");
            }
        }
    }


}
