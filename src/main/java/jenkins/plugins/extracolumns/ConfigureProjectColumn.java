/*
 * The MIT License
 *
 * Copyright (c) 2011, Frederic Gurr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package jenkins.plugins.extracolumns;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.Items;
import hudson.model.Hudson;
import hudson.util.VersionNumber;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;

public class ConfigureProjectColumn extends ListViewColumn {

    
    @DataBoundConstructor
    public ConfigureProjectColumn() {
    }

    public boolean isVersion1430(){
        return Hudson.getVersion().equals(new VersionNumber("1.430")) || Hudson.getVersion().isNewerThan(new VersionNumber("1.430")) ;
    }

    
    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {

        public DescriptorImpl() {
            Items.XSTREAM2.addCompatibilityAlias("hudson.views.ConfigureProjectColumn", jenkins.plugins.extracolumns.ConfigureProjectColumn.class);
        }

        @Override
        public String getDisplayName() {
            return Messages.ConfigureProjectColumn_DisplayName();
        }

        public boolean shownByDefault() {
            return false;
        }
        
        public boolean isVersion1427(){
            return true;
        }
    }

}
