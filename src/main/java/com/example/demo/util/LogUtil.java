package com.example.demo.util;

import com.example.demo.db.DBContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogUtil {
    private static final Logger logger = LoggerFactory.getLogger("s3");

    public static void info(String message) {
        if (DBContextHolder.getDB() != null && !DBContextHolder.getDB().isEmpty()) {
            logger.info("custId="+DBContextHolder.getDB()+" message="+ message);
        }
    }

    public static void error(String message) {
        if (DBContextHolder.getDB() != null && !DBContextHolder.getDB().isEmpty()) {
            logger.error("custId="+DBContextHolder.getDB()+" message="+ message);
        }
    }
}
