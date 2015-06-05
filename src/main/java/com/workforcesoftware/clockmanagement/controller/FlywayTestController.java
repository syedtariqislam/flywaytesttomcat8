package com.workforcesoftware.clockmanagement.controller;
//------------------------------------------------------------------
// Copyright (c) 1999, 2015
// WorkForce Software, Inc.
// All rights reserved.
//
// Web-site: http://www.workforcesoftware.com
// E-mail:   support@workforcesoftware.com
// Phone:    (877) 493-6723
//
// This program is protected by copyright laws and is considered
// a trade secret of WorkForce Software.  Access to this program
// and source code is granted only to licensed customers.  Under
// no circumstances may this software or source code be distributed
// without the prior written consent of WorkForce Software.
// -----------------------------------------------------------------

import com.workforcesoftware.clockmanagement.sql.WrapperDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
public class FlywayTestController {
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
      public @ResponseBody String logon() throws SQLException {
        ds.getConnection();
        return "Successfully Executed";
      }

    @Autowired
    WrapperDataSource ds;
}