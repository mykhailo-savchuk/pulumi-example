/*
 * Copyright (c) 2021 Western Digital Corporation or its affiliates.
 *
 * This code is CONFIDENTIAL and a TRADE SECRET of Western Digital
 * Corporation or its affiliates ("WD").  This code is protected
 * under copyright laws as an unpublished work of WD.  Notice is
 * for informational purposes only and does not imply publication.
 *
 * The receipt or possession of this code does not convey any rights to
 * reproduce or disclose its contents, or to manufacture, use, or sell
 * anything that it may describe, in whole or in part, without the
 * specific written consent of WD.  Any reproduction or distribution
 * of this code without the express written consent of WD is strictly
 * prohibited, is a violation of the copyright laws, and may subject you
 * to criminal prosecution.
 */
package com.msavchuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Device {

    public static void main(String[] args) {
        SpringApplication.run(Device.class, args);
    }

}
