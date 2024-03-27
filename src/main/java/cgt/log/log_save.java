/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.log;

import cgt.resource.ConfigInfoCache;

/**
 *
 * @author Ho Sy Minh
 */
public class log_save {

    ConfigInfoCache config = new ConfigInfoCache();
    private final String logdir = config.ConfigInfoCache("LOG_DIR");

    public void info(String prefix, String logcontent) {
        try {
            String dir = logdir + log_file.NowDate("yyyy") + "/" + prefix + "/";
            log_file.log(dir, prefix + "_info", logcontent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void debug(String prefix, String logcontent) {
        try {
            String dir = logdir + log_file.NowDate("yyyy") + "/" + prefix + "/";
            log_file.log(dir, prefix + "_debug", logcontent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void error(String prefix, String logcontent) {
        try {
            String dir = logdir + log_file.NowDate("yyyy") + "/" + prefix + "/";
            log_file.log(dir, prefix + "_error", logcontent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
