/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.swing.laf.flatlaf;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.modules.ModuleInstall;
import org.openide.util.*;

@NbBundle.Messages({
    "LBL_FLATLAF_LIGHT=FlatLaf Light",
    "LBL_FLATLAF_DARK=FlatLaf Dark"
})
public class Installer extends ModuleInstall {

    @Override
    public void validate() throws IllegalStateException {
        UIManager.installLookAndFeel(new UIManager.LookAndFeelInfo(Bundle.LBL_FLATLAF_LIGHT(), FlatLightLaf.class.getName()));
        UIManager.installLookAndFeel(new UIManager.LookAndFeelInfo(Bundle.LBL_FLATLAF_DARK(), FlatDarkLaf.class.getName()));

        // tell FlatLaf that it should look for .properties files in the given package
        FlatLaf.registerCustomDefaultsSource("org.netbeans.swing.laf.flatlaf", getClass().getClassLoader());

        // tell FlatLaf to look for possible user .properties files in LookAndFeel folder of config file system
        FileObject customFolder = FileUtil.getConfigFile("LookAndFeel");
        if (customFolder != null && customFolder.isFolder()) {
            FlatLaf.registerCustomDefaultsSource(customFolder.toURL());
        }

    }

}
